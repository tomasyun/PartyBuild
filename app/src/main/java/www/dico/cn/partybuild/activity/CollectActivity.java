package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.CollectListAdapter;
import www.dico.cn.partybuild.bean.CollectListBean;
import www.dico.cn.partybuild.modleview.CollectView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.CollectPresenter;

//收藏
@CreatePresenter(CollectPresenter.class)
public class CollectActivity extends AbstractMvpActivity<CollectView, CollectPresenter> implements CollectView {
    @BindView(R.id.rv_collect)
    RecyclerView rv_collect;
    private CollectListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);
        //getMvpPresenter().collectsRequest("");
        rv_collect.setLayoutManager(new LinearLayoutManager(this));
    }

    public void goBackCollect(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        CollectListBean bean = new Gson().fromJson(result, CollectListBean.class);
        if (bean.code.equals("0000")) {

        } else {
            showToast("服务器异常");
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
