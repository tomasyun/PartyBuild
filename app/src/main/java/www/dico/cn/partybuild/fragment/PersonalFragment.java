package www.dico.cn.partybuild.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.PersonalView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.presenter.PersonalPresenter;
@CreatePresenter(PersonalPresenter.class)
public class PersonalFragment extends AbstractFragment<PersonalView,PersonalPresenter> implements PersonalView {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_personal,null);
        return view;
    }
}
