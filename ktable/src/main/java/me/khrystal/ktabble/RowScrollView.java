package me.khrystal.ktabble;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/4/19
 * update time:
 * email: 723526676@qq.com
 */
public class RowScrollView extends HorizontalScrollView{

    TableScrollView tableScrollView;

    public RowScrollView(Context context) {
        super(context);
        initView();
    }

    public RowScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RowScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {}

    public void setTableScrollView(TableScrollView tableScrollView){
        this.tableScrollView = tableScrollView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        tableScrollView.currentRow = this;
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (tableScrollView.currentRow==this){
            tableScrollView.onScrollChanged(l,t,oldl,oldt);
        }else {
            super.onScrollChanged(l, t, oldl, oldt);
        }
    }
}
