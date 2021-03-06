package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.NoticeInfoCommentAdapter;
import www.dico.cn.partybuild.bean.BaseProtocol;
import www.dico.cn.partybuild.bean.NoticeForm;
import www.dico.cn.partybuild.bean.NoticeInfoBean;
import www.dico.cn.partybuild.modleview.NoticeInfoView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.NoticeInfoPresenter;
import www.dico.cn.partybuild.utils.SizeUtils;
import www.dico.cn.partybuild.utils.StringUtils;

@CreatePresenter(NoticeInfoPresenter.class)
public class NoticeInfoActivity extends AbstractMvpActivity<NoticeInfoView, NoticeInfoPresenter> implements NoticeInfoView {
    @BindView(R.id.notice_info_reply_comment)
    View notice_info_reply_comment;
    @BindView(R.id.tv_title_notice_info)
    TextView tv_title_notice_info;
    @BindView(R.id.tv_name_notice_info)
    TextView tv_name_notice_info;
    @BindView(R.id.tv_date_notice_info)
    TextView tv_date_notice_info;
    @BindView(R.id.tv_content_notice_info)
    TextView tv_content_notice_info;
//    @BindView(R.id.rv_notice_info)
//    RecyclerView rv_notice_info;
    @BindView(R.id.sv_notice_info)
    ScrollView sv_notice_info;
    @BindView(R.id.lin_notice_info)
    LinearLayout lin_notice_info;
    private NoticeForm form;
    private EditText et_reply_comment;
    private NoticeInfoCommentAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticeinfo);
        ButterKnife.bind(this);
//        rv_notice_info.setLayoutManager(new LinearLayoutManager(this));
        form = getParam();
        if (form != null) {
            switch (form.isReply) {
                case "0"://不需要回复
                    notice_info_reply_comment.setVisibility(View.GONE);
                    break;
                case "1"://需要回复
                    notice_info_reply_comment.setVisibility(View.VISIBLE);
                    et_reply_comment = notice_info_reply_comment.findViewById(R.id.et_reply_comment);
                    TextView tv_reply_comment = notice_info_reply_comment.findViewById(R.id.tv_reply_comment);
                    ImageView iv_reply_comment = notice_info_reply_comment.findViewById(R.id.iv_reply_comment);
                    tv_reply_comment.setOnClickListener(view -> {
                        if (et_reply_comment.getText().toString().trim().equals("")) {
                            showToast("请填写回复内容");
                        } else {
                            getMvpPresenter().doSubmitCommentRequest(dialog, "1", form.id, et_reply_comment.getText().toString().trim());
                        }
                    });

                    et_reply_comment.setOnEditorActionListener((textView, actionId, keyEvent) -> {
                        if (actionId == EditorInfo.IME_ACTION_SEND) {
                            if (et_reply_comment.getText().toString().trim().equals("")) {
                                showToast("请填写回复内容");
                            } else {
                                getMvpPresenter().doSubmitCommentRequest(dialog, "1", form.id, et_reply_comment.getText().toString().trim());
                                return true;
                            }
                        }
                        return false;
                    });

                    iv_reply_comment.setOnClickListener(view -> showToast("暂未开通"));
                    break;
            }
        }
        getMvpPresenter().doNoticeInfoRequest(dialog, form.id);
    }

    public void goBackNoticeInfo(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        NoticeInfoBean bean = new Gson().fromJson(result, NoticeInfoBean.class);
        if (bean.code.equals("0000")) {
            if (bean.getData() != null) {
                tv_title_notice_info.setText(bean.getData().getTitle());
                tv_name_notice_info.setText(bean.getData().getName());
                String publishDate = bean.getData().getPublishDate();
                publishDate = (null == publishDate) ? "" : publishDate;
                tv_date_notice_info.setText(publishDate);
                tv_content_notice_info.setText(Html.fromHtml(StringUtils.trimStyle(bean.getData().getContent())));
                List<NoticeInfoBean.DataBean.CommitListBean> list = bean.getData().getCommitList();
                if (null != list && list.size() > 0) {
                    adapter = new NoticeInfoCommentAdapter(list);
                    TextView footerView = new TextView(this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    footerView.setLayoutParams(layoutParams);
                    footerView.setText("已显示所有评论");
                    footerView.setTextColor(getResources().getColor(R.color.light_gray));
                    footerView.setPadding(0, SizeUtils.dp2px(this, 30), 0, SizeUtils.dp2px(this, 120));
                    footerView.setGravity(Gravity.CENTER);
                    footerView.setTextSize(13);
                    adapter.setFooterView(footerView);
//                    rv_notice_info.setAdapter(adapter);
                } else {

                }
            }
        } else {
            showToast("服务器异常");
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }

    @Override
    public void submitCommentSuccess(String result) {
        et_reply_comment.setText("");
        BaseProtocol protocol = new Gson().fromJson(result, BaseProtocol.class);
        if (protocol.code.equals("0000")) {
            getMvpPresenter().doNoticeInfoRequest(dialog, form.id);
            sv_notice_info.scrollTo(0, lin_notice_info.getMeasuredHeight() - sv_notice_info.getHeight());
        } else {
            showToast("服务器异常");
        }
    }

    @Override
    public void submitCommentFailure(String result) {
        showToast(result);
    }

    @Override
    public void netWorkUnAvailable() {
        showToast("网络出现异常");
    }
}
