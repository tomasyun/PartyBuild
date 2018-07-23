package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.widget.TextView;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.InfodetailsView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.InfodetailsPresenter;

//资讯详情
@CreatePresenter(InfodetailsPresenter.class)
public class InfodetailsActivity extends AbstractMvpActivity<InfodetailsView,InfodetailsPresenter>{
    @FieldView(R.id.tv_info_title)
    TextView tv_info_title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewFind.bind(this);
        tv_info_title.post(new Runnable() {
            @Override
            public void run() {
                if (tv_info_title.getLineCount()==1){
                    tv_info_title.setGravity(Gravity.CENTER);
                }else {
                    tv_info_title.setGravity(Gravity.LEFT);
                }
            }
        });
    }
}
