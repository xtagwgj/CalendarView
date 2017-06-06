package com.xtagwgj.calendar.theme;

/**
 * 主题抽象类
 * Created by xtagwgj on 2017/6/6.
 */

public abstract class XTheme {


    /**
     * 标题的背景颜色
     *
     * @return 16进制颜色
     */
    public abstract int colorBgTitle();

    /**
     * 标题栏上的字体颜色
     *
     * @return 16进制颜色
     */
    public abstract int colorTitle();

    /**
     * 具体日期的背景颜色 顶部显示的当前月份的信息
     *
     * @return 16进制颜色
     */
    public abstract int colorBgCalendar();

    /**
     * 星期栏目的背景颜色
     *
     * @return 16进制颜色
     */
    public abstract int colorBgWeekend();


    /**
     * 日历顶部 星期几 的颜色值
     *
     * @return 16进制颜色
     */
    public abstract int colorWeek();

    /**
     * 日历Item的阳历的颜色值
     *
     * @return 16进制颜色
     */
    public abstract int colorSolar();

    /**
     * 日历Item的阴历的颜色值
     *
     * @return 16进制颜色
     */
    public abstract int colorLunar();

    /**
     * 今天之前的日期颜色
     *
     * @return 16进制颜色
     */
    public abstract int colorBeforeToday();

    /**
     * 日历不是当前月份的阳历的颜色
     * 区分是当前月还是其他月份
     *
     * @return 16进制颜色
     */
    public abstract int colorOtherMonth();

    /**
     * 日历今天圆形背景上的文字的颜色
     *
     * @return 16进制颜色
     */
    public abstract int colorTodayText();


    /**
     * 开始结束的背景颜色 与 colorRangeBg() 不同的话，有不同的效果
     *
     * @return 16进制颜色
     */
    public abstract int colorStartAndEndBg();

    /**
     * 区间中间的背景颜色
     *
     * @return 16进制颜色
     */
    public abstract int colorRangeBg();

    /**
     * 区域中间的字体颜色
     *
     * @return 16进制颜色
     */
    public abstract int colorRangeText();

    /**
     * 分割线的颜色值
     *
     * @return 16进制颜色
     */
    public abstract int colorSplit();

}