package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.NoticeAdapter;
import www.dico.cn.partybuild.bean.NoticeBean;
import www.dico.cn.partybuild.bean.NoticeForm;
import www.dico.cn.partybuild.modleview.NoticeView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.NoticePresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.SmartRefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.api.RefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.listener.OnRefreshListener;

//通知
@CreatePresenter(NoticePresenter.class)
public class NoticeActivity extends AbstractMvpActivity<NoticeView, NoticePresenter> implements NoticeView {
    @BindView(R.id.rv_notice)
    RecyclerView rv_notice;
    @BindView(R.id.notice_empty_data)
    View notice_empty_data;
    @BindView(R.id.notice_net_error)
    View notice_net_error;
    @BindView(R.id.srl_notice)
    SmartRefreshLayout srl_notice;
    private NoticeAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ButterKnife.bind(this);
        rv_notice.setLayoutManager(new LinearLayoutManager(this));
        srl_notice.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getMvpPresenter().noticeRequest();
            }
        });
        getMvpPresenter().noticeRequest();
    }

    public void goBackNotice(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        srl_notice.finishRefresh();
        NoticeBean bean = new Gson().fromJson(result, NoticeBean.class);
        if (bean.code.equals("0000")) {
            final List<NoticeBean.DataBean> list = bean.getData();
            if (null != list && list.size() > 0) {
                srl_notice.setVisibility(View.VISIBLE);
                notice_empty_data.setVisibility(View.GONE);
                notice_net_error.setVisibility(View.GONE);
                adapter = new NoticeAdapter(this, R.layout.item_notice, bean.getData());
                rv_notice.setAdapter(adapter);
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        NoticeForm form = new NoticeForm();
                        form.noticeId = list.get(position).getId();
                        form.isReply = list.get(position).getIsReply();
                        goTo(NoticeInfoActivity.class, form);
                    }
                });
            } else {
                //空白页面
                srl_notice.setVisibility(View.GONE);
                notice_empty_data.setVisibility(View.VISIBLE);
                notice_net_error.setVisibility(View.GONE);
            }
        } else {
            showToast("服务器异常");
        }
    }

    @Override
    public void resultFailure(String result) {
        srl_notice.finishRefresh();
        showToast(result);
    }

    @Override
    public void netWorkUnAvailable() {
        srl_notice.setVisibility(View.GONE);
        notice_empty_data.setVisibility(View.GONE);
        notice_net_error.setVisibility(View.VISIBLE);
        notice_net_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMvpPresenter().noticeRequest();
            }
        });
    }
}
