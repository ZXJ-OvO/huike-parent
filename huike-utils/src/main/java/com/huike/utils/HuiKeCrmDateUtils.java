package com.huike.utils;

import com.huike.domain.clues.TbAssignRecord;
import com.huike.domain.clues.TbRulePool;

import java.util.Calendar;
import java.util.Date;

/**
 * 获取结束时间的工具类
 *
 * @author 86150
 */
public class HuiKeCrmDateUtils {

    /**
     * 根据规则获取结束时间
     *
     * @param tbAssignRecord
     * @return
     */
    public static Date getEndDateByRule(TbAssignRecord tbAssignRecord, TbRulePool rulePool) {
        if (rulePool == null) {
            return null;
        }
        //回收规则
        Date recoveryDate = getDate(rulePool.getLimitTime().intValue(),
                rulePool.getLimitTimeType(), tbAssignRecord.getCreateTime());
        return recoveryDate;
    }

    public static Date getDate(int time, String type, Date now) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        if (TbRulePool.LimitTimeType.HOUR.getValue().equals(type)) {
            cal.add(Calendar.HOUR, time);
        } else if (TbRulePool.LimitTimeType.DAY.getValue().equals(type)) {
            cal.add(Calendar.DATE, time);
        } else if (TbRulePool.LimitTimeType.WEEK.getValue().equals(type)) {
            cal.add(Calendar.DAY_OF_WEEK, time);
        }
        return cal.getTime();
    }
}
