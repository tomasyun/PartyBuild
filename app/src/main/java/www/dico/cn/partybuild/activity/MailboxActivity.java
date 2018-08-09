package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.MailboxView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.MailboxPresenter;

@CreatePresenter(MailboxPresenter.class)
public class MailboxActivity extends AbstractMvpActivity<MailboxView, MailboxPresenter> implements MailboxView {
    @BindView(R.id.et_name_mail_box)
    EditText et_name_mail_box;
    @BindView(R.id.et_content_mail_box)
    EditText et_content_mail_box;

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

    }

    //发送
    public void submitSuggest(View view) {
        String name = et_name_mail_box.getText().toString().trim();
        String content = et_content_mail_box.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showToast("请选择发送对象");
        } else if (TextUtils.isEmpty(content)) {
            showToast("请填写您要发送内容");
        } else {

        }
    }

    @Override
    public void resultSuccess(String result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
