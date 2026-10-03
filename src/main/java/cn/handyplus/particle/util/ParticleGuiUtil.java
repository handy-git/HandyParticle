package cn.handyplus.particle.util;

import cn.handyplus.lib.core.MapUtil;
import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.lib.inventory.HandyInventory;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.lib.util.ItemStackUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.particle.api.CustomTrailParticle;
import cn.handyplus.particle.api.HandyParticleApi;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.core.projectile.ProjectileParticleManager;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
import cn.handyplus.particle.entity.PlayerParticleEntity;
import cn.handyplus.particle.service.PlayerParticleService;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.Color;
import java.util.List;
import java.util.Map;

/**
 * 粒子菜单工具
 *
 * @author handy
 */
public final class ParticleGuiUtil {

    private ParticleGuiUtil() {
    }

    public static final String DATA_PREFIX = "data:";

    /**
     * 获取已选粒子
     *
     * @param player   玩家
     * @param category 粒子类别
     * @return 粒子记录
     */
    @Nullable
    public static PlayerParticleEntity findCurrent(@NotNull Player player, @Nullable ParticleCategoryTypeEnum category) {
        if (category != null) {
            return PlayerParticleService.findByUuidAndCategory(player.getUniqueId(), category.name()).orElse(null);
        }
        List<PlayerParticleEntity> records = PlayerParticleService.findByUuid(player.getUniqueId());
        return records.isEmpty() ? null : records.get(0);
    }

    /**
     * 设置粒子物品
     *
     * @param config         页面配置
     * @param handyInventory gui
     * @param slot           槽位
     * @param name           显示名称
     * @param desc           效果描述
     * @param selected       是否选中
     * @param dataKey        点击数据
     */
    public static void setParticleItem(@NotNull FileConfiguration config, @NotNull HandyInventory handyInventory, int slot, @NotNull String name, @NotNull String desc,
                                       boolean selected, @Nullable String dataKey) {
        String section = selected ? "selected" : "data";
        boolean enabled = config.getBoolean(section + ".enable", true);
        if (!enabled) {
            return;
        }
        ConfigurationSection itemConfig = config.getConfigurationSection("list.items." + dataKey);
        if (itemConfig != null && !itemConfig.getBoolean("enable", true)) {
            return;
        }
        String material = config.getString(section + ".material");
        String itemName = config.getString(section + ".name");
        List<String> lore = config.getStringList(section + ".lore");
        boolean isEnchant = config.getBoolean(section + ".isEnchant");
        int customModelData = config.getInt(section + ".custom-model-data");
        boolean hideFlag = config.getBoolean(section + ".hideFlag", true);
        int amount = config.getInt(section + ".amount", 1);
        String sound = config.getString(section + ".sound");
        if (itemConfig != null) {
            material = itemConfig.getString("material", material);
            itemName = itemConfig.getString("name", itemName);
            if (itemConfig.contains("lore")) {
                lore = itemConfig.getStringList("lore");
            }
            isEnchant = itemConfig.getBoolean("isEnchant", isEnchant);
            customModelData = itemConfig.getInt("custom-model-data", customModelData);
            hideFlag = itemConfig.getBoolean("hideFlag", hideFlag);
            amount = itemConfig.getInt("amount", amount);
            sound = itemConfig.getString("sound", sound);
        }
        Map<String, String> replaceMap = MapUtil.of("name", name, "desc", desc);
        itemName = StrUtil.replace(itemName, "name", name);
        itemName = StrUtil.replace(itemName, "desc", desc);
        lore = ItemStackUtil.loreReplaceMap(lore, replaceMap);
        ItemStack itemStack = ItemStackUtil.getItemStack(material, itemName, lore, isEnchant, customModelData, hideFlag, null);
        itemStack.setAmount(amount);
        handyInventory.getInventory().setItem(slot, itemStack);
        if (StrUtil.isNotEmpty(sound)) {
            handyInventory.getSoundMap().put(slot, sound);
        }
        if (StrUtil.isNotEmpty(dataKey)) {
            handyInventory.getStrMap().put(slot, DATA_PREFIX + dataKey);
        }
    }

    /**
     * 设置粒子效果
     *
     * @param player 玩家
     * @param type   效果类型
     * @param mode   运动模式
     * @param color  粒子颜色
     */
    public static void apply(@NotNull Player player, @NotNull ParticleTypeEnum type, @Nullable TrailModeTypeEnum mode, @Nullable Color color) {
        if (type.getCategory() == ParticleCategoryTypeEnum.WING) {
            if (type.getWingModel() == null || type.getWingColor() == null) {
                return;
            }
            // 翅膀按外形和图案分别保存
            HandyParticleApi.enable(player, type);
            PlayerParticleService.saveWing(player, type.getWingModel(), type.getWingColor());
        } else if (type.getCategory() == ParticleCategoryTypeEnum.TRAIL) {
            HandyParticleApi.enable(player, new CustomTrailParticle(type, mode == null ? TrailModeTypeEnum.DEFAULT : mode, color));
            PlayerParticleService.save(player, type, mode, color);
        } else {
            // 鞘翅、方块、物品雨、纸屑均无附加参数
            if (type.getCategory() == ParticleCategoryTypeEnum.PROJECTILE) {
                // 换效果时保留玩家已勾选的抛射物范围
                ProjectileParticleManager.ProjectileRecord record = ProjectileParticleManager.getRecord(player);
                String projectiles = record == null ? null : record.getProjectiles();
                HandyParticleApi.enable(player, type);
                PlayerParticleService.save(player, type, null, null, projectiles);
            } else {
                HandyParticleApi.enable(player, type);
                PlayerParticleService.save(player, type, null, null);
            }
        }
        MessageUtil.sendMessage(player, BaseUtil.getLangMsg("guiSetSuccess", MapUtil.of("type", type.getDesc())));
    }

    /**
     * 关闭粒子效果
     *
     * @param player   玩家
     * @param category 粒子类别
     */
    public static void clear(@NotNull Player player, @Nullable ParticleCategoryTypeEnum category) {
        // 抛射物效果不在任务表中，需要单独清理内存记录，否则关闭后箭仍会带粒子
        if (category == null || category == ParticleCategoryTypeEnum.PROJECTILE) {
            ProjectileParticleManager.disable(player.getUniqueId());
        }
        if (category == null) {
            HandyParticleApi.disable(player);
            PlayerParticleService.delete(player.getUniqueId());
            MessageUtil.sendMessage(player, BaseUtil.getLangMsg("guiClearAllSuccess"));
            return;
        }
        HandyParticleApi.disable(player, category);
        PlayerParticleService.delete(player.getUniqueId(), category);
        MessageUtil.sendMessage(player, BaseUtil.getLangMsg("guiClearCategorySuccess", MapUtil.of("category", category.getDesc())));
    }

}
