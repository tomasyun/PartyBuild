package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.SkipForm;

public class SignUpSuccessActivity extends BaseActivity {
    @BindView(R.id.tv_sign_up_success_desc)
    TextView tv_sign_up_success_desc;
    private SkipForm form;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupsuccess);
        ButterKnife.bind(this);
        form = getParam();
        if (form != null) {
            if (form.skipType.equals("0")) {
                tv_sign_up_success_desc.setText("注意开会时间和地点,千万不要迟到哦~~");
            } else {
                tv_sign_up_success_desc.setText("注意活动时间和地点,千万不要迟到哦~~");
            }
        }
    }

    public void goBackSignUpSuccess(View view) {
        this.finish();
    }

    //完成
    public void signUpComplete(View view) {
        this.finish();
    }
}
