package com.ys.yoosir.zzshow.mvp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ys.yoosir.zzshow.MyApplication;
import com.ys.yoosir.zzshow.R;
import com.ys.yoosir.zzshow.mvp.modle.toutiao.ArticleData;
import com.ys.yoosir.zzshow.mvp.ui.adapters.listener.MyRecyclerListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 帖子列表适配器
 * Created by Yoosir on 2016/10/19 0019.
 */
public class PostListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private MyRecyclerListener mItemListener;
    private List<ArticleData>   mPostList;

    public PostListAdapter(MyRecyclerListener itemListener, List<ArticleData> postList){
        this.mItemListener = itemListener;
        this.mPostList = postList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_post_list_item, parent, false),mItemListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PostViewHolder mHolder = (PostViewHolder) holder;
        ArticleData data = mPostList.get(position);
        String imgPath = data.getImage_list().get(1).getUrl();
        //TODO show iv
        Glide.with(MyApplication.getInstance())
                .load(imgPath)
                .placeholder(R.color.image_place_holder)
                .error(R.mipmap.ic_load_fail)
                .into(mHolder.postImgIv);

        mHolder.postImgCountTv.setText(mHolder.itemView.getContext().getString(R.string.post_img_count_value,data.getGallary_image_count()));
        mHolder.postTitleTv.setText(data.getTitle());
    }

    @Override
    public int getItemCount() {
        return mPostList == null ? 0 : mPostList.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.post_img_iv)
        ImageView postImgIv;

        @BindView(R.id.post_img_count_tv)
        TextView postImgCountTv;

        @BindView(R.id.post_title_tv)
        TextView postTitleTv;

        public PostViewHolder(View itemView, final MyRecyclerListener itemListener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemListener.OnItemClickListener(v,getAdapterPosition());
                }
            });
            ButterKnife.bind(this,itemView);
        }
    }
}