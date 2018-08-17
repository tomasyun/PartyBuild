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
import www.dico.cn.partybuild.utils.SizeUtils;
import www.yuntdev.com.imitationiosdialoglibrary.AlertDialog;

@CreatePresenter(MailboxPresenter.class)
public class MailboxActivity extends AbstractMvpActivity<MailboxView, MailboxPresenter> implements MailboxView, MailboxPresenter.LeaderSelectInterface {
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
        getMvpPresenter().setSelectInterface(this);
    }

    public void goBackMailbox(View view) {
        this.finish();
    }

    //选择发送对象
    public void selectLeader(View view) {
        getMvpPresenter().doGetLeaderRequest();
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
            getMvpPresenter().submitMailboxRequest(leaderId, content);
        }
    }

    @Override
    public void resultSuccess(String result) {
        BaseProtocol protocol=new Gson().fromJson(result,BaseProtocol.class);
        if (protocol.code.equals("0000")){
            new AlertDialog(this).builder()
                    .setTitle("发送成功")
                    .setMsg("请耐心等待领导回复")
                    .setCancelable(false)
                    .setPositiveButton("确定", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MailboxActivity.this.finish();
                }
            }).show();
        }else {
            showToast(protocol.msg);
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
            List<LeaderBean.DataBean> list = bean.getData();
            if (null != list && list.size() > 0) {
                getMvpPresenter().setUpLeaderSelectPopupWindow(this, list).showAsDropDown(tv_name_mail_box, tv_name_mail_box.getWidth() - SizeUtils.dp2px(this, 150), SizeUtils.dp2px(this, 10));
            }
        }
    }

    @Override
    public void getLeadersResultFailure(String result) {
        showToast(result);
    }

    @Override
    public void select(String id, String position, String name) {
        tv_name_mail_box.setText(position + " " + name);
        leaderId = id;
    }
}
