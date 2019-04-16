package www.dico.cn.partybuild.activity;

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
import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.ParticipantsAdapter;
import www.dico.cn.partybuild.bean.BaseProtocol;
import www.dico.cn.partybuild.bean.OrgActBriefBean;
import www.dico.cn.partybuild.bean.OrgActForm;
import www.dico.cn.partybuild.bean.SkipForm;
import www.dico.cn.partybuild.modleview.OrgActBriefView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.OrgActBriefPresenter;
import www.dico.cn.partybuild.utils.GlideUtils;

@CreatePresenter(OrgActBriefPresenter.class)
public class OrgActBriefActivity extends AbstractMvpActivity<OrgActBriefView, OrgActBriefPresenter> implements OrgActBriefView {
    @BindView(R.id.iv_orgact_brief_theme_pic)
    ImageView iv_orgact_brief_theme_pic;
    @BindView(R.id.tv_orgact_brief_theme)
    TextView tv_orgact_brief_theme;
    @BindView(R.id.tv_orgact_brief_date)
    TextView tv_orgact_brief_date;
    @BindView(R.id.tv_orgact_brief_address)
    TextView tv_orgact_brief_address;
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
    private OrgActForm form;
    private boolean isHide = true;
    private ParticipantsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgactbrief);
        ButterKnife.bind(this);
        form = getParam();
        iv_orgact_brief_participants.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_arrow_right));
        tfl_orgact_brief_participants.setVisibility(View.GONE);
        rel_orgact_brief_participants.setOnClickListener(view -> {
            if (isHide) {
                tfl_orgact_brief_participants.setVisibility(View.VISIBLE);
                iv_orgact_brief_participants.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_arrow_down));
                isHide = false;
            } else {
                tfl_orgact_brief_participants.setVisibility(View.GONE);
                iv_orgact_brief_participants.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_arrow_right));
                isHide = true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (form != null)
            getMvpPresenter().doOrgActBriefRequest(dialog, form.orgActId);
    }

    public void goBackOrgActBrief(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        OrgActBriefBean briefBean = new Gson().fromJson(result, OrgActBriefBean.class);
        if (briefBean.code.equals("0000")) {
            if (briefBean.getData() != null) {
                GlideUtils.loadImageSetUpError(this, AppConfig.urlFormat(briefBean.getData().getThemeImg()), iv_orgact_brief_theme_pic, R.mipmap.img_dico);
                tv_orgact_brief_theme.setText(briefBean.getData().getTheme());
                tv_orgact_brief_date.setText(briefBean.getData().getStartDate());
                tv_orgact_brief_address.setText(briefBean.getData().getAddress());
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
            }
        } else {
            showToast("服务器异常");
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
            form.skip = 1;
            goTo(SuccessTipsActivity.class, form);
        } else {
            showToast("服务器异常");
        }
    }

    @Override
    public void signUpResultFailure(String result) {
        showToast(result);
    }

    @Override
    public void netWorkUnAvailable() {
        showToast("网络出现异常");
    }
}
