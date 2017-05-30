package com.xtagwgj.calendar.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xtagwgj.calendar.R;
import com.xtagwgj.calendar.listeners.OnCalendarItemClickListener;
import com.xtagwgj.calendar.model.MNCalendarConfig;
import com.xtagwgj.calendar.utils.LunarCalendarUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xtagwgj on 2017/5/29.
 */

public class MNCalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Date> mDatas;

    private LayoutInflater layoutInflater;

    private OnCalendarItemClickListener onCalendarItemClickListener;

    private Calendar currentCalendar;

    private MNCalendarConfig mnCalendarConfig;

    private Context context;

    //选择的日期
    private ArrayList<Date> chooseDate;

    //区间的开始日期
    public Date startDate = null;
    //区间的结束日期
    public Date endDate = null;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public void setOnCalendarItemClickListener(OnCalendarItemClickListener onCalendarItemClickListener) {
        this.onCalendarItemClickListener = onCalendarItemClickListener;
    }

    public MNCalendarAdapter(Context context, ArrayList<Date> mDatas, ArrayList<Date> chooseDate, Calendar currentCalendar, MNCalendarConfig mnCalendarConfig) {
        this.context = context;
        this.mDatas = mDatas;
        this.currentCalendar = currentCalendar;
        this.mnCalendarConfig = mnCalendarConfig;
        layoutInflater = LayoutInflater.from(this.context);
        this.chooseDate = chooseDate;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = layoutInflater.inflate(R.layout.mn_item_calendar, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;

            Date datePosition = mDatas.get(position);
            Date currentDate = currentCalendar.getTime();

            myViewHolder.tvDay.setText(String.valueOf(datePosition.getDate()));

            //动态修改颜色
            GradientDrawable grad = (GradientDrawable) myViewHolder.iv_today_bg.getBackground();
            grad.setColor(Color.parseColor("#FFFFFF"));

            // 当前位置未被选中
            if (!chooseDate.contains(datePosition)) {
                myViewHolder.iv_today_bg.setVisibility(View.GONE);
                myViewHolder.tvDay_lunar.setVisibility(View.VISIBLE);
                myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorSolar());
                myViewHolder.tvDay_lunar.setTextColor(mnCalendarConfig.getMnCalendar_colorLunar());

            } else if (datePosition.getMonth() == currentDate.getMonth() || mnCalendarConfig.isMnCalendar_showOtherMonthInfo()) {
                myViewHolder.iv_today_bg.setVisibility(View.VISIBLE);
                myViewHolder.iv_today_bg.setBackgroundResource(R.drawable.mn_selected_bg_centre);
                myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorRangeText());
                myViewHolder.tvDay_lunar.setTextColor(mnCalendarConfig.getMnCalendar_colorRangeText());
                //动态修改颜色
                GradientDrawable myGrad = (GradientDrawable) myViewHolder.iv_today_bg.getBackground();
                myGrad.setColor(mnCalendarConfig.getMnCalendar_colorRangeBg());
            }

            //不是本月的颜色变灰
            if (datePosition.getMonth() != currentDate.getMonth()) {
                myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorOtherMonth());
            }

            //判断是不是当天
            Date nowDate = new Date();
            String now_yyy_MM_dd = sdf.format(nowDate);
            String position_yyy_MM_dd = sdf.format(datePosition);

            if (mnCalendarConfig.isMnCalendar_showCurrDay() && now_yyy_MM_dd.equals(position_yyy_MM_dd)
                    && (datePosition.getMonth() == currentDate.getMonth() ||
                    mnCalendarConfig.isMnCalendar_showOtherMonthInfo())) {
//                myViewHolder.iv_today_bg.setVisibility(View.VISIBLE);
//                myViewHolder.iv_today_bg.setBackground(context.getResources().getDrawable(R.drawable.mn_today_bg));
//                myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorTodayText());
//                myViewHolder.tvDay_lunar.setTextColor(mnCalendarConfig.getMnCalendar_colorTodayText());
                //动态修改颜色
//                GradientDrawable myGrad = (GradientDrawable) myViewHolder.iv_today_bg.getBackground();
//                myGrad.setColor(mnCalendarConfig.getMnCalendar_colorTodayBg());

                myViewHolder.currDayImageView.setVisibility(View.VISIBLE);

            } else {
                myViewHolder.currDayImageView.setVisibility(View.INVISIBLE);
            }

            //阴历的显示
            if (mnCalendarConfig.isMnCalendar_showLunar()) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(datePosition);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH);
                LunarCalendarUtils.Lunar solarToLunar = LunarCalendarUtils.solarToLunar(new LunarCalendarUtils.Solar(year, month, day));
                String lunarDayString = LunarCalendarUtils.getLunarDayString(solarToLunar.lunarDay);
                myViewHolder.tvDay_lunar.setText(lunarDayString);


                if (now_yyy_MM_dd.equals(position_yyy_MM_dd)) {
                    myViewHolder.currDayImageView.setVisibility(View.VISIBLE);
                    myViewHolder.tvDay_lunar.setVisibility(View.GONE);
                } else {
                    myViewHolder.tvDay_lunar.setVisibility(View.VISIBLE);
                    myViewHolder.currDayImageView.setVisibility(View.GONE);
                }


            } else {
                myViewHolder.tvDay_lunar.setVisibility(View.GONE);
            }

            //非当前月信息的显示
            if (datePosition.getMonth() != currentDate.getMonth() && !mnCalendarConfig.isMnCalendar_showOtherMonthInfo()) {
                myViewHolder.tvDay_lunar.setVisibility(View.GONE);
                myViewHolder.tvDay.setVisibility(View.GONE);
            }

            if (mnCalendarConfig.getMnCalendar_chooseType() == MNCalendarConfig.DATE_CHOOSE_TYPE_RANGE) {

                //判断是不是点击了起始日期
                if (startDate != null && startDate == datePosition) {
                    myViewHolder.iv_today_bg.setVisibility(View.VISIBLE);
                    myViewHolder.iv_today_bg.setBackgroundResource(R.drawable.mn_selected_bg_start);
                    myViewHolder.tvDay_lunar.setVisibility(View.VISIBLE);
                    myViewHolder.tvDay_lunar.setText("开始");
                    myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorRangeText());
                    myViewHolder.tvDay_lunar.setTextColor(mnCalendarConfig.getMnCalendar_colorRangeText());
                    //动态修改颜色
                    GradientDrawable myGrad = (GradientDrawable) myViewHolder.iv_today_bg.getBackground();
                    myGrad.setColor(mnCalendarConfig.getMnCalendar_colorStartAndEndBg());
                }

                //判断是不是点击了结束日期
                if (endDate != null && endDate == datePosition) {
                    myViewHolder.iv_today_bg.setVisibility(View.VISIBLE);
                    myViewHolder.iv_today_bg.setBackgroundResource(R.drawable.mn_selected_bg_end);
                    myViewHolder.tvDay_lunar.setVisibility(View.VISIBLE);
                    myViewHolder.tvDay_lunar.setText("结束");
                    myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorRangeText());
                    myViewHolder.tvDay_lunar.setTextColor(mnCalendarConfig.getMnCalendar_colorRangeText());
                    //动态修改颜色
                    GradientDrawable myGrad = (GradientDrawable) myViewHolder.iv_today_bg.getBackground();
                    myGrad.setColor(mnCalendarConfig.getMnCalendar_colorStartAndEndBg());
                }

                //判断是不是大于起始日期
                if (startDate != null && endDate != null) {
                    if (datePosition.getTime() > startDate.getTime() && datePosition.getTime() < endDate.getTime()) {
                        myViewHolder.iv_today_bg.setVisibility(View.VISIBLE);
                        myViewHolder.iv_today_bg.setBackgroundResource(R.drawable.mn_selected_bg_centre);
                        myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorRangeText());
                        myViewHolder.tvDay_lunar.setTextColor(mnCalendarConfig.getMnCalendar_colorRangeText());
                        //动态修改颜色
                        GradientDrawable myGrad = (GradientDrawable) myViewHolder.iv_today_bg.getBackground();
                        myGrad.setColor(mnCalendarConfig.getMnCalendar_colorRangeBg());
                    }
                }


            }

            if (this.onCalendarItemClickListener != null) {
                myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Date currentDate = currentCalendar.getTime();
                        Date datePosition = mDatas.get(position);

                        //当前月或者显示其他月的信息
                        if (currentDate.getMonth() == datePosition.getMonth() || mnCalendarConfig.isMnCalendar_showOtherMonthInfo()) {

                            switch (mnCalendarConfig.getMnCalendar_chooseType()) {
                                case MNCalendarConfig.DATE_CHOOSE_TYPE_SINGLE:

                                    onCalendarItemClickListener.onSingleChoose(datePosition);
                                    break;

                                case MNCalendarConfig.DATE_CHOOSE_TYPE_MULTI:

                                    if (chooseDate.contains(datePosition))
                                        chooseDate.remove(datePosition);
                                    else
                                        chooseDate.add(datePosition);

                                    onCalendarItemClickListener.onMultiChoose(chooseDate);

                                    notifyItemChanged(position);
                                    break;

                                case MNCalendarConfig.DATE_CHOOSE_TYPE_RANGE:

                                    //必须大于今天
                                    if (datePosition.getTime() < datePosition.getTime()) {
                                        Toast.makeText(context, "选择的日期必须大于今天", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                    if (startDate != null && endDate != null) {
                                        startDate = null;
                                        endDate = null;
                                    }
                                    if (startDate == null) {
                                        startDate = datePosition;
                                    } else {
                                        //判断结束位置是不是大于开始位置
                                        long dateClickTime = datePosition.getTime();
                                        long dateStartTime = startDate.getTime();
                                        if (dateClickTime <= dateStartTime) {
                                            startDate = datePosition;
                                        } else {
                                            endDate = datePosition;

                                            onCalendarItemClickListener.onRangeChoose(startDate, endDate);
                                        }
                                    }

                                    //刷新
                                    notifyDataSetChanged();
                                    break;
                            }

                        }
                    }
                });

            }

        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    private static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDay;
        private TextView tvDay_lunar;
        private ImageView iv_today_bg;
        private ImageView currDayImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvDay = (TextView) itemView.findViewById(R.id.tvDay);
            tvDay_lunar = (TextView) itemView.findViewById(R.id.tvDay_lunar);
            iv_today_bg = (ImageView) itemView.findViewById(R.id.iv_today_bg);
            currDayImageView = (ImageView) itemView.findViewById(R.id.currDayImageView);
        }
    }

}
