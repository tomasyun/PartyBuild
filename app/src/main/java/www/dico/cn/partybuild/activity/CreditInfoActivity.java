package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.CreditInfoAdapter;
import www.dico.cn.partybuild.bean.CreditInfoBean;
import www.dico.cn.partybuild.modleview.CreditInfoView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.CreditInfoPresenter;

//积分详情
@CreatePresenter(CreditInfoPresenter.class)
public class CreditInfoActivity extends AbstractMvpActivity<CreditInfoView, CreditInfoPresenter> implements CreditInfoView {
    @BindView(R.id.tv_credit_info_score)
    TextView tv_credit_info_score;
    @BindView(R.id.rg_credit_info)
    RadioGroup rg_credit_info;
    @BindView(R.id.rv_credit_info)
    RecyclerView rv_credit_info;
    private CreditInfoAdapter adapter;
    @BindView(R.id.tv_desc_credit_info)
    TextView tv_desc_credit_info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditinfo);
        ButterKnife.bind(this);
        SpannableString scoreContent = new SpannableString("0分");
        scoreContent.setSpan(new AbsoluteSizeSpan(40), 0, scoreContent.length() - 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        scoreContent.setSpan(new AbsoluteSizeSpan(28), scoreContent.length() - 1, scoreContent.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_credit_info_score.setText(scoreContent);
        getMvpPresenter().creditInfoRequest("0");
        rg_credit_info.check(R.id.rbt_credit_info_all);
        rg_credit_info.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbt_credit_info_all:
                        tv_desc_credit_info.setText("全部积分");
                        getMvpPresenter().creditInfoRequest("0");
                        break;
                    case R.id.rbt_credit_info_month:
                        tv_desc_credit_info.setText("本月积分");
                        getMvpPresenter().creditInfoRequest("1");
                        break;
                    case R.id.rbt_credit_info_week:
                        tv_desc_credit_info.setText("本周积分");
                        getMvpPresenter().creditInfoRequest("2");
                        break;
                    case R.id.rbt_credit_info_day:
                        tv_desc_credit_info.setText("本日积分");
                        getMvpPresenter().creditInfoRequest("3");
                        break;
                }
            }
        });

        rv_credit_info.setLayoutManager(new LinearLayoutManager(this));
    }

    public void goBackCreditInfo(View view) {
        this.finish();
    }

    public void creditRule(View view) {
        goTo(CreditRuleActivity.class, null);
    }

    @Override
    public void resultSuccess(String result) {
        CreditInfoBean bean = new Gson().fromJson(result, CreditInfoBean.class);
        if (bean.code.equals("0000")) {
            if (bean.getData() != null) {
                tv_credit_info_score.setText(bean.getData().getTotalScore());
                List<CreditInfoBean.DataBean.CreditListBean> beans = bean.getData().getCreditList();
                if (null != beans && beans.size() > 0) {
                    for (int i = 0; i < beans.size(); i++) {
                        adapter = new CreditInfoAdapter(this, R.layout.item_credit_info, beans);
                        rv_credit_info.setAdapter(adapter);
                    }
                }
            }
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
