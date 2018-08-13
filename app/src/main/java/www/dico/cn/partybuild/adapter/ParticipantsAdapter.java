package www.dico.cn.partybuild.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.R;

public class ParticipantsAdapter extends TagAdapter<String> {
    private LayoutInflater mInflater;

    public ParticipantsAdapter(List<String> datas) {
        super(datas);
        mInflater = LayoutInflater.from(AppManager.getManager().curActivity());
    }

    @Override
    public View getView(FlowLayout parent, int position, String s) {
        TextView tv = (TextView) mInflater.inflate(R.layout.item_name_tag, parent, false);
        tv.setText(s);
        return tv;
    }
}
