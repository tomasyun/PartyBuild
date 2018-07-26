package www.dico.cn.partybuild.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.activity.BaseInfoActivity;
import www.dico.cn.partybuild.activity.CollectActivity;
import www.dico.cn.partybuild.activity.CreditInfoActivity;
import www.dico.cn.partybuild.activity.CreditRankActivity;
import www.dico.cn.partybuild.activity.FeedbackActivity;
import www.dico.cn.partybuild.activity.NoticeActivity;
import www.dico.cn.partybuild.activity.SettingActivity;
import www.dico.cn.partybuild.modleview.PersonalView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.presenter.PersonalPresenter;

//个人中心
@CreatePresenter(PersonalPresenter.class)
public class PersonalFragment extends AbstractFragment<PersonalView, PersonalPresenter> implements PersonalView {
    private LinearLayout lin_credit_personal;
    private LinearLayout lin_rank_personal;
    private RelativeLayout rel_baseinfo_personal;
    private RelativeLayout rel_collect_personal;
    private RelativeLayout rel_feedback_personal;
    private ImageView iv_setting_personal;
    private ImageView iv_notice_personal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, null);
        lin_credit_personal = view.findViewById(R.id.lin_credit_personal);
        lin_rank_personal = view.findViewById(R.id.lin_rank_personal);
        rel_baseinfo_personal = view.findViewById(R.id.rel_baseinfo_personal);
        rel_collect_personal = view.findViewById(R.id.rel_collect_personal);
        rel_feedback_personal = view.findViewById(R.id.rel_feedback_personal);
        iv_setting_personal = view.findViewById(R.id.iv_setting_personal);
        iv_notice_personal = view.findViewById(R.id.iv_notice_personal);
        lin_credit_personal.setOnClickListener(new View.OnClickListener() {
            //积分明细
            @Override
            public void onClick(View view) {
                goTo(CreditInfoActivity.class, null);
            }
        });
        lin_rank_personal.setOnClickListener(new View.OnClickListener() {
            //积分排名
            @Override
            public void onClick(View view) {
                goTo(CreditRankActivity.class, null);
            }
        });
        rel_baseinfo_personal.setOnClickListener(new View.OnClickListener() {
            //基本信息
            @Override
            public void onClick(View view) {
                goTo(BaseInfoActivity.class, null);
            }
        });
        rel_collect_personal.setOnClickListener(new View.OnClickListener() {
            //收藏
            @Override
            public void onClick(View view) {
                goTo(CollectActivity.class, null);
            }
        });
        rel_feedback_personal.setOnClickListener(new View.OnClickListener() {
            //意见反馈
            @Override
            public void onClick(View view) {
                goTo(FeedbackActivity.class, null);
            }
        });
        iv_setting_personal.setOnClickListener(new View.OnClickListener() {
            //设置
            @Override
            public void onClick(View view) {
                goTo(SettingActivity.class, null);
            }
        });
        iv_notice_personal.setOnClickListener(new View.OnClickListener() {
            //通知消息
            @Override
            public void onClick(View view) {
                goTo(NoticeActivity.class, null);
            }
        });
        return view;
    }

}
