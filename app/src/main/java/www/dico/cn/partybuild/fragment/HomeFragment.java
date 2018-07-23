package www.dico.cn.partybuild.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stx.xhb.xbanner.XBanner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.HomeView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractFragment;
import www.dico.cn.partybuild.persistance.AdvertiseImgM;
import www.dico.cn.partybuild.persistance.HomeBean;
import www.dico.cn.partybuild.presenter.HomePresenter;
import www.dico.cn.partybuild.utils.GlideUtils;

@CreatePresenter(HomePresenter.class)
public class HomeFragment extends AbstractFragment<HomeView, HomePresenter> implements HomeView {
    private XBanner xbanner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        xbanner = view.findViewById(R.id.xbanner);
        List<AdvertiseImgM> urls = new ArrayList<>();
        AdvertiseImgM advertise = new AdvertiseImgM();
        advertise.setPoster("http://pic.5tu.cn/uploads/allimg/1606/pic_5tu_big_201606272309319893.jpg");
        urls.add(advertise);
        AdvertiseImgM advertise2 = new AdvertiseImgM();
        advertise2.setPoster("http://pic.5tu.cn/uploads/allimg/1607/pic_5tu_big_2016070102141153200.jpg");
        urls.add(advertise2);
        AdvertiseImgM advertise3 = new AdvertiseImgM();
        advertise3.setPoster("http://pic.5tu.cn/uploads/allimg/1607/pic_5tu_big_201607091531527229.jpg");
        urls.add(advertise3);
        List<String> titles=new ArrayList<>();
        titles.add("飞屋环游记");
        titles.add("蓝色科技");
        titles.add("运动会");
        xbanner.setAutoPlayAble(urls.size() > 1);
        xbanner.setData(urls,titles);
        xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                GlideUtils.loadImage(getActivity(),((AdvertiseImgM)model).getPoster(), (ImageView) view);
            }
        });

        return view;
    }

    @Override
    public void resultSuccess(HomeBean result) {

    }

    @Override
    public void resultFailure(String result) {

    }
}
