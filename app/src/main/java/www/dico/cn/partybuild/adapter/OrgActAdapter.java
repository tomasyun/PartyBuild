package www.dico.cn.partybuild.adapter;

import android.content.Context;

import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.OrgActBean;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class OrgActAdapter extends CommonAdapter<OrgActBean> {

    public OrgActAdapter(Context context, int layoutId, List<OrgActBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, OrgActBean orgActBean, int position) {
        holder.setText(R.id.tv_date_meeting_item, orgActBean.getDate());
    }
}
