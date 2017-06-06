package com.xtagwgj.calendar.theme;

/**
 * 默认的日历主题
 * Created by xtagwgj on 2017/6/6.
 */

public class DefaultTheme extends XTheme {
    @Override
    public int colorBgTitle() {
        return 0xFFFFFFFF;
    }

    @Override
    public int colorTitle() {
        return 0xFF282828;
    }

    @Override
    public int colorBgCalendar() {
        return colorBgTitle();
    }

    @Override
    public int colorBgWeekend() {
        return colorBgTitle();
    }

    @Override
    public int colorWeek() {
        return 0xFF5E5E5E;
    }

    @Override
    public int colorSolar() {
        return colorTitle();
    }

    @Override
    public int colorLunar() {
        return 0xFF979797;
    }

    @Override
    public int colorBeforeToday() {
        return colorLunar();
    }

    @Override
    public int colorOtherMonth() {
        return colorLunar();
    }

    @Override
    public int colorTodayText() {
        return 0xFFFFFFFF;
    }

    @Override
    public int colorStartAndEndBg() {
        return 0xdf0e0e0e;
    }

    @Override
    public int colorRangeBg() {
        return colorStartAndEndBg();
    }

    @Override
    public int colorRangeText() {
        return colorTodayText();
    }

    @Override
    public int colorSplit() {
        return 0xFFCCCCCC;
    }
}
