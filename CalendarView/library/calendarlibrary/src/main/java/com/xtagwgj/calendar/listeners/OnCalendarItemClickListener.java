package com.xtagwgj.calendar.listeners;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by maning on 2017/5/9.
 */

public interface OnCalendarItemClickListener {

    void onSingleChoose(Date date);

    void onMultiChoose(ArrayList<Date> dateArrayList);

    void onRangeChoose(Date startDate, Date endDate);
}
