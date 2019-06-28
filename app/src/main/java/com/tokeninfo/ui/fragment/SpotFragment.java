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
import butterknife.ButterKnife;
import com.tokeninfo.R;
import com.tokeninfo.base.BaseFragment;
import com.tokeninfo.base.BaseResult;
import com.tokeninfo.ui.adapter.RecordAdapter;
import com.tokeninfo.ui.bean.RecordBean;
import com.tokeninfo.ui.contract.SpotContract;
import com.tokeninfo.ui.presenter.SpotPresenter;

import java.util.List;

public class SpotFragment extends BaseFragment implements SpotContract.BsView, SwipeRefreshLayout.OnRefreshListener {

    private SpotFragment fragment = null;

    public static SpotFragment fragment() {
        SpotFragment fragment = new SpotFragment();
        return fragment;
    }

    Context context;
    SpotContract.Presenter presenter;

    View view;
    TextView nameTxt;
    TextView accountTxt;
    SwipeRefreshLayout refreshLayout;
    RecyclerView recyclerView;

    private RecordAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_spot, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new SpotPresenter(this).start();
    }

    @Override
    public void init() {
        context = getContext();

        nameTxt = view.findViewById(R.id.txt_name);
        accountTxt = view.findViewById(R.id.txt_account);
        refreshLayout = view.findViewById(R.id.swipefresh_layout);
        recyclerView = view.findViewById(R.id.recyclerview);

        nameTxt.setText("现货");

        // 下拉刷新
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        refreshLayout.setOnRefreshListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        adapter = new RecordAdapter();
        recyclerView.setAdapter(adapter);

        pull();
    }

    @Override
    public void onRefresh() {
        RecordBean recordBean = adapter.lastData();
        if (recordBean != null) {
            int recordId = recordBean.getId();
            presenter.records(getActivity(), recordId, new BaseResult<List<RecordBean>>() {
                @Override
                public void success(List<RecordBean> recordBeans) {
                    if (recordBeans.size() > 0) {
                        adapter.appendData(recordBeans);
                    }
                }

                @Override
                public void fail(String string) {

                }
            });
        }

        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            pull();
        }
    }

    @Override
    public void pull() {
        presenter.account(getActivity(), new BaseResult<String>() {
            @Override
            public void success(String s) {
                accountTxt.setText(s);
            }

            @Override
            public void fail(String string) {

            }
        });

        presenter.records(getActivity(), 0, new BaseResult<List<RecordBean>>() {
            @Override
            public void success(List<RecordBean> recordBeans) {
                adapter.setData(recordBeans);
            }

            @Override
            public void fail(String string) {

            }
        });
    }

    @Override
    public void setPresenter(SpotContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
