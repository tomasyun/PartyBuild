package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
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
        adapter = new NoticeAdapter(this, R.layout.item_notice, notices());
        rv_notice.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                goTo(NoticeInfoActivity.class, null);
            }
        });
    }

    public void goBackNotice(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }

    public List<NoticeBean> notices() {
        List<NoticeBean> list = new ArrayList<>();
        NoticeBean bean = new NoticeBean();
        bean.setAvatar("");
        bean.setName("席沛锋");
        bean.setDate("5小时前");
        bean.setContent("为使我公司各部门工作顺利的开展，并且保证各部门之间能够衍接顺畅，有效地提高工作效率。经公司领导研究决定将定期召开公司员工例会。");
        list.add(bean);
        return list;
    }
}
