package www.dico.cn.partybuild.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.PayDuesView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.presenter.PayDuesPresenter;
//党费缴纳
@CreatePresenter(PayDuesPresenter.class)
public class PayDuesFragment extends AbstractFragment<PayDuesView,PayDuesPresenter> implements PayDuesView {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_paydues,null);
        return view;
    }
}
