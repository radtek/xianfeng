package com.qingyii.hxt.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyii.hxt.R;
import com.qingyii.hxt.fragment.SectionLevelFragment;
import com.qingyii.hxt.http.MyApplication;
import com.qingyii.hxt.pojo.RankingList;

import java.util.List;

/**
 * Created by XRJ on 16/11/7.
 */

public class SectionLevelAdapter extends RecyclerView.Adapter<SectionLevelAdapter.ViewHolder> {
    private SectionLevelAdapter.ViewHolder viewHolder;
    private Context context;
    private List<RankingList.DataBean> rDataBeanList;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public SectionLevelAdapter(Context context, List<RankingList.DataBean> rDataBeanList) {
        this.context = context;
        this.rDataBeanList = rDataBeanList;
    }

    public void addItems(List<String> uList) {
        uList.addAll(uList);
    }

    @Override
    public SectionLevelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_section_level_item, parent, false);
        viewHolder = new SectionLevelAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
                }
            });
        }
        RankingList.DataBean rDataBean = rDataBeanList.get(position);

        holder.section_level_item_ranking.setText(position + 1 + "");
        holder.section_level_item_name.setText(rDataBean.getTruename() + "");
        holder.section_level_item_section.setText(rDataBean.getDepartment() + "");
        holder.section_level_item_integral.setText(rDataBean.getScore() + "");
        if (rDataBean.getId() == MyApplication.userUtil.getId()) {
            holder.section_level_item_ranking.setTextColor(Color.RED);
            holder.section_level_item_name.setTextColor(Color.RED);
            holder.section_level_item_section.setTextColor(Color.RED);
            holder.section_level_star1_no.setTextColor(Color.RED);
            holder.section_level_item_integral.setTextColor(Color.RED);
        } else {
            holder.section_level_item_ranking.setTextColor(Color.BLACK);
            holder.section_level_item_name.setTextColor(Color.BLACK);
            holder.section_level_item_section.setTextColor(Color.BLACK);
            holder.section_level_star1_no.setTextColor(Color.BLACK);
            holder.section_level_item_integral.setTextColor(Color.BLACK);
        }

        int i = 0;
//        Log.e("列表星级：", rDataBean.getStars() + "");
        for (; i < rDataBean.getStars(); i++)
            holder.section_level_star1[i].setVisibility(View.VISIBLE);

        for (; i < holder.section_level_star1.length; i++)
            holder.section_level_star1[i].setVisibility(View.GONE);

        //无星标识显示
        if (rDataBean.getStars() > 0) {
            holder.section_level_star1_no.setVisibility(View.GONE);
            holder.unit_buhege.setVisibility(View.GONE);
        }else {

            if (rDataBean.getPunishtype().equals("red")){ //不合格
                holder.unit_buhege.setVisibility(View.VISIBLE);
            }else {

                holder.section_level_star1_no.setVisibility(View.VISIBLE);
                holder.unit_buhege.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return rDataBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView section_level_item_ranking;
        private TextView section_level_item_name;
        private TextView section_level_item_section;
        private ImageView section_level_star1[] = new ImageView[5];
        private TextView section_level_star1_no;
        private TextView section_level_item_integral;
        private ImageView unit_buhege;

        public ViewHolder(View itemView) {
            super(itemView);
            section_level_item_ranking = (TextView) itemView.findViewById(R.id.section_level_item_ranking);
            section_level_item_name = (TextView) itemView.findViewById(R.id.section_level_item_name);
            section_level_item_section = (TextView) itemView.findViewById(R.id.section_level_item_section);
            section_level_star1[0] = (ImageView) itemView.findViewById(R.id.section_level_star1);
            section_level_star1[1] = (ImageView) itemView.findViewById(R.id.section_level_star2);
            section_level_star1[2] = (ImageView) itemView.findViewById(R.id.section_level_star3);
            section_level_star1[3] = (ImageView) itemView.findViewById(R.id.section_level_star4);
            section_level_star1[4] = (ImageView) itemView.findViewById(R.id.section_level_star5);
            section_level_star1_no = (TextView) itemView.findViewById(R.id.section_level_star1_no);
            unit_buhege = (ImageView)itemView.findViewById(R.id.unit_buhege);
            section_level_item_integral = (TextView) itemView.findViewById(R.id.section_level_item_integral);
        }
    }
}
