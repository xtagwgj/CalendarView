package com.xtagwgj.calendar.model;

import android.graphics.Color;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by maning on 2017/5/10.
 * 垂直方向的配置信息
 */

public class MNCalendarVerticalConfig {

    //当天日期的显示方式
    public static final int SHOW_TODAY_TEXT = 1;
    public static final int SHOW_TODAY_DRAWABLE = 2;

    //选择日期的的方式
    public static final int SINGLE = 0;
    public static final int MULTI = 1;

    //是否显示阴历
    private boolean mnCalendar_showLunar = true;
    //是否显示星期栏
    private boolean mnCalendar_showWeek = true;
    //今天的显示方式
    private int mnCalendar_showTodayType = SHOW_TODAY_TEXT;
    //日期选择的方式
    private int mnCalendar_chooseType = MULTI;
    //显示多少月(默认1个月)
    private int mnCalendar_countMonth = 1;


    //每个月标题的样式
    private String mnCalendar_titleFormat = "yyyy年MM月";
    //每个月标题的颜色
    private int mnCalendar_colorTitle = Color.parseColor("#282828");
    //每个月标题的背景颜色
    private int mnCalendar_colorTitleBg = Color.parseColor("#FFFFFF");

    //星期几的背景颜色
    private int mnCalendar_colorWeekendTitleBg = Color.parseColor("#FFFFFF");

    //日历的背景颜色
    private int mnCalendar_colorBg = Color.parseColor("#FFFFFF");
    //星期栏的颜色
    private int mnCalendar_colorWeek = Color.parseColor("#5E5E5E");
    //阳历的颜色
    private int mnCalendar_colorSolar = Color.parseColor("#282828");
    //阴历的颜色
    private int mnCalendar_colorLunar = Color.parseColor("#979797");
    //今天之前的日期的颜色
    private int mnCalendar_colorBeforeToday = Color.parseColor("#979797");
    //开始结束的背景颜色
    private int mnCalendar_colorStartAndEndBg = Color.parseColor("#df0e0e0e");
    //区间中间的背景颜色
    private int mnCalendar_colorRangeBg = Color.parseColor("#df0e0e0e");
    //选择区间文字的颜色
    private int mnCalendar_colorRangeText = Color.parseColor("#FFFFFF");


