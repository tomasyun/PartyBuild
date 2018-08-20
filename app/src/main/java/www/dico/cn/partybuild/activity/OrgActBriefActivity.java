package www.dico.cn.partybuild.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
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
import www.dico.cn.partybuild.bean.OrgActBriefBean;
import www.dico.cn.partybuild.bean.OrgActForm;
import www.dico.cn.partybuild.modleview.OrgActBriefView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.OrgActBriefPresenter;
import www.dico.cn.partybuild.utils.GlideUtils;

@CreatePresenter(OrgActBriefPresenter.class)
public class OrgActBriefActivity extends AbstractMvpActivity<OrgActBriefView, OrgActBriefPresenter> implements OrgActBriefView {
    private OrgActForm form;
    private boolean isHide = true;
    @BindView(R.id.iv_orgact_brief_theme_pic)
    ImageView iv_orgact_brief_theme_pic;
    @BindView(R.id.tv_orgact_brief_theme)
    TextView tv_orgact_brief_theme;
    @BindView(R.id.tv_orgact_brief_date)
    TextView tv_orgact_brief_date;
    @BindView(R.id.tv_orgact_brief_address)
    TextView tv_orgact_brief_address;
    @BindView(R.id.tv_orgact_brief_speaker)
    TextView tv_orgact_brief_speaker;
    @BindView(R.id.tv_orgact_brief_category)
    TextView tv_orgact_brief_category;
    @BindView(R.id.tv_orgact_brief_content)
    ExpandableTextView tv_orgact_brief_content;
    @BindView(R.id.tv_orgact_brief_participants)
    TextView tv_orgact_brief_participants;
    @BindView(R.id.iv_orgact_brief_participants)
    ImageView iv_orgact_brief_participants;
    @BindView(R.id.tfl_orgact_brief_participants)
    TagFlowLayout tfl_orgact_brief_participants;
    @BindView(R.id.rel_orgact_brief_participants)
    RelativeLayout rel_orgact_brief_participants;
    private ParticipantsAdapter adapter;
    @BindView(R.id.sign_up_orgact_brief)
    View sign_up_orgact_brief;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgactbrief);
        ButterKnife.bind(this);
        form = getParam();
        iv_orgact_brief_participants.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_arrow_right));
        tfl_orgact_brief_participants.setVisibility(View.GONE);
        rel_orgact_brief_participants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isHide) {
                    tfl_orgact_brief_participants.setVisibility(View.VISIBLE);
                    iv_orgact_brief_participants.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_arrow_down));
                    isHide = false;
                } else {
                    tfl_orgact_brief_participants.setVisibility(View.GONE);
                    iv_orgact_brief_participants.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_arrow_right));
                    isHide = true;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (form != null)
            getMvpPresenter().doOrgActBriefRequest(form.orgActId);
    }

    public void goBackOrgActBrief(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        OrgActBriefBean briefBean = new Gson().fromJson(result, OrgActBriefBean.class);
        if (briefBean.code.equals("0000")) {
            if (briefBean.getData() != null) {
                GlideUtils.loadImage(this, briefBean.getData().getThemeImg(), iv_orgact_brief_theme_pic);
                tv_orgact_brief_theme.setText(briefBean.getData().getTheme());
                tv_orgact_brief_date.setText(briefBean.getData().getStartDate());
                tv_orgact_brief_address.setText(briefBean.getData().getAddress());
                tv_orgact_brief_speaker.setText(briefBean.getData().getSpeaker());
                tv_orgact_brief_category.setText(briefBean.getData().getCategory());
                tv_orgact_brief_content.setText(briefBean.getData().getBrief());
                adapter = new ParticipantsAdapter(briefBean.getData().getAttender());
                tv_orgact_brief_participants.setText(briefBean.getData().getAttendNum());
                if (briefBean.getData().getAttendNum().equals("0")) {
                    rel_orgact_brief_participants.setEnabled(false);
                    rel_orgact_brief_participants.setClickable(false);
                } else {
                    rel_orgact_brief_participants.setEnabled(true);
                    rel_orgact_brief_participants.setClickable(true);
                }
                tfl_orgact_brief_participants.setAdapter(adapter);
                String conferenceState = briefBean.getData().getConferenceState();
                String signUpState = briefBean.getData().getSignUpState();
                switch (conferenceState) {
                    case "0"://未开始
                        if (signUpState!=null&&!signUpState.equals("")){
                        if (signUpState.equals("0")) {//未报名
                            TextView tv_sign_up = sign_up_orgact_brief.findViewById(R.id.tv_sign_up);
                            tv_sign_up.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    getMvpPresenter().doSignUpRequest(form.orgActId);
                                }
                            });
                        } else {//已报名
                            TextView tv_sign_up = sign_up_orgact_brief.findViewById(R.id.tv_sign_up);
                            tv_sign_up.setText("已报名");
                            tv_sign_up.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_corner20_light_red_bg));
                            tv_sign_up.setTextColor(Color.parseColor("#febfb5"));
                        }}
                        break;
                    case "1"://进行中
                        if (signUpState!=null&&!signUpState.equals("")){
                        if (signUpState.equals("0")) {//未报名
                            TextView tv_sign_up = sign_up_orgact_brief.findViewById(R.id.tv_sign_up);
                            tv_sign_up.setText("会议进行中");
                            tv_sign_up.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_corner20_light_red_bg));
                            tv_sign_up.setTextColor(Color.parseColor("#febfb5"));
                        } else {//已报名
                            TextView tv_sign_up = sign_up_orgact_brief.findViewById(R.id.tv_sign_up);
                            tv_sign_up.setText("已报名");
                            tv_sign_up.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_corner20_light_red_bg));
                            tv_sign_up.setTextColor(Color.parseColor("#febfb5"));
                        }}
                        break;
                    case "2"://已结束
                        TextView tv_sign_up = sign_up_orgact_brief.findViewById(R.id.tv_sign_up);
                        tv_sign_up.setText("查看会议记录");
                        tv_sign_up.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_corner20_light_red_bg));
                        tv_sign_up.setTextColor(Color.parseColor("#febfb5"));
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
            goTo(SignUpSuccessActivity.class, null);
        } else {
            showToast(protocol.msg);
        }
    }

    @Override
    public void signUpResultFailure(String result) {
        showToast(result);
    }
}
