package www.dico.cn.partybuild.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.QuestionBean;
import www.dico.cn.partybuild.utils.SizeUtils;

/**
 * @Class: QuestionsAdapter
 * @Description:试题适配器
 * @author: yun tuo
 * @Date: 2018\5\24 0024 11:10
 */
public class QuestionsAdapter extends ViewPagerCommonAdapter<QuestionBean.DataBean.QuestionListBean> {
    String radioAnswer = "";
    private List<QuestionBean.DataBean.QuestionListBean> datas;
    private CallBackInterface callBackInterface;
//    String tqId="";

    public QuestionsAdapter(Context context, List<QuestionBean.DataBean.QuestionListBean> datas, int itemViewId) {
        super(context, datas, itemViewId);
        this.datas = datas;
    }

    public void setCallBackInterface(CallBackInterface callBackInterface) {
        this.callBackInterface = callBackInterface;
    }

    @Override
    protected void convert(final ViewPagerCommonViewHolder holder, final QuestionBean.DataBean.QuestionListBean item, final int position) {
        LinearLayout rg_ls = holder.getView(R.id.rg_ls);//选项父容器
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, SizeUtils.dp2px(AppManager.getManager().curActivity(), 15), 0, 0);
        final List<CheckBox> list = new ArrayList<>();

        /**
         * 单选题
         */
        if (item.getType().equals("单选题")) {
            SpannableString content = new SpannableString((position + 1) + ".  " + item.getContent() + "  " + "(单选题)");
            content.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 0, content.length() - 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            content.setSpan(new ForegroundColorSpan(Color.parseColor("#a1a1a1")), content.length() - 5, content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            content.setSpan(new AbsoluteSizeSpan(42), content.length() - 5, content.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
            TextView tv_title_question = holder.getView(R.id.tv_title_question);
            tv_title_question.setText(content);
            tv_title_question.setTextSize(16);
            RadioGroup group = new RadioGroup(mContext);
            group.setOrientation(LinearLayout.VERTICAL);
            if (item.getQuestionOptionsList().size() > 0) {
                for (int j = 0; j < item.getQuestionOptionsList().size(); j++) {
                    RadioButton radioBtn = new RadioButton(mContext);
                    radioBtn.setText(item.getQuestionOptionsList().get(j).getName() + "   " + item.getQuestionOptionsList().get(j).getContent());
                    radioBtn.setTextSize(14);
                    radioBtn.setPadding(SizeUtils.dp2px(AppManager.getManager().curActivity(), 15), SizeUtils.dp2px(AppManager.getManager().curActivity(), 10), 0, SizeUtils.dp2px(AppManager.getManager().curActivity(), 10));
                    radioBtn.setMaxLines(1);
                    radioBtn.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.rbt_options_selected));
                    radioBtn.setButtonDrawable(null);
                    group.addView(radioBtn, layoutParams);

                }
                rg_ls.addView(group);
                group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        RadioButton radioBtn = radioGroup.findViewById(checkedId);
                        radioAnswer = radioBtn.getText().toString().trim().substring(0, 1);
                    }
                });
            }
        }
        /**
         * 多选题
         */
        else if (item.getType().equals("多选题")) {
            SpannableString content = new SpannableString((position + 1) + ".  " + item.getContent() + "  " + "(多选题)");
            content.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 0, content.length() - 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            content.setSpan(new ForegroundColorSpan(Color.parseColor("#a1a1a1")), content.length() - 5, content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            content.setSpan(new AbsoluteSizeSpan(42), content.length() - 5, content.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
            TextView tv_title_question = holder.getView(R.id.tv_title_question);
            tv_title_question.setText(content);
            tv_title_question.setTextSize(16);
            if (item.getQuestionOptionsList().size() > 0) {
                for (int k = 0; k < item.getQuestionOptionsList().size(); k++) {
                    CheckBox checkBox = (CheckBox) LayoutInflater.from(mContext).inflate(R.layout.item_checkbox, null);
                    checkBox.setText(item.getQuestionOptionsList().get(k).getName() + "   " + item.getQuestionOptionsList().get(k).getContent());
                    checkBox.setTextSize(14);
                    checkBox.setPadding(SizeUtils.dp2px(AppManager.getManager().curActivity(), 15), SizeUtils.dp2px(AppManager.getManager().curActivity(), 10), 0, SizeUtils.dp2px(AppManager.getManager().curActivity(), 10));
                    checkBox.setLayoutParams(layoutParams);
                    list.add(checkBox);
                    rg_ls.addView(checkBox);
                }
            }
        }
        /**
         *
         * 选择结果处理
         */
        if (position + 1 == datas.size())
            holder.setText(R.id.tv_next_question, "交卷");
        else
            holder.setText(R.id.tv_next_question, "下一题");
        holder.setOnClickListener(R.id.tv_next_question, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = ((TextView) holder.getView(R.id.tv_next_question)).getText().toString().trim();
                switch (content) {
                    case "下一题":
                        if (item.getQuestionOptionsList().size() > 0) {
                            if (item.getType().equals("单选题")) {
                                if (radioAnswer.equals("")) {
                                    Toast.makeText(AppManager.getManager().curActivity(), "请选择", Toast.LENGTH_SHORT).show();
                                } else {
                                    callBackInterface.nextStep(item.getId(), String.valueOf(position + 1), radioAnswer);
                                }
                            } else if (item.getType().equals("多选题")) {
                                String str = "";
                                for (CheckBox checkBox : list) {
                                    if (checkBox.isChecked()) {
                                        str += checkBox.getText().toString().trim().substring(0, 1);
                                    }
                                }
                                if (str.equals("")) {
                                    Toast.makeText(AppConfig.getContext(), "请选择", Toast.LENGTH_SHORT).show();
                                } else {
                                    char[] c = str.toCharArray();//将字符串转换成char数组
                                    Arrays.sort(c);//对数组进行排序
                                    String sortAnswer = new String(c);
                                    callBackInterface.nextStep(item.getId(), String.valueOf(position + 1), sortAnswer);
                                }
                            }
                        }
                        break;
                    case "交卷":
                        if (datas.size() > 0) {
                            if (item.getType().equals("单选题")) {
                                if (radioAnswer.equals("")) {
                                    Toast.makeText(AppManager.getManager().curActivity(), "请选择", Toast.LENGTH_SHORT).show();
                                } else {
                                    callBackInterface.submit(item.getId(), String.valueOf(position + 1), radioAnswer);
                                }
                            } else if (item.getType().equals("多选题")) {
                                String str = "";
                                for (CheckBox checkBox : list) {
                                    if (checkBox.isChecked()) {
                                        str += checkBox.getText().toString().trim().substring(0, 1);
                                    }
                                }
                                if (str.equals("")) {
                                    Toast.makeText(AppManager.getManager().curActivity(), "请选择", Toast.LENGTH_SHORT).show();
                                } else {
                                    char[] c = str.toCharArray();//将字符串转换成char数组
                                    Arrays.sort(c);//对数组进行排序
                                    String sortAnswer = new String(c);
                                    callBackInterface.submit(item.getId(), String.valueOf(position + 1), sortAnswer);
                                }
                            }
                        }
                        break;
                }
            }
        });
    }

    public interface CallBackInterface {
        /**
         * 下一题
         *
         * @param tqId
         * @param serial
         * @param answer
         */
        void nextStep(String tqId, String serial, String answer);

        /**
         * 提交
         *
         * @param tqId
         * @param serial
         * @param answer
         */
        void submit(String tqId, String serial, String answer);
    }
}
