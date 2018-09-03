package www.dico.cn.partybuild.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.widget.TextView;

import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.CreditInfoBean;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class CreditInfoAdapter extends CommonAdapter<CreditInfoBean.DataBean.CreditListBean> {
    public CreditInfoAdapter(Context context, int layoutId, List<CreditInfoBean.DataBean.CreditListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, CreditInfoBean.DataBean.CreditListBean creditInfoBean, int position) {
        holder.setText(R.id.tv_credit_info_title_item, creditInfoBean.getTitle());
        holder.setText(R.id.tv_credit_info_date_item, creditInfoBean.getAuditDate());
        SpannableString score = new SpannableString("+ " + creditInfoBean.getScore());
        score.setSpan(new AbsoluteSizeSpan(35), 0, 2, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        score.setSpan(new AbsoluteSizeSpan(45), 2, score.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView tv_credit_info_score_item = holder.getView(R.id.tv_credit_info_score_item);
        tv_credit_info_score_item.setText(score);
    }
}
