package com.xtagwgj.calendar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xtagwgj.calendar.adapter.MNCalendarAdapter;
import com.xtagwgj.calendar.listeners.OnCalendarChangeListener;
import com.xtagwgj.calendar.listeners.OnCalendarItemClickListener;
import com.xtagwgj.calendar.listeners.RecyclerViewClickListener;
import com.xtagwgj.calendar.model.MNCalendarConfig;
import com.xtagwgj.calendar.view.MNGestureView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日历控件
 * Created by xtagwgj on 2017/5/29.
 */

public class XCalendar extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "XCalendar";

    private static SimpleDateFormat sdf_yyyy_MM = new SimpleDateFormat("yyyy-MM", Locale.CHINA);

    private Context context;

    private RecyclerView recyclerViewCalendar;
    private MNGestureView mnGestureView;

    //日期
    private LinearLayout ll_week;
    private TextView tv_week_01;
    private TextView tv_week_02;
    private TextView tv_week_03;
    private TextView tv_week_04;
    private TextView tv_week_05;
    private TextView tv_week_06;
    private TextView tv_week_07;

    //标题
    private RelativeLayout rl_title_view;
    private ImageView btn_left;
    private ImageView btn_right;
    private TextView tv_calendar_title;

    private OnCalendarItemClickListener onCalendarItemClickListener;
    private OnCalendarChangeListener onCalendarChangeListener;

    //配置信息
    private MNCalendarConfig mnCalendarConfig = new MNCalendarConfig.Builder().build();

    //当前的日期
    private Calendar currentCalendar = Calendar.getInstance();
    private MNCalendarAdapter mnCalendarAdapter;

    //选中的日期
    private ArrayList<Date> chooseDateList;

    //区间的开始日期
    private Date mStartDate = null;
    //区间的结束日期
    private Date mEndDate = null;

    public XCalendar(Context context) {
        this(context, null);
    }

    public XCalendar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        //初始化相关
        init();
    }

    private void init() {

        //初始化
        initViews();

        //绘制日历
        set2Today();

    }

    private void initViews() {
        //绑定View
        View.inflate(context, R.layout.mn_layout_calendar, this);
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
        rl_title_view = (RelativeLayout) findViewById(R.id.rl_title_view);
        btn_left = (ImageView) findViewById(R.id.btn_left);
        btn_right = (ImageView) findViewById(R.id.btn_right);
        tv_calendar_title = (TextView) findViewById(R.id.tv_calendar_title);

        //初始化RecycleerView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 7);
        recyclerViewCalendar.setLayoutManager(gridLayoutManager);

        mnGestureView.setSWIPE_MODE(mnCalendarConfig.getMnCalendar_swipeMode());
        //手势监听
        mnGestureView.setOnSwipeListener(new MNGestureView.OnSwipeListener() {
            @Override
            public void rightSwipe() {
                setLastMonth();
            }

            @Override
            public void leftSwipe() {
                setNextMonth();
            }

            @Override
            public void topSwipe() {
                setLastMonth();
            }

            @Override
            public void bottomSwipe() {
                setNextMonth();
            }
        });


        //点击事件
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);

        if (chooseDateList == null)
            chooseDateList = new ArrayList<>();
        else
            chooseDateList.clear();

    }

    private void drawCalendar() {

        //标题
        if (!mnCalendarConfig.isMnCalendar_showTitle()) {
            rl_title_view.setVisibility(View.GONE);
        } else {
            rl_title_view.setVisibility(View.VISIBLE);
            rl_title_view.setBackgroundColor(mnCalendarConfig.getMnCalendar_colorBgTitle());
            //标题
            tv_calendar_title.setText(sdf_yyyy_MM.format(getCurrentDate()));

            //标题颜色值
            int mnCalendar_colorTitle = mnCalendarConfig.getMnCalendar_colorTitle();
            tv_calendar_title.setTextColor(mnCalendar_colorTitle);
            btn_left.setColorFilter(mnCalendar_colorTitle);
            btn_right.setColorFilter(mnCalendar_colorTitle);
        }


        //判断是不是隐藏Week
        if (!mnCalendarConfig.isMnCalendar_showWeek()) {
            ll_week.setVisibility(View.GONE);
        } else {
            ll_week.setVisibility(View.VISIBLE);
            ll_week.setBackgroundColor(mnCalendarConfig.getMnCalendar_colorBgWeekend());

            //week的颜色值
            int mnCalendar_colorWeek = mnCalendarConfig.getMnCalendar_colorWeek();
            tv_week_01.setTextColor(mnCalendar_colorWeek);
            tv_week_02.setTextColor(mnCalendar_colorWeek);
            tv_week_03.setTextColor(mnCalendar_colorWeek);
            tv_week_04.setTextColor(mnCalendar_colorWeek);
            tv_week_05.setTextColor(mnCalendar_colorWeek);
            tv_week_06.setTextColor(mnCalendar_colorWeek);
            tv_week_07.setTextColor(mnCalendar_colorWeek);
        }

        //设置分割线的颜色
        findViewById(R.id.split1).setBackgroundColor(mnCalendarConfig.getMnCalendar_colorSplit());
        findViewById(R.id.split2).setBackgroundColor(mnCalendarConfig.getMnCalendar_colorSplit());


        //计算日期
        ArrayList<Date> mDatas = new ArrayList<>();
        Calendar calendar = (Calendar) currentCalendar.clone();

        //至于当月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        //将时间移到0点
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        //获取当月第一天是星期几
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        //移动到需要绘制的第一天
        calendar.add(Calendar.DAY_OF_MONTH, -day_of_week);

        float weeks = (maxDays + day_of_week) / 7f;


        //6*7
        while (mDatas.size() < (weeks > 5 ? 6 : 5) * 7) {
            mDatas.add(calendar.getTime());
            //向前移动一天
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        //设置Adapter
        if (mnCalendarAdapter == null) {
            mnCalendarAdapter = new MNCalendarAdapter(context, mDatas, chooseDateList, currentCalendar,
                    mnCalendarConfig, mStartDate, mEndDate);
        } else {
            mStartDate = mnCalendarAdapter.getStartRangeDate();
            mEndDate = mnCalendarAdapter.getEndRangeDate();

            mnCalendarAdapter.refreshAll(mDatas, chooseDateList, currentCalendar,
                    mnCalendarConfig, mStartDate, mEndDate);
        }


        recyclerViewCalendar.setAdapter(mnCalendarAdapter);
        recyclerViewCalendar.setBackgroundColor(mnCalendarConfig.getMnCalendar_colorBgCalendar());
        recyclerViewCalendar.addOnItemTouchListener(new RecyclerViewClickListener(getContext(), recyclerViewCalendar, null));

        //设置Item点击事件
        mnCalendarAdapter.setOnCalendarItemClickListener(onCalendarItemClickListener);

    }

    /**
     * 设置点击事件
     *
     * @param onCalendarItemClickListener 日历点击监听事件，可为null
     */
    public void setOnCalendarItemClickListener(@Nullable OnCalendarItemClickListener onCalendarItemClickListener) {
        this.onCalendarItemClickListener = onCalendarItemClickListener;
        //设置Item点击事件
        if (mnCalendarAdapter != null) {
            mnCalendarAdapter.setOnCalendarItemClickListener(onCalendarItemClickListener);
        }
    }

    /**
     * 设置改变的监听
     *
     * @param onCalendarChangeListener 日历月份改变的监听
     */
    public void setOnCalendarChangeListener(@Nullable OnCalendarChangeListener onCalendarChangeListener) {
        this.onCalendarChangeListener = onCalendarChangeListener;
    }

    /**
     * 获取当前的时间
     *
     * @return 当前显示的月份
     */
    public Date getCurrentDate() {
        return currentCalendar.getTime();
    }

    /**
     * 跳转到设置的月份
     *
     * @param date 设置的月份
     */
    public void setCurrentDate(Date date) {
        if (date != null) {
            currentCalendar.setTime(date);
            drawCalendar();
        }
    }

    /**
     * 切换到下个月
     */
    public void setNextMonth() {
        currentCalendar.add(Calendar.MONTH, 1);
        drawCalendar();
        if (onCalendarChangeListener != null) {
            onCalendarChangeListener.nextMonth();
        }
    }

    /**
     * 切换到上个月
     */
    public void setLastMonth() {
        currentCalendar.add(Calendar.MONTH, -1);
        drawCalendar();
        if (onCalendarChangeListener != null) {
            onCalendarChangeListener.lastMonth();
        }
    }

    /**
     * 回到今天
     */
    public void set2Today() {
        currentCalendar = Calendar.getInstance();
        drawCalendar();
    }

    /**
     * 配置参数
     *
     * @param config 配置
     */
    public void setConfig(MNCalendarConfig config) {
        this.mnCalendarConfig = config != null ? config : new MNCalendarConfig.Builder().build();

        if (mnGestureView != null) {
            mnGestureView.setCanSwipe(mnCalendarConfig.getMnCalendar_swipeMode() != MNCalendarConfig.SWIPE_MODE_NONE);
            mnGestureView.setSWIPE_MODE(mnCalendarConfig.getMnCalendar_swipeMode());
        }

        drawCalendar();
    }

    public void setChooseDateList(ArrayList<Date> chooseDate) {
        this.chooseDateList = chooseDate == null ? new ArrayList<Date>() : chooseDate;

        if (chooseDateList.size() > 0) {
            for (int i = 0; i < chooseDateList.size(); i++) {
                chooseDateList.set(i, parseDate2Zero(chooseDateList.get(i)));
            }
        }
        mnCalendarAdapter.refreshChooseDate(chooseDateList);
    }

    /**
     * 将时间重置到0点
     *
     * @param date 时间
     * @return 0点的时间
     */
    private Date parseDate2Zero(Date date) {

        if (date == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //将时间移到0点
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 设置选中范围的时间
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     */
    public void setRangeDate(Date startDate, Date endDate) {
        mStartDate = parseDate2Zero(startDate);
        mEndDate = parseDate2Zero(endDate);
        if (mnCalendarAdapter != null) {
            mnCalendarAdapter.refreshRangeDate(mStartDate, mEndDate);
        } else {
            drawCalendar();
        }
    }

    @Override
    public void setClickable(boolean clickable) {
        super.setClickable(clickable);
        mnCalendarConfig.setMnCalendar_calendarClickable(clickable);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_left) {
            setLastMonth();
        } else if (view.getId() == R.id.btn_right) {
            setNextMonth();
        }
    }
}
