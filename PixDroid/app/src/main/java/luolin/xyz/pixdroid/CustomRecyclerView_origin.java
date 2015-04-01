package luolin.xyz.pixdroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Created by yx on 2015/4/1.
 */
public class CustomRecyclerView_origin extends RecyclerView {

    private OnScrollListener mSwipeDismissScrollListener;

    public CustomRecyclerView_origin(Context context) {
        super(context);
    }

    public void setupSwipeToDismiss(final SwipeDismissRecyclerViewTouchListener.DismissCallbacks listener) {
        SwipeDismissRecyclerViewTouchListener touchListener =
                new SwipeDismissRecyclerViewTouchListener(this, new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(int position) {
                        return listener.canDismiss(position);
                    }

                    @Override
                    public void onDismiss(RecyclerView recyclerView, int[] reverseSortedPositions) {
                        listener.onDismiss(recyclerView, reverseSortedPositions);
                    }
                });
        mSwipeDismissScrollListener = touchListener.makeScrollListener();
        this.setOnTouchListener(touchListener);
    }
}
