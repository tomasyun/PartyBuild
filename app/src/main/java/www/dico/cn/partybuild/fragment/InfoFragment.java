package www.dico.cn.partybuild.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.InfoAdapter;
import www.dico.cn.partybuild.bean.InfoBean;
import www.dico.cn.partybuild.modleview.InfoView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.presenter.InfoPresenter;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.SmartRefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.api.RefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.listener.OnRefreshLoadmoreListener;

@CreatePresenter(InfoPresenter.class)
public class InfoFragment extends AbstractFragment<InfoView, InfoPresenter> implements InfoView {
    @BindView(R.id.rg_info)
    RadioGroup rg_info;
    @BindView(R.id.srl_info)
    SmartRefreshLayout srl_info;
    @BindView(R.id.rv_info)
    RecyclerView rv_info;
    private InfoAdapter newsAdapter;
    private InfoAdapter talkAdapter;
    private InfoAdapter historyAdapter;
    private InfoAdapter vanguardAdapter;
    private int position = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, null);
        ButterKnife.bind(this, view);
        rg_info.check(R.id.rbt_news_info);
        rg_info.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbt_news_info://党建要闻
                        position = 0;
                        getMvpPresenter().doGetInfoRequest("0");
                        break;
                    case R.id.rbt_talk_info://习总讲话
                        position = 1;
                        getMvpPresenter().doGetInfoRequest("1");
                        break;
                    case R.id.rbt_history_info://国史党史
                        position = 2;
                        getMvpPresenter().doGetInfoRequest("2");
                        break;
                    case R.id.tv_vanguard_info://时代先锋
                        position = 3;
                        getMvpPresenter().doGetInfoRequest("3");
                        break;
                }
            }
        });

        srl_info.setOnRefreshListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }
        });
        rv_info.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void resultSuccess(String result) {
        InfoBean bean = new Gson().fromJson(result, InfoBean.class);
        if (bean.code.equals("0000")) {

        } else {

        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }

    @Override
    public void preventPreLoad() {
        super.preventPreLoad();
        switch (position){
            case 0:
                getMvpPresenter().doGetInfoRequest("0");
                break;
            case 1:
                getMvpPresenter().doGetInfoRequest("1");
                break;
            case 2:
                getMvpPresenter().doGetInfoRequest("2");
                break;
            case 3:
                getMvpPresenter().doGetInfoRequest("3");
                break;
        }

    }
}
