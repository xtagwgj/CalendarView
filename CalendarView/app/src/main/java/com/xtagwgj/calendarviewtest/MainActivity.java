package com.xtagwgj.calendarviewtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.xtagwgj.calendar.XCalendar;
import com.xtagwgj.calendar.listeners.OnCalendarChangeListener;
import com.xtagwgj.calendar.listeners.OnCalendarItemClickListener;
import com.xtagwgj.calendar.model.ChooseType;
import com.xtagwgj.calendar.model.SwipeType;
import com.xtagwgj.calendar.model.XCalendarConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Context context;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM", Locale.getDefault());
    private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private XCalendar mnCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        XCalendarConfig build = new XCalendarConfig.Builder()
                .setXCalendar_showLunar(false)//是否显示阴历
                .setXCalendar_showWeek(true)//是否显示星期栏
                .setXCalendar_showOtherMonthInfo(false)//是否显示其他月份的信息
                .setXCalendar_showTitle(true)//是否显示标题栏
                .setXCalendar_swipeMode(SwipeType.SWIPE_MODE_VER)//滑动切换的方式
                .setXCalendar_chooseType(ChooseType.DATE_CHOOSE_TYPE_RANGE)//选择的类型
                .setXCalendar_canSelectDayBeforeNow(false)//是否可以选择今日之前的日期
                .setXCalendar_showRangeText(true)//显示区域文本
                .setTheme(new CustomTheme())//日历的主题 可继承XTheme实现自己的主题
                .build();

        mnCalendar = (XCalendar) findViewById(R.id.mnCalendar);


        mnCalendar.setConfig(build);

//        ArrayList<Date> dates = new ArrayList<>();
//        dates.add(Calendar.getInstance().getTime());
//        mnCalendar.setChooseDateList(dates);

        Calendar canlendar1 = Calendar.getInstance();
        canlendar1.set(Calendar.DAY_OF_MONTH, 3);

        Log.e("22", canlendar1.getTime().toString());

        Calendar canlendar2 = Calendar.getInstance();
        canlendar2.set(Calendar.DAY_OF_MONTH, 13);

        mnCalendar.setRangeDate(canlendar1.getTime(), canlendar2.getTime());

        /**
         * Item点击监听
         */
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

        /**
         * 日历改变监听
         */
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_01:
                //跳转到当前月份
                String newDateString = "2017-10";
                Date date = null;
                try {
                    date = sdf.parse(newDateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mnCalendar.setCurrentDate(date);//跳转到其他月份
                break;
            case R.id.action_02:
                mnCalendar.set2Today();//回到今天
                break;
            case R.id.action_03:
                XCalendarConfig build = new XCalendarConfig.Builder()
//                        .setXCalendar_colorWeek(Color.parseColor("#00ff00"))
//                        .setXCalendar_colorLunar("#FF0000")
//                        .setXCalendar_colorSolar("#9BCCAF")
//                        .setXCalendar_colorTodayBg("#00FFFF")
//                        .setXCalendar_colorTodayText("#000000")
//                        .setXCalendar_colorOtherMonth("#F1EDBD")
//                        .setXCalendar_colorTitle("#FF0000")
                        .setXCalendar_swipeMode(SwipeType.SWIPE_MODE_VER)
                        .setXCalendar_chooseType(ChooseType.DATE_CHOOSE_TYPE_MULTI)
                        .setXCalendar_showLunar(true)
                        .setXCalendar_showWeek(true)
                        .setXCalendar_showOtherMonthInfo(false)
                        .build();
                mnCalendar.setConfig(build);
                break;
            case R.id.action_04:
                XCalendarConfig buildDefault = new XCalendarConfig.Builder().build();
                mnCalendar.setConfig(buildDefault);
                break;
            case R.id.action_10:
                mnCalendar.setLastMonth();//上一个月
                break;
            case R.id.action_11:
                mnCalendar.setNextMonth();//下一个月
                break;
            case R.id.action_05:
                XCalendarConfig build05 = new XCalendarConfig.Builder()
                        .setXCalendar_showTitle(false)
                        .build();
                mnCalendar.setConfig(build05);
                break;
            case R.id.action_06:
                XCalendarConfig build06 = new XCalendarConfig.Builder()
                        .setXCalendar_showWeek(false)
                        .build();
                mnCalendar.setConfig(build06);
                break;
            case R.id.action_07:
                XCalendarConfig build07 = new XCalendarConfig.Builder()
                        .setXCalendar_showLunar(false)
                        .build();
                mnCalendar.setConfig(build07);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
