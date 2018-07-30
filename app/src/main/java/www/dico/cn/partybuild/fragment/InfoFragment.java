package www.dico.cn.partybuild.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.activity.InfodetailsActivity;
import www.dico.cn.partybuild.adapter.InfoAdapter;
import www.dico.cn.partybuild.bean.InfoBean;
import www.dico.cn.partybuild.modleview.InfoView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.presenter.InfoPresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.SmartRefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.api.RefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.listener.OnRefreshLoadmoreListener;

@CreatePresenter(InfoPresenter.class)
public class InfoFragment extends AbstractFragment<InfoView, InfoPresenter> implements InfoView {
    private RadioGroup rg_info;
    private SmartRefreshLayout srl_info;
    private RecyclerView rv_info;
    private InfoAdapter newsAdapter;
    private InfoAdapter talkAdapter;
    private InfoAdapter historyAdapter;
    private InfoAdapter vanguardAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, null);
        rg_info = view.findViewById(R.id.rg_info);
        rg_info.check(R.id.rbt_news_info);
        rg_info.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbt_news_info://党建要闻
                        newsAdapter = new InfoAdapter(getActivity(), R.layout.item_info, initNews());
                        rv_info.setAdapter(newsAdapter);
                        break;
                    case R.id.rbt_talk_info://习总讲话
                        talkAdapter = new InfoAdapter(getActivity(), R.layout.item_info, initTalk());
                        rv_info.setAdapter(talkAdapter);
                        break;
                    case R.id.rbt_history_info://国史党史
                        historyAdapter = new InfoAdapter(getActivity(), R.layout.item_info, initHistory());
                        rv_info.setAdapter(historyAdapter);
                        break;
                    case R.id.tv_vanguard_info://时代先锋
                        vanguardAdapter = new InfoAdapter(getActivity(), R.layout.item_info, initVanguard());
                        rv_info.setAdapter(vanguardAdapter);
                        break;
                }
            }
        });

        srl_info = view.findViewById(R.id.srl_info);
        srl_info.setOnRefreshListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }
        });
        rv_info = view.findViewById(R.id.rv_info);
        rv_info.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsAdapter = new InfoAdapter(getActivity(), R.layout.item_info, initNews());
        rv_info.setAdapter(newsAdapter);
        newsAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                goTo(InfodetailsActivity.class, null);
            }
        });
        return view;
    }

    public List<InfoBean> initNews() {
        List<InfoBean> list = new ArrayList<>();
        InfoBean bean = new InfoBean();
        bean.setTitle("党建要闻");
        bean.setDate("2018-7-26 18:00");
        bean.setComment("75");
        list.add(bean);
        return list;
    }

    public List<InfoBean> initTalk() {
        List<InfoBean> list = new ArrayList<>();
        InfoBean bean = new InfoBean();
        bean.setTitle("习总讲话");
        bean.setDate("2018-7-26 18:00");
        bean.setComment("75");
        list.add(bean);
        return list;
    }

    public List<InfoBean> initHistory() {
        List<InfoBean> list = new ArrayList<>();
        InfoBean bean = new InfoBean();
        bean.setTitle("国史党史");
        bean.setDate("2018-7-26 18:00");
        bean.setComment("75");
        list.add(bean);
        return list;
    }

    public List<InfoBean> initVanguard() {
        List<InfoBean> list = new ArrayList<>();
        InfoBean bean = new InfoBean();
        bean.setTitle("时代先锋");
        bean.setDate("2018-7-26 18:00");
        bean.setComment("75");
        list.add(bean);
        return list;
    }
}
