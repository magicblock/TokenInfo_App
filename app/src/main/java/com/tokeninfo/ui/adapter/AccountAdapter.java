package com.tokeninfo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tokeninfo.R;
import com.tokeninfo.ui.bean.AccountBeean;
import com.tokeninfo.util.MathUtil;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {

    private Context context;
    private List<AccountBeean> accountBeeanList;

    public void setData(List<AccountBeean> beeans) {
        this.accountBeeanList = beeans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_account, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AccountBeean accountBeean = accountBeeanList.get(position);

        holder.symbolTxt.setText(accountBeean.getSymbol().toUpperCase());
        holder.totalTxt.setText(String.valueOf(MathUtil.decimail(accountBeean.getTotal(), 3)));
        holder.usedTxt.setText(String.valueOf(accountBeean.getBuy()));
        holder.rateTxt.setText(String.valueOf(accountBeean.getRate()));
    }

    @Override
    public int getItemCount() {
        return accountBeeanList == null ? 0 : accountBeeanList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        TextView symbolTxt;
        TextView totalTxt;
        TextView usedTxt;
        TextView rateTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            symbolTxt = itemView.findViewById(R.id.txt_symbol);
            totalTxt = itemView.findViewById(R.id.txt_account);
            usedTxt = itemView.findViewById(R.id.txt_used);
            rateTxt = itemView.findViewById(R.id.txt_profit_rate);
        }
    }
}
