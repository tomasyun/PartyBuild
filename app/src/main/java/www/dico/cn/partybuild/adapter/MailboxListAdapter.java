package www.dico.cn.partybuild.adapter;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.activity.MailboxListActivity;
import www.dico.cn.partybuild.bean.MailboxListBean;
import www.dico.cn.partybuild.utils.DateTimeUtils;
import www.dico.cn.partybuild.utils.GlideUtils;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class MailboxListAdapter extends CommonAdapter<MailboxListBean.DataBean> {

    public MailboxListAdapter(Context context, int layoutId, List<MailboxListBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, MailboxListBean.DataBean mailboxListBean, int position) {
        GlideUtils.loadCircleImage(AppManager.getManager().findActivity(MailboxListActivity.class), AppConfig.urlFormat("http://47.104.72.111/", mailboxListBean.getAvatar()), (ImageView) holder.getView(R.id.iv_avatar_mailbox_item));
        holder.setText(R.id.tv_name_mailbox_item, mailboxListBean.getName());
        String submitDate = mailboxListBean.getSubmitDate();
        submitDate = (submitDate == null) ? DateTimeUtils.getNow() : mailboxListBean.getSubmitDate();
        int minutes = Integer.valueOf(DateTimeUtils.getMinutes(submitDate, DateTimeUtils.getNow()));
        if (minutes < 60) {
            holder.setText(R.id.tv_date_mailbox_item, minutes + "分钟前");
        } else if (minutes > 60 && minutes <= 60 * 24) {
            holder.setText(R.id.tv_date_mailbox_item, minutes / 60 + "小时前");
        } else {
            holder.setText(R.id.tv_date_mailbox_item, minutes / (60 * 24) + "天前");
        }
        holder.setText(R.id.tv_content_mailbox_item, mailboxListBean.getContent());
    }
}
