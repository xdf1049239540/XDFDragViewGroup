package com.example.xdf.xdfdragviewgroup;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xdf on 2017/7/25.
 */

public class DragViewGrop extends ViewGroup {
    private List<ViewModel> viewModels = new ArrayList<>();
    private View mSelectedChild;
    public DragViewGrop(Context context) {
        super(context);
    }

    public DragViewGrop(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragViewGrop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DragViewGrop(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int iWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int iHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int iWidthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int iHeightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child_view = getChildAt(i);
            measureChild(child_view, iWidthMode, iHeightMode);

        }

        setMeasuredDimension(iWidthSpecSize, iHeightSpecSize);
    }

    boolean change = false;

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        change = b;
        if (b) {
            int childCount = getChildCount();
            int left = 0;
            int top = 0;
            int right = 0;
            int bottom = 0;
            for (int j = 0; j < childCount; j++) {
                View child_view = getChildAt(j);
                Rect rect = new Rect();
                bottom = 300;
                left += 300 * j;
                right = left + 300;
                top = 0;
                rect.set(left, top, right, bottom);
                Log.i("ssss:" + j, "left:" + rect.left + "top:" + rect.top + "right:" + rect.right + "bottom:" + rect.bottom);
                child_view.layout(rect.left, rect.top, rect.right, rect.bottom);
            }
        }

    }
    /**
     *判断点击是哪个view
     */
    private View findChildViewInsideTouch(int x, int y) {
        for(int i=getChildCount()-1; i>=0; i--) {
            View view = getChildAt(i);
            Rect rect = new Rect();
            view.getHitRect(rect);
            if (rect.contains(x, y)) {
                return view;
            }
        }
        return null;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mSelectedChild=findChildViewInsideTouch(Math.round(x), Math.round(y));
            case MotionEvent.ACTION_MOVE:
                LayoutParams lp = (LayoutParams) mSelectedChild.getLayoutParams();
                float dx=event.getX()-x;
                float dy=event.getY()-y;
                //mSelectedChild.layout(lp.left, t, r, b);
                invalidate();
                break;
        }
        return true;
    }
}
