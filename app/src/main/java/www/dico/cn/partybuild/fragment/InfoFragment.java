package www.dico.cn.partybuild.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.InfoView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.presenter.InfoPresenter;

@CreatePresenter(InfoPresenter.class)
public class InfoFragment extends AbstractFragment<InfoView, InfoPresenter> implements InfoView {
    private RadioGroup rg_info;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, null);
        rg_info = view.findViewById(R.id.rg_info);
        rg_info.check(R.id.rbt_news_info);
        rg_info.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbt_news_info://党建要闻
                        break;
                    case R.id.rbt_talk_info://习总讲话
                        break;
                    case R.id.rbt_history_info://国史党史
                        break;
                    case R.id.tv_vanguard_info://时代先锋
                        break;
                }
            }
        });
        return view;
    }
}
