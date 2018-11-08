package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.SkipForm;

public class SuccessTipsActivity extends BaseActivity {
    @BindView(R.id.tv_success_tips_title)
    TextView tv_success_tips_title;
    @BindView(R.id.tv_success_tips_desc)
    TextView tv_success_tips_desc;
    @BindView(R.id.tv_success_tips_prompt)
    TextView tv_success_tips_prompt;
    private SkipForm form;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successtips);
        ButterKnife.bind(this);
        form = getParam();
        if (form != null) {
            //0:会议报名成功 1:活动报名成功 2:请假成功 3:投票成功 4:调查提交成功
            switch (form.skip) {
                case 0:
                    tv_success_tips_title.setText("报名成功");
                    tv_success_tips_desc.setText("恭喜您!报名成功");
                    tv_success_tips_prompt.setText("注意开会时间和地点,千万不要迟到哦");
                    break;
                case 1:
                    tv_success_tips_title.setText("报名成功");
                    tv_success_tips_desc.setText("恭喜您!报名成功");
                    tv_success_tips_prompt.setText("注意活动时间和地点,千万不要迟到哦");
                    break;
                case 2:
                    tv_success_tips_title.setText("请假成功");
                    tv_success_tips_desc.setText("成功提交请假条");
                    tv_success_tips_prompt.setText("下次请尽量参加会议哦");
                    break;
                case 3:
                    tv_success_tips_title.setText("投票成功");
                    tv_success_tips_desc.setText("投票成功!");
                    tv_success_tips_prompt.setText("感谢您的参与");
                    break;
                case 4:
                    tv_success_tips_title.setText("提交成功");
                    tv_success_tips_desc.setText("提交成功!");
                    tv_success_tips_prompt.setText("感谢您的宝贵时间");
                    break;
            }
        }
    }

    public void goBackSuccessTips(View view) {
        this.finish();
    }

    //完成
    public void complete(View view) {
        this.finish();
    }
}
