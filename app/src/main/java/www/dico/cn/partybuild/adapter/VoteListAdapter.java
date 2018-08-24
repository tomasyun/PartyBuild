package www.dico.cn.partybuild.adapter;

import android.content.Context;

import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.VoteListBean;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class VoteListAdapter extends CommonAdapter<VoteListBean.DataBean> {
    public VoteListAdapter(Context context, int layoutId, List<VoteListBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, VoteListBean.DataBean bean, int position) {
        holder.setText(R.id.tv_title_vote_item, bean.getTitle());
        holder.setText(R.id.tv_date_vote_item, bean.getLimitDate());
        holder.setText(R.id.tv_population_vote_item, bean.getVoterNum());
    }
}
