package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.CollectListAdapter;
import www.dico.cn.partybuild.bean.CollectListBean;
import www.dico.cn.partybuild.modleview.CollectView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.bean.CollectsBean;
import www.dico.cn.partybuild.presenter.CollectPresenter;

//收藏
@CreatePresenter(CollectPresenter.class)
public class CollectActivity extends AbstractMvpActivity<CollectView, CollectPresenter> implements CollectView {
    private CollectListAdapter adapter;
    @FieldView(R.id.rv_collect)
    RecyclerView rv_collect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ViewFind.bind(this);
        //getMvpPresenter().collectsRequest("");
        rv_collect.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CollectListAdapter(this, R.layout.item_collect, collects());
        rv_collect.setAdapter(adapter);
    }

    public void goback(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }

    public List<CollectListBean> collects() {
        List<CollectListBean> list = new ArrayList<>();
        CollectListBean bean = new CollectListBean();
        bean.setContent("习近平出席金砖国家领导人第十次会晤并发表重要讲话");
        list.add(bean);
        return list;
    }
}
