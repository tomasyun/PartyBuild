package www.dico.cn.partybuild.adapter;

import android.content.Context;
import android.text.Html;
import android.widget.TextView;

import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.CollectListBean;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class CollectListAdapter extends CommonAdapter<CollectListBean> {
    public CollectListAdapter(Context context, int layoutId, List<CollectListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, CollectListBean collectListBean, int position) {
        holder.setText(R.id.tv_title_collect_item,collectListBean.getContent());
        TextView tv_cancel_collect_item=holder.getView(R.id.tv_cancel_collect_item);
        tv_cancel_collect_item.setText(Html.fromHtml("取消"+"<br>"+"收藏"));
    }
}
