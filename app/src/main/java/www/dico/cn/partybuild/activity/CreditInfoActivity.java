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
    @BindView(R.id.tv_desc_credit_info)
    TextView tv_desc_credit_info;
    @BindView(R.id.credit_info_empty_data)
    View credit_info_empty_data;
    @BindView(R.id.credit_info_net_error)
    View credit_info_net_error;
    private CreditInfoAdapter adapter;
    private int position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditinfo);
        ButterKnife.bind(this);
        rg_credit_info.check(R.id.rbt_credit_info_all);
        rg_credit_info.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbt_credit_info_all:
                        position = 0;
                        createRequest(position);
                        break;
                    case R.id.rbt_credit_info_month:
                        position = 1;
                        createRequest(position);
                        break;
                    case R.id.rbt_credit_info_week:
                        position = 2;
                        createRequest(position);
                        break;
                    case R.id.rbt_credit_info_day:
                        position = 3;
                        createRequest(position);
                        break;
                }
            }
        });

        rv_credit_info.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        createRequest(position);
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
                SpannableString scoreContent = new SpannableString(bean.getData().getTotalScore() + "分");
                scoreContent.setSpan(new AbsoluteSizeSpan(45), 0, scoreContent.length() - 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                scoreContent.setSpan(new AbsoluteSizeSpan(35), scoreContent.length() - 1, scoreContent.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv_credit_info_score.setText(scoreContent);
                List<CreditInfoBean.DataBean.CreditListBean> beans = bean.getData().getCreditList();
                if (null != beans && beans.size() > 0) {
                    for (int i = 0; i < beans.size(); i++) {
                        rv_credit_info.setVisibility(View.VISIBLE);
                        credit_info_empty_data.setVisibility(View.GONE);
                        credit_info_net_error.setVisibility(View.GONE);
                        adapter = new CreditInfoAdapter(this, R.layout.item_credit_info, beans);
                        rv_credit_info.setAdapter(adapter);
                    }
                } else {
                    //空白页面
                    rv_credit_info.setVisibility(View.GONE);
                    credit_info_empty_data.setVisibility(View.VISIBLE);
                    credit_info_net_error.setVisibility(View.GONE);
                }
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
    public void netWorkUnAvailable() {
        rv_credit_info.setVisibility(View.GONE);
        credit_info_empty_data.setVisibility(View.GONE);
        credit_info_net_error.setVisibility(View.VISIBLE);
        credit_info_net_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createRequest(position);
            }
        });
    }

    public void createRequest(int position) {
        switch (position) {
            case 0:
                tv_desc_credit_info.setText("全部积分");
                getMvpPresenter().creditInfoRequest(dialog, position);
                break;
            case 1:
                tv_desc_credit_info.setText("本月积分");
                getMvpPresenter().creditInfoRequest(dialog, position);
                break;
            case 2:
                tv_desc_credit_info.setText("本周积分");
                getMvpPresenter().creditInfoRequest(dialog, position);
                break;
            case 3:
                tv_desc_credit_info.setText("本日积分");
                getMvpPresenter().creditInfoRequest(dialog, position);
                break;
        }
    }
}
