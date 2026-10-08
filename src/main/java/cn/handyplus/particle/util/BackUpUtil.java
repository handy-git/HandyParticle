package cn.handyplus.particle.util;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.core.DateUtil;
import cn.handyplus.lib.db.SqlBackupUtil;
import cn.handyplus.lib.internal.HandySchedulerUtil;

import java.util.Date;

/**
 * 备份
 *
 * @author handy
 */
public class BackUpUtil {

    /**
     * 定时任务
     */
    public static void init() {
        // 每分钟执行一次
        HandySchedulerUtil.runTaskTimerAsynchronously(() -> {
            // 是否开启备份
            boolean backUp = BaseConstants.CONFIG.getBoolean("backUp");
            if (!backUp) {
                return;
            }
            Date date = new Date();
            // 判断时间
            String time = DateUtil.format(date, DateUtil.HH_MM);
            String backUpTime = BaseConstants.CONFIG.getString("backUpTime", DateUtil.ZERO);
            if (!backUpTime.equals(time)) {
                return;
            }
            // 进行备份
            SqlBackupUtil.backup();
        }, 20 * 30, 20 * 60);
    }

}