    @IntDef({SHOW_TODAY_TEXT, SHOW_TODAY_DRAWABLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ShowTodayType {

    }

    @IntDef({SINGLE, MULTI})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ChooseType {

    }


    private MNCalendarVerticalConfig() {
    }

    public int getMnCalendar_chooseType() {
        return mnCalendar_chooseType;
    }

    public void setMnCalendar_chooseType(int mnCalendar_chooseType) {
        this.mnCalendar_chooseType = mnCalendar_chooseType;
    }

    public int getMnCalendar_colorTitleBg() {
        return mnCalendar_colorTitleBg;
    }

    public void setMnCalendar_colorTitleBg(int mnCalendar_colorTitleBg) {
        this.mnCalendar_colorTitleBg = mnCalendar_colorTitleBg;
    }

    public int getMnCalendar_colorBg() {
        return mnCalendar_colorBg;
    }

    public int getMnCalendar_colorWeekendTitleBg() {
        return mnCalendar_colorWeekendTitleBg;
    }

    public void setMnCalendar_colorWeekendTitleBg(int mnCalendar_colorWeekendTitleBg) {
        this.mnCalendar_colorWeekendTitleBg = mnCalendar_colorWeekendTitleBg;
    }

    public void setMnCalendar_colorBg(int mnCalendar_colorBg) {
        this.mnCalendar_colorBg = mnCalendar_colorBg;
    }

    public int getMnCalendar_showTodayType() {
        return mnCalendar_showTodayType;
    }

    public void setMnCalendar_showTodayType(int mnCalendar_showTodayType) {
        this.mnCalendar_showTodayType = mnCalendar_showTodayType;
    }

    public boolean isMnCalendar_showLunar() {
        return mnCalendar_showLunar;
    }

    private void setMnCalendar_showLunar(boolean mnCalendar_showLunar) {
        this.mnCalendar_showLunar = mnCalendar_showLunar;
    }

    public boolean isMnCalendar_showWeek() {
        return mnCalendar_showWeek;
    }

    private void setMnCalendar_showWeek(boolean mnCalendar_showWeek) {
        this.mnCalendar_showWeek = mnCalendar_showWeek;
    }

    public String getMnCalendar_titleFormat() {
        return mnCalendar_titleFormat;
    }

    private void setMnCalendar_titleFormat(String mnCalendar_titleFormat) {
        this.mnCalendar_titleFormat = mnCalendar_titleFormat;
    }

    public int getMnCalendar_colorWeek() {
        return mnCalendar_colorWeek;
    }

    private void setMnCalendar_colorWeek(int mnCalendar_colorWeek) {
        this.mnCalendar_colorWeek = mnCalendar_colorWeek;
    }

    public int getMnCalendar_colorSolar() {
        return mnCalendar_colorSolar;
    }

    private void setMnCalendar_colorSolar(int mnCalendar_colorSolar) {
        this.mnCalendar_colorSolar = mnCalendar_colorSolar;
    }

    public int getMnCalendar_colorLunar() {
        return mnCalendar_colorLunar;
    }

    private void setMnCalendar_colorLunar(int mnCalendar_colorLunar) {
        this.mnCalendar_colorLunar = mnCalendar_colorLunar;
    }

    public int getMnCalendar_colorBeforeToday() {
        return mnCalendar_colorBeforeToday;
    }

    private void setMnCalendar_colorBeforeToday(int mnCalendar_colorBeforeToday) {
        this.mnCalendar_colorBeforeToday = mnCalendar_colorBeforeToday;
    }

    public int getMnCalendar_colorStartAndEndBg() {
        return mnCalendar_colorStartAndEndBg;
    }

    private void setMnCalendar_colorStartAndEndBg(int mnCalendar_colorStartAndEndBg) {
        this.mnCalendar_colorStartAndEndBg = mnCalendar_colorStartAndEndBg;
    }

    public int getMnCalendar_colorRangeBg() {
        return mnCalendar_colorRangeBg;
    }

    private void setMnCalendar_colorRangeBg(int mnCalendar_colorRangeBg) {
        this.mnCalendar_colorRangeBg = mnCalendar_colorRangeBg;
    }

    public int getMnCalendar_colorRangeText() {
        return mnCalendar_colorRangeText;
    }

    private void setMnCalendar_colorRangeText(int mnCalendar_colorRangeText) {
        this.mnCalendar_colorRangeText = mnCalendar_colorRangeText;
    }

    public int getMnCalendar_colorTitle() {
        return mnCalendar_colorTitle;
    }

    private void setMnCalendar_colorTitle(int mnCalendar_colorTitle) {
        this.mnCalendar_colorTitle = mnCalendar_colorTitle;
    }

    public int getMnCalendar_countMonth() {
        return mnCalendar_countMonth;
    }

    private void setMnCalendar_countMonth(int mnCalendar_countMonth) {
        this.mnCalendar_countMonth = mnCalendar_countMonth;
    }

    @Override
    public String toString() {
        return "MNCalendarVerticalConfig{" +
                "mnCalendar_showLunar=" + mnCalendar_showLunar +
                ", mnCalendar_showWeek=" + mnCalendar_showWeek +
                ", mnCalendar_colorTitleBg=" + mnCalendar_colorTitleBg +
                ", mnCalendar_colorBg=" + mnCalendar_colorBg +
                ", mnCalendar_chooseType=" + mnCalendar_chooseType +
                ", mnCalendar_colorWeekendTitleBg=" + mnCalendar_colorWeekendTitleBg +
                ", mnCalendar_titleFormat='" + mnCalendar_titleFormat + '\'' +
                ", mnCalendar_colorTitle=" + mnCalendar_colorTitle +
                ", mnCalendar_colorWeek=" + mnCalendar_colorWeek +
                ", mnCalendar_colorSolar=" + mnCalendar_colorSolar +
                ", mnCalendar_colorLunar=" + mnCalendar_colorLunar +
                ", mnCalendar_colorBeforeToday=" + mnCalendar_colorBeforeToday +
                ", mnCalendar_colorStartAndEndBg=" + mnCalendar_colorStartAndEndBg +
                ", mnCalendar_colorRangeBg=" + mnCalendar_colorRangeBg +
                ", mnCalendar_colorRangeText=" + mnCalendar_colorRangeText +
                ", mnCalendar_countMonth=" + mnCalendar_countMonth +
                ", mmCalendar_showTodayType=" + mnCalendar_showTodayType +
                '}';
    }

    public static class Builder {
        private MNCalendarVerticalConfig mnCalendarConfig = null;

        public Builder() {
            this.mnCalendarConfig = new MNCalendarVerticalConfig();
        }

        public Builder setMnCalendar_chooseType(@ChooseType int mnCalendar_chooseType) {
            mnCalendarConfig.setMnCalendar_chooseType(mnCalendar_chooseType);
            return this;
        }

        public Builder setMnCalendar_colorWeekendTitleBg(int mnCalendar_colorWeekendTitleBg) {
            mnCalendarConfig.setMnCalendar_colorWeekendTitleBg(mnCalendar_colorWeekendTitleBg);
            return this;
        }

        public Builder setMnCalendar_colorTitleBg(int mnCalendar_colorTitleBg) {
            mnCalendarConfig.setMnCalendar_colorTitleBg(mnCalendar_colorTitleBg);
            return this;
        }

        public Builder setMnCalendar_colorBg(int mnCalendar_colorBg) {
            mnCalendarConfig.setMnCalendar_colorBg(mnCalendar_colorBg);
            return this;
        }

        public Builder setMnCalendar_showTodayType(@ShowTodayType int showTodayType) {
            mnCalendarConfig.setMnCalendar_showTodayType(showTodayType);
            return this;
        }

        public Builder setMnCalendar_showLunar(boolean mnCalendar_showLunar) {
            mnCalendarConfig.setMnCalendar_showLunar(mnCalendar_showLunar);
            return this;
        }


        public Builder setMnCalendar_showWeek(boolean mnCalendar_showWeek) {
            mnCalendarConfig.setMnCalendar_showWeek(mnCalendar_showWeek);
            return this;
        }


        public Builder setMnCalendar_titleFormat(String mnCalendar_titleFormat) {
            mnCalendarConfig.setMnCalendar_titleFormat(mnCalendar_titleFormat);
            return this;
        }


        public Builder setMnCalendar_colorTitle(int mnCalendar_colorTitle) {
            mnCalendarConfig.setMnCalendar_colorTitle(mnCalendar_colorTitle);
            return this;
        }


        public Builder setMnCalendar_colorWeek(int mnCalendar_colorWeek) {
            mnCalendarConfig.setMnCalendar_colorWeek(mnCalendar_colorWeek);
            return this;
        }


        public Builder setMnCalendar_colorSolar(int mnCalendar_colorSolar) {
            mnCalendarConfig.setMnCalendar_colorSolar(mnCalendar_colorSolar);
            return this;
        }


        public Builder setMnCalendar_colorLunar(int mnCalendar_colorLunar) {
            mnCalendarConfig.setMnCalendar_colorLunar(mnCalendar_colorLunar);
            return this;
        }


        public Builder setMnCalendar_colorBeforeToday(int mnCalendar_colorBeforeToday) {
            mnCalendarConfig.setMnCalendar_colorBeforeToday(mnCalendar_colorBeforeToday);
            return this;
        }


        public Builder setMnCalendar_colorStartAndEndBg(int mnCalendar_colorStartAndEndBg) {
            mnCalendarConfig.setMnCalendar_colorStartAndEndBg(mnCalendar_colorStartAndEndBg);
            return this;
        }


        public Builder setMnCalendar_colorRangeBg(int mnCalendar_colorRangeBg) {
            mnCalendarConfig.setMnCalendar_colorRangeBg(mnCalendar_colorRangeBg);
            return this;
        }


        public Builder setMnCalendar_colorRangeText(int mnCalendar_colorRangeText) {
            mnCalendarConfig.setMnCalendar_colorRangeText(mnCalendar_colorRangeText);
            return this;
        }

        public Builder setMnCalendar_countMonth(int mnCalendar_countMonth) {
            mnCalendarConfig.setMnCalendar_countMonth(mnCalendar_countMonth);
            return this;
        }


        public MNCalendarVerticalConfig build() {
            return mnCalendarConfig;
        }

    }


}
