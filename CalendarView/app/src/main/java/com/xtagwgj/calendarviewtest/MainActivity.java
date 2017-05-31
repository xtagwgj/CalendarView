package com.xtagwgj.calendarviewtest;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.xtagwgj.calendar.XCalendar;
import com.xtagwgj.calendar.listeners.OnCalendarChangeListener;
import com.xtagwgj.calendar.listeners.OnCalendarItemClickListener;
import com.xtagwgj.calendar.model.MNCalendarConfig;

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

        MNCalendarConfig build = new MNCalendarConfig.Builder()
                .setMnCalendar_showLunar(false)
                .setMnCalendar_showWeek(true)
                .setMnCalendar_showOtherMonthInfo(false)
                .setMnCalendar_showTitle(true)
                .setMnCalendar_colorBgWeekend(Color.parseColor("#191A1F"))
                .setMnCalendar_colorWeek(Color.parseColor("#CCCCCC"))
                .setMnCalendar_colorBgCalendar(Color.parseColor("#1E1F25"))
                .setMnCalendar_colorBeforeToday(Color.parseColor("#CCCCCC"))
                .setMnCalendar_colorStartAndEndBg(Color.parseColor("#174254"))
                .setMnCalendar_colorRangeBg(Color.parseColor("#174254"))
                .setMnCalendar_colorSolar(Color.parseColor("#FFFFFF"))
                .setMnCalendar_colorSplit(Color.parseColor("#222427"))
                .setMnCalendar_swipeMode(MNCalendarConfig.SWIPE_MODE_HOR)
                .setMnCalendar_chooseType(MNCalendarConfig.DATE_CHOOSE_TYPE_RANGE)
                .setMnCalendar_canSelectDayBeforeNow(false)
                .setMnCalendar_showRangeText(false)
                .build();

        mnCalendar = (XCalendar) findViewById(R.id.mnCalendar);


        mnCalendar.setConfig(build);

        ArrayList<Date> dates = new ArrayList<>();
        dates.add(Calendar.getInstance().getTime());
        mnCalendar.setChooseDateList(dates);


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
                mnCalendar.setClickable(false);

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
                mnCalendar.setCurrentDate(date);
                break;
            case R.id.action_02:
                mnCalendar.set2Today();
                break;
            case R.id.action_03:
                MNCalendarConfig build = new MNCalendarConfig.Builder()
                        .setMnCalendar_colorWeek(Color.parseColor("#00ff00"))
//                        .setMnCalendar_colorLunar("#FF0000")
//                        .setMnCalendar_colorSolar("#9BCCAF")
//                        .setMnCalendar_colorTodayBg("#00FFFF")
//                        .setMnCalendar_colorTodayText("#000000")
//                        .setMnCalendar_colorOtherMonth("#F1EDBD")
//                        .setMnCalendar_colorTitle("#FF0000")
                        .setMnCalendar_swipeMode(MNCalendarConfig.SWIPE_MODE_VER)
                        .setMnCalendar_chooseType(MNCalendarConfig.DATE_CHOOSE_TYPE_MULTI)
                        .setMnCalendar_showLunar(true)
                        .setMnCalendar_showWeek(true)
                        .setMnCalendar_showOtherMonthInfo(false)
                        .build();
                mnCalendar.setConfig(build);
                break;
            case R.id.action_04:
                MNCalendarConfig buildDefault = new MNCalendarConfig.Builder().build();
                mnCalendar.setConfig(buildDefault);
                break;
            case R.id.action_10:
                mnCalendar.setLastMonth();
                break;
            case R.id.action_11:
                mnCalendar.setNextMonth();
                break;
            case R.id.action_05:
                MNCalendarConfig build05 = new MNCalendarConfig.Builder()
                        .setMnCalendar_showTitle(false)
                        .build();
                mnCalendar.setConfig(build05);
                break;
            case R.id.action_06:
                MNCalendarConfig build06 = new MNCalendarConfig.Builder()
                        .setMnCalendar_showWeek(false)
                        .build();
                mnCalendar.setConfig(build06);
                break;
            case R.id.action_07:
                MNCalendarConfig build07 = new MNCalendarConfig.Builder()
                        .setMnCalendar_showLunar(false)
                        .build();
                mnCalendar.setConfig(build07);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
