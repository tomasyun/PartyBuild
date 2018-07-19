package www.dico.cn.partybuild.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import www.dico.cn.partybuild.utils.SizeUtils;

/**
 * @Class: CustomToast
 * @Description:自定义toast
 * @author: yun tuo
 * @Date: 2018\4\8 0008 11:58
 */
public class CustomToast {
    private static CustomToast _instance = null;
    private Toast toast = null;
    private int marginVertical = 0;
    private final int MARGIN_DP = 50;

    private CustomToast() {

    }

    public static CustomToast Instance() {
        if (_instance == null) {
            _instance = new CustomToast();
        }
        return _instance;
    }

    public void cancel() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
    }

    public void showToastBottom(Context ctx, int resId) {
        String msg = ctx.getString(resId);
        showToast(ctx, msg);
    }

    public void showToast(Context ctx, String msg) {
        cancel();
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showToastCenter(Context ctx, int resId) {
        String msg = ctx.getString(resId);
        showToastCenter(ctx, msg);
    }

    public void showToastCenter(Context ctx, String msg) {
        cancel();
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        toast = new Toast(ctx);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
//		toast.setText(msg);
        LinearLayout layout = new LinearLayout(ctx);
        TextView textView = new TextView(ctx);
        textView.setText(msg);
        layout.addView(textView);
        toast.setView(layout);
        toast.show();
    }

    public void showToastCustom(Context ctx, String msg, int layoutResId, int txtResId) {
        showToastCustom(ctx, msg, layoutResId, txtResId, Gravity.CENTER, -1, -1);
    }

    public void showToastCustom(Context ctx, String msg, int layoutResId, int txtResId, int gravity, int bgColor, int txtColor) {
        cancel();
        try {
            if (TextUtils.isEmpty(msg)) {
                return;
            }
            View layout = View.inflate(ctx, layoutResId, null);
            if (bgColor > -1) {
                layout.setBackgroundColor(bgColor);
            }
            TextView txtMsg = layout.findViewById(txtResId);
            if (txtColor > -1) {
                txtMsg.setBackgroundColor(txtColor);
            }
            txtMsg.setText(msg);
            toast = new Toast(ctx);
            toast.setDuration(Toast.LENGTH_SHORT);
            if (gravity == Gravity.TOP) {
                if (this.marginVertical == 0) {
                    this.marginVertical = SizeUtils.dp2px(ctx,MARGIN_DP);
                }
                toast.setGravity(gravity, 0, this.marginVertical);
            } else if (gravity == Gravity.BOTTOM) {
                if (this.marginVertical == 0) {
                    this.marginVertical = SizeUtils.dp2px(ctx,MARGIN_DP);
                }
                toast.setGravity(gravity, 0, -this.marginVertical);
            } else {
                toast.setGravity(gravity, 0, 0);
            }
            toast.setView(layout);
            toast.show();

        } catch (Exception e) {

        }
    }
}
