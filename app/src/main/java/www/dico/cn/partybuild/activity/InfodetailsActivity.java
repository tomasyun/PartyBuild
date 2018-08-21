package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.CommentAdapter;
import www.dico.cn.partybuild.bean.InfodetailBean;
import www.dico.cn.partybuild.bean.InfodetailForm;
import www.dico.cn.partybuild.modleview.InfodetailsView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.InfodetailsPresenter;
import www.dico.cn.partybuild.utils.StringUtils;

//资讯详情
@CreatePresenter(InfodetailsPresenter.class)
public class InfodetailsActivity extends AbstractMvpActivity<InfodetailsView, InfodetailsPresenter> implements InfodetailsView {
    @BindView(R.id.tv_info_title)
    TextView tv_info_title;
    private InfodetailForm form;
    @BindView(R.id.tv_info_source)
    TextView tv_info_source;
    @BindView(R.id.tv_info_date)
    TextView tv_info_date;
    @BindView(R.id.tv_info_content)
    TextView tv_info_content;
    @BindView(R.id.rv_info_comment)
    RecyclerView rv_info_comment;
    private CommentAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infodetails);
        ButterKnife.bind(this);
        form = getParam();
        if (form != null)
            getMvpPresenter().infoDetailsRequest(form.infoId);
        tv_info_title.post(new Runnable() {
            @Override
            public void run() {
                if (tv_info_title.getLineCount() == 1) {
                    tv_info_title.setGravity(Gravity.CENTER);
                } else {
                    tv_info_title.setGravity(Gravity.LEFT);
                }
            }
        });
        rv_info_comment.setLayoutManager(new LinearLayoutManager(this));
    }

    public void goBackInfodetail(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        InfodetailBean bean = new Gson().fromJson(result, InfodetailBean.class);
        if (bean.code.equals("0000")) {
            if (bean.getData() != null) {
                tv_info_title.setText(bean.getData().getTitle());
                tv_info_source.setText(bean.getData().getPublicUnit());
                tv_info_date.setText(bean.getData().getPublishDate());
                tv_info_content.setText(StringUtils.delHtmlTag(bean.getData().getContent()));
                List<InfodetailBean.DataBean.CommitListBean> beans = bean.getData().getCommitList();
                if (null != beans && beans.size() > 0) {
                    adapter = new CommentAdapter(beans);
                    View footerView = LayoutInflater.from(InfodetailsActivity.this).inflate(R.layout.comment_footer, null);
                    adapter.setFooterView(footerView);
                    rv_info_comment.setAdapter(adapter);
                } else {
                    //空白页面
                }
            }
        } else {
            showToast(bean.msg);
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }

    @Override
    public void submitCommentSuccess(String result) {

    }

    @Override
    public void submitCommentFailure(String result) {
        showToast(result);
    }
}
