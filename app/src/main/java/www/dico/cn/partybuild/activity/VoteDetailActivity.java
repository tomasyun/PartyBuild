package www.dico.cn.partybuild.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.VoteDetailBean;
import www.dico.cn.partybuild.modleview.VoteDetailView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.VoteDetailPresenter;
import www.dico.cn.partybuild.presenter.VoteManagerPresenter;
import www.dico.cn.partybuild.utils.SizeUtils;

@CreatePresenter(VoteDetailPresenter.class)
public class VoteDetailActivity extends AbstractMvpActivity<VoteDetailView, VoteDetailPresenter> implements VoteDetailView {
    @FieldView(R.id.lin_options_vote)
    LinearLayout lin_options_vote;
    private boolean isSelected = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votedetail);
        ViewFind.bind(this);
        addOptionsChildView();
    }

    public void goback(View view) {
        this.finish();
    }

    //提交
    public void submit(View view) {
        showToast("暂未开通");
    }

    public void addOptionsChildView() {
        for (int i = 0; i < 4; i++) {
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout layout1 = new LinearLayout(this);
            layout1.setLayoutParams(params1);
            layout1.setOrientation(LinearLayout.VERTICAL);
            params1.setMargins(0, SizeUtils.dp2px(this, 15), 0, 0);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout layout = new LinearLayout(this);
            layout.setLayoutParams(params);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setGravity(Gravity.CENTER_VERTICAL);
            params.setMargins(0, 0, 0, SizeUtils.dp2px(this, 5));

            final ImageView image = new ImageView(this);
            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            image.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_cb_ok));

            TextView optionText = new TextView(this);
            optionText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            optionText.setText("烧烤");
            optionText.setTextSize(16);
            optionText.setTypeface(Typeface.DEFAULT_BOLD);
            optionText.setTextColor(getResources().getColor(R.color.text_color));
            optionText.setSingleLine(true);
            optionText.setPadding(SizeUtils.dp2px(this, 10), 0, 0, 0);
            optionText.setEllipsize(TextUtils.TruncateAt.END);

            layout.addView(image);
            layout.addView(optionText);

            TextView votesText = new TextView(this);
            votesText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            votesText.setText("99票");
            votesText.setTextSize(14);
            votesText.setPadding(SizeUtils.dp2px(this, 33), 0, 0, 0);
            votesText.setTextColor(getResources().getColor(R.color.theme_color));

            layout1.addView(layout);
            layout1.addView(votesText);
            lin_options_vote.addView(layout1);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isSelected) {
                        image.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_cb_ok));
                        isSelected = false;
                    } else {
                        image.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_cb_on));
                        isSelected = true;
                    }
                }
            });
        }
    }

    @Override
    public void resultSuccess(String result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
