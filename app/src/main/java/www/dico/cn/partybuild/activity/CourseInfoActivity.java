package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import com.google.gson.Gson;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.BaseProtocol;
import www.dico.cn.partybuild.bean.CourseForm;
import www.dico.cn.partybuild.bean.CourseInfoBean;
import www.dico.cn.partybuild.modleview.CourseInfoView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.CourseInfoPresenter;

@CreatePresenter(CourseInfoPresenter.class)
public class CourseInfoActivity extends AbstractMvpActivity<CourseInfoView, CourseInfoPresenter> implements CourseInfoView {
    private CourseForm form;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courseinfo);
        form = getParam();
        if (form != null)
            getMvpPresenter().doGetIntoCourseInfoRequest(form.courseId, form.taskId, "0");
    }

    public void goBackCourseInfo(View view) {
        if (form != null)
            getMvpPresenter().doGetOutCourseInfoRequest(form.courseId, form.taskId, "1");
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (form != null) {
                getMvpPresenter().doGetOutCourseInfoRequest(form.courseId, form.taskId, "1");
            }
        }
        return false;
    }

    @Override
    public void intoResultSuccess(String result) {
        CourseInfoBean bean = new Gson().fromJson(result, CourseInfoBean.class);

    }

    @Override
    public void intoResultFailure(String result) {
        showToast(result);
    }

    @Override
    public void outResultSuccess(String result) {
        BaseProtocol protocol = new Gson().fromJson(result, BaseProtocol.class);
        if (protocol.code.equals("0000")) {
            this.finish();
        } else {
            showToast("");
        }
    }

    @Override
    public void outResultFailure(String result) {
        showToast(result);
    }
}
