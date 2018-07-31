package www.dico.cn.partybuild.adapter;

import android.content.Context;

import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.MailboxListBean;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class MailboxListAdapter extends CommonAdapter<MailboxListBean> {

    public MailboxListAdapter(Context context, int layoutId, List<MailboxListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, MailboxListBean mailboxListBean, int position) {
        holder.setText(R.id.tv_name_mailbox_item,mailboxListBean.getName());
    }
}
