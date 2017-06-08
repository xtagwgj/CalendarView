package com.xtagwgj.calendar.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 刷新的方式
 * Created by xtagwgj on 2017/6/8.
 */

@IntDef({
       SwipeType.SWIPE_MODE_NONE,
        SwipeType.SWIPE_MODE_HOR,
        SwipeType.SWIPE_MODE_VER}
)
@Retention(RetentionPolicy.SOURCE)
public @interface SwipeType {
    int SWIPE_MODE_NONE = 0;
    int SWIPE_MODE_HOR = 1;
    int SWIPE_MODE_VER = 2;
}
