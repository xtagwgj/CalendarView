package com.xtagwgj.calendar.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 日期选择的类型
 * Created by xtagwgj on 2017/6/8.
 */

@IntDef({
        ChooseType.DATE_CHOOSE_TYPE_SINGLE,
        ChooseType.DATE_CHOOSE_TYPE_MULTI,
        ChooseType.DATE_CHOOSE_TYPE_RANGE}
)
@Retention(RetentionPolicy.SOURCE)
public @interface ChooseType {
    //只选择一个日期
    int DATE_CHOOSE_TYPE_SINGLE = 0;
    //多个日期，点一次选一次
    int DATE_CHOOSE_TYPE_MULTI = 1;
    //时间段选择
    int DATE_CHOOSE_TYPE_RANGE = 2;
}
