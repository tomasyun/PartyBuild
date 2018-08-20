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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.activity.InfodetailsActivity;
import www.dico.cn.partybuild.adapter.InfoAdapter;
import www.dico.cn.partybuild.bean.InfoBean;
import www.dico.cn.partybuild.bean.InfodetailForm;
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
    @BindView(R.id.rg_info)
    RadioGroup rg_info;
    @BindView(R.id.srl_info)
    SmartRefreshLayout srl_info;
    @BindView(R.id.rv_info)
    RecyclerView rv_info;
    private InfoAdapter adapter;
    private int position = 0;
    private int start = 0;
    private int length = 10;
    private List<InfoBean.DataBeanX.DataBean> list;

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
                        start = 0;
                        getMvpPresenter().doGetInfoRequest("0", "0", start, length);
                        break;
                    case R.id.rbt_talk_info://习总讲话
                        position = 1;
                        start = 0;
                        getMvpPresenter().doGetInfoRequest("1", "0", start, length);
                        break;
                    case R.id.rbt_history_info://国史党史
                        position = 2;
                        start = 0;
                        getMvpPresenter().doGetInfoRequest("2", "0", start, length);
                        break;
                    case R.id.tv_vanguard_info://时代先锋
                        position = 3;
                        start = 0;
                        getMvpPresenter().doGetInfoRequest("3", "0", start, length);
                        break;
                }
            }
        });

        srl_info.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                start = start + length;
                switch (position) {
                    case 0:
                        getMvpPresenter().doGetInfoRequest("0", "0", start, length);
                        break;
                    case 1:
                        getMvpPresenter().doGetInfoRequest("1", "0", start, length);
                        break;
                    case 2:
                        getMvpPresenter().doGetInfoRequest("2", "0", start, length);
                        break;
                    case 3:
                        getMvpPresenter().doGetInfoRequest("3", "0", start, length);
                        break;
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                start = 0;
                switch (position) {
                    case 0:
                        getMvpPresenter().doGetInfoRequest("0", "0", start, length);
                        break;
                    case 1:
                        getMvpPresenter().doGetInfoRequest("1", "0", start, length);
                        break;
                    case 2:
                        getMvpPresenter().doGetInfoRequest("2", "0", start, length);
                        break;
                    case 3:
                        getMvpPresenter().doGetInfoRequest("3", "0", start, length);
                        break;
                }
            }
        });
        rv_info.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void resultSuccess(String result) {
        srl_info.finishRefresh();
        srl_info.finishLoadmore();
        InfoBean bean = new Gson().fromJson(result, InfoBean.class);
        if (bean.code.equals("0000")) {
            if (bean.getData() != null) {
                if (start == 0) {
                    list = bean.getData().getData();
                    if (null != list && list.size() > 0) {
                        adapter = new InfoAdapter(getActivity(), R.layout.item_info, list);
                        rv_info.setAdapter(adapter);
                        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                                InfodetailForm form=new InfodetailForm();
                                form.infoId=list.get(position).getId();
                                goTo(InfodetailsActivity.class,form);
                            }
                        });
                    } else {

                    }
                } else {
                    List<InfoBean.DataBeanX.DataBean> list = bean.getData().getData();
                    if (null != list && list.size() > 0) {
                        this.list.addAll(list);
                        adapter.notifyDataSetChanged();
                        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                                InfodetailForm form=new InfodetailForm();
                                form.infoId=InfoFragment.this.list.get(position).getId();
                                goTo(InfodetailsActivity.class,form);
                            }
                        });
                    }
                }
            }
        } else {

        }
    }

    @Override
    public void resultFailure(String result) {
        srl_info.finishRefresh();
        srl_info.finishLoadmore();
        showToast(result);
    }

    @Override
    public void preventPreLoad() {
        super.preventPreLoad();
        start = 0;
        switch (position) {
            case 0:
                getMvpPresenter().doGetInfoRequest("0", "0", start, length);
                break;
            case 1:
                getMvpPresenter().doGetInfoRequest("1", "0", start, length);
                break;
            case 2:
                getMvpPresenter().doGetInfoRequest("2", "0", start, length);
                break;
            case 3:
                getMvpPresenter().doGetInfoRequest("3", "0", start, length);
                break;
        }
    }
}
