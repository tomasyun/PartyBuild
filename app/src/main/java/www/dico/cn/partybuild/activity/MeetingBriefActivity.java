package www.dico.cn.partybuild.activity;

import android.graphics.Color;
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
import cn.carbs.android.expandabletextview.library.ExpandableTextView;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.ParticipantsAdapter;
import www.dico.cn.partybuild.bean.BaseProtocol;
import www.dico.cn.partybuild.bean.MeetBriefBean;
import www.dico.cn.partybuild.bean.MeetingForm;
import www.dico.cn.partybuild.bean.SkipForm;
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
    ExpandableTextView tv_brief_meeting_brief;
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
        iv_participants_meeting_brief.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_arrow_right));
        tfl_participants_meeting_brief.setVisibility(View.GONE);
        rel_participants_meeting_brief.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isHide) {
                    tfl_participants_meeting_brief.setVisibility(View.VISIBLE);
                    iv_participants_meeting_brief.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_arrow_down));
                    isHide = false;
                } else {
                    tfl_participants_meeting_brief.setVisibility(View.GONE);
                    iv_participants_meeting_brief.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_arrow_right));
                    isHide = true;
                }
            }
        });
    }

    public void goBackMeetBrief(View view) {
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (form != null)
            getMvpPresenter().doMeetingBriefRequest(form.meetingId);
    }

    @Override
    public void resultSuccess(String result) {
        MeetBriefBean briefBean = new Gson().fromJson(result, MeetBriefBean.class);
        if (briefBean.code.equals("0000")) {
            if (briefBean.getData() != null) {
                GlideUtils.loadImage(this, briefBean.getData().getThemeImg(), iv_theme_meeting_brief);
                tv_theme_meeting_brief.setText(briefBean.getData().getTheme());
                tv_date_meeting_brief.setText(briefBean.getData().getStartDate());
                tv_address_meeting_brief.setText(briefBean.getData().getAddress());
                tv_speaker_meeting_brief.setText(briefBean.getData().getSpeaker());
                tv_type_meeting_brief.setText(briefBean.getData().getCategory());
                tv_brief_meeting_brief.setText(briefBean.getData().getBrief());
                adapter = new ParticipantsAdapter(briefBean.getData().getAttender());
                tv_participants_meeting_brief.setText(briefBean.getData().getAttendNum());
                if (tv_participants_meeting_brief.getText().toString().equals("0")) {
                    rel_participants_meeting_brief.setEnabled(false);
                    rel_participants_meeting_brief.setClickable(false);
                } else {
                    rel_participants_meeting_brief.setEnabled(true);
                    rel_participants_meeting_brief.setClickable(true);
                }
                tfl_participants_meeting_brief.setAdapter(adapter);
                String conferenceState = briefBean.getData().getConferenceState();
                String signUpState = briefBean.getData().getSignUpState();
                String leaveState = briefBean.getData().getLeaveState();
                switch (conferenceState) {
                    case "0"://会议未开始
                        if (signUpState.equals("0")) {//未报名
                            if (leaveState.equals("0")) {//未请假
                                lin_leave_and_sign_up.setVisibility(View.VISIBLE);
                                sign_up.setVisibility(View.GONE);
                                TextView tv_leave_brief = lin_leave_and_sign_up.findViewById(R.id.tv_leave_brief);
                                TextView tv_sign_up_brief = lin_leave_and_sign_up.findViewById(R.id.tv_sign_up_brief);
                                tv_leave_brief.setOnClickListener(new View.OnClickListener() {//开始请假
                                    @Override
                                    public void onClick(View view) {
                                        getMvpPresenter().doLeaveRequest(form.meetingId);
                                    }
                                });
                                tv_sign_up_brief.setOnClickListener(new View.OnClickListener() {//开始报名
                                    @Override
                                    public void onClick(View view) {
                                        getMvpPresenter().doSignUpRequest(form.meetingId);
                                    }
                                });
                            } else {//已请假
                                lin_leave_and_sign_up.setVisibility(View.GONE);
                                sign_up.setVisibility(View.VISIBLE);
                                TextView tv_sign_up = sign_up.findViewById(R.id.tv_sign_up);
                                tv_sign_up.setText("已请假");
                                tv_sign_up.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_corner20_light_red_bg));
                                tv_sign_up.setTextColor(Color.parseColor("#febfb5"));
                            }
                        } else {//已报名
                            lin_leave_and_sign_up.setVisibility(View.GONE);
                            sign_up.setVisibility(View.VISIBLE);
                            TextView tv_sign_up = sign_up.findViewById(R.id.tv_sign_up);
                            tv_sign_up.setText("已报名");
                            tv_sign_up.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_corner20_light_red_bg));
                            tv_sign_up.setTextColor(Color.parseColor("#febfb5"));
                        }
                        break;
                    case "1"://会议进行中
                        if (signUpState.equals("0")) {//未报名
                            if (leaveState.equals("0")) {//未请假
                                lin_leave_and_sign_up.setVisibility(View.GONE);
                                sign_up.setVisibility(View.VISIBLE);
                                TextView tv_sign_up = sign_up.findViewById(R.id.tv_sign_up);
                                tv_sign_up.setText("会议进行中");
                                tv_sign_up.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_corner20_light_red_bg));
                                tv_sign_up.setTextColor(Color.parseColor("#febfb5"));
                            } else {//已请假
                                lin_leave_and_sign_up.setVisibility(View.GONE);
                                sign_up.setVisibility(View.VISIBLE);
                                TextView tv_sign_up = sign_up.findViewById(R.id.tv_sign_up);
                                tv_sign_up.setText("已请假");
                                tv_sign_up.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_corner20_light_red_bg));
                                tv_sign_up.setTextColor(Color.parseColor("#febfb5"));
                            }
                        } else {//已报名
                            lin_leave_and_sign_up.setVisibility(View.GONE);
                            sign_up.setVisibility(View.VISIBLE);
                            TextView tv_sign_up = sign_up.findViewById(R.id.tv_sign_up);
                            tv_sign_up.setText("已报名");
                            tv_sign_up.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_corner20_light_red_bg));
                            tv_sign_up.setTextColor(Color.parseColor("#febfb5"));
                        }
                        break;
                    case "2"://会议已结束
                        lin_leave_and_sign_up.setVisibility(View.GONE);
                        sign_up.setVisibility(View.VISIBLE);
                        TextView tv_sign_up = sign_up.findViewById(R.id.tv_sign_up);
                        tv_sign_up.setText("查看会议记录");
                        tv_sign_up.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_corner20_light_red_bg));
                        tv_sign_up.setTextColor(Color.parseColor("#febfb5"));
                        tv_sign_up.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                        break;
                }
            }
        } else {
            showToast(briefBean.msg);
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }

    @Override
    public void signUpResultSuccess(String result) {
        BaseProtocol protocol = new Gson().fromJson(result, BaseProtocol.class);
        if (protocol.code.equals("0000")) {
            SkipForm form = new SkipForm();
            form.skipType = "0";
            goTo(SignUpSuccessActivity.class, form);
        } else {
            showToast(protocol.msg);
        }
    }

    @Override
    public void signUpResultFailure(String result) {
        showToast(result);
    }

    @Override
    public void leaveResultSuccess(String result) {
        BaseProtocol protocol = new Gson().fromJson(result, BaseProtocol.class);
        if (protocol.code.equals("0000")) {
            goTo(LeaveSuccessActivity.class, null);
        } else {
            showToast(protocol.msg);
        }
    }

    @Override
    public void leaveResultFailure(String result) {
        showToast(result);
    }

    @Override
    public void netWorkUnAvailable() {
        showToast("网络出现异常");
    }
}
