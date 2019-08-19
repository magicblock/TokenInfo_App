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

import java.util.List;

public class MarginRecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

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
    public RecordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_record, parent, false);
        RecordAdapter.ViewHolder holder = new RecordAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.ViewHolder holder, int position) {
        RecordBean recordBean = recordBeanList.get(position);

        holder.symbolTxt.setText(recordBean.getSymbol());
        holder.timeTxt.setText(recordBean.getCreateTime());
        if (recordBean.getOperation() == 1) {
            holder.operationTxt.setText("[开多]");
            holder.operationTxt.setBackgroundColor(context.getResources().getColor(R.color.color_blue));
        } else if (recordBean.getOperation() == 2) {
            holder.operationTxt.setText("[开空]");
            holder.operationTxt.setBackgroundColor(context.getResources().getColor(R.color.color_blue));
        } else {
            holder.operationTxt.setText("[平仓]");
            holder.operationTxt.setBackgroundColor(context.getResources().getColor(R.color.color_red));
        }

        holder.profitTxt.setText(recordBean.getTotal() + "");
        holder.explainTxt.setText(recordBean.getExplain());
    }

    @Override
    public int getItemCount() {
        return recordBeanList == null ? 0 : recordBeanList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        TextView symbolTxt;
        TextView timeTxt;
        TextView operationTxt;
        TextView profitTxt;
        TextView explainTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            symbolTxt = itemView.findViewById(R.id.txt_symbol);
            timeTxt = itemView.findViewById(R.id.txt_time);
            operationTxt = itemView.findViewById(R.id.txt_operation);
            profitTxt = itemView.findViewById(R.id.txt_profit);
            explainTxt = itemView.findViewById(R.id.txt_explain);
        }
    }
}