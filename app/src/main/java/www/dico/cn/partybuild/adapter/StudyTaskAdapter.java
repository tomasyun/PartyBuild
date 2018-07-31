package www.dico.cn.partybuild.adapter;

import android.content.Context;

import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.StudyTaskBean;
import www.yuntdev.com.baseadapterlibrary.base.CommonAdapter;
import www.yuntdev.com.baseadapterlibrary.base.ViewHolder;

public class StudyTaskAdapter extends CommonAdapter<StudyTaskBean> {
    public StudyTaskAdapter(Context context, int layoutId, List<StudyTaskBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, StudyTaskBean studyTaskBean, int position) {
        holder.setText(R.id.tv_title_study_task_item, studyTaskBean.getTitle());
    }
}
