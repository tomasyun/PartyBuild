package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.CreditRankView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.persistance.CreditRankBean;
import www.dico.cn.partybuild.presenter.CreditRankPresenter;

//积分排名
@CreatePresenter(CreditRankPresenter.class)
public class CreditRankActivity extends AbstractMvpActivity<CreditRankView, CreditRankPresenter> implements CreditRankView {
    @FieldView(R.id.tv_user_rank_num)
    TextView tv_user_rank_num;//名次
    @FieldView(R.id.iv_user_icon_rank)
    ImageView iv_user_icon_rank;//个人头像
    @FieldView(R.id.tv_user_rank_score)
    TextView tv_user_rank_score;//积分
    @FieldView(R.id.rg_rank)
    RadioGroup rg_rank;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditrank);
        ViewFind.bind(this);

        SpannableString numContent = new SpannableString("12名");
        numContent.setSpan(new AbsoluteSizeSpan(40), 0, numContent.length() - 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        numContent.setSpan(new AbsoluteSizeSpan(28), numContent.length() - 1, numContent.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_user_rank_num.setText(numContent);
        SpannableString  scoreContent= new SpannableString("520分");
        scoreContent.setSpan(new AbsoluteSizeSpan(40), 0, scoreContent.length() - 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        scoreContent.setSpan(new AbsoluteSizeSpan(28), scoreContent.length() - 1, scoreContent.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_user_rank_score.setText(scoreContent);

        //getMvpPresenter().creditRankRequest("");
        rg_rank.check(R.id.rbt_rank_all);
        rg_rank.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbt_rank_all:
                        break;
                    case R.id.rbt_rank_master:
                        break;
                    case R.id.rbt_rank_branch:
                        break;
                    case R.id.rbt_rank_group:
                        break;
                }
            }
        });
    }

    public void goback(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(CreditRankBean result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
