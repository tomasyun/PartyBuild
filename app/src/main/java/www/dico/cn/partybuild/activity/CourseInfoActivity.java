package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.BaseProtocol;
import www.dico.cn.partybuild.bean.CourseForm;
import www.dico.cn.partybuild.bean.CourseInfoBean;
import www.dico.cn.partybuild.modleview.CourseInfoView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.CourseInfoPresenter;
import www.dico.cn.partybuild.utils.StringUtils;

@CreatePresenter(CourseInfoPresenter.class)
public class CourseInfoActivity extends AbstractMvpActivity<CourseInfoView, CourseInfoPresenter> implements CourseInfoView {
    private CourseForm form;
    @BindView(R.id.tv_title_course_info)
    TextView tv_title_course_info;
    @BindView(R.id.tv_content_course_info)
    TextView tv_content_course_info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courseinfo);
        ButterKnife.bind(this);
        form = getParam();
        if (form != null)
            getMvpPresenter().doGetIntoCourseInfoRequest(form.courseId, form.taskId, "0");
        tv_title_course_info.post(new Runnable() {
            @Override
            public void run() {
                if (tv_title_course_info.getLineCount() == 1) {
                    tv_title_course_info.setGravity(Gravity.CENTER);
                } else {
                    tv_title_course_info.setGravity(Gravity.LEFT);
                }
            }
        });
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
        if (bean.code.equals("0000")) {
            if (bean.getData() != null) {
                tv_title_course_info.setText(bean.getData().getTitle());
                tv_content_course_info.setText(StringUtils.delHtmlTag(bean.getData().getContext()));
            }
        }
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
            showToast(protocol.msg);
            this.finish();
        }
    }

    @Override
    public void outResultFailure(String result) {
        showToast(result);
    }
}
