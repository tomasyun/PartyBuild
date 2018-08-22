package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.CreditRankAdapter;
import www.dico.cn.partybuild.bean.CreditRankBean;
import www.dico.cn.partybuild.modleview.CreditRankView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.CreditRankPresenter;

//积分排名
@CreatePresenter(CreditRankPresenter.class)
public class CreditRankActivity extends AbstractMvpActivity<CreditRankView, CreditRankPresenter> implements CreditRankView {
    @BindView(R.id.tv_user_rank_num)
    TextView tv_user_rank_num;//名次
    @BindView(R.id.iv_user_icon_rank)
    ImageView iv_user_icon_rank;//个人头像
    @BindView(R.id.tv_user_rank_score)
    TextView tv_user_rank_score;//积分
    //    @BindView(R.id.rg_rank)
//    RadioGroup rg_rank;
    @BindView(R.id.rv_rank)
    RecyclerView rv_rank;
    private CreditRankAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditrank);
        ButterKnife.bind(this);
        //getMvpPresenter().creditRankRequest("");
//        rg_rank.check(R.id.rbt_rank_all);
//        rg_rank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
//                switch (checkedId) {
//                    case R.id.rbt_rank_all:
//                        break;
//                    case R.id.rbt_rank_master:
//                        break;
//                    case R.id.rbt_rank_branch:
//                        break;
//                    case R.id.rbt_rank_group:
//                        break;
//                }
//            }
//        });
        rv_rank.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpPresenter().creditRankRequest();
    }

    public void goBackRank(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        CreditRankBean bean = new Gson().fromJson(result, CreditRankBean.class);
        if (bean.code.equals("0000")) {
            if (bean.getData() != null) {
                SpannableString numContent = new SpannableString(bean.getData().getMrank() + "名");
                numContent.setSpan(new AbsoluteSizeSpan(40), 0, numContent.length() - 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                numContent.setSpan(new AbsoluteSizeSpan(28), numContent.length() - 1, numContent.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv_user_rank_num.setText(numContent);
                SpannableString scoreContent = new SpannableString(bean.getData().getMscore() + "分");
                scoreContent.setSpan(new AbsoluteSizeSpan(40), 0, scoreContent.length() - 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                scoreContent.setSpan(new AbsoluteSizeSpan(28), scoreContent.length() - 1, scoreContent.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv_user_rank_score.setText(scoreContent);
                List<CreditRankBean.DataBean.CreditInfoListBean> list = bean.getData().getCreditInfoList();
                if (null != list && list.size() > 0) {
                    adapter = new CreditRankAdapter(this, R.layout.item_credit_rank, list);
                    rv_rank.setAdapter(adapter);
                } else {
                    //空白页面
                }
            }
        } else {
            showToast(bean.msg);
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
