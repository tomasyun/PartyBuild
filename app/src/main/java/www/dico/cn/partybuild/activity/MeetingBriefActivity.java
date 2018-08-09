package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.MeetingBriefView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.MeetingBriefPresenter;

@CreatePresenter(MeetingBriefPresenter.class)
public class MeetingBriefActivity extends AbstractMvpActivity<MeetingBriefView, MeetingBriefPresenter> implements MeetingBriefView {
    @BindView(R.id.iv_theme_meeting_brief)
    ImageView iv_theme_meeting_brief;
    @BindView(R.id.tv_title_meeting_brief)
    TextView tv_title_meeting_brief;
    @BindView(R.id.tv_date_meeting_brief)
    TextView tv_date_meeting_brief;
    @BindView(R.id.tv_address_meeting_brief)
    TextView tv_address_meeting_brief;
    @BindView(R.id.tv_speaker_meeting_brief)
    TextView tv_speaker_meeting_brief;
    @BindView(R.id.tv_type_meeting_brief)
    TextView tv_type_meeting_brief;
    @BindView(R.id.tv_brief_meeting_brief)
    TextView tv_brief_meeting_brief;
    @BindView(R.id.tv_participants_meeting_brief)
    TextView tv_participants_meeting_brief;
    @BindView(R.id.tfl_participants_meeting_brief)
    FlowLayout tfl_participants_meeting_brief;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetingbrief);
        ButterKnife.bind(this);
    }

    public void goBackMeetBrief(View view) {
        this.finish();
    }

    //请假
    public void askForLeave(View view) {
        goTo(LeaveReasonActivity.class, null);
    }

    //报名
    public void signUp(View view) {
        goTo(SignUpSuccessActivity.class, null);
    }

    @Override
    public void resultSuccess(String result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
