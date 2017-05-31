package com.xtagwgj.calendar.model;

import android.graphics.Color;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 配置
 * Created by xtagwgj on 2017/5/29.
 */

public class MNCalendarConfig {

    //翻页的方式
    public static final int SWIPE_MODE_HOR = 0;
    public static final int SWIPE_MODE_VER = 1;

    //只选择一个日期
    public static final int DATE_CHOOSE_TYPE_SINGLE = 0;
    //多个日期，点一次选一次
    public static final int DATE_CHOOSE_TYPE_MULTI = 1;
    //时间段选择
    public static final int DATE_CHOOSE_TYPE_RANGE = 2;

    //日历是否显示阴历 : true-显示,false-不显示 (默认显示)
    private boolean mnCalendar_showLunar = true;
    //日历是否显示星期一栏 : true-显示,false-不显示 (默认显示)
    private boolean mnCalendar_showWeek = true;
    //是否显示标题
    private boolean mnCalendar_showTitle = true;
    //是否显示其他月份的信息
    private boolean mnCalendar_showOtherMonthInfo = true;
    //是否显示今天
    private boolean mnCalendar_showCurrDay = true;
    //是否可以选择今日之前的日期
    private boolean mnCalendar_canSelectDayBeforeNow = false;
    //日历星期的颜色值
    private int mnCalendar_colorWeek = Color.parseColor("#5E5E5E");
    //日历Item的阳历的颜色值
    private int mnCalendar_colorSolar = Color.parseColor("#282828");
    //日历Item的阴历的颜色值
    private int mnCalendar_colorLunar = Color.parseColor("#979797");
    //本月今日之前的日期颜色
    private int mnCalendar_colorBeforeToday = Color.parseColor("#979797");
    //日历今天圆形背景
    private int mnCalendar_colorTodayBg = Color.parseColor("#282828");
    //日历不是当前月份的阳历的颜色
    private int mnCalendar_colorOtherMonth = Color.parseColor("#979797");
    //日历今天圆形背景上的文字的颜色
    private int mnCalendar_colorTodayText = Color.parseColor("#FFFFFF");
    //标题字体的颜色
    private int mnCalendar_colorTitle = Color.parseColor("#282828");
    //开始结束的背景颜色
    private int mnCalendar_colorStartAndEndBg = Color.parseColor("#df0e0e0e");
    //区间中间的背景颜色
    private int mnCalendar_colorRangeBg = Color.parseColor("#df0e0e0e");
    //选择区间文字的颜色
    private int mnCalendar_colorRangeText = Color.parseColor("#FFFFFF");
    //滑动的方式
    private int mnCalendar_swipeMode = SWIPE_MODE_HOR;
    //日期的选择方式
    private int mnCalendar_chooseType = DATE_CHOOSE_TYPE_SINGLE;
    //标题的背景颜色
    private int mnCalendar_colorBgTitle = Color.parseColor("#FFFFFF");
    //星期栏目的背景颜色
    private int mnCalendar_colorBgWeekend = Color.parseColor("#FFFFFF");
    //具体日期的背景颜色
    private int mnCalendar_colorBgCalendar = Color.parseColor("#FFFFFF");
    //分割线的颜色值
    private int mnCalendar_colorSplit = Color.parseColor("#CCCCCC");


    private MNCalendarConfig() {
    }

