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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArchiveFragment extends Fragment implements SwipeDismissRecyclerViewTouchListener.DismissCallbacks{

    private CustomRecyclerView mRecyclerView;

    private CustomAdapter mAdapter;
    private SparseItemRemoveAnimator mSparseAnimator;
    private List<Picture> picList;
//    private int index =2;

    public ArchiveFragment() {
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
        View homeView = inflater.inflate(R.layout.fragment_archive, container, false);
        picList = new ArrayList<Picture>();
        mAdapter = new CustomAdapter(homeView.getContext(), picList);
        mRecyclerView = (CustomRecyclerView)homeView.findViewById(R.id.image_list_archive);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(homeView.getContext()));
        mRecyclerView.setupSwipeToDismiss(this);
        mSparseAnimator = new SparseItemRemoveAnimator();
        mRecyclerView.getRecyclerView().setItemAnimator(mSparseAnimator);
        mRecyclerView.setAdapter(mAdapter);
        for(int i=0;i<Repo.getInstance().ARCHIVE.size();i++){
            mAdapter.add(new Picture(Repo.getInstance().ARCHIVE.get(i)));
        }

        //mRecyclerView.setRefreshListener(refreshListener);
        //mRecyclerView.setRefreshingColorResources(R.color.customBlue,R.color.customBlue,R.color.customBlue,R.color.customBlue);


        return homeView;
    }

    public SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 停止刷新

//                    mAdapter.insert(new Picture("pic"+index), 0);
//                        mSwipeRefreshLayout.setRefreshing(false);
//                    index =(index + 1) % 20 + 1;
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

            Repo.getInstance().ARCHIVE.add(mAdapter.getPics().get(position).getName());
            mSparseAnimator.setSkipNext(true);
            mAdapter.remove(position);

        }

        //Toast.makeText(getActivity(), R.string.to_archive, Toast.LENGTH_SHORT).show();
    }
}
