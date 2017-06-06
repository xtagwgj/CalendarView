package com.xtagwgj.calendar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

import com.xtagwgj.calendar.model.MNCalendarConfig;

/**
 * Created by xtagwgj on 2017/5/10.
 * 这个类使用了:https://github.com/MorochoRochaDarwin/OneCalendarView
 */

public class MNGestureView extends LinearLayout {

    private boolean canSwap = true;

    //默认横向切换
    private int SWIPE_MODE = MNCalendarConfig.SWIPE_MODE_HOR;

    public MNGestureView(Context context) {
        this(context, null);
    }

    public MNGestureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MNGestureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private static final int mWidth = 500;
    private static final int mHeight = 500;
    private float mDisplacementX;
    private float mDisplacementY;
    private float mInitialTx;
    private float mInitialTy;
    private boolean mTracking;

    private float currentTranslateX;
    private float currentTranslateY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        if (!canSwap)
            return false;

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:

                mDisplacementX = event.getRawX();
                mDisplacementY = event.getRawY();

                mInitialTx = getTranslationX();
                mInitialTy = getTranslationY();
                break;

            case MotionEvent.ACTION_MOVE:
                // get the delta distance in X and Y direction
                float deltaX = event.getRawX() - mDisplacementX;
                float deltaY = event.getRawY() - mDisplacementY;
                // updatePressedState(false);

                // set the touch and cancel event

                if (SWIPE_MODE == MNCalendarConfig.SWIPE_MODE_HOR) {
                    if ((Math.abs(deltaX) > ViewConfiguration.get(getContext())
                            .getScaledTouchSlop() * 2 && Math.abs(deltaY) < Math
                            .abs(deltaX) / 2)
                            || mTracking) {

                        mTracking = true;

                        if (getTranslationX() <= mWidth / 2
                                && getTranslationX() >= -(mWidth / 2)) {

//                            setTranslationX(mInitialTx + deltaX);
                            currentTranslateX = mInitialTx + deltaX;
                            break;
                        }

                    }
                } else if (SWIPE_MODE == MNCalendarConfig.SWIPE_MODE_VER) {
                    if ((Math.abs(deltaY) > ViewConfiguration.get(getContext())
                            .getScaledTouchSlop() * 2 && Math.abs(deltaX) < Math
                            .abs(deltaY) / 2)
                            || mTracking) {

                        mTracking = true;

                        if (getTranslationY() <= mHeight / 2
                                && getTranslationY() >= -(mHeight / 2)) {

//                            setTranslationY(mInitialTy + deltaY);
                            currentTranslateY = mInitialTy + deltaY;
                            break;
                        }

                    }
                }

                break;

            case MotionEvent.ACTION_UP:

                if (mTracking) {
                    mTracking = false;
//                    float currentTranslateX = getTranslationX();
//                    float currentTranslateY = getTranslationY();

                    if (SWIPE_MODE == MNCalendarConfig.SWIPE_MODE_HOR) {
                        if (currentTranslateX > mWidth / 4) {
                            onSwipeListener.rightSwipe();
                        } else if (currentTranslateX < -(mWidth / 4)) {
                            onSwipeListener.leftSwipe();
                        }
                    } else if (SWIPE_MODE == MNCalendarConfig.SWIPE_MODE_VER) {
                        if (currentTranslateY > mHeight / 4) {
                            onSwipeListener.topSwipe();
                        } else if (currentTranslateY < -(mHeight / 4)) {
                            onSwipeListener.bottomSwipe();
                        }
                    }


                    // comment this line if you don't want your frame layout to
                    // take its original position after releasing the touch
                    setTranslationX(0);
                    setTranslationY(0);
                    break;
                } else {
                    // handle click event
                    setTranslationX(0);
                    setTranslationY(0);
                }
                break;
        }
        return false;
    }

    public void setSWIPE_MODE(int SWIPE_MODE) {
        this.SWIPE_MODE = SWIPE_MODE;
    }

    public void setCanSwipe(boolean canSwap) {
        this.canSwap = canSwap;
    }

    public interface OnSwipeListener {
        void rightSwipe();

        void leftSwipe();

        void topSwipe();

        void bottomSwipe();
    }


    private OnSwipeListener onSwipeListener;

    public void setOnSwipeListener(OnSwipeListener onSwipeListener) {
        this.onSwipeListener = onSwipeListener;
    }
}
