package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.BaseProtocol;
import www.dico.cn.partybuild.bean.LeaderBean;
import www.dico.cn.partybuild.modleview.MailboxView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.MailboxPresenter;
import www.yuntdev.com.imitationiosdialoglibrary.ActionSheetDialog;
import www.yuntdev.com.imitationiosdialoglibrary.AlertDialog;

@CreatePresenter(MailboxPresenter.class)
public class MailboxActivity extends AbstractMvpActivity<MailboxView, MailboxPresenter> implements MailboxView {
    @BindView(R.id.tv_name_mail_box)
    TextView tv_name_mail_box;
    @BindView(R.id.et_content_mail_box)
    EditText et_content_mail_box;
    private String leaderId = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailbox);
        ButterKnife.bind(this);
    }

    public void goBackMailbox(View view) {
        this.finish();
    }

    //选择发送对象
    public void selectLeader(View view) {
        getMvpPresenter().doGetLeaderRequest(dialog);
    }

    //发送
    public void submitSuggest(View view) {
        String name = tv_name_mail_box.getText().toString().trim();
        String content = et_content_mail_box.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showToast("请选择发送对象");
        } else if (TextUtils.isEmpty(content)) {
            showToast("请填写您要发送内容");
        } else {
            getMvpPresenter().submitMailboxRequest(dialog, leaderId, content);
        }
    }

    @Override
    public void resultSuccess(String result) {
        BaseProtocol protocol = new Gson().fromJson(result, BaseProtocol.class);
        if (protocol.code.equals("0000")) {
            new AlertDialog(this).builder()
                    .setTitle("发送成功")
                    .setMsg("请耐心等待领导回复")
                    .setCancelable(false)
                    .setPositiveButton("知道了", view -> MailboxActivity.this.finish()).show();
        } else {
            showToast("服务器异常");
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }

    @Override
    public void getLeadersResultSuccess(String result) {
        LeaderBean bean = new Gson().fromJson(result, LeaderBean.class);
        if (bean.code.equals("0000")) {
            final List<LeaderBean.DataBean> list = bean.getData();
            if (null != list) {
                ActionSheetDialog dialog = new ActionSheetDialog(this).builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false);
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        final int position = i;
                        dialog.addSheetItem(list.get(i).getPosition(), ActionSheetDialog.SheetItemColor.Blue, which -> {
                            tv_name_mail_box.setText(list.get(position).getPosition());
                            leaderId = list.get(position).getId();
                        });
                    }
                    dialog.show();
                } else {
                    //无数据
                    showToast("没有可选的对象");
                }
            }
        } else {
            showToast("服务器异常");
        }
    }

    @Override
    public void getLeadersResultFailure(String result) {
        showToast(result);
    }

    @Override
    public void netWorkUnAvailable() {
        showToast("网络出现异常");
    }
}
