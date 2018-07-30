package www.dico.cn.partybuild.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.activity.ExamResultActivity;
import www.dico.cn.partybuild.bean.QuestionBean;

/**
 * @Class: QuestionsAdapter
 * @Description:试题适配器
 * @author: yun tuo
 * @Date: 2018\5\24 0024 11:10
 */
public class QuestionsAdapter extends ViewPagerCommonAdapter<QuestionBean> {
    private List<QuestionBean> datas;
    private CallBackInterface callBackInterface;
    String radioAnswer = "";
//    String tqId="";

    public void setCallBackInterface(CallBackInterface callBackInterface) {
        this.callBackInterface = callBackInterface;
    }

    public QuestionsAdapter(Context context, List<QuestionBean> datas, int itemViewId) {
        super(context, datas, itemViewId);
        this.datas = datas;
    }

    @Override
    protected void convert(final ViewPagerCommonViewHolder holder, QuestionBean item, final int position) {

        holder.setText(R.id.tv_title_question,item.getTitle());
        holder.setOnClickListener(R.id.tv_next_question, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AppManager.getManager().curActivity(), ExamResultActivity.class);
                AppManager.getManager().curActivity().startActivity(intent);
            }
        });
        /**
         * 单选题
         */


        /**
         * 多选题
         */

        /**
         *
         * 选择结果处理
         */
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
