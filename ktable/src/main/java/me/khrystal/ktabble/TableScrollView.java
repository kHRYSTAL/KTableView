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
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.khrystal.ktabble.helper.ItemTouchHelperAdapter;
import me.khrystal.ktabble.helper.ItemTouchHelperViewHolder;
import me.khrystal.ktabble.helper.OnStartDragListener;
import me.khrystal.ktabble.helper.SimpleItemTouchHelperCallback;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/4/19
 * update time:
 * email: 723526676@qq.com
 */
public class TableScrollView extends LinearLayout implements OnStartDragListener{

    public static volatile RowScrollView currentRow;
    public RowScrollView headerRow;
    protected List<RowScrollView> mRows = new ArrayList<>();
    private RecyclerView rowsContainer;
    private TextView leftTopText;
    private LinearLayout colsNamesContainer;
    private List<List<String>> mDatas;
    private ItemTouchHelper mItemTouchHelper;
    private OnRowsSwapListener onRowsSwapListener;

    public TableScrollView(Context context) {
        super(context);
        initView(context);
    }

    public TableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mDatas = new ArrayList<>();
        View.inflate(context,R.layout.table_layout,this);
        leftTopText = (TextView)findViewById(R.id.table_left_top_text);
        colsNamesContainer = (LinearLayout) findViewById(R.id.table_cols_title);
        rowsContainer = (RecyclerView)findViewById(R.id.table_rows);
        headerRow = (RowScrollView)findViewById(R.id.table_cols_header);
        headerRow.setTableScrollView(this);
        rowsContainer.setHasFixedSize(true);
        rowsContainer.setLayoutManager(new LinearLayoutManager(context));
    }

    public void setLeftTopText(CharSequence charSequence,int width,int height){
        LinearLayout.LayoutParams params = null;
        if (width>0&&height>0)
            params = new LayoutParams(width, height);
        else
            params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftTopText.setLayoutParams(params);

        if (leftTopText != null) {
            leftTopText.setText(charSequence);
        }
    }

    public void setColsNames(List<String> colNames,int colWidth,int rowHeight){
        if (colNames==null){
            Log.e("TabScrollView","list of colsNames is null!");
            return;
        }
        for (int i = 0; i < colNames.size(); i++) {
            TextView col = new TextView(getContext());

            LinearLayout.LayoutParams params = null;
            if (colWidth>0&&rowHeight>0)
                params = new LayoutParams(colWidth,rowHeight);
            else
                params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            col.setLayoutParams(params);
            col.setGravity(Gravity.CENTER);
            col.setText(colNames.get(i));
            colsNamesContainer.addView(col);
        }
    }

    /**
     * eg.
     * List<Map<String,String>> list = new ArrayList<>();
     * //rows length is 10
     * for(i:9){
     * Map<String,String> map = new HashMap<>();
     * //columns length is 10
     *  for(j:9){
     *     map.put("rows_"+i+"cols_"+j,"j");
     *  }
     *  list.add(map);
     *}
     *
     * Map<String,String> map= new HashMap();
     * map
     *
     * @param columnLength
     * @param datas
     */
    public void setTableData(int columnLength,int columnWidth,int rowHeight,List<List<String>> datas){
        if (mDatas.size()>0){
            mDatas.clear();
        }
        mDatas.addAll(datas);

        TableAdapter adapter = new TableAdapter(columnLength,columnWidth,rowHeight,this);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(rowsContainer);
        rowsContainer.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        for( RowScrollView scrollView : mRows) {
            if(currentRow != scrollView)
                scrollView.smoothScrollTo(l, t);
        }
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    public void addRowScrollView(final RowScrollView rowScrollView) {
        if(!mRows.isEmpty()) {
            int size = mRows.size();
            RowScrollView scrollView = mRows.get(size - 1);
            final int scrollX = scrollView.getScrollX();
            if(scrollX != 0) {
                rowsContainer.post(new Runnable() {
                    @Override
                    public void run() {
                        rowScrollView.scrollTo(scrollX, 0);
                    }
                });
            }
        }
        mRows.add(rowScrollView);
    }


    class TableAdapter extends RecyclerView.Adapter<TableViewHolder>
            implements ItemTouchHelperAdapter{

        private int mColumnLength;
        private int mColumnWidth;
        private int mRowHeight;
        private final OnStartDragListener mDragStartListener;

        public TableAdapter(int columnLength,int columnWidth,int rowHeight,OnStartDragListener dragStartListener){
            mColumnLength = columnLength;
            mColumnWidth = columnWidth;
            mRowHeight = rowHeight;
            mDragStartListener = dragStartListener;
        }

        @Override
        public TableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.tab_row_item,parent,false);
            if (itemView!=null){
                RowScrollView viewById = (RowScrollView) itemView.findViewById(R.id.row_scroller);
                viewById.setTableScrollView(TableScrollView.this);
                addRowScrollView(viewById);
            }
            TableViewHolder holder = new TableViewHolder(itemView);
            return holder;
        }

        @Override
        public void onBindViewHolder(final TableViewHolder holder, final int position) {
            List<String> data = mDatas.get(position);

            holder.rowTitle.setText(TextUtils.isEmpty(data.get(0)) ?
                    "":data.get(0));
            LinearLayout.LayoutParams params = null;
            if (mColumnWidth>0&&mRowHeight>0)
                params = new LayoutParams(mColumnWidth, mRowHeight);
            else
                params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.rowTitle.setLayoutParams(params);
            for (int i = 1; i < mColumnLength; i++) {
                TextView col = new TextView(getContext());
                col.setLayoutParams(params);
                col.setGravity(Gravity.CENTER);
                final String text = TextUtils.isEmpty(data.get(i))?"":data.get(i);
                col.setText(text);
                final int column = i-1;
                holder.rowDataContainer.addView(col);
            }

            // Start a drag whenever the handle view it touched
            holder.rowDataContainer.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                        mDragStartListener.onStartDrag(holder);
                    }
                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            Collections.swap(mDatas, fromPosition, toPosition);
            notifyItemMoved(fromPosition, toPosition);
            if (onRowsSwapListener!=null)
                onRowsSwapListener.onRowsSwap(fromPosition,toPosition);
            return true;
        }

        @Override
        public void onItemDismiss(int position) {

        }
    }

    class TableViewHolder extends RecyclerView.ViewHolder
        implements ItemTouchHelperViewHolder{

        TextView rowTitle;
        LinearLayout rowDataContainer;

        public TableViewHolder(View itemView) {
            super(itemView);
            rowTitle = (TextView) itemView.findViewById(R.id.row_title);
            rowDataContainer = (LinearLayout)itemView.findViewById(R.id.row_data_container);
        }

        @Override
        public void onItemSelected() {

        }

        @Override
        public void onItemClear() {

        }
    }


    public void setOnRowsSwapListener(OnRowsSwapListener listener){
        this.onRowsSwapListener = listener;
    }

    public interface OnRowsSwapListener{
        void onRowsSwap(int fromPosition,int toPosition);
    }
}
