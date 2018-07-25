package www.dico.cn.partybuild.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.PayDuesView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.presenter.PayDuesPresenter;

//党费缴纳
@CreatePresenter(PayDuesPresenter.class)
public class PayDuesFragment extends AbstractFragment<PayDuesView, PayDuesPresenter> implements PayDuesView {
    private TextView tv_amount_pay_dues;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paydues, null);
        tv_amount_pay_dues = view.findViewById(R.id.tv_amount_pay_dues);
        SpannableString content=new SpannableString("20.00元");
        content.setSpan(new AbsoluteSizeSpan(40), 0, content.length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        content.setSpan(new AbsoluteSizeSpan(25), 5, content.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        content.setSpan(new ForegroundColorSpan(Color.parseColor("#fd9494")),5,content.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_amount_pay_dues.setText(content);
        return view;
    }
}
