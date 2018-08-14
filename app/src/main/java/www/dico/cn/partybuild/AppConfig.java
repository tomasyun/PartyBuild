package www.dico.cn.partybuild;

import android.content.Context;

import www.dico.cn.partybuild.utils.SPUtils;

public class AppConfig {
    public static final boolean DEBUG = Boolean.parseBoolean("true");
    public static final String ACTION = "cn.diconet.www";
    private static Context context = null;
    private static SPUtils spUtils;

    public static SPUtils getSpUtils() {
        return spUtils;
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        AppConfig.context = context;
        spUtils = new SPUtils("dico", context);
        spUtils.put("isLoginOk", 0);//1.登录成功  0登录失败

        //启动通知消息服务
//        Intent intent = new Intent(context,RefreshNoticeService.class);
//        context.startService(intent);
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }
}
