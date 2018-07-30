package www.dico.cn.partybuild.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.SignInView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.presenter.SignInPresenter;
//签到
@CreatePresenter(SignInPresenter.class)
public class SignInFragment extends AbstractFragment<SignInView,SignInPresenter> implements SignInView{
    private ImageView iv_conference_theme_pic;//主题图片
    private TextView tv_sign_in_address;//地址
    private TextView tv_sign_in_date;//日期
    private TextView tv_sign_in_enable;//立即签到
    private TextView tv_sign_in_count_down;//倒计时显示
    private ImageView iv_sign_in_enable;//
    private TextView tv_sign_in_tips;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_signin,null);
        iv_conference_theme_pic=view.findViewById(R.id.iv_conference_theme_pic);
        tv_sign_in_address=view.findViewById(R.id.tv_sign_in_address);
        tv_sign_in_date=view.findViewById(R.id.tv_sign_in_date);
        tv_sign_in_enable=view.findViewById(R.id.tv_sign_in_enable);
        tv_sign_in_count_down=view.findViewById(R.id.tv_sign_in_count_down);
        iv_sign_in_enable=view.findViewById(R.id.iv_sign_in_enable);
        tv_sign_in_tips=view.findViewById(R.id.tv_sign_in_tips);
        return view;
    }
}
