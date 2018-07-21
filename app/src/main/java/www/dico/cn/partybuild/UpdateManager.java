package www.dico.cn.partybuild;

import android.app.Activity;
import android.view.WindowManager;
import android.widget.Toast;

import www.dico.cn.partybuild.utils.DeviceUtils;

public class UpdateManager {
    private Activity mContext;

    public UpdateManager(Activity mContext) {
        this.mContext = mContext;
    }

    /**
     * 检测软件更新
     */
    public void checkUpdate(boolean isToast) {
        /**
         * 在这里请求后台接口，获取更新的内容和最新的版本号
         */
        // 版本的更新信息
//        String version_info = "更新内容\n" + "    1. 底部导航\n" + "    2. 增加视频学习资料\n" + "    ";
        String version_info = "";
        int mVersion_code = DeviceUtils.getVersionCode(mContext);// 当前的版本号
        int nVersion_code = 1;
        if (mVersion_code < nVersion_code) {
            // 显示提示对话

            WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
            lp.alpha = 0.5f;
            mContext.getWindow().setAttributes(lp);
        } else {
            if (isToast) {
                Toast.makeText(mContext, "已经是最新版本", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
