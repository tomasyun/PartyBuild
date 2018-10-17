package www.dico.cn.partybuild;

import android.content.Context;

import www.dico.cn.partybuild.utils.SPUtils;

public class AppConfig {
    public static final boolean DEBUG = Boolean.parseBoolean("true");
    public static final String ACTION = "cn.diconet.www";
//    public static final String resUrl = "http://124.152.247.124:8081/";//图片资源路径
    public static final String resUrl = "http://47.104.72.111/";//图片资源路径
    public static final String appDownLoadUrl = "http://47.104.72.111/";//安装包下载路径
    public static String nVersionCode = "";
    public static String content = "";
    public static String updateUrl = "";
    private static Context context = null;
    private static SPUtils spUtils = null;

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
        spUtils.put("isLoginOk", 0);//1.登录成功  0.登录失败

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

    public static String urlFormat(String url) {
        String newUrl = "";
        if (url.substring(0, 1).equals("/")) {
            newUrl = url.substring(1, url.length());
            return resUrl + newUrl;
        }
        return resUrl + url;
    }

    public static String urlFormat1(String url) {
        String newUrl = "";
        if (url.substring(0, 1).equals("/")) {
            newUrl = url.substring(1, url.length());
            return appDownLoadUrl + newUrl;
        }
        return appDownLoadUrl + url;
    }
}
