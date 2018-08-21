package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.NoticeAdapter;
import www.dico.cn.partybuild.bean.NoticeBean;
import www.dico.cn.partybuild.modleview.NoticeView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.NoticePresenter;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;

//通知
@CreatePresenter(NoticePresenter.class)
public class NoticeActivity extends AbstractMvpActivity<NoticeView, NoticePresenter> implements NoticeView {
    @BindView(R.id.rv_notice)
    RecyclerView rv_notice;
    private NoticeAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ButterKnife.bind(this);
        rv_notice.setLayoutManager(new LinearLayoutManager(this));
        getMvpPresenter().noticeRequest();
    }

    public void goBackNotice(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        NoticeBean bean = new Gson().fromJson(result, NoticeBean.class);
        if (bean.code.equals("0000")) {
            List<NoticeBean.DataBean> list = bean.getData();
            if (null != list && list.size() > 0) {
                adapter = new NoticeAdapter(this, R.layout.item_notice, bean.getData());
                rv_notice.setAdapter(adapter);
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        goTo(NoticeInfoActivity.class, null);
                    }
                });
            } else {
                //空白页面
            }
        } else {
            showToast(bean.msg);
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
