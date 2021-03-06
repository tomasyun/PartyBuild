package www.dico.cn.partybuild.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.PayDuesView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.PayDuesPresenter;

@CreatePresenter(PayDuesPresenter.class)
public class PayDuesActivity extends AbstractMvpActivity<PayDuesView, PayDuesPresenter> implements PayDuesView {
    @BindView(R.id.tv_amount_pay_dues)
    TextView tv_amount_pay_dues;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paydues);
        ButterKnife.bind(this);
        SpannableString content = new SpannableString("20.00元");
        content.setSpan(new AbsoluteSizeSpan(40), 0, content.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        content.setSpan(new AbsoluteSizeSpan(25), 5, content.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        content.setSpan(new ForegroundColorSpan(Color.parseColor("#fd9494")), 5, content.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_amount_pay_dues.setText(content);
    }

    public void goBackPayDues(View view) {
        this.finish();
    }

    //立即缴纳
    public void payment(View view) {
        showToast("暂未开通");
    }

    //缴费规则
    public void gotoPayrule(View view) {
        goTo(PaymentRuleActivity.class, null);
    }
}
