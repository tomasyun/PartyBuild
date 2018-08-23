package www.dico.cn.partybuild.presenter;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import java.util.List;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.LeaderSelectAdapter;
import www.dico.cn.partybuild.bean.LeaderBean;
import www.dico.cn.partybuild.modleview.MailboxView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.baseadapterlibrary.MultiItemTypeAdapter;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class MailboxPresenter extends BaseMvpPresenter<MailboxView> {
    //领导信箱
    IProgressDialog dialog = new IProgressDialog() {
        @Override
        public Dialog getDialog() {
            LoadingDialog.Builder builder = new LoadingDialog.Builder(AppManager.getManager().curActivity())
                    .setCancelable(true)
                    .setCancelOutside(true)
                    .setMessage("请求中..")
                    .setShowMessage(true);
            return builder.create();
        }
    };
    private LeaderSelectInterface selectInterface;

    public void setSelectInterface(LeaderSelectInterface selectInterface) {
        this.selectInterface = selectInterface;
    }

    public void doGetLeaderRequest() {
        EasyHttp.post("leaderList")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .execute(new ProgressDialogCallBack<String>(dialog, true, true) {
                    @Override
                    public void onSuccess(String s) {
                        getMvpView().getLeadersResultSuccess(s);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        if (e.getCode() == ApiException.ERROR.NETWORD_ERROR)
                            getMvpView().netWorkUnAvailable();
                        else
                            getMvpView().getLeadersResultFailure(e.getMessage());
                    }
                });
    }

    public void submitMailboxRequest(String id, String content) {
        EasyHttp.post("leaderMailbox")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("id", id)
                .params("content", content)
                .execute(new ProgressDialogCallBack<String>(dialog, true, true) {
                    @Override
                    public void onSuccess(String result) {
                        getMvpView().resultSuccess(result);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        if (e.getCode() == ApiException.ERROR.NETWORD_ERROR)
                            getMvpView().netWorkUnAvailable();
                        else
                            getMvpView().resultFailure(e.getMessage());
                    }
                });
    }

    /**
     * 领导选择弹出框
     *
     * @param activity
     * @param dataBeans
     * @return
     */
    public PopupWindow setUpLeaderSelectPopupWindow(Activity activity, final List<LeaderBean.DataBean> dataBeans) {
        final PopupWindow popup = new PopupWindow(activity);
        View covertView = LayoutInflater.from(activity).inflate(R.layout.pop_leader_select, null);
        RecyclerView rv_leader_select = covertView.findViewById(R.id.rv_leader_select);
        rv_leader_select.setLayoutManager(new LinearLayoutManager(activity));
        LeaderSelectAdapter adapter = new LeaderSelectAdapter(activity, R.layout.item_leader, dataBeans);
        rv_leader_select.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                selectInterface.select(dataBeans.get(position).getId(), dataBeans.get(position).getPosition(), dataBeans.get(position).getName());
                popup.dismiss();
            }
        });
        popup.setContentView(covertView);
        popup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        popup.setBackgroundDrawable(new BitmapDrawable());
        return popup;
    }

    public interface LeaderSelectInterface {
        void select(String id, String position, String name);
    }
}
