package com.xtagwgj.calendar.listeners;

import java.util.ArrayList;
import java.util.Date;

/**
 * 日历点击监听
 * Created by xtagwgj on 2017/5/9.
 */

public interface OnCalendarItemClickListener {

    void onSingleChoose(Date date);

    void onMultiChoose(ArrayList<Date> dateArrayList);

    void onRangeChoose(Date startDate, Date endDate);
}
