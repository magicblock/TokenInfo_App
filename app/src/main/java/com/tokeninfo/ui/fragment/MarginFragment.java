package com.tokeninfo.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tokeninfo.R;
import com.tokeninfo.base.BaseFragment;
import com.tokeninfo.base.BaseResult;
import com.tokeninfo.ui.adapter.AccountAdapter;
import com.tokeninfo.ui.adapter.ChannelAdapter;
import com.tokeninfo.ui.adapter.RecordAdapter;
import com.tokeninfo.ui.bean.AccountBeean;
import com.tokeninfo.ui.bean.ChannelBean;
import com.tokeninfo.ui.bean.RecordBean;
import com.tokeninfo.ui.contract.MarginContract;
import com.tokeninfo.ui.presenter.MarginPresenter;

import java.util.List;

public class MarginFragment extends BaseFragment implements MarginContract.BsView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerview_account)
    RecyclerView recyclerviewAccount;
    @BindView(R.id.recyclerview_channel)
    RecyclerView recyclerviewChannel;
    @BindView(R.id.recyclerview_record)
    RecyclerView recyclerviewRecord;
    @BindView(R.id.swipefresh_layout)
    SwipeRefreshLayout swipefreshLayout;

    public static MarginFragment fragment() {
        MarginFragment fragment = new MarginFragment();
        return fragment;
    }

    Context context;
    View view;
    MarginContract.Presenter presenter;

    AccountAdapter accountAdapter;
    ChannelAdapter channelAdapter;
    RecordAdapter recordAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_margin, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new MarginPresenter(this).start();
    }

    @Override
    public void init() {
        context = getContext();

        swipefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipefreshLayout.setOnRefreshListener(this);

        pull();
    }

    void pull() {
        presenter.account(getActivity(), new BaseResult<List<AccountBeean>>() {

            @Override
            public void success(List<AccountBeean> accountBeeans) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                recyclerviewAccount.setLayoutManager(layoutManager);
                recyclerviewAccount.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
                accountAdapter = new AccountAdapter();
                recyclerviewAccount.setAdapter(accountAdapter);
                accountAdapter.setData(accountBeeans);
            }

            @Override
            public void fail(String string) {

            }
        });

        presenter.channel(getActivity(), new BaseResult<List<ChannelBean>>() {

            @Override
            public void success(List<ChannelBean> channelBeans) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                recyclerviewChannel.setLayoutManager(layoutManager);
                recyclerviewChannel.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
                channelAdapter = new ChannelAdapter();
                recyclerviewChannel.setAdapter(channelAdapter);
                channelAdapter.setData(channelBeans);
            }

            @Override
            public void fail(String string) {

            }
        });

        presenter.records(getActivity(), 0, new BaseResult<List<RecordBean>>() {
            @Override
            public void success(List<RecordBean> recordBeans) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                recyclerviewRecord.setLayoutManager(layoutManager);
                recyclerviewRecord.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
                recordAdapter = new RecordAdapter();
                recyclerviewRecord.setAdapter(recordAdapter);
                recordAdapter.setData(recordBeans);
            }

            @Override
            public void fail(String string) {

            }
        });
    }

    @Override
    public void onRefresh() {
        RecordBean recordBean = recordAdapter.lastData();
        if (recordBean != null) {
            int recordId = recordBean.getId();
            presenter.records(getActivity(), recordId, new BaseResult<List<RecordBean>>() {
                @Override
                public void success(List<RecordBean> recordBeans) {
                    if (recordBeans.size() > 0) {
                        recordAdapter.appendData(recordBeans);
                    }
                }

                @Override
                public void fail(String string) {

                }
            });
        }

        swipefreshLayout.setRefreshing(false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            pull();
        }
    }

    @Override
    public void setPresenter(MarginContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