    @IntDef({SWIPE_MODE_HOR, SWIPE_MODE_VER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SwipeType {

    }

    @IntDef({DATE_CHOOSE_TYPE_SINGLE, DATE_CHOOSE_TYPE_MULTI, DATE_CHOOSE_TYPE_RANGE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ChooseType {

    }

    public boolean isMnCalendar_showCurrDay() {
        return mnCalendar_showCurrDay;
    }

    public void setMnCalendar_showCurrDay(boolean mnCalendar_showCurrDay) {
        this.mnCalendar_showCurrDay = mnCalendar_showCurrDay;
    }

    public boolean isMnCalendar_showLunar() {
        return mnCalendar_showLunar;
    }

    public void setMnCalendar_showLunar(boolean mnCalendar_showLunar) {
        this.mnCalendar_showLunar = mnCalendar_showLunar;
    }

    public boolean isMnCalendar_showWeek() {
        return mnCalendar_showWeek;
    }

    public void setMnCalendar_showWeek(boolean mnCalendar_showWeek) {
        this.mnCalendar_showWeek = mnCalendar_showWeek;
    }

    public boolean isMnCalendar_showTitle() {
        return mnCalendar_showTitle;
    }

    public void setMnCalendar_showTitle(boolean mnCalendar_showTitle) {
        this.mnCalendar_showTitle = mnCalendar_showTitle;
    }

    public boolean isMnCalendar_showOtherMonthInfo() {
        return mnCalendar_showOtherMonthInfo;
    }

    public void setMnCalendar_showOtherMonthInfo(boolean mnCalendar_showOtherMonthInfo) {
        this.mnCalendar_showOtherMonthInfo = mnCalendar_showOtherMonthInfo;
    }

    public int getMnCalendar_colorWeek() {
        return mnCalendar_colorWeek;
    }

    public void setMnCalendar_colorWeek(int mnCalendar_colorWeek) {
        this.mnCalendar_colorWeek = mnCalendar_colorWeek;
    }

    public int getMnCalendar_colorSolar() {
        return mnCalendar_colorSolar;
    }

    public void setMnCalendar_colorSolar(int mnCalendar_colorSolar) {
        this.mnCalendar_colorSolar = mnCalendar_colorSolar;
    }

    public int getMnCalendar_colorLunar() {
        return mnCalendar_colorLunar;
    }

    public void setMnCalendar_colorLunar(int mnCalendar_colorLunar) {
        this.mnCalendar_colorLunar = mnCalendar_colorLunar;
    }

    public int getMnCalendar_colorTodayBg() {
        return mnCalendar_colorTodayBg;
    }

    public void setMnCalendar_colorTodayBg(int mnCalendar_colorTodayBg) {
        this.mnCalendar_colorTodayBg = mnCalendar_colorTodayBg;
    }

    public int getMnCalendar_colorOtherMonth() {
        return mnCalendar_colorOtherMonth;
    }

    public void setMnCalendar_colorOtherMonth(int mnCalendar_colorOtherMonth) {
        this.mnCalendar_colorOtherMonth = mnCalendar_colorOtherMonth;
    }

    public int getMnCalendar_colorTodayText() {
        return mnCalendar_colorTodayText;
    }

    public void setMnCalendar_colorTodayText(int mnCalendar_colorTodayText) {
        this.mnCalendar_colorTodayText = mnCalendar_colorTodayText;
    }

    public int getMnCalendar_colorTitle() {
        return mnCalendar_colorTitle;
    }

    public void setMnCalendar_colorTitle(int mnCalendar_colorTitle) {
        this.mnCalendar_colorTitle = mnCalendar_colorTitle;
    }

    public int getMnCalendar_colorRangeBg() {
        return mnCalendar_colorRangeBg;
    }

    public void setMnCalendar_colorRangeBg(int mnCalendar_colorRangeBg) {
        this.mnCalendar_colorRangeBg = mnCalendar_colorRangeBg;
    }

    public int getMnCalendar_colorRangeText() {
        return mnCalendar_colorRangeText;
    }

    public void setMnCalendar_colorRangeText(int mnCalendar_colorRangeText) {
        this.mnCalendar_colorRangeText = mnCalendar_colorRangeText;
    }

    public int getMnCalendar_swipeMode() {
        return mnCalendar_swipeMode;
    }

    public void setMnCalendar_swipeMode(int mnCalendar_swipeMode) {
        this.mnCalendar_swipeMode = mnCalendar_swipeMode;
    }

    public int getMnCalendar_chooseType() {
        return mnCalendar_chooseType;
    }

    public void setMnCalendar_chooseType(@ChooseType int mnCalendar_chooseType) {
        this.mnCalendar_chooseType = mnCalendar_chooseType;
    }

    public int getMnCalendar_colorStartAndEndBg() {
        return mnCalendar_colorStartAndEndBg;
    }

    public void setMnCalendar_colorStartAndEndBg(int mnCalendar_colorStartAndEndBg) {
        this.mnCalendar_colorStartAndEndBg = mnCalendar_colorStartAndEndBg;
    }

    public int getMnCalendar_colorBgTitle() {
        return mnCalendar_colorBgTitle;
    }

    public void setMnCalendar_colorBgTitle(int mnCalendar_colorBgTitle) {
        this.mnCalendar_colorBgTitle = mnCalendar_colorBgTitle;
    }

    public int getMnCalendar_colorBgWeekend() {
        return mnCalendar_colorBgWeekend;
    }

    public void setMnCalendar_colorBgWeekend(int mnCalendar_colorBgWeekend) {
        this.mnCalendar_colorBgWeekend = mnCalendar_colorBgWeekend;
    }

    public int getMnCalendar_colorBgCalendar() {
        return mnCalendar_colorBgCalendar;
    }

    public void setMnCalendar_colorBgCalendar(int mnCalendar_colorBgCalendar) {
        this.mnCalendar_colorBgCalendar = mnCalendar_colorBgCalendar;
    }


    public int getMnCalendar_colorBeforeToday() {
        return mnCalendar_colorBeforeToday;
    }

    public void setMnCalendar_colorBeforeToday(int mnCalendar_colorBeforeToday) {
        this.mnCalendar_colorBeforeToday = mnCalendar_colorBeforeToday;
    }

    public int getMnCalendar_colorSplit() {
        return mnCalendar_colorSplit;
    }

    public void setMnCalendar_colorSplit(int mnCalendar_colorSplit) {
        this.mnCalendar_colorSplit = mnCalendar_colorSplit;
    }

    public boolean isMnCalendar_canSelectDayBeforeNow() {
        return mnCalendar_canSelectDayBeforeNow;
    }

    public void setMnCalendar_canSelectDayBeforeNow(boolean mnCalendar_canSelectDayBeforeNow) {
        this.mnCalendar_canSelectDayBeforeNow = mnCalendar_canSelectDayBeforeNow;
    }

    @Override
    public String toString() {
        return "MNCalendarConfig{" +
                "mnCalendar_showLunar=" + mnCalendar_showLunar +
                ", mnCalendar_showWeek=" + mnCalendar_showWeek +
                ", mnCalendar_showTitle=" + mnCalendar_showTitle +
                ", mnCalendar_showOtherMonthInfo=" + mnCalendar_showOtherMonthInfo +
                ", mnCalendar_showCurrDay=" + mnCalendar_showCurrDay +
                ", mnCalendar_colorWeek=" + mnCalendar_colorWeek +
                ", mnCalendar_colorSolar=" + mnCalendar_colorSolar +
                ", mnCalendar_colorLunar=" + mnCalendar_colorLunar +
                ", mnCalendar_colorTodayBg=" + mnCalendar_colorTodayBg +
                ", mnCalendar_colorOtherMonth=" + mnCalendar_colorOtherMonth +
                ", mnCalendar_colorTodayText=" + mnCalendar_colorTodayText +
                ", mnCalendar_colorTitle=" + mnCalendar_colorTitle +
                ", mnCalendar_colorStartAndEndBg=" + mnCalendar_colorStartAndEndBg +
                ", mnCalendar_colorRangeBg=" + mnCalendar_colorRangeBg +
                ", mnCalendar_colorRangeText=" + mnCalendar_colorRangeText +
                ", mnCalendar_swipeMode=" + mnCalendar_swipeMode +
                ", mnCalendar_chooseType=" + mnCalendar_chooseType +
                ", mnCalendar_colorBgTitle=" + mnCalendar_colorBgTitle +
                ", mnCalendar_colorBgWeekend=" + mnCalendar_colorBgWeekend +
                ", mnCalendar_colorBgCalendar=" + mnCalendar_colorBgCalendar +
                '}';
    }

    public static class Builder {
        private MNCalendarConfig mnCalendarConfig = null;

        public Builder() {
            this.mnCalendarConfig = new MNCalendarConfig();
        }

        public Builder setMnCalendar_showOtherMonthInfo(boolean mnCalendar_showOtherMonthInfo) {
            this.mnCalendarConfig.setMnCalendar_showOtherMonthInfo(mnCalendar_showOtherMonthInfo);
            return this;
        }

        public Builder setMnCalendar_swipeMode(@SwipeType int swipeMode) {
            this.mnCalendarConfig.setMnCalendar_swipeMode(swipeMode);
            return this;
        }

        public Builder setMnCalendar_chooseType(@ChooseType int mnCalendar_chooseType) {
            this.mnCalendarConfig.setMnCalendar_chooseType(mnCalendar_chooseType);
            return this;
        }

        public Builder setMnCalendar_showTitle(boolean mnCalendar_showTitle) {
            this.mnCalendarConfig.setMnCalendar_showTitle(mnCalendar_showTitle);
            return this;
        }

        public Builder setMnCalendar_showLunar(boolean mnCalendar_showLunar) {
            this.mnCalendarConfig.setMnCalendar_showLunar(mnCalendar_showLunar);
            return this;
        }


        public Builder setMnCalendar_showWeek(boolean mnCalendar_showWeek) {
            this.mnCalendarConfig.setMnCalendar_showWeek(mnCalendar_showWeek);
            return this;
        }


        public Builder setMnCalendar_colorWeek(int mnCalendar_colorWeek) {
            this.mnCalendarConfig.setMnCalendar_colorWeek(mnCalendar_colorWeek);
            return this;
        }


        public Builder setMnCalendar_colorSolar(int mnCalendar_colorSolar) {
            this.mnCalendarConfig.setMnCalendar_colorSolar(mnCalendar_colorSolar);
            return this;
        }


        public Builder setMnCalendar_colorLunar(int mnCalendar_colorLunar) {
            this.mnCalendarConfig.setMnCalendar_colorLunar(mnCalendar_colorLunar);
            return this;
        }

        public Builder setMnCalendar_colorTodayBg(int mnCalendar_colorTodayBg) {
            this.mnCalendarConfig.setMnCalendar_colorTodayBg(mnCalendar_colorTodayBg);
            return this;
        }


        public Builder setMnCalendar_colorOtherMonth(int mnCalendar_colorOtherMonth) {
            this.mnCalendarConfig.setMnCalendar_colorOtherMonth(mnCalendar_colorOtherMonth);
            return this;
        }


        public Builder setMnCalendar_colorTodayText(int mnCalendar_colorTodayText) {
            this.mnCalendarConfig.setMnCalendar_colorTodayText(mnCalendar_colorTodayText);
            return this;
        }

        public Builder setMnCalendar_colorTitle(int mnCalendar_colorTitle) {
            this.mnCalendarConfig.setMnCalendar_colorTitle(mnCalendar_colorTitle);
            return this;
        }

        public Builder setMnCalendar_colorRangeBg(int mnCalendar_colorRangeBg) {
            this.mnCalendarConfig.setMnCalendar_colorRangeBg(mnCalendar_colorRangeBg);
            return this;
        }


        public Builder setMnCalendar_colorRangeText(int mnCalendar_colorRangeText) {
            this.mnCalendarConfig.setMnCalendar_colorRangeText(mnCalendar_colorRangeText);
            return this;
        }

        public Builder setMnCalendar_colorStartAndEndBg(int mnCalendar_colorStartAndEndBg) {
            mnCalendarConfig.setMnCalendar_colorStartAndEndBg(mnCalendar_colorStartAndEndBg);
            return this;
        }

        public Builder setMnCalendar_showCurrDay(boolean mnCalendar_showCurrDay) {
            mnCalendarConfig.setMnCalendar_showCurrDay(mnCalendar_showCurrDay);
            return this;
        }

        public Builder setMnCalendar_colorBgTitle(int mnCalendar_colorBgTitle) {
            mnCalendarConfig.setMnCalendar_colorBgTitle(mnCalendar_colorBgTitle);
            return this;
        }

        public Builder setMnCalendar_colorBgWeekend(int mnCalendar_colorBgWeekend) {
            mnCalendarConfig.setMnCalendar_colorBgWeekend(mnCalendar_colorBgWeekend);
            return this;
        }

        public Builder setMnCalendar_colorBgCalendar(int mnCalendar_colorBgCalendar) {
            mnCalendarConfig.setMnCalendar_colorBgCalendar(mnCalendar_colorBgCalendar);
            return this;
        }

        public Builder setMnCalendar_colorBeforeToday(int mnCalendar_colorBeforeToday) {
            mnCalendarConfig.setMnCalendar_colorBeforeToday(mnCalendar_colorBeforeToday);
            return this;
        }

        public Builder setMnCalendar_colorSplit(int mnCalendar_colorSplit) {
            mnCalendarConfig.setMnCalendar_colorSplit(mnCalendar_colorSplit);
            return this;
        }

        public Builder setMnCalendar_canSelectDayBeforeNow(boolean mnCalendar_canSelectDayBeforeNow) {
            mnCalendarConfig.setMnCalendar_canSelectDayBeforeNow(mnCalendar_canSelectDayBeforeNow);
            return this;
        }


        public MNCalendarConfig build() {
            return mnCalendarConfig;
        }

    }


}
