package me.khrystal.ktabble.helper;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/4/19
 * update time:
 * email: 723526676@qq.com
 */

import android.support.v7.widget.RecyclerView;

/**
 * Listener for manual initiation of a drag.
 */
public interface OnStartDragListener {

    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);

}
