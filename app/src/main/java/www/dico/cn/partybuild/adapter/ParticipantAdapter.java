package www.dico.cn.partybuild.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.ParticipantBean;

public class ParticipantAdapter extends TagAdapter<ParticipantBean> {
    private LayoutInflater mInflater;

    public ParticipantAdapter(Context mContext, List<ParticipantBean> datas) {
        super(datas);
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(FlowLayout parent, int position, ParticipantBean bean) {
        TextView tv = (TextView) mInflater.inflate(R.layout.item_tag, parent, false);
        return tv;
    }
}
