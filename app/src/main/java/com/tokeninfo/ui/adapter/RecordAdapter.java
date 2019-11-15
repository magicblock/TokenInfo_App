package com.tokeninfo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tokeninfo.R;
import com.tokeninfo.ui.bean.RecordBean;
import com.tokeninfo.util.MathUtil;
import com.tokeninfo.util.TimeUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    private Context context;
    private List<RecordBean> recordBeanList;

    public void setData(List<RecordBean> records) {
        this.recordBeanList = records;
        notifyDataSetChanged();
    }

    public void appendData(List<RecordBean> records) {
        this.recordBeanList.addAll(records);
        notifyDataSetChanged();
    }

    public RecordBean lastData() {
        RecordBean recordBean = null;
        if (recordBeanList != null && recordBeanList.size() > 0) {
            recordBean = recordBeanList.get(recordBeanList.size() - 1);
        }
        return recordBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_record, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecordBean recordBean = recordBeanList.get(position);

        holder.OpTxt.setText(OpToString(recordBean.getOperation()));
        if (recordBean.getOperation() == 1 || recordBean.getOperation() == 4) {
            holder.OpTxt.setTextColor(context.getResources().getColor(R.color.color_80BB8B));
        } else if (recordBean.getOperation() == 2 || recordBean.getOperation() == 3) {
            holder.OpTxt.setTextColor(context.getResources().getColor(R.color.color_DE7A74));
        }

        holder.symbolTxt.setText(recordBean.getSymbol().toUpperCase());
        holder.timeTxt.setText(TimeUtil.ISO(recordBean.getCreateTime()));
        holder.amountTxt.setText(context.getString(R.string.amount_size, MathUtil.decimail(recordBean.getSize(), 1)));
        holder.priceTxt.setText(context.getString(R.string.price_dollar, MathUtil.decimail(recordBean.getPrice(), 3)));
    }

    String OpToString(int op) {
        String string = "";
        switch (op) {
            case 1:
                string = context.getString(R.string.bug_long);
                break;
            case 2:
                string = context.getString(R.string.bug_short);
                break;
            case 3:
                string = context.getString(R.string.sell_long);
                break;
            case 4:
                string = context.getString(R.string.sell_short);
                break;
        }
        return string;
    }

    @Override
    public int getItemCount() {
        return recordBeanList == null ? 0 : recordBeanList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        TextView OpTxt;
        TextView symbolTxt;
        TextView timeTxt;
        TextView amountTxt;
        TextView priceTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            OpTxt = itemView.findViewById(R.id.txt_operation);
            symbolTxt = itemView.findViewById(R.id.txt_symbol);
            timeTxt = itemView.findViewById(R.id.txt_time);
            amountTxt = itemView.findViewById(R.id.txt_amount);
            priceTxt = itemView.findViewById(R.id.txt_price);
        }
    }
}