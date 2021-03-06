package www.dico.cn.partybuild.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.activity.BaseInfoActivity;
import www.dico.cn.partybuild.activity.CreditInfoActivity;
import www.dico.cn.partybuild.activity.CreditRankActivity;
import www.dico.cn.partybuild.activity.FeedbackActivity;
import www.dico.cn.partybuild.activity.MailboxListActivity;
import www.dico.cn.partybuild.activity.NoticeActivity;
import www.dico.cn.partybuild.activity.SettingActivity;
import www.dico.cn.partybuild.utils.GlideUtils;

//个人中心
public class PersonalFragment extends Fragment {
    @BindView(R.id.iv_user_avatar_personal)
    ImageView iv_user_avatar_personal;
    @BindView(R.id.tv_name_personal)
    TextView tv_name_personal;
    @BindView(R.id.tv_position_personal)
    TextView tv_position_personal;
    @BindView(R.id.lin_credit_personal)
    LinearLayout lin_credit_personal;
    @BindView(R.id.lin_rank_personal)
    LinearLayout lin_rank_personal;
    @BindView(R.id.rel_baseinfo_personal)
    RelativeLayout rel_baseinfo_personal;
    @BindView(R.id.rel_collect_personal)
    RelativeLayout rel_collect_personal;
    @BindView(R.id.rel_feedback_personal)
    RelativeLayout rel_feedback_personal;
    @BindView(R.id.iv_setting_personal)
    ImageView iv_setting_personal;
    @BindView(R.id.iv_notice_personal)
    ImageView iv_notice_personal;
    @BindView(R.id.rel_mailbox_personal)
    RelativeLayout rel_mailbox_personal;
    @BindView(R.id.divide)
    View divide;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, null);
        ButterKnife.bind(this, view);
        if (!AppConfig.getSpUtils().getBoolean("isManager")) {
            rel_mailbox_personal.setVisibility(View.GONE);
            divide.setVisibility(View.GONE);
        } else {
            rel_mailbox_personal.setVisibility(View.VISIBLE);
            divide.setVisibility(View.VISIBLE);
        }
        //积分明细
        lin_credit_personal.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), CreditInfoActivity.class);
            startActivity(intent);
        });
        //积分排名
        lin_rank_personal.setOnClickListener(view12 -> {
            Intent intent = new Intent(getActivity(), CreditRankActivity.class);
            startActivity(intent);
        });
        //基本信息
        rel_baseinfo_personal.setOnClickListener(view13 -> {
            Intent intent = new Intent(getActivity(), BaseInfoActivity.class);
            startActivity(intent);
        });
        //收藏
        rel_collect_personal.setOnClickListener(view14 -> {
//                Intent intent = new Intent(getActivity(), CollectActivity.class);
//                startActivity(intent);
            Toast.makeText(getActivity(), "暂未开通", Toast.LENGTH_SHORT).show();
        });
        //意见反馈
        rel_feedback_personal.setOnClickListener(view15 -> {
            Intent intent = new Intent(getActivity(), FeedbackActivity.class);
            startActivity(intent);
        });
        //设置
        iv_setting_personal.setOnClickListener(view16 -> {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
        });
        //通知消息
        iv_notice_personal.setOnClickListener(view17 -> {
            Intent intent = new Intent(getActivity(), NoticeActivity.class);
            startActivity(intent);
        });
        rel_mailbox_personal.setOnClickListener(view18 -> {
            Intent intent = new Intent(getActivity(), MailboxListActivity.class);
            startActivity(intent);
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        GlideUtils.loadCircleImage(getActivity(), AppConfig.urlFormat(AppConfig.getSpUtils().getString("avatar")), iv_user_avatar_personal);
        tv_name_personal.setText(AppConfig.getSpUtils().getString("name"));
        tv_position_personal.setText(AppConfig.getSpUtils().getString("partyPost"));
    }
}
