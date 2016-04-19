/*
 * Copyright (C) 2016 kHRYSTAL
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
