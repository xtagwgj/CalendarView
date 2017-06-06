package com.xtagwgj.calendar.model;

import android.support.annotation.IntDef;

import com.xtagwgj.calendar.theme.DefaultTheme;
import com.xtagwgj.calendar.theme.XTheme;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 配置
 * Created by xtagwgj on 2017/5/29.
 */

public class XCalendarConfig {

    //翻页的方式
    public static final int SWIPE_MODE_NONE = 0;
    public static final int SWIPE_MODE_HOR = 1;
    public static final int SWIPE_MODE_VER = 2;

    //只选择一个日期
    public static final int DATE_CHOOSE_TYPE_SINGLE = 0;
    //多个日期，点一次选一次
    public static final int DATE_CHOOSE_TYPE_MULTI = 1;
    //时间段选择
    public static final int DATE_CHOOSE_TYPE_RANGE = 2;

    //日历是否显示阴历 : true-显示,false-不显示 (默认显示)
    private boolean xCalendar_showLunar = true;
    //日历是否显示星期一栏 : true-显示,false-不显示 (默认显示)
    private boolean xCalendar_showWeek = true;
    //是否显示标题
    private boolean xCalendar_showTitle = true;
    //是否显示其他月份的信息
    private boolean xCalendar_showOtherMonthInfo = true;
    //是否显示今天
    private boolean xCalendar_showCurrDay = true;
    //是否可以选择今日之前的日期
    private boolean xCalendar_canSelectDayBeforeNow = false;
    //区间选择时，是否显示开始、结束的文字
    private boolean xCalendar_showRangeText = true;
    //内容是否可点击
    private boolean xCalendar_calendarClickable = true;
    //滑动的方式
    private int xCalendar_swipeMode = SWIPE_MODE_HOR;
    //日期的选择方式
    private int xCalendar_chooseType = DATE_CHOOSE_TYPE_SINGLE;

    private XTheme theme = new DefaultTheme();


    private XCalendarConfig() {
    }

    @IntDef({SWIPE_MODE_NONE, SWIPE_MODE_HOR, SWIPE_MODE_VER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SwipeType {

    }

    @IntDef({DATE_CHOOSE_TYPE_SINGLE, DATE_CHOOSE_TYPE_MULTI, DATE_CHOOSE_TYPE_RANGE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ChooseType {

    }

    public boolean isMnCalendar_showLunar() {
        return xCalendar_showLunar;
    }

    public boolean isMnCalendar_showWeek() {
        return xCalendar_showWeek;
    }

    public boolean isMnCalendar_showTitle() {
        return xCalendar_showTitle;
    }

    public boolean isMnCalendar_showOtherMonthInfo() {
        return xCalendar_showOtherMonthInfo;
    }

    public boolean isMnCalendar_showCurrDay() {
        return xCalendar_showCurrDay;
    }

    public boolean isMnCalendar_canSelectDayBeforeNow() {
        return xCalendar_canSelectDayBeforeNow;
    }

    public boolean isMnCalendar_showRangeText() {
        return xCalendar_showRangeText;
    }

    public boolean isMnCalendar_calendarClickable() {
        return xCalendar_calendarClickable;
    }

    public int getMnCalendar_swipeMode() {
        return xCalendar_swipeMode;
    }

    public int getMnCalendar_chooseType() {
        return xCalendar_chooseType;
    }

    public void setXCalendar_showLunar(boolean xCalendar_showLunar) {
        this.xCalendar_showLunar = xCalendar_showLunar;
    }

    public void setXCalendar_showWeek(boolean xCalendar_showWeek) {
        this.xCalendar_showWeek = xCalendar_showWeek;
    }

    public void setXCalendar_showTitle(boolean xCalendar_showTitle) {
        this.xCalendar_showTitle = xCalendar_showTitle;
    }

    public void setXCalendar_showOtherMonthInfo(boolean xCalendar_showOtherMonthInfo) {
        this.xCalendar_showOtherMonthInfo = xCalendar_showOtherMonthInfo;
    }

    public void setXCalendar_showCurrDay(boolean xCalendar_showCurrDay) {
        this.xCalendar_showCurrDay = xCalendar_showCurrDay;
    }

    public void setXCalendar_canSelectDayBeforeNow(boolean xCalendar_canSelectDayBeforeNow) {
        this.xCalendar_canSelectDayBeforeNow = xCalendar_canSelectDayBeforeNow;
    }

    public void setXCalendar_showRangeText(boolean xCalendar_showRangeText) {
        this.xCalendar_showRangeText = xCalendar_showRangeText;
    }

    public void setXCalendar_calendarClickable(boolean xCalendar_calendarClickable) {
        this.xCalendar_calendarClickable = xCalendar_calendarClickable;
    }

    public void setXCalendar_swipeMode(int xCalendar_swipeMode) {
        this.xCalendar_swipeMode = xCalendar_swipeMode;
    }

    public void setXCalendar_chooseType(int xCalendar_chooseType) {
        this.xCalendar_chooseType = xCalendar_chooseType;
    }

    public XTheme getTheme() {
        return theme;
    }

    public void setTheme(XTheme theme) {
        if (theme == null)
            this.theme = new DefaultTheme();
        else
            this.theme = theme;
    }

    public static class Builder {
        private XCalendarConfig xCalendarConfig = null;

        public Builder() {
            this.xCalendarConfig = new XCalendarConfig();
        }

        public Builder setXCalendar_showOtherMonthInfo(boolean xCalendar_showOtherMonthInfo) {
            this.xCalendarConfig.setXCalendar_showOtherMonthInfo(xCalendar_showOtherMonthInfo);
            return this;
        }

        public Builder setXCalendar_swipeMode(@SwipeType int swipeMode) {
            this.xCalendarConfig.setXCalendar_swipeMode(swipeMode);
            return this;
        }

        public Builder setXCalendar_chooseType(@ChooseType int xCalendar_chooseType) {
            this.xCalendarConfig.setXCalendar_chooseType(xCalendar_chooseType);
            return this;
        }

        public Builder setXCalendar_showTitle(boolean xCalendar_showTitle) {
            this.xCalendarConfig.setXCalendar_showTitle(xCalendar_showTitle);
            return this;
        }

        public Builder setXCalendar_showLunar(boolean xCalendar_showLunar) {
            this.xCalendarConfig.setXCalendar_showLunar(xCalendar_showLunar);
            return this;
        }


        public Builder setXCalendar_showWeek(boolean xCalendar_showWeek) {
            this.xCalendarConfig.setXCalendar_showWeek(xCalendar_showWeek);
            return this;
        }


        public Builder setXCalendar_showCurrDay(boolean xCalendar_showCurrDay) {
            xCalendarConfig.setXCalendar_showCurrDay(xCalendar_showCurrDay);
            return this;
        }

        public Builder setXCalendar_canSelectDayBeforeNow(boolean xCalendar_canSelectDayBeforeNow) {
            xCalendarConfig.setXCalendar_canSelectDayBeforeNow(xCalendar_canSelectDayBeforeNow);
            return this;
        }

        public Builder setXCalendar_showRangeText(boolean xCalendar_showRangeText) {
            xCalendarConfig.setXCalendar_showRangeText(xCalendar_showRangeText);
            return this;
        }

        public Builder setXCalendar_calendarClickable(boolean xCalendar_calendarClickable) {
            xCalendarConfig.setXCalendar_calendarClickable(xCalendar_calendarClickable);
            return this;
        }

        public Builder setTheme(XTheme xTheme) {
            xCalendarConfig.setTheme(xTheme);
            return this;
        }


        public XCalendarConfig build() {
            return xCalendarConfig;
        }

    }


}
