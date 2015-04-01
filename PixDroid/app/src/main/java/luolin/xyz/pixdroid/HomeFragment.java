package luolin.xyz.pixdroid;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import luolin.xyz.pixdroid.model.Picture;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private CustomAdapter mAdapter;
    private List<Picture> picList = new ArrayList<Picture>();
    private String[] picNames = { "p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8" };

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        picList.add(new Picture("p1"));
        picList.add(new Picture("p2"));
        picList.add(new Picture("p3"));
        picList.add(new Picture("p4"));
        picList.add(new Picture("p5"));
       // picList.add(new Picture("p6"));
      //  picList.add(new Picture("p7"));
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = (RecyclerView) homeView.findViewById(R.id.image_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity().getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new CustomAdapter(this.getActivity(), picList);
        mRecyclerView.setAdapter(mAdapter);


        mSwipeRefreshLayout = (SwipeRefreshLayout) homeView.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(refreshListener);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.customBlue);
        mSwipeRefreshLayout.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        //mSwipeRefreshLayout.setProgressBackgroundColor(R.color.red);
//        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        return homeView;
    }

    public SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 停止刷新
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        };


}
