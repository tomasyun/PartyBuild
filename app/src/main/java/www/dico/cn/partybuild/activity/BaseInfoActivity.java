package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.BaseInfoBean;
import www.dico.cn.partybuild.modleview.BaseInfoView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.BaseInfoPresenter;
import www.dico.cn.partybuild.utils.GlideUtils;

//党员个人基本信息
@CreatePresenter(BaseInfoPresenter.class)
public class BaseInfoActivity extends AbstractMvpActivity<BaseInfoView, BaseInfoPresenter> implements BaseInfoView {
    @BindView(R.id.iv_user_head_icon)
    ImageView iv_user_head_icon;//头像
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;//用户名
    @BindView(R.id.tv_user_real_name)
    TextView tv_user_real_name;//姓名
    @BindView(R.id.tv_user_number)
    TextView tv_user_number;//党员编号
    @BindView(R.id.tv_user_birth)
    TextView tv_user_birth;//出生日期
    @BindView(R.id.tv_user_phone)
    TextView tv_user_phone;//联系电话
    @BindView(R.id.tv_user_nation)
    TextView tv_user_nation;//民族
    @BindView(R.id.tv_user_native_place)
    TextView tv_user_native_place;//籍贯
    @BindView(R.id.tv_user_edu)
    TextView tv_user_edu;//学历
    @BindView(R.id.tv_user_brief)
    TextView tv_user_brief;//个人简介

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseinfo);
        ButterKnife.bind(this);
        getMvpPresenter().getBaseInfoRequest(dialog);
    }

    public void goBackBaseInfo(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        BaseInfoBean bean = new Gson().fromJson(result, BaseInfoBean.class);
        if (bean.code.equals("0000")) {
            if (bean.getData() != null) {
                GlideUtils.loadCircleImage(this, AppConfig.urlFormat(bean.getData().getAvatar()), iv_user_head_icon);
                tv_user_name.setText(bean.getData().getUsername());
                tv_user_real_name.setText(bean.getData().getName());
                tv_user_number.setText(bean.getData().getPartyNo());
                tv_user_birth.setText(bean.getData().getBirthday());
                tv_user_phone.setText(bean.getData().getPhone());
                tv_user_nation.setText(bean.getData().getEthnic());
                tv_user_native_place.setText(bean.getData().getBirthPlace());
                tv_user_edu.setText(bean.getData().getEducation());
                tv_user_brief.setText(bean.getData().getBrief());
            }
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
