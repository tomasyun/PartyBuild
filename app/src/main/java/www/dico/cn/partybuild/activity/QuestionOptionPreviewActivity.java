package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.QuestionOptionBean;
import www.dico.cn.partybuild.bean.QuestionOptionPreviewForm;
import www.dico.cn.partybuild.modleview.QuestionOptionPreviewView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.QuestionOptionPreviewPresenter;
import www.dico.cn.partybuild.utils.SizeUtils;

@CreatePresenter(QuestionOptionPreviewPresenter.class)
public class QuestionOptionPreviewActivity extends AbstractMvpActivity<QuestionOptionPreviewView, QuestionOptionPreviewPresenter> implements QuestionOptionPreviewView {
    @BindView(R.id.tv_title_option_preview)
    TextView tv_title_option_preview;
    @BindView(R.id.rg_ls_option_preview)
    LinearLayout rg_ls_option_preview;
    @BindView(R.id.tv_correct_answer_option_preview)
    TextView tv_correct_answer_option_preview;
    private QuestionOptionPreviewForm form;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionoptionpreview);
        ButterKnife.bind(this);
        form = getParam();
        if (form != null)
            getMvpPresenter().doQuestionPreviewRequest(form.questionId);
    }

    public void goBackQuestionOptionPreview(View view) {
        this.finish();
    }

    @Override
    public void questionPreviewResultSuccess(String result) {
        QuestionOptionBean bean = new Gson().fromJson(result, QuestionOptionBean.class);
        if (bean.code.equals("0000")) {
            if (bean.getData() != null) {
                tv_title_option_preview.setText(form.position + bean.getData().getContent() + "(" + bean.getData().getTypeId() + ")");
                tv_correct_answer_option_preview.setText(bean.getData().getAnswer());
                List<QuestionOptionBean.DataBean.QuestionOptionsListBean> beans = bean.getData().getQuestionOptionsList();
                if (null != beans && beans.size() > 0) {
                    for (int i = 0; i < beans.size(); i++) {
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        LinearLayout layout = new LinearLayout(this);
                        layout.setLayoutParams(params);
                        layout.setOrientation(LinearLayout.HORIZONTAL);
                        layout.setGravity(Gravity.CENTER_VERTICAL);
                        params.setMargins(0, 0, 0, SizeUtils.dp2px(this, 15));

                        final ImageView image = new ImageView(this);
                        image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


                        switch (beans.get(i).getIsTrue()) {
                            case "0":
                                if (bean.getData().getAnswer().equals(beans.get(i).getName())) {
                                    image.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_right));
                                } else {
                                    image.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_wrong));
                                }
                                break;
                            case "1":
                                image.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_right));
                                break;
                            case "2":
                                image.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_cb_on));
                                break;
                        }

                        TextView optionText = new TextView(this);
                        optionText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        optionText.setText(beans.get(i).getName() + "." + beans.get(i).getContent());
                        optionText.setTextSize(14);
                        optionText.setTextColor(getResources().getColor(R.color.text_color));
                        optionText.setSingleLine(true);
                        optionText.setPadding(SizeUtils.dp2px(this, 10), 0, 0, 0);
                        optionText.setEllipsize(TextUtils.TruncateAt.END);

                        layout.addView(image);
                        layout.addView(optionText);

                        rg_ls_option_preview.addView(layout);

                    }
                }
            }
        } else {
            showToast(bean.msg);
        }
    }

    @Override
    public void questionPreviewResultFailure(String result) {
        showToast(result);
    }

    @Override
    public void netWorkUnAvailable() {
        showToast("网络出现异常");
    }
}
