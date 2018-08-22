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

@CreatePresenter(MeetingPresenter.class)
public class MeetingActivity extends AbstractMvpActivity<MeetingView, MeetingPresenter> implements MeetingView {
    @BindView(R.id.rv_meeting)
    RecyclerView rv_meeting;
    private MeetingAdapter adapter;
    @BindView(R.id.meeting_empty_data)
    View meeting_empty_data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        ButterKnife.bind(this);
        rv_meeting.setLayoutManager(new LinearLayoutManager(this));
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
        final MeetingBean bean = new Gson().fromJson(result, MeetingBean.class);
        if (bean.code.equals("0000")) {
            List<MeetingBean.DataBean> list = bean.getData();
            if (null != list && list.size() > 0) {
                rv_meeting.setVisibility(View.VISIBLE);
                meeting_empty_data.setVisibility(View.GONE);
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
                rv_meeting.setVisibility(View.GONE);
                meeting_empty_data.setVisibility(View.VISIBLE);
            }
        } else {
            showToast(bean.msg);
        }

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
