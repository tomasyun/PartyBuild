package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.InfoAdapter;
import www.dico.cn.partybuild.bean.InfoBean;
import www.dico.cn.partybuild.bean.SkipForm;
import www.dico.cn.partybuild.modleview.CommonView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.CommonPresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.SmartRefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.api.RefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.listener.OnRefreshLoadmoreListener;

@CreatePresenter(CommonPresenter.class)
public class CommonActivity extends AbstractMvpActivity<CommonView, CommonPresenter> implements CommonView {
    @BindView(R.id.tv_title_common)
    TextView tv_title_common;
    @BindView(R.id.srl_common)
    SmartRefreshLayout srl_common;
    @BindView(R.id.rv_common)
    RecyclerView rv_common;
    @BindView(R.id.common_empty_data)
    View common_empty_data;
    @BindView(R.id.common_net_error)
    View common_net_error;
    private int start = 0;
    private int length = 10;
    private SkipForm form;
    private List<InfoBean.DataBeanX.DataBean> list;
    private InfoAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        ButterKnife.bind(this);
        form = getParam();
        if (form != null) {
            switch (form.skip) {
                case 0://党员园地
                    tv_title_common.setText("党员园地");
                    getMvpPresenter().doCommonArticleRequest("13", "", "0", start, length);
                    break;
                case 1://支部园地
                    tv_title_common.setText("支部园地");
                    getMvpPresenter().doCommonArticleRequest("14", "", "0", start, length);
                    break;
                case 2://培训园地
                    tv_title_common.setText("培训园地");
                    getMvpPresenter().doCommonArticleRequest("15", "", "0", start, length);
                    break;
                case 3://组织机构
                    tv_title_common.setText("组织机构");
                    getMvpPresenter().doCommonArticleRequest("16", "", "0", start, length);
                    break;
                case 4://公告通知
                    tv_title_common.setText("公告通知");
                    getMvpPresenter().doCommonArticleRequest("4", "", "0", start, length);
                    break;
                case 5://团员园地
                    tv_title_common.setText("团员园地");
                    getMvpPresenter().doCommonArticleRequest("6", "", "0", start, length);
                    break;
                case 6://规章制度
                    tv_title_common.setText("规章制度");
                    getMvpPresenter().doCommonArticleRequest("7", "", "0", start, length);
                    break;
                case 7://组织机构
                    tv_title_common.setText("组织机构");
                    getMvpPresenter().doCommonArticleRequest("8", "", "0", start, length);
                    break;
                case 8://分工会
                    tv_title_common.setText("分工会");
                    getMvpPresenter().doCommonArticleRequest("", "36", "0", start, length);
                    break;
                case 9://工会委员会
                    tv_title_common.setText("工会委员会");
                    getMvpPresenter().doCommonArticleRequest("", "35", "0", start, length);
                    break;
                case 10://女工委员会
                    tv_title_common.setText("女工委员会");
                    getMvpPresenter().doCommonArticleRequest("", "37", "0", start, length);
                    break;
                case 11://相关制度
                    tv_title_common.setText("相关制度");
                    getMvpPresenter().doCommonArticleRequest("", "41", "0", start, length);
                    break;
                case 12://公开信息
                    tv_title_common.setText("公开信息");
                    getMvpPresenter().doCommonArticleRequest("", "42", "0", start, length);
                    break;
                case 13://十届一次职代会
                    tv_title_common.setText("十届一次职代会");
                    getMvpPresenter().doCommonArticleRequest("", "34", "0", start, length);
                    break;
                case 14://十届二次职代会
                    tv_title_common.setText("十届二次职代会");
                    getMvpPresenter().doCommonArticleRequest("", "38", "0", start, length);
                    break;
                case 15://十届三次职代会
                    tv_title_common.setText("十届三次职代会");
                    getMvpPresenter().doCommonArticleRequest("", "39", "0", start, length);
                    break;
                case 16://十届四次职代会
                    tv_title_common.setText("十届四次职代会");
                    getMvpPresenter().doCommonArticleRequest("", "40", "0", start, length);
                    break;
                case 17://棋牌协会
                    tv_title_common.setText("棋牌协会");
                    getMvpPresenter().doCommonArticleRequest("", "26", "0", start, length);
                    break;
                case 18://舞蹈、瑜伽协会
                    tv_title_common.setText("舞蹈、瑜伽协会");
                    getMvpPresenter().doCommonArticleRequest("", "24", "0", start, length);
                    break;
                case 19://台球、乒乓球协会
                    tv_title_common.setText("台球、乒乓球协会");
                    getMvpPresenter().doCommonArticleRequest("", "25", "0", start, length);
                    break;
                case 20://篮球协会
                    tv_title_common.setText("篮球协会");
                    getMvpPresenter().doCommonArticleRequest("", "27", "0", start, length);
                    break;
                case 21://摄影协会
                    tv_title_common.setText("摄影协会");
                    getMvpPresenter().doCommonArticleRequest("", "28", "0", start, length);
                    break;
                case 22://网球、羽毛球协会
                    tv_title_common.setText("网球、羽毛球协会");
                    getMvpPresenter().doCommonArticleRequest("", "30", "0", start, length);
                    break;
                case 23://游泳协会
                    tv_title_common.setText("游泳协会");
                    getMvpPresenter().doCommonArticleRequest("", "29", "0", start, length);
                    break;
                case 24://第一分工会
                    tv_title_common.setText("第一分工会");
                    getMvpPresenter().doCommonArticleRequest("", "18", "0", start, length);
                    break;
                case 25://第二分工会
                    tv_title_common.setText("第二分工会");
                    getMvpPresenter().doCommonArticleRequest("", "19", "0", start, length);
                    break;
                case 26://第三分工会
                    tv_title_common.setText("第三分工会");
                    getMvpPresenter().doCommonArticleRequest("", "20", "0", start, length);
                    break;
                case 27://第四分工会
                    tv_title_common.setText("第四分工会");
                    getMvpPresenter().doCommonArticleRequest("", "21", "0", start, length);
                    break;
                case 28://第五分工会
                    tv_title_common.setText("第五分工会");
                    getMvpPresenter().doCommonArticleRequest("", "22", "0", start, length);
                    break;
                case 29://第六分工会
                    tv_title_common.setText("第六分工会");
                    getMvpPresenter().doCommonArticleRequest("", "23", "0", start, length);
                    break;
            }
        }
        rv_common.setLayoutManager(new LinearLayoutManager(this));
        srl_common.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                start = start + length;
                length = 10;
                createData(start, length, form.skip);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                start = 0;
                length = 10;
                createData(start, length, form.skip);
            }
        });
    }

    @Override
    public void resultSuccess(String result) {
        srl_common.finishLoadmore();
        srl_common.finishRefresh();
        InfoBean bean = new Gson().fromJson(result, InfoBean.class);
        if (bean.code.equals("0000")) {
            if (bean.getData() != null) {
                if (start == 0) {
                    list = bean.getData().getData();
                    if (null != list && list.size() > 0) {
                        srl_common.setVisibility(View.VISIBLE);
                        common_empty_data.setVisibility(View.GONE);
                        common_net_error.setVisibility(View.GONE);
                        adapter = new InfoAdapter(this, R.layout.item_info, list);
                        rv_common.setAdapter(adapter);
                        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                            }
                        });
                    } else {
                        srl_common.setVisibility(View.GONE);
                        common_empty_data.setVisibility(View.VISIBLE);
                        common_net_error.setVisibility(View.GONE);
                    }
                } else {
                    List<InfoBean.DataBeanX.DataBean> list = bean.getData().getData();
                    if (null != list && list.size() > 0) {
                        this.list.addAll(list);
                        adapter.notifyDataSetChanged();
                        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                            }
                        });
                    } else {

                    }
                }
            }
        } else {
            showToast(bean.msg);
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
        srl_common.finishLoadmore();
        srl_common.finishRefresh();
    }

    @Override
    public void netWorkUnAvailable() {
        srl_common.setVisibility(View.GONE);
        common_empty_data.setVisibility(View.GONE);
        common_net_error.setVisibility(View.VISIBLE);
        common_net_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start = 0;
                length = 10;
                createData(start, length, form.skip);
            }
        });
    }

    public void goBackCommon(View view) {
        this.finish();
    }

    public void createData(int start, int length, int skip) {
        switch (skip) {
            case 0://党员园地
                getMvpPresenter().doCommonArticleRequest("13", "", "0", start, length);
                break;
            case 1://支部园地
                getMvpPresenter().doCommonArticleRequest("14", "", "0", start, length);
                break;
            case 2://培训园地
                getMvpPresenter().doCommonArticleRequest("15", "", "0", start, length);
                break;
            case 3://组织机构
                getMvpPresenter().doCommonArticleRequest("16", "", "0", start, length);
                break;
            case 4://公告通知
                getMvpPresenter().doCommonArticleRequest("4", "", "0", start, length);
                break;
            case 5://团员园地
                getMvpPresenter().doCommonArticleRequest("6", "", "0", start, length);
                break;
            case 6://规章制度
                getMvpPresenter().doCommonArticleRequest("7", "", "0", start, length);
                break;
            case 7://组织机构
                getMvpPresenter().doCommonArticleRequest("8", "", "0", start, length);
                break;
            case 8://分工会
                getMvpPresenter().doCommonArticleRequest("", "36", "0", start, length);
                break;
            case 9://工会委员会
                getMvpPresenter().doCommonArticleRequest("", "35", "0", start, length);
                break;
            case 10://女工委员会
                getMvpPresenter().doCommonArticleRequest("", "37", "0", start, length);
                break;
            case 11://相关制度
                getMvpPresenter().doCommonArticleRequest("", "41", "0", start, length);
                break;
            case 12://公开信息
                getMvpPresenter().doCommonArticleRequest("", "42", "0", start, length);
                break;
            case 13://十届一次职代会
                getMvpPresenter().doCommonArticleRequest("", "34", "0", start, length);
                break;
            case 14://十届二次职代会
                getMvpPresenter().doCommonArticleRequest("", "38", "0", start, length);
                break;
            case 15://十届三次职代会
                getMvpPresenter().doCommonArticleRequest("", "39", "0", start, length);
                break;
            case 16://十届四次职代会
                getMvpPresenter().doCommonArticleRequest("", "40", "0", start, length);
                break;
            case 17://棋牌协会
                getMvpPresenter().doCommonArticleRequest("", "26", "0", start, length);
                break;
            case 18://舞蹈、瑜伽协会
                getMvpPresenter().doCommonArticleRequest("", "24", "0", start, length);
                break;
            case 19://台球、乒乓球协会
                getMvpPresenter().doCommonArticleRequest("", "25", "0", start, length);
                break;
            case 20://篮球协会
                getMvpPresenter().doCommonArticleRequest("", "27", "0", start, length);
                break;
            case 21://摄影协会
                getMvpPresenter().doCommonArticleRequest("", "28", "0", start, length);
                break;
            case 22://网球、羽毛球协会
                getMvpPresenter().doCommonArticleRequest("", "30", "0", start, length);
                break;
            case 23://游泳协会
                getMvpPresenter().doCommonArticleRequest("", "29", "0", start, length);
                break;
            case 24://第一分工会
                getMvpPresenter().doCommonArticleRequest("", "18", "0", start, length);
                break;
            case 25://第二分工会
                getMvpPresenter().doCommonArticleRequest("", "19", "0", start, length);
                break;
            case 26://第三分工会
                getMvpPresenter().doCommonArticleRequest("", "20", "0", start, length);
                break;
            case 27://第四分工会
                getMvpPresenter().doCommonArticleRequest("", "21", "0", start, length);
                break;
            case 28://第五分工会
                getMvpPresenter().doCommonArticleRequest("", "22", "0", start, length);
                break;
            case 29://第六分工会
                getMvpPresenter().doCommonArticleRequest("", "23", "0", start, length);
                break;
        }
    }
}
