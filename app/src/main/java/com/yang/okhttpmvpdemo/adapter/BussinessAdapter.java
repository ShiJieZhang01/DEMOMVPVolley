package com.yang.okhttpmvpdemo.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.widget.RecyclerView;
import android.util.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yang.okhttpmvpdemo.R;
import com.yang.okhttpmvpdemo.util.BusinessBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 17/4/21.
 */

public class BussinessAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_LOADING_MORE = -1;
    private static final int NOMAL_ITEM = 1;
    private Context mContext;
    private boolean showLoadingMore;

    private List<BusinessBean.Business> bussinesslist = new ArrayList<>();

    public BussinessAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case NOMAL_ITEM:
                return new TopNewsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_bussiness, parent, false));

            case TYPE_LOADING_MORE:
                return new LoadingMoreHolder(LayoutInflater.from(mContext).inflate(R.layout.infinite_loading, parent, false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type) {
            case NOMAL_ITEM:
                bindViewHolderNormal((TopNewsViewHolder) holder, position);
                break;
            case TYPE_LOADING_MORE:
                bindLoadingViewHold((LoadingMoreHolder) holder, position);
                break;
        }
    }

    private void bindLoadingViewHold(LoadingMoreHolder holder, int position) {
        holder.progressBar.setVisibility(showLoadingMore ? View.VISIBLE : View.INVISIBLE);
    }

    private void bindViewHolderNormal(final TopNewsViewHolder holder, final int position) {

        final BusinessBean.Business newsBeanItem = bussinesslist.get(holder.getAdapterPosition());

        holder.textView.setTextColor(Color.BLACK);
        holder.sourceTextview.setTextColor(Color.BLACK);
        holder.textView.setText(newsBeanItem.getName());
        holder.sourceTextview.setText(newsBeanItem.getAddress());
    }

    public void addItems(List<BusinessBean.Business> list) {

        // 移除第一个数据，因为此数据不符合我们的条件
        int n = list.size();
        bussinesslist.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < getDataItemCount()
                && getDataItemCount() > 0) {

            return NOMAL_ITEM;
        }
        return TYPE_LOADING_MORE;
    }
    private int getDataItemCount() {

        return bussinesslist.size();
    }
    private int getLoadingMoreItemPosition() {
        return showLoadingMore ? getItemCount() - 1 : RecyclerView.NO_POSITION;
    }

    public void loadingStart() {
        if (showLoadingMore) return;
        showLoadingMore = true;
        notifyItemInserted(getLoadingMoreItemPosition());
    }

    public void loadingfinish() {
        if (!showLoadingMore) return;
        final int loadingPos = getLoadingMoreItemPosition();
        showLoadingMore = false;
        notifyItemRemoved(loadingPos);
    }

    public void clearData() {
        bussinesslist.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return bussinesslist.size();
    }
    public static class LoadingMoreHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public LoadingMoreHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView;
        }
    }

    static class TopNewsViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;
        final TextView sourceTextview;
        ImageView imageView;

        TopNewsViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_text);
            textView = (TextView) itemView.findViewById(R.id.tv_text1);
            sourceTextview = (TextView) itemView.findViewById(R.id.tv_text2);
        }
    }
}
