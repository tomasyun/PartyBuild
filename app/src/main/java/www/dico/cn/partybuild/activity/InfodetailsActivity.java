package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.CourseForm;
import www.dico.cn.partybuild.bean.InfodetailBean;
import www.dico.cn.partybuild.modleview.InfodetailsView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.InfodetailsPresenter;

//资讯详情
@CreatePresenter(InfodetailsPresenter.class)
public class InfodetailsActivity extends AbstractMvpActivity<InfodetailsView, InfodetailsPresenter> implements InfodetailsView {
    @BindView(R.id.tv_info_title)
    TextView tv_info_title;
    private CourseForm form;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infodetails);
        ButterKnife.bind(this);
        form = getParam();
        getMvpPresenter().infoDetailsRequest(form.courseId, form.taskId, "0");
        tv_info_title.post(new Runnable() {
            @Override
            public void run() {
                if (tv_info_title.getLineCount() == 1) {
                    tv_info_title.setGravity(Gravity.CENTER);
                } else {
                    tv_info_title.setGravity(Gravity.LEFT);
                }
            }
        });
    }

    public void goBackInfodetail(View view) {
        getMvpPresenter().infoDetailsRequest(form.courseId, form.taskId, "1");
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        InfodetailBean bean = new Gson().fromJson(result, InfodetailBean.class);
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
