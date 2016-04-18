package me.khrystal.ktabble.helper;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/4/19
 * update time:
 * email: 723526676@qq.com
 */

/**
 * Interface to listen for a move or dismissal event from a {@link android.support.v7.widget.helper.ItemTouchHelper.Callback}.
 *
 * @author Paul Burke (ipaulpro)
 */
public interface ItemTouchHelperAdapter {
    /**
     * Called when an item has been dragged far enough to trigger a move. This is called every time
     * an item is shifted, and <strong>not</strong> at the end of a "drop" event.<br/>
     * <br/>
     * Implementations should call {@link android.support.v7.widget.RecyclerView.Adapter#notifyItemMoved(int, int)} after
     * adjusting the underlying data to reflect this move.
     *
     * @param fromPosition The start position of the moved item.
     * @param toPosition   Then resolved position of the moved item.
     * @return True if the item was moved to the new adapter position.

     */
    boolean onItemMove(int fromPosition, int toPosition);


    /**
     * Called when an item has been dismissed by a swipe.<br/>
     * <br/>
     * Implementations should call {@link android.support.v7.widget.RecyclerView.Adapter#notifyItemRemoved(int)} after
     * adjusting the underlying data to reflect this removal.
     *
     * @param position The position of the item dismissed.
     */
    void onItemDismiss(int position);
}
