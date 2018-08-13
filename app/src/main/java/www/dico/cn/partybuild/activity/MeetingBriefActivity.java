package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.ParticipantsAdapter;
import www.dico.cn.partybuild.bean.MeetBriefBean;
import www.dico.cn.partybuild.bean.MeetingForm;
import www.dico.cn.partybuild.modleview.MeetingBriefView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.MeetingBriefPresenter;
import www.dico.cn.partybuild.utils.GlideUtils;

@CreatePresenter(MeetingBriefPresenter.class)
public class MeetingBriefActivity extends AbstractMvpActivity<MeetingBriefView, MeetingBriefPresenter> implements MeetingBriefView {
    @BindView(R.id.iv_theme_meeting_brief)
    ImageView iv_theme_meeting_brief;
    @BindView(R.id.tv_theme_meeting_brief)
    TextView tv_theme_meeting_brief;
    @BindView(R.id.tv_date_meeting_brief)
    TextView tv_date_meeting_brief;
    @BindView(R.id.tv_address_meeting_brief)
    TextView tv_address_meeting_brief;
    @BindView(R.id.tv_speaker_meeting_brief)
    TextView tv_speaker_meeting_brief;
    @BindView(R.id.tv_type_meeting_brief)
    TextView tv_type_meeting_brief;
    @BindView(R.id.rel_participants_meeting_brief)
    RelativeLayout rel_participants_meeting_brief;
    @BindView(R.id.tv_brief_meeting_brief)
    TextView tv_brief_meeting_brief;
    @BindView(R.id.tv_participants_meeting_brief)
    TextView tv_participants_meeting_brief;
    @BindView(R.id.iv_participants_meeting_brief)
    ImageView iv_participants_meeting_brief;
    @BindView(R.id.tfl_participants_meeting_brief)
    TagFlowLayout tfl_participants_meeting_brief;
    @BindView(R.id.sign_up)
    View sign_up;
    @BindView(R.id.lin_leave_and_sign_up)
    LinearLayout lin_leave_and_sign_up;
    private MeetingForm form;
    private ParticipantsAdapter adapter;
    private boolean isHide = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetingbrief);
        ButterKnife.bind(this);
        form = getParam();
        if (form != null)
            getMvpPresenter().doMeetingBriefRequest(form.meetingId);

        tfl_participants_meeting_brief.setVisibility(View.GONE);
        rel_participants_meeting_brief.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isHide) {
                    tfl_participants_meeting_brief.setVisibility(View.VISIBLE);
                    iv_participants_meeting_brief.setBackgroundDrawable(MeetingBriefActivity.this.getResources().getDrawable(R.mipmap.img_arrow_down));
                    isHide = false;
                } else {
                    tfl_participants_meeting_brief.setVisibility(View.GONE);
                    iv_participants_meeting_brief.setBackgroundDrawable(MeetingBriefActivity.this.getResources().getDrawable(R.mipmap.img_arrow_right));
                    isHide = true;
                }
            }
        });
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
        MeetBriefBean briefBean = new Gson().fromJson(result, MeetBriefBean.class);
        if (briefBean.code.equals("0000")) {
            GlideUtils.loadImage(this, briefBean.getData().getThemeImg(), iv_theme_meeting_brief);
            tv_theme_meeting_brief.setText(briefBean.getData().getTheme());
            tv_date_meeting_brief.setText(briefBean.getData().getStartDate());
            tv_address_meeting_brief.setText(briefBean.getData().getAddress());
            tv_speaker_meeting_brief.setText(briefBean.getData().getSpeaker());
            tv_type_meeting_brief.setText(briefBean.getData().getCategory());
            tv_brief_meeting_brief.setText(briefBean.getData().getBrief());
            adapter = new ParticipantsAdapter(briefBean.getData().getAttender());
            tv_participants_meeting_brief.setText(briefBean.getData().getAttendNum());
            tfl_participants_meeting_brief.setAdapter(adapter);

//            switch (briefBean.getData().getSignUpState()) {
//                case "0":
//
//                    break;
//                case "1":
//
//                    break;
//            }
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
