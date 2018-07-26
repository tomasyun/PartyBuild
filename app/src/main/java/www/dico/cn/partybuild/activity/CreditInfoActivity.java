package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.bean.CreditInfoBean;
import www.dico.cn.partybuild.presenter.CreditInfoPresenter;
import www.dico.cn.partybuild.modleview.CreditInfoView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;

//积分详情
@CreatePresenter(CreditInfoPresenter.class)
public class CreditInfoActivity extends AbstractMvpActivity<CreditInfoView, CreditInfoPresenter> implements CreditInfoView {
    @FieldView(R.id.tv_credit_info_score)
    TextView tv_credit_info_score;
    @FieldView(R.id.rg_credit_info)
    RadioGroup rg_credit_info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditinfo);
        ViewFind.bind(this);
        SpannableString scoreContent = new SpannableString("0分");
        scoreContent.setSpan(new AbsoluteSizeSpan(40), 0, scoreContent.length() - 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        scoreContent.setSpan(new AbsoluteSizeSpan(28), scoreContent.length() - 1, scoreContent.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_credit_info_score.setText(scoreContent);
        //getMvpPresenter().creditInfoRequest("");
        rg_credit_info.check(R.id.rbt_credit_info_all);
        rg_credit_info.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbt_credit_info_all:
                        break;
                    case R.id.rbt_credit_info_month:
                        break;
                    case R.id.rbt_credit_info_week:
                        break;
                    case R.id.rbt_credit_info_day:
                        break;
                }
            }
        });
    }

    public void goback(View view) {
        this.finish();
    }

    public void creditRule(View view) {
        goTo(CreditRuleActivity.class, null);
    }

    @Override
    public void resultSuccess(CreditInfoBean result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
