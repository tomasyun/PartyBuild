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
import www.dico.cn.partybuild.adapter.MailboxListAdapter;
import www.dico.cn.partybuild.bean.MailboxListBean;
import www.dico.cn.partybuild.modleview.MailboxListView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.MailboxListPresenter;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.SmartRefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.api.RefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.listener.OnRefreshListener;

@CreatePresenter(MailboxListPresenter.class)
public class MailboxListActivity extends AbstractMvpActivity<MailboxListView, MailboxListPresenter> implements MailboxListView {
    @BindView(R.id.rv_mailbox_list)
    RecyclerView rv_mailbox_list;
    @BindView(R.id.mailbox_empty_data)
    View mailbox_empty_data;
    @BindView(R.id.mailbox_net_error)
    View mailbox_net_error;
    private MailboxListAdapter adapter;
    @BindView(R.id.srl_mailbox_list)
    SmartRefreshLayout srl_mailbox_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailboxlist);
        ButterKnife.bind(this);
        rv_mailbox_list.setLayoutManager(new LinearLayoutManager(this));

        srl_mailbox_list.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getMvpPresenter().doMailboxListRequest();
            }
        });
        getMvpPresenter().doMailboxListRequest();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        getMvpPresenter().doMailboxListRequest();
//    }

    public void goBackMailboxList(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        srl_mailbox_list.finishRefresh();
        MailboxListBean bean = new Gson().fromJson(result, MailboxListBean.class);
        if (bean.code.equals("0000")) {
            if (null != bean.getData()) {
                List<MailboxListBean.DataBean> list = bean.getData();
                if (null != list && list.size() > 0) {
                    srl_mailbox_list.setVisibility(View.VISIBLE);
                    mailbox_empty_data.setVisibility(View.GONE);
                    mailbox_net_error.setVisibility(View.GONE);
                    adapter = new MailboxListAdapter(this, R.layout.item_mailbox, list);
                    rv_mailbox_list.setAdapter(adapter);
                } else {
                    //空白页面
                    srl_mailbox_list.setVisibility(View.GONE);
                    mailbox_empty_data.setVisibility(View.VISIBLE);
                    mailbox_net_error.setVisibility(View.GONE);
                }
            }
        } else {
            showToast("服务器异常");
        }
    }

    @Override
    public void resultFailure(String result) {
        srl_mailbox_list.finishRefresh();
        showToast(result);
    }

    @Override
    public void netWorkUnAvailable() {
        srl_mailbox_list.setVisibility(View.GONE);
        mailbox_empty_data.setVisibility(View.GONE);
        mailbox_net_error.setVisibility(View.VISIBLE);
        mailbox_net_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMvpPresenter().doMailboxListRequest();
            }
        });
    }
}
