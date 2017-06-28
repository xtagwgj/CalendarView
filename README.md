日历控件
======

[![](https://jitpack.io/v/xtagwgj/CalendarView.svg)](https://jitpack.io/#xtagwgj/CalendarView)

一个自定义的日历控件

**库的添加**
======
将库添加到project级别下的build.gradle中
```xml
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
添加依赖
```xml
	dependencies {
	        compile 'com.github.xtagwgj:CalendarView:v0.1'
	}
```

**使用方法**
======
在xml中添加自定义的控件
```xml
 <com.xtagwgj.calendar.XCalendar
        android:id="@+id/mnCalendar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
```

配置日历的基本属性
```java
        XCalendarConfig build = new XCalendarConfig.Builder()
                .setXCalendar_showLunar(false)//是否显示阴历
                .setXCalendar_showWeek(true)//是否显示星期栏
                .setXCalendar_showOtherMonthInfo(false)//是否显示其他月份的信息
                .setXCalendar_showTitle(true)//是否显示标题栏
                .setXCalendar_swipeMode(SwipeType.SWIPE_MODE_VER)//滑动切换的方式
                .setXCalendar_chooseType(ChooseType.DATE_CHOOSE_TYPE_RANGE)//选择的类型 
                .setXCalendar_canSelectDayBeforeNow(false)//是否可以选择今日之前的日期
                .setXCalendar_showRangeText(true)//显示区域文本
//                .setTheme(new CustomTheme())//日历的主题 可继承XTheme实现自己的主题
                .build();
```

设置属性
```java
        mnCalendar = (XCalendar) findViewById(R.id.mnCalendar);
        mnCalendar.setConfig(build);
```

其他的方法
```java
         mnCalendar.setCurrentDate(date);//跳转到其他月份
         mnCalendar.set2Today();//回到今天
         mnCalendar.setLastMonth();//上一个月
         mnCalendar.setNextMonth();//下一个月
```

日历的监听事件
```java
        mnCalendar.setOnCalendarItemClickListener(new OnCalendarItemClickListener() {

            @Override
            public void onSingleChoose(Date date) {
                Toast.makeText(context, "单击:" + sdf2.format(date.getTime()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMultiChoose(ArrayList<Date> dateArrayList) {
                Toast.makeText(context, "单击:" + dateArrayList.size(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRangeChoose(Date startDate, Date endDate) {
                Toast.makeText(context, "单击:" + sdf2.format(startDate.getTime()) + "到" + sdf2.format(endDate.getTime()), Toast.LENGTH_SHORT).show();
            }
            
        });
```

日历月份切换的监听
```java
 mnCalendar.setOnCalendarChangeListener(new OnCalendarChangeListener() {
            @Override
            public void lastMonth() {
                Toast.makeText(context, sdf.format(mnCalendar.getCurrentDate()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void nextMonth() {
                Toast.makeText(context, sdf.format(mnCalendar.getCurrentDate()), Toast.LENGTH_SHORT).show();
            }
        });
```

自定义日历主题
======
```java
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
     * 今日的背景颜色
     *
     * @return 16进制颜色
     */
    public abstract int colorBgToday();


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
```
