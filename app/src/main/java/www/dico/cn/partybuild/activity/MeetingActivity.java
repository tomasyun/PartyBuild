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
import www.dico.cn.partybuild.adapter.MeetingAdapter;
import www.dico.cn.partybuild.bean.MeetingBean;
import www.dico.cn.partybuild.bean.MeetingForm;
import www.dico.cn.partybuild.modleview.MeetingView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.MeetingPresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.SmartRefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.api.RefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.listener.OnRefreshListener;

@CreatePresenter(MeetingPresenter.class)
public class MeetingActivity extends AbstractMvpActivity<MeetingView, MeetingPresenter> implements MeetingView {
    @BindView(R.id.rv_meeting)
    RecyclerView rv_meeting;
    @BindView(R.id.meeting_empty_data)
    View meeting_empty_data;
    @BindView(R.id.meeting_net_error)
    View meeting_net_error;
    @BindView(R.id.srl_meeting)
    SmartRefreshLayout srl_meeting;
    private MeetingAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        ButterKnife.bind(this);
        rv_meeting.setLayoutManager(new LinearLayoutManager(this));
        srl_meeting.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getMvpPresenter().doMeetingRequest();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpPresenter().doMeetingRequest();
    }

    public void goBackMeeting(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        srl_meeting.finishRefresh();
        final MeetingBean bean = new Gson().fromJson(result, MeetingBean.class);
        if (bean.code.equals("0000")) {
            List<MeetingBean.DataBean> list = bean.getData();
            if (null != list && list.size() > 0) {
                srl_meeting.setVisibility(View.VISIBLE);
                meeting_empty_data.setVisibility(View.GONE);
                meeting_net_error.setVisibility(View.GONE);
                adapter = new MeetingAdapter(this, R.layout.item_meeting, bean.getData());
                rv_meeting.setAdapter(adapter);
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        MeetingForm form = new MeetingForm();
                        form.meetingId = bean.getData().get(position).getId();
                        form.state = bean.getData().get(position).getState();
                        goTo(MeetingBriefActivity.class, form);
                    }
                });
            } else {
                //空白页面
                srl_meeting.setVisibility(View.GONE);
                meeting_empty_data.setVisibility(View.VISIBLE);
                meeting_net_error.setVisibility(View.GONE);
            }
        } else {
            showToast("服务器异常");
        }

    }

    @Override
    public void resultFailure(String result) {
        srl_meeting.finishRefresh();
        showToast(result);
    }

    @Override
    public void netWorkUnAvailable() {
        srl_meeting.setVisibility(View.GONE);
        meeting_empty_data.setVisibility(View.GONE);
        meeting_net_error.setVisibility(View.VISIBLE);
        meeting_net_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMvpPresenter().doMeetingRequest();
            }
        });
    }
}
