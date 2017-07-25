package com.example.xdf.xdfdragviewgroup;

import android.graphics.Rect;
import android.view.View;

/**
 * Created by xdf on 2017/7/25.
 */

public class ViewModel {


    public ViewModel(int pos, View view,Rect rect) {
        this.pos = pos;
        this.view = view;
        this.rect=rect;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    private View view;

    private int pos;
    Rect rect;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

}
