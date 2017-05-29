package com.xtagwgj.calendar.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xtagwgj.calendar.R;
import com.xtagwgj.calendar.model.MNCalendarVerticalConfig;
import com.xtagwgj.calendar.utils.LunarCalendarUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by maning on 2017/5/9.
 */

public class MNCalendarVerticalItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "MNCalendar";
    private ArrayList<Date> mDatas;

    private LayoutInflater layoutInflater;

    private Context context;

    private Calendar currentCalendar;

    private MNCalendarVerticalAdapter adapter;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String now_yyy_mm_dd;
    private Date nowDate = new Date();

    //配置信息
    private MNCalendarVerticalConfig mnCalendarVerticalConfig;


    public MNCalendarVerticalItemAdapter(Context context, ArrayList<Date> mDatas, Calendar currentCalendar, MNCalendarVerticalAdapter adapter, MNCalendarVerticalConfig mnCalendarVerticalConfig) {
        this.context = context;
        this.mDatas = mDatas;
        this.currentCalendar = currentCalendar;
        this.adapter = adapter;
        this.mnCalendarVerticalConfig = mnCalendarVerticalConfig;
        layoutInflater = LayoutInflater.from(this.context);

        now_yyy_mm_dd = sdf.format(nowDate);
        try {
            nowDate = sdf.parse(now_yyy_mm_dd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = layoutInflater.inflate(R.layout.mn_item_calendar_vertical_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;

            Date datePosition = mDatas.get(position);

            myViewHolder.itemView.setVisibility(View.VISIBLE);
            myViewHolder.tv_small.setVisibility(View.GONE);
            myViewHolder.iv_bg.setVisibility(View.GONE);
            myViewHolder.tv_small.setText("");
            myViewHolder.tvDay.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorSolar());
            myViewHolder.tv_small.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorLunar());

            myViewHolder.tvDay.setText(String.valueOf(datePosition.getDate()));

            //不是本月的隐藏
            Date currentDate = currentCalendar.getTime();
            if (datePosition.getMonth() != currentDate.getMonth()) {
                myViewHolder.itemView.setVisibility(View.GONE);
            }

            //阴历的显示
            if (mnCalendarVerticalConfig.isMnCalendar_showLunar()) {
                myViewHolder.tv_small.setVisibility(View.VISIBLE);
                Calendar cal = Calendar.getInstance();
                cal.setTime(datePosition);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH);
                LunarCalendarUtils.Lunar solarToLunar = LunarCalendarUtils.solarToLunar(new LunarCalendarUtils.Solar(year, month, day));
                String lunarDayString = LunarCalendarUtils.getLunarDayString(solarToLunar.lunarDay);
                myViewHolder.tv_small.setText(lunarDayString);
            } else {
                myViewHolder.tv_small.setVisibility(View.GONE);
            }

            //判断是不是当天
            String position_yyy_MM_dd = sdf.format(datePosition);
            if (now_yyy_mm_dd.equals(position_yyy_MM_dd)) {
                if (mnCalendarVerticalConfig.getMnCalendar_showTodayType() == MNCalendarVerticalConfig.SHOW_TODAY_TEXT) {
                    myViewHolder.tvDay.setText("今天");
                    myViewHolder.todayImageView.setVisibility(View.GONE);
                } else {
                    myViewHolder.tv_small.setVisibility(View.GONE);
                    myViewHolder.todayImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.circle_today_drawable));
                    myViewHolder.todayImageView.setVisibility(View.VISIBLE);
                }
            }

            //小于今天的颜色变灰
            if (datePosition.getTime() < nowDate.getTime()) {
                myViewHolder.tvDay.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorBeforeToday());
            }

            //判断是不是点击了起始日期
            if (adapter.startDate != null && adapter.startDate == datePosition) {
                myViewHolder.iv_bg.setVisibility(View.VISIBLE);
                myViewHolder.iv_bg.setBackgroundResource(R.drawable.mn_selected_bg_start);
                myViewHolder.tv_small.setVisibility(View.VISIBLE);
                myViewHolder.tv_small.setText("开始");
                myViewHolder.tvDay.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorRangeText());
                myViewHolder.tv_small.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorRangeText());
                //动态修改颜色
                GradientDrawable myGrad = (GradientDrawable) myViewHolder.iv_bg.getBackground();
                myGrad.setColor(mnCalendarVerticalConfig.getMnCalendar_colorStartAndEndBg());
            }
            if (adapter.endDate != null && adapter.endDate == datePosition) {
                myViewHolder.iv_bg.setVisibility(View.VISIBLE);
                myViewHolder.iv_bg.setBackgroundResource(R.drawable.mn_selected_bg_end);
                myViewHolder.tv_small.setVisibility(View.VISIBLE);
                myViewHolder.tv_small.setText("结束");
                myViewHolder.tvDay.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorRangeText());
                myViewHolder.tv_small.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorRangeText());
                //动态修改颜色
                GradientDrawable myGrad = (GradientDrawable) myViewHolder.iv_bg.getBackground();
                myGrad.setColor(mnCalendarVerticalConfig.getMnCalendar_colorStartAndEndBg());
            }

            //判断是不是大于起始日期
            if (adapter.startDate != null && adapter.endDate != null) {
                if (datePosition.getTime() > adapter.startDate.getTime() && datePosition.getTime() < adapter.endDate.getTime()) {
                    myViewHolder.iv_bg.setVisibility(View.VISIBLE);
                    myViewHolder.iv_bg.setBackgroundResource(R.drawable.mn_selected_bg_centre);
                    myViewHolder.tvDay.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorRangeText());
                    myViewHolder.tv_small.setTextColor(mnCalendarVerticalConfig.getMnCalendar_colorRangeText());
                    //动态修改颜色
                    GradientDrawable myGrad = (GradientDrawable) myViewHolder.iv_bg.getBackground();
                    myGrad.setColor(mnCalendarVerticalConfig.getMnCalendar_colorRangeBg());
                }
            }

            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Date dateClick = mDatas.get(position);

                    //必须大于今天
                    if (dateClick.getTime() < nowDate.getTime()) {
                        Toast.makeText(context, "选择的日期必须大于今天", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (adapter.startDate != null && adapter.endDate != null) {
                        adapter.startDate = null;
                        adapter.endDate = null;
                    }
                    if (adapter.startDate == null) {
                        adapter.startDate = dateClick;
                    } else {
                        //判断结束位置是不是大于开始位置
                        long deteClickTime = dateClick.getTime();
                        long deteStartTime = adapter.startDate.getTime();
                        if (deteClickTime <= deteStartTime) {
                            adapter.startDate = dateClick;
                        } else {
                            adapter.endDate = dateClick;
                        }
                    }

                    //选取通知
                    adapter.notifyChoose();

                    //刷新
                    notifyDataSetChanged();
                    adapter.notifyDataSetChanged();


                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    private static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDay;
        private TextView tv_small;
        private ImageView iv_bg;
        private ImageView todayImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvDay = (TextView) itemView.findViewById(R.id.tvDay);
            tv_small = (TextView) itemView.findViewById(R.id.tv_small);
            iv_bg = (ImageView) itemView.findViewById(R.id.iv_bg);
            todayImageView = (ImageView) itemView.findViewById(R.id.todayImageView);
        }

    }

}
