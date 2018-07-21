package www.dico.cn.partybuild.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.InformationView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.presenter.InformationPresenter;

@CreatePresenter(InformationPresenter.class)
public class InformationFragment extends AbstractFragment<InformationView, InformationPresenter> {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, null);
        return view;
    }
}
