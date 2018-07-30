package www.dico.cn.partybuild.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPagerCommonViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    private ViewPagerCommonViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mContext = context;
    }

    /**
     * 获取一个通用的ViewHolder
     *
     * @param context
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewPagerCommonViewHolder get(Context context, ViewGroup parent, int layoutId, int position) {

        return new ViewPagerCommonViewHolder(context, parent, layoutId, position);

    }

    /**
     * 通过View的id获取视图对象
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        //当获取不到视图时，再去加载，同时添加
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            //很重要，一定要添加到集合中
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }


    //--------------提供一些通用视图的操作-------------------------------

    /**
     * 给TextView设置 文本
     *
     * @param viewId  视图的id
     * @param content 需要设置的文本的内容
     * @return
     */
    public ViewPagerCommonViewHolder setText(int viewId, String content) {
        TextView textView = getView(viewId);
        textView.setText(content);
        return this; //返回该类对象，可以链式调用
    }

    /**
     * 给TextView设置 文本
     *
     * @param viewId  视图的id
     * @param content 需要设置的文本的内容
     * @return
     */
    public ViewPagerCommonViewHolder setText(int viewId, String content, int color) {
        TextView textView = getView(viewId);
        textView.setText(content);
        textView.setTextColor(color);
        return this; //返回该类对象，可以链式调用
    }

    /**
     * 为IamgeView设置ImageResourceid
     *
     * @param viewId     视图的id
     * @param resourceId
     * @return
     */
    public ViewPagerCommonViewHolder setImageResource(int viewId, int resourceId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resourceId);
        return this;
    }

    /**
     * 为ImageView设置Bitmap图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public ViewPagerCommonViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }


    public ViewPagerCommonViewHolder setVisiable(int viewId, boolean visiable) {
        View view = getView(viewId);
        view.setVisibility(visiable ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 关于事件的
     */
    public ViewPagerCommonViewHolder setOnClickListener(int viewId,
                                                        View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;

    }
}