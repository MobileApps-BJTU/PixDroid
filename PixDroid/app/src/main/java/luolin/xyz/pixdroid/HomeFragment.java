package luolin.xyz.pixdroid;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements SwipeDismissRecyclerViewTouchListener.DismissCallbacks {

    private CustomRecyclerView mRecyclerView;
//    private SwipeRefreshLayout mSwipeRefreshLayout;
    private CustomAdapter mAdapter;
    private String[] picNames = { "p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8" };
    private SparseItemRemoveAnimator mSparseAnimator;
    private List<Picture> picList;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);
        picList = new ArrayList<Picture>();
        mAdapter = new CustomAdapter(homeView.getContext(), picList);
        mRecyclerView = (CustomRecyclerView)homeView.findViewById(R.id.image_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(homeView.getContext()));
        mRecyclerView.setupSwipeToDismiss(this);
        mSparseAnimator = new SparseItemRemoveAnimator();
        mRecyclerView.getRecyclerView().setItemAnimator(mSparseAnimator);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.add(new Picture("p1"));
        mAdapter.add(new Picture("p2"));
        mRecyclerView.setRefreshListener(refreshListener);
        mRecyclerView.setRefreshingColorResources(R.color.customBlue,R.color.customBlue,R.color.customBlue,R.color.customBlue);

        //mRecyclerView.getRecyclerView().setHasFixedSize(true);
        //mRecyclerView.getRecyclerView().setAdapter(mAdapter);


//        mSwipeRefreshLayout = mRecyclerView.getSwipeToRefresh();
//        mSwipeRefreshLayout.setOnRefreshListener(refreshListener);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.customBlue);
//        mSwipeRefreshLayout.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新
//        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);

       // mRecyclerView.setRefreshListener(refreshListener);






        return homeView;
    }

    public SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 停止刷新
                        mAdapter.insert(new Picture("p5"), 0);
//                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1500);
            }
        };

    @Override
    public boolean canDismiss(int position) {
        return true;
    }

    @Override
    public void onDismiss(RecyclerView recyclerView, int[] reverseSortedPositions) {
        for (int position : reverseSortedPositions) {
            mSparseAnimator.setSkipNext(true);

            mAdapter.remove(position);

        }
        Toast.makeText(getActivity(),R.string.to_archive,Toast.LENGTH_SHORT).show();
    }
}
