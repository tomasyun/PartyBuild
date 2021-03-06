package www.dico.cn.partybuild.activity;

import android.graphics.Color;
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

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.BaseProtocol;
import www.dico.cn.partybuild.bean.SkipForm;
import www.dico.cn.partybuild.bean.VoteDetailBean;
import www.dico.cn.partybuild.bean.VoteForm;
import www.dico.cn.partybuild.modleview.VoteDetailView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.VoteDetailPresenter;
import www.dico.cn.partybuild.utils.DateTimeUtils;
import www.dico.cn.partybuild.utils.SizeUtils;

@CreatePresenter(VoteDetailPresenter.class)
public class VoteDetailActivity extends AbstractMvpActivity<VoteDetailView, VoteDetailPresenter> implements VoteDetailView {
    @BindView(R.id.lin_options_vote)
    LinearLayout lin_options_vote;
    @BindView(R.id.tv_title_vote_detail)
    TextView tv_title_vote_detail;
    @BindView(R.id.tv_limit_date_vote_detail)
    TextView tv_limit_date_vote_detail;
    @BindView(R.id.tv_type_vote_detail)
    TextView tv_type_vote_detail;
    @BindView(R.id.tv_des_vote_detail)
    TextView tv_des_vote_detail;
    @BindView(R.id.tv_submit_vote_detail)
    TextView tv_submit_vote_detail;
    private VoteForm form;
    private List<String> options;
    private String voteType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votedetail);
        ButterKnife.bind(this);
//        addOptionsChildView();
        options = new ArrayList<>();
        form = getParam();
        if (form != null)
            getMvpPresenter().doVoteDetailRequest(dialog, form.voteId);
    }

    public void goBackVoteDetail(View view) {
        this.finish();
    }

    //提交
    public void submitVote(View view) {
        if (options.size() > 0) {
            Map map = new HashMap();
            map.put("id", form.voteId);
            map.put("optionIds", options);
            String json = new Gson().toJson(map);
            getMvpPresenter().doSubmitVoteResultRequest(dialog, json);
        } else {
            showToast("请选择");
        }
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

            image.setOnClickListener(view -> {

            });
        }
    }

    @Override
    public void resultSuccess(String result) {
        VoteDetailBean bean = new Gson().fromJson(result, VoteDetailBean.class);
        if (bean.code.equals("0000")) {
            if (null != bean.getData()) {
                tv_title_vote_detail.setText(bean.getData().getTitle());
                tv_title_vote_detail.setTextSize(16);
                tv_limit_date_vote_detail.setText(bean.getData().getLimitDate());
                voteType = bean.getData().getVoteType();
                switch (voteType) {
                    case "1":
                        tv_type_vote_detail.setText("单选");
                        break;
                    case "0":
                        tv_type_vote_detail.setText("多选");
                        break;
                }
                tv_des_vote_detail.setText(bean.getData().getDescription());
                if (form.isVoter.equals("1")) {
                    tv_submit_vote_detail.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_corner20_light_red_bg));
                    tv_submit_vote_detail.setTextColor(Color.parseColor("#febfb5"));
                    tv_submit_vote_detail.setText("已投票");
                    tv_submit_vote_detail.setEnabled(false);
                    tv_submit_vote_detail.setClickable(false);
                } else {
                    String limitDate = bean.getData().getLimitDate();
                    if (limitDate != null && !limitDate.equals("")) {
                        if (DateTimeUtils.isExpired(limitDate)) {
                            tv_submit_vote_detail.setText("已过期");
                            tv_submit_vote_detail.setEnabled(false);
                            tv_submit_vote_detail.setClickable(false);
                            tv_submit_vote_detail.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_corner20_light_red_bg));
                        } else {
                            tv_submit_vote_detail.setText("提交");
                            tv_submit_vote_detail.setEnabled(true);
                            tv_submit_vote_detail.setClickable(true);
                            tv_submit_vote_detail.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_corner20_red_bg));
                        }
                    }
                }
                final List<VoteDetailBean.DataBean.OptionsBean> beans = bean.getData().getOptions();
                if (null != beans && beans.size() > 0) {
                    lin_options_vote.setVisibility(View.VISIBLE);
                    lin_options_vote.removeAllViews();
                    final Boolean[] isSelects = new Boolean[beans.size()];
                    final ImageView[] imageViews = new ImageView[beans.size()];
                    for (int i = 0; i < beans.size(); i++) {
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
                        if (beans.get(i).getIsVote().equals("0")) {
                            image.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_cb_on));
                        } else {
                            image.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_vote_ok));
                        }
                        isSelects[i] = false;
                        imageViews[i] = image;
                        TextView optionText = new TextView(this);
                        optionText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        optionText.setText(beans.get(i).getOption());
                        optionText.setTextSize(15);
                        optionText.setTextColor(getResources().getColor(R.color.text_color));
                        optionText.setSingleLine(true);
                        optionText.setPadding(SizeUtils.dp2px(this, 10), 0, 0, 0);
                        optionText.setEllipsize(TextUtils.TruncateAt.END);

                        layout.addView(image);
                        layout.addView(optionText);

                        TextView votesText = new TextView(this);
                        votesText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        votesText.setText(beans.get(i).getVotes());
                        votesText.setTextSize(14);
                        votesText.setPadding(SizeUtils.dp2px(this, 33), 0, 0, 0);
                        votesText.setTextColor(getResources().getColor(R.color.theme_color));

                        layout1.addView(layout);
                        layout1.addView(votesText);
                        lin_options_vote.addView(layout1);

                        final String optionId = beans.get(i).getId();
                        if (form.isVoter.equals("0")) {
                            image.setEnabled(true);
                            image.setClickable(true);
                            final int position = i;
                            switch (voteType) {
                                case "0"://多选
                                    image.setOnClickListener(view -> {
                                        if (isSelects[position]) {
                                            image.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_cb_on));
                                            isSelects[position] = !isSelects[position];
                                            for (int i12 = 0; i12 < options.size(); i12++) {
                                                if (options.get(i12).equals(optionId)) {
                                                    options.remove(i12);
                                                }
                                            }
                                        } else {
                                            image.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_cb_ok));
                                            isSelects[position] = !isSelects[position];
                                            options.add(optionId);
                                        }
                                    });
                                    break;
                                case "1"://单选
                                    image.setOnClickListener(view -> {
                                        for (int i1 = 0; i1 < isSelects.length; i1++) {
                                            if (isSelects[i1]) {
                                                isSelects[i1] = !isSelects[i1];
                                                imageViews[i1].setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_cb_on));
                                                options.clear();
                                            }
                                        }
                                        if (isSelects[position]) {
                                            image.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_cb_on));
                                            isSelects[position] = !isSelects[position];
                                        } else {
                                            image.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_cb_ok));
                                            isSelects[position] = !isSelects[position];
                                            options.add(optionId);
                                        }
                                    });
                                    break;
                            }
                        } else {
                            image.setEnabled(false);
                            image.setClickable(false);
                        }
                    }
                } else {
                    lin_options_vote.setVisibility(View.GONE);
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
    public void submitVoteResultSuccess(String result) {
        BaseProtocol protocol = new Gson().fromJson(result, BaseProtocol.class);
        if (protocol.code.equals("0000")) {
            SkipForm form = new SkipForm();
            form.skip = 3;
            goTo(SuccessTipsActivity.class, form);
            this.finish();
        } else {
            showToast("服务器异常");
        }
    }

    @Override
    public void submitVoteResultFailure(String result) {
        showToast(result);
    }

    @Override
    public void netWorkUnAvailable() {
        showToast("网络出现异常");
    }
}
