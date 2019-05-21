package com.tokeninfo.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tokeninfo.R;
import com.tokeninfo.ui.bean.RecordBean;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    private Activity activity;
    private List<RecordBean> recordBeanList;

    public RecordAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(List<RecordBean> records) {
        this.recordBeanList = records;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.item_record, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecordBean recordBean = recordBeanList.get(position);

        holder.symbolTxt.setText(recordBean.getSymbol());
        holder.timeTxt.setText(recordBean.getCreateTime());
        holder.operationTxt.setText(recordBean.getOpetation() == 1 ? "[买入]" : "[卖出]");
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
