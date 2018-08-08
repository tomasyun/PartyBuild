package www.dico.cn.partybuild.activity;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.carbs.android.expandabletextview.library.ExpandableTextView;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.StudyTaskForm;
import www.dico.cn.partybuild.bean.TaskBriefBean;
import www.dico.cn.partybuild.modleview.TaskBriefView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.TaskBriefPresenter;
import www.dico.cn.partybuild.utils.SizeUtils;

@CreatePresenter(TaskBriefPresenter.class)
public class TaskBriefActivity extends AbstractMvpActivity<TaskBriefView, TaskBriefPresenter> implements TaskBriefView {
    @FieldView(R.id.tv_content_task)
    ExpandableTextView tv_content_task;
    @FieldView(R.id.lin_course_task_brief)
    LinearLayout lin_course_task_brief;
    private StudyTaskForm form;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskbrief);
        ViewFind.bind(this);
        form = getParam();
        tv_content_task.setText("\u3000\u3000" + "中央外事工作会议刚刚结束不久，习近平主席开启今年首次出访，这是中央着眼国际局势出现的新机遇、新挑战作出的重大外交部署。当今世界面临百年未遇之大变局，新兴市场和发展中国家集体崛起势不可挡，团结协作、联合自强的意愿日益高涨。习近平主席此次亚非之行携手有关国家领导人，共商友好合作大计，共绘发展振兴蓝图，共创互利共赢前景。引领中国特色大国外交开辟了新境界，打开了中外关系和南南合作的新局面，拓宽了国内发展和战略运筹的新空间，推进了构建人类命运共同体的新实践。");
        addCourseChildView();
        getMvpPresenter().doTaskBriefRequest(form.taskId);
    }

    public void goback(View view) {
        this.finish();
    }

    public void addCourseChildView() {
        for (int i = 0; i < 4; i++) {
            TextView courseView = new TextView(this);
            courseView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            courseView.setPadding(SizeUtils.dp2px(this, 15), SizeUtils.dp2px(this, 25), SizeUtils.dp2px(this, 15), 0);
            courseView.setTextColor(getResources().getColor(R.color.actionsheet_blue));
            courseView.setSingleLine(true);
            courseView.setEllipsize(TextUtils.TruncateAt.END);
            courseView.setTypeface(Typeface.DEFAULT_BOLD);
            courseView.setText("坚决打好三大攻坚战 夯实全面建成小康社会基础，二论贯彻落实省委十三届三次全会精神");
            lin_course_task_brief.addView(courseView);
        }
    }

    public void studyResult(View view) {
        showToast("抱歉!课时未够");
    }

    @Override
    public void resultSuccess(String result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
