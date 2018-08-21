package www.dico.cn.partybuild.adapter;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.activity.MailboxListActivity;
import www.dico.cn.partybuild.bean.MailboxListBean;
import www.dico.cn.partybuild.utils.GlideUtils;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class MailboxListAdapter extends CommonAdapter<MailboxListBean.DataBean> {

    public MailboxListAdapter(Context context, int layoutId, List<MailboxListBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, MailboxListBean.DataBean mailboxListBean, int position) {
        GlideUtils.loadImage(AppManager.getManager().findActivity(MailboxListActivity.class),mailboxListBean.getAvatar(), (ImageView) holder.getView(R.id.iv_avatar_mailbox_item));
        holder.setText(R.id.tv_name_mailbox_item, mailboxListBean.getName());
        holder.setText(R.id.tv_date_mailbox_item,mailboxListBean.getSubmitDate());
        holder.setText(R.id.tv_content_mailbox_item,mailboxListBean.getContent());
    }
}
