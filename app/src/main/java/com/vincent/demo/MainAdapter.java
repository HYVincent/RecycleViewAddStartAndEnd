package com.vincent.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.List;

/**
 * description ：
 * project name：CCloud
 * author : Vincent
 * creation date: 2017/5/4 17:10
 *
 * @version 1.0
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Entity> data;
    private Context context;
    private HeadListener headListener;
    private OtherListener otherListener;
    private ButtomListener buttomListener;

    private int BUTTOM = 110;//底部的
    private int OTHER = 111;//中间的
    private int HEAD = 112;//   头部的

    private int HeadItem = 1;//头部的那个
    private int ButtomItem=1;//底部的item

    int opened = -1;


    public MainAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Entity> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setHeadClickListener(HeadListener listener) {
        this.headListener = listener;
    }

    public void setOtherListener(OtherListener otherListener) {
        this.otherListener = otherListener;
    }

    public void setButtomListener(ButtomListener buttomListener) {
        this.buttomListener = buttomListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        RecyclerView.ViewHolder holder = null;
        if(viewType == BUTTOM){
            holder = new ButtomViewHolder(LayoutInflater.from(context).inflate(R.layout.item_buttom,parent,false),buttomListener);
        }else if(viewType == HEAD){
            holder = new HeadViewHolder(LayoutInflater.from(context).inflate(R.layout.item_head,parent,false),headListener);
        }else if(viewType == OTHER){
            holder = new OtherViewHolder(LayoutInflater.from(context).inflate(R.layout.item_other,parent,false),otherListener);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeadViewHolder){
            HeadViewHolder holderB = (HeadViewHolder) holder;
            //TODO ....
        }else if(holder instanceof OtherViewHolder){
            OtherViewHolder otherViewHolder = (OtherViewHolder) holder;
            ((OtherViewHolder) holder).title.setText(data.get(position - HeadItem).title);
            ((OtherViewHolder) holder).content.setText(data.get(position-HeadItem).content);
            ((OtherViewHolder) holder).bind(position);
            //TODO ...
        }else if(holder instanceof ButtomViewHolder){
            ButtomViewHolder buttomViewHolder = (ButtomViewHolder) holder;
            //TODO ...

        }
    }

    @Override
    public int getItemViewType(int position) {
        int dataItemCount = data.size();
        if (HeadItem != 0 && position < HeadItem) {
            return HEAD;
        } else if (ButtomItem != 0 && position >= (HeadItem + dataItemCount)) {
            return BUTTOM;
        } else {
            return OTHER;
        }
    }


    @Override
    public int getItemCount() {
        return data.size()+HeadItem+ButtomItem;
    }

    class OtherViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView content;
        private RelativeLayout rlTitle;
        private RelativeLayout rlContent;

        public OtherViewHolder(View itemView, final OtherListener l) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.item_title);
            content = (TextView)itemView.findViewById(R.id.item_content);
            rlContent = (RelativeLayout)itemView.findViewById(R.id.rl_content);
            rlTitle = (RelativeLayout)itemView.findViewById(R.id.rl_title);
            rlTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    l.onItemClick(v,getLayoutPosition());
                    if (opened == getLayoutPosition()) {
                        opened = -1;
                        notifyItemChanged(getLayoutPosition());
                    }
                    else {
                        int oldOpened = opened;
                        opened = getLayoutPosition();
                        notifyItemChanged(oldOpened);
                        notifyItemChanged(opened);
                    }
                }
            });
        }

        public void bind(int pos) {
            if (pos == opened) {
                rlContent.setVisibility(View.VISIBLE);
//                Glide.with(context).load(R.mipmap.icon_common_arrow_buttom).animate(R.anim.anim_img_rotate).into(ivArrow);
            }else {
                rlContent.setVisibility(View.GONE);
//                Glide.with(context).load(R.mipmap.icon_common_arrow_right).animate(R.anim.anim_img_rotate).into(ivArrow);
            }
        }

    }

    class ButtomViewHolder extends RecyclerView.ViewHolder{

        public ButtomViewHolder(View itemView, final ButtomListener l) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    l.onItemClick(v,getLayoutPosition());
                }
            });
        }
    }

    class HeadViewHolder extends RecyclerView.ViewHolder{

        public HeadViewHolder(View itemView, final HeadListener l) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    l.onItemClick(v,getLayoutPosition());
                }
            });
        }
    }
}
