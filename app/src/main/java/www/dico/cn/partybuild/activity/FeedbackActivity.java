package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.BaseProtocol;
import www.dico.cn.partybuild.modleview.FeedbackView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.FeedbackPresenter;

//意见反馈
@CreatePresenter(FeedbackPresenter.class)
public class FeedbackActivity extends AbstractMvpActivity<FeedbackView, FeedbackPresenter> implements FeedbackView {
    @FieldView(R.id.et_feed_back_content)
    EditText et_feed_back_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ViewFind.bind(this);
    }

    public void goback(View view) {
        this.finish();
    }

    //提交意见
    public void submit(View view) {
        String content = et_feed_back_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            showToast("请填写意见");
        } else {
            getMvpPresenter().feedBackSubmit("", content);
        }
    }

    @Override
    public void resultSuccess(BaseProtocol result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
