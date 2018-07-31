package www.dico.cn.partybuild.adapter;

import android.content.Context;

import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.VoteListBean;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class VoteListAdapter extends CommonAdapter<VoteListBean> {
    public VoteListAdapter(Context context, int layoutId, List<VoteListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, VoteListBean voteListBean, int position) {
        holder.setText(R.id.tv_title_vote_item,voteListBean.getTitle());
    }
}
