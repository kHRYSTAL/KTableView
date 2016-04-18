package me.khrystal.ktabble.helper;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/4/19
 * update time:
 * email: 723526676@qq.com
 */
/**
 * Interface to notify an item ViewHolder of relevant callbacks from {@link
 * android.support.v7.widget.helper.ItemTouchHelper.Callback}.
 *
 * @author Paul Burke (ipaulpro)
 */
public interface ItemTouchHelperViewHolder {

    /**
     * Called when the {@link android.support.v7.widget.helper.ItemTouchHelper} first registers an item as being moved or swiped.
     * Implementations should update the item view to indicate it's active state.
     */
    void onItemSelected();


    /**
     * Called when the {@link android.support.v7.widget.helper.ItemTouchHelper} has completed the move or swipe, and the active item
     * state should be cleared.
     */
    void onItemClear();
}
