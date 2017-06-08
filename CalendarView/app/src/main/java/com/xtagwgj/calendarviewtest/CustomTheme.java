package com.xtagwgj.calendarviewtest;

import com.xtagwgj.calendar.theme.XTheme;

/**
 * 默认的日历主题
 * Created by xtagwgj on 2017/6/6.
 */

public class CustomTheme extends XTheme {
    @Override
    public int colorBgTitle() {
        return 0xFF191B1F;
    }

    @Override
    public int colorTitle() {
        return 0xFFFFFFFF;
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
    public int colorBgToday() {
        return 0xFF282828;
    }

    @Override
    public int colorWeek() {
        return 0xFF5E5E5E;
    }

    @Override
    public int colorSolar() {
        return 0xFF282828;
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
        return 0xFF979797;
    }

    @Override
    public int colorTodayText() {
        return 0xFFFFFFFF;
    }

    @Override
    public int colorStartAndEndBg() {
        return 0xdf174254;
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
        return 0xFF222427;
    }
}
