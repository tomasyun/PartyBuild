package www.dico.cn.partybuild.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.SignInView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.presenter.SignInPresenter;
//签到
@CreatePresenter(SignInPresenter.class)
public class SignInFragment extends AbstractFragment<SignInView,SignInPresenter> implements SignInView{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_signin,null);
        return view;
    }
}
