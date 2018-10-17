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
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.listener.OnRefreshLoadmoreListener;

//通知
@CreatePresenter(NoticePresenter.class)
public class NoticeActivity extends AbstractMvpActivity<NoticeView, NoticePresenter> implements NoticeView,NoticeAdapter.SkipNoticeInfoInterface {
    @BindView(R.id.rv_notice)
    RecyclerView rv_notice;
    @BindView(R.id.notice_empty_data)
    View notice_empty_data;
    @BindView(R.id.notice_net_error)
    View notice_net_error;
    @BindView(R.id.srl_notice)
    SmartRefreshLayout srl_notice;
    private NoticeAdapter adapter;
    private int start = 0;
    private List<NoticeBean.DataBean> noticeList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ButterKnife.bind(this);
        rv_notice.setLayoutManager(new LinearLayoutManager(this));
        getMvpPresenter().noticeRequest(dialog, "", "1", "0", start, 10);
        srl_notice.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                start = start + 10;
                getMvpPresenter().noticeRequest(dialog, "", "1", "0", start, 10);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                start = 0;
                getMvpPresenter().noticeRequest(dialog, "", "1", "0", start, 10);
            }
        });
    }

    public void goBackNotice(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        srl_notice.finishLoadmore();
        srl_notice.finishRefresh();
        NoticeBean bean = new Gson().fromJson(result, NoticeBean.class);
        if (bean.code.equals("0000")) {
            if (start == 0) {
                noticeList = bean.getData();
                if (noticeList != null && noticeList.size() > 0) {
                    srl_notice.setVisibility(View.VISIBLE);
                    notice_empty_data.setVisibility(View.GONE);
                    notice_net_error.setVisibility(View.GONE);
                    adapter = new NoticeAdapter(this, R.layout.item_notice, noticeList);
                    rv_notice.setAdapter(adapter);
                    adapter.setInfoInterface(NoticeActivity.this);
//                    adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                            NoticeForm form = new NoticeForm();
//                            form.id = noticeList.get(position).getId();
//                            form.isReply = noticeList.get(position).getIsReply();
//                            goTo(NoticeInfoActivity.class, form);
//                        }
//                    });
                } else {
                    //空白页面
                    srl_notice.setVisibility(View.GONE);
                    notice_empty_data.setVisibility(View.VISIBLE);
                    notice_net_error.setVisibility(View.GONE);
                }
            } else {
                List<NoticeBean.DataBean> list = bean.getData();
                if (list != null && list.size() > 0) {
                    this.noticeList.addAll(list);
                    adapter.notifyDataSetChanged();
                    adapter.setInfoInterface(NoticeActivity.this);
//                    adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                            NoticeForm form = new NoticeForm();
//                            form.id = NoticeActivity.this.noticeList.get(position).getId();
//                            form.isReply = NoticeActivity.this.noticeList.get(position).getIsReply();
//                            goTo(NoticeInfoActivity.class, form);
//                        }
//                    });
                } else {

                }
            }
        } else {
            showToast("服务器异常");
        }
    }

    @Override
    public void resultFailure(String result) {
        srl_notice.finishLoadmore();
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
                getMvpPresenter().noticeRequest(dialog, "", "1", "0", start, 10);
            }
        });
    }

    @Override
    public void skip(int position) {
        if(start==0){
            NoticeForm form = new NoticeForm();
            form.id = noticeList.get(position).getId();
            form.isReply = noticeList.get(position).getIsReply();
            goTo(NoticeInfoActivity.class, form);
        }else {
            NoticeForm form = new NoticeForm();
            form.id = NoticeActivity.this.noticeList.get(position).getId();
            form.isReply = NoticeActivity.this.noticeList.get(position).getIsReply();
            goTo(NoticeInfoActivity.class, form);
        }
    }
}
