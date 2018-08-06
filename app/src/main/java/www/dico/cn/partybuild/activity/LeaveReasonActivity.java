package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.BaseProtocol;
import www.dico.cn.partybuild.modleview.LeaveReasonView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.LeaveReasonPresenter;
import www.yuntdev.com.imitationiosdialoglibrary.AlertDialog;

@CreatePresenter(LeaveReasonPresenter.class)
public class LeaveReasonActivity extends AbstractMvpActivity<LeaveReasonView, LeaveReasonPresenter> implements LeaveReasonView {
    @FieldView(R.id.et_content_leave_reason)
    EditText et_content_leave_reason;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leavereason);
        ViewFind.bind(this);
    }

    public void goback(View view) {
        this.finish();
    }

    //提交
    public void submit(View view) {
        String content = et_content_leave_reason.getText().toString();
        if (TextUtils.isEmpty(content)) {
            showToast("请假理由不能为空");
        } else {
            new AlertDialog(this).builder()
                    .setTitle("确定请假？")
                    .setMsg("请假后您将不能参加此次会议")
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    }).setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }).show();
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
