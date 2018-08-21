package www.dico.cn.partybuild.adapter;

import android.content.Context;
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
import www.dico.cn.partybuild.bean.SurveyQuestionBean;
import www.dico.cn.partybuild.utils.SizeUtils;

public class SurveyQuestionAdapter extends ViewPagerCommonAdapter<SurveyQuestionBean.DataBean> {
    String radioAnswer = "";
    List<SurveyQuestionBean.DataBean> mDatas;
    private SurveyQuestionHandleInterface handleInterface;

    public SurveyQuestionAdapter(Context context, List<SurveyQuestionBean.DataBean> datas, int itemViewId) {
        super(context, datas, itemViewId);
        this.mDatas = datas;
    }

    public void setHandleInterface(SurveyQuestionHandleInterface handleInterface) {
        this.handleInterface = handleInterface;
    }

    @Override
    protected void convert(final ViewPagerCommonViewHolder holder, final SurveyQuestionBean.DataBean dataBean, final int position) {
        LinearLayout rg_ls = holder.getView(R.id.rg_ls);//选项父容器
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, SizeUtils.dp2px(AppManager.getManager().curActivity(), 15), 0, 0);
        final List<CheckBox> list = new ArrayList<>();

        /**
         * 单选题
         */
        if (dataBean.getTypeId().equals("1")) {
            holder.setText(R.id.tv_title_question, (position + 1) + "." + dataBean.getContent() + "(单选题)");//标题
            RadioGroup group = new RadioGroup(mContext);
            group.setOrientation(LinearLayout.VERTICAL);
            if (dataBean.getQuestionOptionsList().size() > 0) {
                for (int j = 0; j < dataBean.getQuestionOptionsList().size(); j++) {
                    RadioButton radioBtn = new RadioButton(mContext);
                    radioBtn.setText(dataBean.getQuestionOptionsList().get(j).getName() + "   " + dataBean.getQuestionOptionsList().get(j).getContent());
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
        else if (dataBean.getTypeId().equals("2")) {
            holder.setText(R.id.tv_title_question, (position + 1) + "." + dataBean.getContent() + "(多选题)");//标题
            if (dataBean.getQuestionOptionsList().size() > 0) {
                for (int k = 0; k < dataBean.getQuestionOptionsList().size(); k++) {
                    CheckBox checkBox = (CheckBox) LayoutInflater.from(mContext).inflate(R.layout.item_checkbox, null);
                    checkBox.setText(dataBean.getQuestionOptionsList().get(k).getName() + "   " + dataBean.getQuestionOptionsList().get(k).getContent());
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
        if (position + 1 == mDatas.size())
            holder.setText(R.id.tv_next_question, "提交");
        else
            holder.setText(R.id.tv_next_question, "下一题");
        holder.setOnClickListener(R.id.tv_next_question, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = ((TextView) holder.getView(R.id.tv_next_question)).getText().toString().trim();
                switch (content) {
                    case "下一题":
                        if (dataBean.getQuestionOptionsList().size() > 0) {
                            if (dataBean.getTypeId().equals("1")) {
                                if (radioAnswer.equals("")) {
                                    Toast.makeText(AppManager.getManager().curActivity(), "请选择", Toast.LENGTH_SHORT).show();
                                } else {
                                    handleInterface.nextStep(dataBean.getId(), String.valueOf(position + 1), radioAnswer);
                                }
                            } else if (dataBean.getTypeId().equals("2")) {
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
                                    handleInterface.nextStep(dataBean.getId(), String.valueOf(position + 1), sortAnswer);
                                }
                            }
                        }
                        break;
                    case "提交":
                        if (mDatas.size() > 0) {
                            if (dataBean.getTypeId().equals("1")) {
                                if (radioAnswer.equals("")) {
                                    Toast.makeText(AppManager.getManager().curActivity(), "请选择", Toast.LENGTH_SHORT).show();
                                } else {
                                    handleInterface.submit(dataBean.getId(), String.valueOf(position + 1), radioAnswer);
                                }
                            } else if (dataBean.getTypeId().equals("2")) {
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
                                    handleInterface.submit(dataBean.getId(), String.valueOf(position + 1), sortAnswer);
                                }
                            }
                        }
                        break;
                }
            }
        });
    }

    public interface SurveyQuestionHandleInterface {
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