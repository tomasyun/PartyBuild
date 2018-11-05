package www.dico.cn.partybuild;

import android.app.Application;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import www.dico.cn.partybuild.interceptor.CustomSignInterceptor;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.cache.converter.SerializableDiskConverter;
import www.yuntdev.com.library.model.HttpHeaders;
import www.yuntdev.com.library.model.HttpParams;
import www.yuntdev.com.library.utils.HttpLog;

public class DicoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.init(this.getApplicationContext());
        EasyHttp.init(this);
        //这里涉及到安全我把url去掉了，demo都是调试通的
//        String Url = "http://47.104.72.111/";//缔科党建外网
//        String Url = "http://124.152.247.124:8081/";//兰州电力设计院外网
        String Url = "http://192.168.0.35:8080/";//内网
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
//        headers.put("User-Agent", SystemInfoUtils.getUserAgent(this, ""));
        //设置请求参数
        HttpParams params = new HttpParams();
//        params.put("appId", "");
        EasyHttp.getInstance()
                .debug("PartyBuild", true)
                .setReadTimeOut(60 * 1000)
                .setWriteTimeOut(60 * 1000)
                .setConnectTimeout(60 * 1000)
                .setRetryCount(3)//默认网络不好自动重试3次
                .setRetryDelay(500)//每次延时500ms重试
                .setRetryIncreaseDelay(500)//每次延时叠加500ms
                .setBaseUrl(Url)
                .setCacheDiskConverter(new SerializableDiskConverter())//默认缓存使用序列化转化
                .setCacheMaxSize(50 * 1024 * 1024)//设置缓存大小为50M
                .setCacheVersion(1)//缓存版本为1
                .setHostnameVerifier(new UnSafeHostnameVerifier(Url))//全局访问规则
                .setCertificates()//信任所有证书
//                .addConverterFactory(GsonConverterFactory.create(new Gson()))//本框架没有采用Retrofit的Gson转化，所以不用配置
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCommonHeaders(headers)//设置全局公共头
                .addCommonParams(params)//设置全局公共参数
                .addInterceptor(new CustomSignInterceptor());//添加参数签名拦截器
        //.addInterceptor(new HeTInterceptor());//处理自己业务的拦截器
    }

    public class UnSafeHostnameVerifier implements HostnameVerifier {
        private String host;

        public UnSafeHostnameVerifier(String host) {
            this.host = host;
            HttpLog.i("###############　UnSafeHostnameVerifier " + host);
        }

        @Override
        public boolean verify(String hostname, SSLSession session) {
            HttpLog.i("############### verify " + hostname + " " + this.host);
            return this.host != null && !"".equals(this.host) && this.host.contains(hostname);
        }
    }
}
