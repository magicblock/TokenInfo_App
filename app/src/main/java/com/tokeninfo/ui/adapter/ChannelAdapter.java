package com.tokeninfo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tokeninfo.R;
import com.tokeninfo.ui.bean.ChannelBean;
import com.tokeninfo.util.MathUtil;

import java.util.List;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ViewHolder> {

    private Context context;
    private List<ChannelBean> channelBeanList;

    public void setData(List<ChannelBean> beans) {
        this.channelBeanList = beans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_channel, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChannelBean channelBean = channelBeanList.get(position);

        holder.symbolTxt.setText(channelBean.getSymbol().toUpperCase());
        holder.OpTxt.setText(OpToString(channelBean.getOp()));
        if (channelBean.getOp() == 1) {
            holder.OpTxt.setTextColor(context.getResources().getColor(R.color.color_DE7A74));
        } else if (channelBean.getOp() == 2) {
            holder.OpTxt.setTextColor(context.getResources().getColor(R.color.color_80BB8B));
        }

        holder.shortTxt.setText(String.valueOf(MathUtil.decimail(channelBean.getLow(), 3)));
        holder.longTxt.setText(String.valueOf(MathUtil.decimail(channelBean.getHigh(), 3)));
        holder.addTxt.setText(String.valueOf(MathUtil.decimail(channelBean.getAdd(), 3)));
        holder.stopTxt.setText(String.valueOf(MathUtil.decimail(channelBean.getStop(), 3)));
        holder.priceTxt.setText("$ " + MathUtil.decimail(channelBean.getPrice(), 3));
    }

    String OpToString(int op) {
        String string = "";
        switch (op) {
            case 1:
                string = context.getString(R.string.op_long);
                break;
            case 2:
                string = context.getString(R.string.op_short);
                break;
            case 3:
                break;
            case 4:
                break;
        }
        return string;
    }


    @Override
    public int getItemCount() {
        return channelBeanList == null ? 0 : channelBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView symbolTxt;
        TextView OpTxt;
        TextView shortTxt;
        TextView longTxt;
        TextView addTxt;
        TextView stopTxt;
        TextView priceTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            symbolTxt = itemView.findViewById(R.id.txt_symbol);
            OpTxt = itemView.findViewById(R.id.txt_operation);
            shortTxt = itemView.findViewById(R.id.txt_short);
            longTxt = itemView.findViewById(R.id.txt_long);
            addTxt = itemView.findViewById(R.id.txt_add);
            stopTxt = itemView.findViewById(R.id.txt_stop);
            priceTxt = itemView.findViewById(R.id.txt_price);
        }
    }
}
