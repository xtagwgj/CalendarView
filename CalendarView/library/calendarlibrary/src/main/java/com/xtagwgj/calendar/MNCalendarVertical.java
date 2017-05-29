package com.xtagwgj.calendar;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xtagwgj.calendar.adapter.MNCalendarVerticalAdapter;
import com.xtagwgj.calendar.listeners.OnCalendarChangeListener;
import com.xtagwgj.calendar.listeners.OnCalendarRangeChooseListener;
import com.xtagwgj.calendar.model.MNCalendarVerticalConfig;
import com.xtagwgj.calendar.view.MNGestureView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by maning on 2017/5/10.
 * 垂直方向的日历
 */

public class MNCalendarVertical extends LinearLayout implements MNGestureView.OnSwipeListener {

    private Context context;

    private RecyclerView recyclerViewCalendar;
    private MNGestureView mnGestureView;
    private LinearLayout ll_week;
    private TextView tv_week_01;
    private TextView tv_week_02;
    private TextView tv_week_03;
    private TextView tv_week_04;
    private TextView tv_week_05;
    private TextView tv_week_06;
    private TextView tv_week_07;

    private Calendar currentCalendar = Calendar.getInstance();

    private MNCalendarVerticalConfig mnCalendarVerticalConfig = new MNCalendarVerticalConfig.Builder().build();
    private MNCalendarVerticalAdapter mnCalendarVerticalAdapter;
    private HashMap<String, ArrayList<Date>> dataMap;

    private OnCalendarRangeChooseListener onCalendarRangeChooseListener;
    private OnCalendarChangeListener onCalendarChangeListener;

    public MNCalendarVertical(Context context) {
        this(context, null);
    }

    public MNCalendarVertical(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MNCalendarVertical(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {

        initViews();

    }


    private void initViews() {
        //绑定View
        View.inflate(context, R.layout.mn_layout_calendar_vertical, this);
        recyclerViewCalendar = (RecyclerView) findViewById(R.id.recyclerViewCalendar);
        mnGestureView = (MNGestureView) findViewById(R.id.mnGestureView);
        ll_week = (LinearLayout) findViewById(R.id.ll_week);
        tv_week_01 = (TextView) findViewById(R.id.tv_week_01);
        tv_week_02 = (TextView) findViewById(R.id.tv_week_02);
        tv_week_03 = (TextView) findViewById(R.id.tv_week_03);
        tv_week_04 = (TextView) findViewById(R.id.tv_week_04);
        tv_week_05 = (TextView) findViewById(R.id.tv_week_05);
        tv_week_06 = (TextView) findViewById(R.id.tv_week_06);
        tv_week_07 = (TextView) findViewById(R.id.tv_week_07);

        ll_week.setBackgroundColor(mnCalendarVerticalConfig.getMnCalendar_colorWeekendTitleBg());

        //手势监听
        mnGestureView.setOnSwipeListener(this);

        //初始化RecycleerView
        recyclerViewCalendar.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewCalendar.setBackgroundColor(mnCalendarVerticalConfig.getMnCalendar_colorBg());

        //星期栏的显示和隐藏
        boolean mnCalendar_showWeek = mnCalendarVerticalConfig.isMnCalendar_showWeek();
        if (mnCalendar_showWeek) {
            ll_week.setVisibility(View.VISIBLE);
            tv_week_01.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorWeek());
            tv_week_02.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorWeek());
            tv_week_03.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorWeek());
            tv_week_04.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorWeek());
            tv_week_05.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorWeek());
            tv_week_06.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorWeek());
            tv_week_07.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorWeek());
        } else {
            ll_week.setVisibility(View.GONE);
        }

        initCalendarDatas();
    }


    private void initCalendarDatas() {
        //日期集合
        if (dataMap == null)
            dataMap = new HashMap<>();
        else
            dataMap.clear();

        //计算日期
        int mnCalendar_countMonth = mnCalendarVerticalConfig.getMnCalendar_countMonth();
        for (int i = 0; i < mnCalendar_countMonth; i++) {
            int count7 = 0;
            ArrayList<Date> mDatas = new ArrayList<>();
            Calendar calendar = (Calendar) currentCalendar.clone();
            calendar.add(Calendar.MONTH, i);
            //至于当月的第一天
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            //获取当月第一天是星期几
            int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            //移动到需要绘制的第一天
            calendar.add(Calendar.DAY_OF_MONTH, -day_of_week);
            //6*7
            while (mDatas.size() < 6 * 7) {
                mDatas.add(calendar.getTime());
                //包含两个7就多了一行
                if (String.valueOf(calendar.getTime().getDate()).equals("7")) {
                    count7++;
                }
                //向前移动一天
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            if (count7 >= 2) {
                ArrayList<Date> mDatas2 = new ArrayList<>();
                for (int j = 0; j < mDatas.size() - 7; j++) {
                    mDatas2.add(mDatas.get(j));
                }
                mDatas = new ArrayList<>();
                mDatas.addAll(mDatas2);
            }

            dataMap.put(String.valueOf(i), mDatas);
        }

        //设置Adapter
        initAdapter();

    }

    /**
     * 切换到下个月
     */
    public void setNextMonth() {
        currentCalendar.add(Calendar.MONTH, 1);
        initViews();
        if (onCalendarChangeListener != null) {
            onCalendarChangeListener.nextMonth();
        }
    }

    /**
     * 切换到上个月
     */
    public void setLastMonth() {
        currentCalendar.add(Calendar.MONTH, -1);
        initViews();
        if (onCalendarChangeListener != null) {
            onCalendarChangeListener.lastMonth();
        }
    }

    private void initAdapter() {
        if (mnCalendarVerticalAdapter == null) {
            mnCalendarVerticalAdapter = new MNCalendarVerticalAdapter(context, dataMap, currentCalendar, mnCalendarVerticalConfig);
            recyclerViewCalendar.setAdapter(mnCalendarVerticalAdapter);
        } else {
            mnCalendarVerticalAdapter.updateDatas(dataMap, currentCalendar, mnCalendarVerticalConfig);
        }
        if (onCalendarRangeChooseListener != null) {
            mnCalendarVerticalAdapter.setOnCalendarRangeChooseListener(onCalendarRangeChooseListener);
        }
    }

    /**
     * 设置配置文件
     *
     * @param config
     */
    public void setConfig(MNCalendarVerticalConfig config) {
        this.mnCalendarVerticalConfig = config;
        initCalendarDatas();
    }


    public void setOnCalendarRangeChooseListener(OnCalendarRangeChooseListener onCalendarRangeChooseListener) {
        this.onCalendarRangeChooseListener = onCalendarRangeChooseListener;
        if (mnCalendarVerticalAdapter != null) {
            mnCalendarVerticalAdapter.setOnCalendarRangeChooseListener(onCalendarRangeChooseListener);
        }
    }

    /**
     * 设置改变的监听
     *
     * @param onCalendarChangeListener
     */
    public void setOnCalendarChangeListener(OnCalendarChangeListener onCalendarChangeListener) {
        this.onCalendarChangeListener = onCalendarChangeListener;
    }


    @Override
    public void rightSwipe() {
        setLastMonth();
    }

    @Override
    public void leftSwipe() {
        setNextMonth();
    }
}
