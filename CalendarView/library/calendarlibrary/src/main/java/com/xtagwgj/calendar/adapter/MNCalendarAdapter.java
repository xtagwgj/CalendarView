package com.xtagwgj.calendar.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import java.util.Locale;

/**
 * 日历的适配器
 * Created by xtagwgj on 2017/5/29.
 */

public class MNCalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    //日期集合
    private ArrayList<Date> mDatas;

    private OnCalendarItemClickListener onCalendarItemClickListener;

    private Calendar currentShowCalendar;

    //今天零点
    private Date currentDateZero;

    //配置文件
    private MNCalendarConfig mnCalendarConfig;


    //选择的日期
    private ArrayList<Date> chooseDate;

    //区间的开始日期
    private Date startDate = null;

    //区间的结束日期
    private Date endDate = null;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());


    public void setOnCalendarItemClickListener(OnCalendarItemClickListener onCalendarItemClickListener) {
        this.onCalendarItemClickListener = onCalendarItemClickListener;
    }

    public void refreshChooseDate(ArrayList<Date> chooseDate) {
        this.chooseDate = chooseDate;
        notifyDataSetChanged();
    }

    public MNCalendarAdapter(Context context, ArrayList<Date> mDatas, ArrayList<Date> chooseDate,
                             Calendar currentShowCalendar, MNCalendarConfig mnCalendarConfig,
                             Date startDate, Date endDate) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        currentDateZero = calendar.getTime();
        this.context = context;
        refreshAll(mDatas, chooseDate, currentShowCalendar, mnCalendarConfig, startDate, endDate);
    }

    public void refreshAll(ArrayList<Date> mDatas, ArrayList<Date> chooseDate,
                           Calendar currentShowCalendar, MNCalendarConfig mnCalendarConfig,
                           Date startDate, Date endDate) {

        this.mDatas = mDatas;
        this.currentShowCalendar = currentShowCalendar;
        this.mnCalendarConfig = mnCalendarConfig;
        this.chooseDate = chooseDate;
        this.startDate = startDate;
        this.endDate = endDate;

        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.mn_item_calendar, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;

            Date datePosition = mDatas.get(position);
            Date currentShowDate = currentShowCalendar.getTime();

            myViewHolder.tvDay.setText(String.valueOf(datePosition.getDate()));

            //动态修改颜色
            GradientDrawable grad = (GradientDrawable) myViewHolder.iv_today_bg.getBackground();
            grad.setColor(mnCalendarConfig.getMnCalendar_colorBgCalendar());

            // 当前位置未被选中
            if (!chooseDate.contains(datePosition)) {
                myViewHolder.iv_today_bg.setVisibility(View.GONE);
                myViewHolder.tvDay_lunar.setVisibility(View.VISIBLE);
                myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorSolar());
                myViewHolder.tvDay_lunar.setTextColor(mnCalendarConfig.getMnCalendar_colorLunar());

            } else if (datePosition.getMonth() == currentShowDate.getMonth() || mnCalendarConfig.isMnCalendar_showOtherMonthInfo()) {
                myViewHolder.iv_today_bg.setVisibility(View.VISIBLE);
                myViewHolder.iv_today_bg.setBackgroundResource(R.drawable.mn_selected_bg_centre);
                myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorRangeText());
                myViewHolder.tvDay_lunar.setTextColor(mnCalendarConfig.getMnCalendar_colorRangeText());
                //动态修改颜色
                GradientDrawable myGrad = (GradientDrawable) myViewHolder.iv_today_bg.getBackground();
                myGrad.setColor(mnCalendarConfig.getMnCalendar_colorRangeBg());
            }

            //不是本月的颜色变灰
            if (datePosition.getMonth() != currentShowDate.getMonth()) {
                myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorOtherMonth());
            }

            //判断是不是当天
            String now_yyy_MM_dd = sdf.format(currentDateZero);
            String position_yyy_MM_dd = sdf.format(datePosition);

            //当天下面显示圆点
            if (mnCalendarConfig.isMnCalendar_showCurrDay() && now_yyy_MM_dd.equals(position_yyy_MM_dd)
                    && (datePosition.getMonth() == currentDateZero.getMonth() || mnCalendarConfig.isMnCalendar_showOtherMonthInfo())) {

                myViewHolder.currDayImageView.setVisibility(View.VISIBLE);
            } else {
                myViewHolder.currDayImageView.setVisibility(View.INVISIBLE);
            }


            //本月 今日之前的字体颜色变化
            if (datePosition.getMonth() == currentShowDate.getMonth() && datePosition.getTime() < currentDateZero.getTime()) {
                myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorBeforeToday());
            }

            //阴历的显示
            if (mnCalendarConfig.isMnCalendar_showLunar()) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(datePosition);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH);
                LunarCalendarUtils.Lunar solarToLunar = LunarCalendarUtils.solarToLunar(new LunarCalendarUtils.Solar(year, month, day));
                //String lunarDayString = LunarCalendarUtils.getLunarDayString(solarToLunar.lunarDay);
                String lunarDayString = LunarCalendarUtils.getLunarDayString(solarToLunar);
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

            //判断是否显示 非当前月信息的显示
            boolean showInCalendarInfos = datePosition.getMonth() != currentShowDate.getMonth() &&
                    !mnCalendarConfig.isMnCalendar_showOtherMonthInfo();

            //非当前月信息的显示
            if (showInCalendarInfos) {
                myViewHolder.tvDay_lunar.setVisibility(View.GONE);
                myViewHolder.tvDay.setVisibility(View.INVISIBLE);
                myViewHolder.currDayImageView.setVisibility(View.GONE);
                myViewHolder.rlContent.setEnabled(false);

                //如果不显示非当前月的信息，跳出界面绘制，执行下一个界面的绘制
                return;
            } else {
                myViewHolder.rlContent.setEnabled(true);
            }

            if (mnCalendarConfig.getMnCalendar_chooseType() == MNCalendarConfig.DATE_CHOOSE_TYPE_RANGE) {

                //判断是不是点击了起始日期
                if (startDate != null && startDate.getTime() == datePosition.getTime() ) {
                    myViewHolder.iv_today_bg.setVisibility(View.VISIBLE);
                    myViewHolder.iv_today_bg.setBackgroundResource(R.drawable.mn_selected_bg_start);

                    if (mnCalendarConfig.isMnCalendar_showRangeText()) {
                        myViewHolder.tvDay_lunar.setText(context.getResources().getString(R.string.prompt_start));
                        myViewHolder.tvDay_lunar.setVisibility(View.VISIBLE);
                    }

                    myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorRangeText());
                    myViewHolder.tvDay_lunar.setTextColor(mnCalendarConfig.getMnCalendar_colorRangeText());

                    //如果小圆点显示就隐藏
                    if (((MyViewHolder) holder).currDayImageView.getVisibility() == View.VISIBLE) {
                        ((MyViewHolder) holder).currDayImageView.setVisibility(View.INVISIBLE);
                    }

                    //动态修改颜色
                    GradientDrawable myGrad = (GradientDrawable) myViewHolder.iv_today_bg.getBackground();
                    myGrad.setColor(mnCalendarConfig.getMnCalendar_colorStartAndEndBg());
                }

                //判断是不是点击了结束日期
                if (endDate != null && endDate.getTime() == datePosition.getTime()) {
                    myViewHolder.iv_today_bg.setVisibility(View.VISIBLE);
                    myViewHolder.iv_today_bg.setBackgroundResource(R.drawable.mn_selected_bg_end);

                    if (mnCalendarConfig.isMnCalendar_showRangeText()) {
                        myViewHolder.tvDay_lunar.setVisibility(View.VISIBLE);
                        myViewHolder.tvDay_lunar.setText(context.getResources().getString(R.string.prompt_end));
                    }
                    myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorRangeText());
                    myViewHolder.tvDay_lunar.setTextColor(mnCalendarConfig.getMnCalendar_colorRangeText());

                    //如果小圆点显示就隐藏
                    if (((MyViewHolder) holder).currDayImageView.getVisibility() == View.VISIBLE) {
                        ((MyViewHolder) holder).currDayImageView.setVisibility(View.INVISIBLE);
                    }

                    //动态修改颜色
                    GradientDrawable myGrad = (GradientDrawable) myViewHolder.iv_today_bg.getBackground();
                    myGrad.setColor(mnCalendarConfig.getMnCalendar_colorStartAndEndBg());
                }

                //判断是不是大于起始日期 在区间内
                if (startDate != null && endDate != null ) {
                    if (datePosition.getTime() > startDate.getTime() && datePosition.getTime() < endDate.getTime()) {
                        myViewHolder.iv_today_bg.setVisibility(View.VISIBLE);
                        myViewHolder.iv_today_bg.setBackgroundResource(R.drawable.mn_selected_bg_centre);
                        myViewHolder.tvDay.setTextColor(mnCalendarConfig.getMnCalendar_colorRangeText());
                        myViewHolder.tvDay_lunar.setTextColor(mnCalendarConfig.getMnCalendar_colorRangeText());

                        //动态修改颜色
                        GradientDrawable myGrad = (GradientDrawable) myViewHolder.iv_today_bg.getBackground();

                        if (datePosition.getMonth() != currentShowDate.getMonth() && !mnCalendarConfig.isMnCalendar_showOtherMonthInfo()) {
                            myGrad.setColor(mnCalendarConfig.getMnCalendar_colorBgCalendar());
                        } else {
                            myGrad.setColor(mnCalendarConfig.getMnCalendar_colorRangeBg());
                        }
                    }
                }


            }

            if (this.onCalendarItemClickListener != null && mnCalendarConfig.isMnCalendar_calendarClickable()) {
                myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Date currentShowDate = currentShowCalendar.getTime();
                        Date datePosition = mDatas.get(position);

                        //必须大于今天
                        if (!mnCalendarConfig.isMnCalendar_canSelectDayBeforeNow() && datePosition.getTime() < currentDateZero.getTime()) {
                            Toast.makeText(context, R.string.prompt_choose_error, Toast.LENGTH_SHORT).show();
                            return;
                        }

                        //当前月或者显示其他月的信息
                        if (currentShowDate.getMonth() == datePosition.getMonth() || mnCalendarConfig.isMnCalendar_showOtherMonthInfo()) {

                            switch (mnCalendarConfig.getMnCalendar_chooseType()) {
                                case MNCalendarConfig.DATE_CHOOSE_TYPE_SINGLE:

                                    onCalendarItemClickListener.onSingleChoose(datePosition);

                                    notifyItemChanged(position);
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

                                    if (startDate != null && endDate != null) {
                                        startDate = null;
                                        endDate = null;

                                        notifyDataSetChanged();
                                    }
                                    if (startDate == null) {
                                        startDate = datePosition;
                                        notifyItemChanged(position);
                                    } else {
                                        //判断结束位置是不是大于开始位置
                                        long dateClickTime = datePosition.getTime();
                                        long dateStartTime = startDate.getTime();
                                        if (dateClickTime <= dateStartTime) {
                                            int startPosition = mDatas.indexOf(startDate);
                                            if (startPosition == -1)
                                                startPosition = 0;

                                            startDate = datePosition;

                                            notifyItemChanged(startPosition);
                                            notifyItemChanged(position);
                                        } else {
                                            endDate = datePosition;
                                            onCalendarItemClickListener.onRangeChoose(startDate, endDate);

                                            int startPosition = mDatas.indexOf(startDate);
                                            if (startPosition == -1)
                                                startPosition = 0;
                                            notifyItemRangeChanged(startPosition, position - startPosition + 1);

                                        }
                                    }

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
        private RelativeLayout rlContent;

         MyViewHolder(View itemView) {
            super(itemView);
            tvDay = (TextView) itemView.findViewById(R.id.tvDay);
            tvDay_lunar = (TextView) itemView.findViewById(R.id.tvDay_lunar);
            iv_today_bg = (ImageView) itemView.findViewById(R.id.iv_today_bg);
            currDayImageView = (ImageView) itemView.findViewById(R.id.currDayImageView);
            rlContent = (RelativeLayout) itemView.findViewById(R.id.rlContent);
        }
    }

    public Date getStartRangeDate() {
        return startDate;
    }

    public Date getEndRangeDate() {
        return endDate;
    }

}
