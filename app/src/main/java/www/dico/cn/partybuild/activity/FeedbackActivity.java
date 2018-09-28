package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.BaseProtocol;
import www.dico.cn.partybuild.modleview.FeedbackView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.FeedbackPresenter;
import www.yuntdev.com.imitationiosdialoglibrary.AlertDialog;

//意见反馈
@CreatePresenter(FeedbackPresenter.class)
public class FeedbackActivity extends AbstractMvpActivity<FeedbackView, FeedbackPresenter> implements FeedbackView {
    @BindView(R.id.et_feed_back_content)
    EditText et_feed_back_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
    }

    public void goBackFeedBack(View view) {
        this.finish();
    }

    //提交意见
    public void submit(View view) {
        String content = et_feed_back_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            showToast("请填写意见");
        } else {
            getMvpPresenter().feedBackSubmit(dialog, content);
        }
    }

    @Override
    public void resultSuccess(String result) {
        BaseProtocol protocol = new Gson().fromJson(result, BaseProtocol.class);
        if (protocol.code.equals("0000")) {
            new AlertDialog(this).builder()
                    .setTitle("提交成功")
                    .setMsg("感谢您此次提交的意见，我们会尽快予您回复。")
                    .setCancelable(false)
                    .setPositiveButton("知道了", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            FeedbackActivity.this.finish();
                        }
                    }).show();
        } else {
            showToast("服务器异常");
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }

    @Override
    public void netWorkUnAvailable() {
        showToast("网络出现异常");
    }
}
