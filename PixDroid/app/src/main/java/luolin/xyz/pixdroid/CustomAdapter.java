package luolin.xyz.pixdroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuyuanwei on 2015/3/30.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<Picture> pics;
    private Context mContext;
    private Picture picTemp;
    public CustomAdapter(Context con, List<Picture> p) {
        this.mContext = con;
        this.pics = p;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        Picture pic = pics.get(i);
        picTemp = pic;
        if(Repo.getInstance().LIKE.contains(pic.getName())){
            viewHolder.likeBtn.setTextColor(Color.parseColor("#e91e63"));
        }
        viewHolder.mImageView.setImageResource(pic.getImageResourceId(mContext));
        viewHolder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Repo.getInstance().LIKE.contains(picTemp.getName())){
                    viewHolder.likeBtn.setTextColor(Color.BLACK);
                    Repo.getInstance().LIKE.remove(picTemp.getName());
                    Toast.makeText(v.getContext(), R.string.to_unlike, Toast.LENGTH_SHORT).show();

                }else{
                    Repo.getInstance().LIKE.add(picTemp.getName());
                    Toast.makeText(v.getContext(), R.string.to_like, Toast.LENGTH_SHORT).show();
                    viewHolder.likeBtn.setTextColor(Color.parseColor("#e91e63"));
                }


            }
        });
        viewHolder.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share Picture");
                shareIntent.putExtra(Intent.EXTRA_TEXT,"Do you Like my Picture?");

                shareIntent.setType("text/plain");

                v.getContext().startActivity(Intent.createChooser(shareIntent, "Share Picture"));

            }
        });
    }

    @Override
    public int getItemCount() {
        return pics == null ? 0 : pics.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public Button likeBtn;
        public Button shareBtn;
        public ViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.picture);
            likeBtn = (Button) v.findViewById(R.id.likeBtn);
            shareBtn = (Button) v.findViewById(R.id.shareBtn);
        }
    }

    public void add(Picture picture){
        insert(picture, pics.size());
    }

    public void insert(Picture picture, int position){
        pics.add(position,picture);
        notifyItemInserted(position);
    }

    public void remove(int pos) {
        Log.v("Remove", pics.get(pos).getName());
        pics.remove(pos);

        notifyItemRemoved(pos);
    }

    public List<Picture> getPics(){
        return this.pics;
    }


}
