package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import butterknife.BindView;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.BaseProtocol;
import www.dico.cn.partybuild.bean.StudyTaskForm;
import www.dico.cn.partybuild.modleview.StudyResultView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.StudyResultPresenter;
import www.yuntdev.com.imitationiosdialoglibrary.AlertDialog;

@CreatePresenter(StudyResultPresenter.class)
public class StudyResultActivity extends AbstractMvpActivity<StudyResultView, StudyResultPresenter> implements StudyResultView {
    private StudyTaskForm form;
    @BindView(R.id.et_content_study_result)
    EditText et_content_study_result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studyresult);
        ViewFind.bind(this);
        form = getParam();
    }

    public void goBackStudyResult(View view) {
        this.finish();
    }

    public void submitStudyResult(View view) {
        String content = et_content_study_result.getText().toString().trim();
        if (content.equals("")) {
            showToast("请填写学习成果");
        } else {
            if (form != null)
                getMvpPresenter().doSubmitStudyResultRequest(form.taskId, content);
        }
    }

    @Override
    public void resultSuccess(String result) {
        BaseProtocol protocol=new Gson().fromJson(result,BaseProtocol.class);
        if (protocol.code.equals("0000")){
            new AlertDialog(this).builder()
                    .setTitle("提交成功")
                    .setMsg("恭喜您完成了本次学习任务")
                    .setCancelable(false)
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            goTo(StudyTaskActivity.class,null);
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
}