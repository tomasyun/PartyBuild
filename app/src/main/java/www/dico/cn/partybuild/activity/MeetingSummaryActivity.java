package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tencent.smtt.sdk.QbSdk;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.MeetSummaryBean;
import www.dico.cn.partybuild.bean.MeetingSummaryForm;
import www.dico.cn.partybuild.modleview.MeetingSummaryView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.MeetingSummaryPresenter;
import www.dico.cn.partybuild.widget.HtmlImageGetter;

@CreatePresenter(MeetingSummaryPresenter.class)
public class MeetingSummaryActivity extends AbstractMvpActivity<MeetingSummaryView, MeetingSummaryPresenter> implements MeetingSummaryView {
    private MeetingSummaryForm form;
    @BindView(R.id.tv_title_meet_summary)
    TextView tv_title_meet_summary;
    @BindView(R.id.tv_category_meet_summary)
    TextView tv_category_meet_summary;
    @BindView(R.id.tv_speaker_meet_summary)
    TextView tv_speaker_meet_summary;
    @BindView(R.id.tv_date_meet_summary)
    TextView tv_date_meet_summary;
    @BindView(R.id.tv_content_meet_summary)
    TextView tv_content_meet_summary;
    @BindView(R.id.rel_attachment_meet_summary)
    RelativeLayout rel_attachment_meet_summary;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetingsummary);
        ButterKnife.bind(this);
        QbSdk.initX5Environment(this, null);
        form = getParam();
        tv_title_meet_summary.post(new Runnable() {
            @Override
            public void run() {
                if (tv_title_meet_summary.getLineCount() == 1) {
                    tv_title_meet_summary.setGravity(Gravity.CENTER);
                } else {
                    tv_title_meet_summary.setGravity(Gravity.LEFT);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (form != null)
            getMvpPresenter().doMeetingSummaryRequest(dialog, form.id);
    }

    public void goBackMeetdetail(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        final MeetSummaryBean bean = new Gson().fromJson(result, MeetSummaryBean.class);
        if (bean.code.equals("0000")) {
            if (bean.getData() != null) {
                tv_title_meet_summary.setText(bean.getData().getTitle());
                String category = bean.getData().getCategory();
                switch (category) {
                    case "0":
                        tv_category_meet_summary.setText("支部党员大会");
                        break;
                    case "1":
                        tv_category_meet_summary.setText("支部委员会");
                        break;
                    case "2":
                        tv_category_meet_summary.setText("党小组会");
                        break;
                    case "3":
                        tv_category_meet_summary.setText("党课");
                        break;
                }
                tv_speaker_meet_summary.setText(bean.getData().getSpeaker());
                tv_date_meet_summary.setText(bean.getData().getEndDate());

                HtmlImageGetter imageGetter = new HtmlImageGetter(this, tv_content_meet_summary);
                Spanned spanned = Html.fromHtml(bean.getData().getSummary(), imageGetter, null);
                tv_content_meet_summary.setText(spanned);
                if (bean.getData().getAttachment() != null && !bean.getData().getAttachment().equals("")) {
                    //TODO 附件处理
                    rel_attachment_meet_summary.setVisibility(View.VISIBLE);
                    TextView tv_attachment_meet_info = rel_attachment_meet_summary.findViewById(R.id.tv_attachment_meet_info);
                    tv_attachment_meet_info.setText(bean.getData().getAttachment());
                    rel_attachment_meet_summary.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {//文件预览
                            String filePath = AppConfig.urlFormat(bean.getData().getAttachment());
                            String fileName = filePath.trim().substring(filePath.lastIndexOf("/") + 1);
                            DisplayFileActivity.openDispalyFileActivity(MeetingSummaryActivity.this, filePath, fileName);
                        }
                    });
                } else {
                    rel_attachment_meet_summary.setVisibility(View.GONE);
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
    public void netWorkUnAvailable() {
        showToast("网络出现异常");
    }
}
