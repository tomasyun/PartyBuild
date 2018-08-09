package www.dico.cn.partybuild.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.route.DistanceResult;
import com.amap.api.services.route.DistanceSearch;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.SignInView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.presenter.SignInPresenter;
import www.dico.cn.partybuild.widget.CountDownButtonHelper;
import www.dico.cn.partybuild.widget.LoadingDialog;

//签到
@CreatePresenter(SignInPresenter.class)
public class SignInFragment extends AbstractFragment<SignInView, SignInPresenter> implements SignInView, AMapLocationListener, GeocodeSearch.OnGeocodeSearchListener, DistanceSearch.OnDistanceSearchListener {
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    @BindView(R.id.iv_conference_theme_pic)
    ImageView iv_conference_theme_pic;//主题图片
    @BindView(R.id.tv_sign_in_address)
    TextView tv_sign_in_address;//地址
    @BindView(R.id.tv_sign_in_date)
    TextView tv_sign_in_date;//日期
    @BindView(R.id.tv_sign_in_enable)
    TextView tv_sign_in_enable;//立即签到
    @BindView(R.id.tv_sign_in_count_down)
    TextView tv_sign_in_count_down;//倒计时显示
    @BindView(R.id.iv_sign_in_enable)
    ImageView iv_sign_in_enable;//
    @BindView(R.id.tv_sign_in_tips)
    TextView tv_sign_in_tips;
    @BindView(R.id.rel_sign_in_start)
    RelativeLayout rel_sign_in_start;
    private LoadingDialog dialog;
    private GeocodeSearch geocodeSearch;
    private DistanceSearch distanceSearch;
    private double startLatitude;
    private double startLongitude;
    private double destLatitude;
    private double destLongitude;
    private double distance;//距离
    private int during = 0;
    private CountDownButtonHelper helper;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (tv_sign_in_count_down != null) {
                        helper = new CountDownButtonHelper(tv_sign_in_count_down, "00:00:00", during * 60, 1, 1);
                        helper.setOnFinishListener(new CountDownButtonHelper.OnFinishListener() {
                            @Override
                            public void finish() {

                            }
                        });
                        helper.start();
                    }
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin, null);
        ButterKnife.bind(this, view);
        tv_sign_in_count_down.setText("00:00:00");
//        initLocationClient();
//        initGeocodeSearch();
//        initDistanceSearch();
        //立即签到
        rel_sign_in_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new LoadingDialog.Builder(getActivity())
                        .setCancelable(true)
                        .setCancelOutside(true)
                        .setMessage("定位中...")
                        .setShowMessage(true)
                        .create();
                dialog.show();
//                mLocationClient.startLocation();//启动定位
            }
        });
        return view;
    }


    /**
     * 初始化定位对象
     */
    public void initLocationClient() {
        if (mLocationClient == null) {
            //初始化定位  需要传Context类型的参数。推荐用getApplicationConext()方法
            mLocationClient = new AMapLocationClient(getActivity());
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mLocationClient.setLocationListener(this);
            //设置单次定位 该方法默认为false。
            mLocationOption.setOnceLocation(true);
            //选择定位模式 SDK默认选择使用高精度定位模式。
            // 高精度定位模式:Hight_Accuracy 同时使用网络定位和GPS定位
            //低功耗定位模式：Battery_Saving 不会使用GPS和其他传感器，只会使用网络定位（Wi-Fi和基站定位）
            //仅用设备定位模式：Device_Sensors 不需要连接网络，只使用GPS进行定位，这种模式下不支持室内环境的定位，自 v2.9.0 版本支持返回地址描述信息
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位间隔,单位毫秒,默认为2000ms 最低1000ms
            mLocationOption.setInterval(5000);
            //设置是否返回地址信息（默认返回地址信息）
            mLocationOption.setNeedAddress(true);
            //设置是否强制刷新WIFI，默认为true，强制刷新,会增加电量消耗。
            mLocationOption.setWifiActiveScan(true);
            //设置定位请求超时时间，单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
            mLocationOption.setHttpTimeOut(30000);
            //设置是否开启定位缓存机制 缓存机制默认开启true 网络定位结果均会生成本地缓存，
            // 不区分单次定位还是连续定位。GPS定位结果不会被缓存
            mLocationOption.setLocationCacheEnable(true);
            //设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        }
    }

    /**
     * 初始化地理编码对象
     */
    private void initGeocodeSearch() {
        geocodeSearch = new GeocodeSearch(getActivity());
        geocodeSearch.setOnGeocodeSearchListener(this);
    }

    /**
     * 初始化距离测量对象
     */
    private void initDistanceSearch() {
        distanceSearch = new DistanceSearch(getActivity());
        distanceSearch.setDistanceSearchListener(this);
    }

    /**
     * 定位回调方法
     *
     * @param aMapLocation
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {//定位成功
                startLatitude = aMapLocation.getLatitude();
                startLongitude = aMapLocation.getLongitude();
//                GeocodeQuery query = new GeocodeQuery(address, "");
//                geocodeSearch.getFromLocationNameAsyn(query);
            }
        }
    }

    /**
     * 逆地理编码
     * 根据已知地理编码获得具体地址
     *
     * @param regeocodeResult
     * @param
     */
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int errorCode) {

    }

    /**
     * 地理编码
     * 根据具体地址获取地理编码
     *
     * @param geocodeResult
     * @param
     */
    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int errorCode) {
        if (errorCode == 1000) {
            if (geocodeResult != null && geocodeResult.getGeocodeAddressList() != null
                    && geocodeResult.getGeocodeAddressList().size() != 0) {
                destLatitude = geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLatitude();
                destLongitude = geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLongitude();
                DistanceSearch.DistanceQuery distanceQuery = new DistanceSearch.DistanceQuery();
                LatLonPoint start = new LatLonPoint(startLatitude, startLongitude);
                LatLonPoint dest = new LatLonPoint(destLatitude, destLongitude);
                //设置起点和终点，其中起点支持多个
                List<LatLonPoint> latLonPoints = new ArrayList<>();
                latLonPoints.add(start);
                distanceQuery.setOrigins(latLonPoints);
                distanceQuery.setDestination(dest);
                //设置测量方式，支持直线和驾车
                distanceQuery.setType(DistanceSearch.TYPE_DRIVING_DISTANCE);
                distanceSearch.calculateRouteDistanceAsyn(distanceQuery);
            }
        }
    }

    /**
     * 测量距离
     *
     * @param distanceResult
     * @param
     */
    @Override
    public void onDistanceSearched(DistanceResult distanceResult, int errorCode) {
        if (errorCode == 1000) {
            dialog.dismiss();
            distance = distanceResult.getDistanceResults().get(0).getDistance();
            if (distance < 100) {//设置签到距离100m
            } else {
            }
            Log.i("####", distance + "");
        }
    }
}