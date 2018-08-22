package www.dico.cn.partybuild.adapter;

import android.content.Context;

import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.LeaderBean;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class LeaderSelectAdapter extends CommonAdapter<LeaderBean.DataBean> {
    public LeaderSelectAdapter(Context context, int layoutId, List<LeaderBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, LeaderBean.DataBean dataBean, int position) {
        holder.setText(R.id.tv_leader_position_pop, dataBean.getPosition());
        holder.setText(R.id.tv_leader_name_pop, dataBean.getName());
    }
}
