package com.tokeninfo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tokeninfo.R;
import com.tokeninfo.ui.bean.TargetBean;

import java.util.List;

public class TargetAdapter extends RecyclerView.Adapter<TargetAdapter.TargetHolder> {

    private List<TargetBean> targetBeanList;

    public TargetAdapter() {
    }

    public void setBeanList(List<TargetBean> list) {
        this.targetBeanList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TargetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View baseView = LayoutInflater.from(context).inflate(R.layout.item_target, parent, false);
        TargetHolder targetHolder = new TargetHolder(baseView);
        return targetHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TargetHolder holder, int position) {
        final TargetBean targetBean = targetBeanList.get(position);
        String showTxt = targetBean.getSymbol() + "      " + targetBean.getPrice();
        holder.symbolTxt.setText(showTxt);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                callBack.remove(targetBean);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        int count = targetBeanList == null ? 0 : targetBeanList.size();
        return count;
    }

    protected static class TargetHolder extends RecyclerView.ViewHolder {

        TextView symbolTxt;

        public TargetHolder(@NonNull View itemView) {
            super(itemView);
            symbolTxt = itemView.findViewById(R.id.txt_symbol);
        }
    }


    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public interface CallBack{
        void remove( TargetBean bean);
    }
}
