package www.dico.cn.partybuild.presenter

import com.google.gson.Gson
import www.dico.cn.partybuild.AppConfig
import www.dico.cn.partybuild.bean.LoginBean
import www.dico.cn.partybuild.modleview.LoginView
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter
import www.dico.cn.partybuild.utils.SPUtils
import www.yuntdev.com.library.EasyHttp
import www.yuntdev.com.library.callback.ProgressDialogCallBack
import www.yuntdev.com.library.exception.ApiException
import www.yuntdev.com.library.subsciber.IProgressDialog
import java.util.*

class LoginPresenter : BaseMvpPresenter<LoginView>() {
    private val sp: SPUtils = AppConfig.getSpUtils()
    fun clickRequest(dialog: IProgressDialog, name: String, password: String) {
        val map = HashMap<String, String>()
        map["username"] = name
        map["password"] = password
        val params = Gson().toJson(map)
        EasyHttp.post("auth/mobileLogin")
                .upJson(params)
                .execute(object : ProgressDialogCallBack<String>(dialog, true, true) {
                    override fun onSuccess(result: String) {
                        if (mvpView == null) {
                            val bean = Gson().fromJson<LoginBean>(result, LoginBean::class.java)
                            if (bean.code == "0000") {
                                if (null != bean.data) {
                                    var name: String? = bean.data.name
                                    var partyPost: String? = bean.data.partyPost//党内职务
                                    val token = bean.data.token
                                    val userId = bean.data.userId//用户id
                                    val avatar = bean.data.avatar//头像
                                    val isManager = bean.data.IsManager()//是否为管理员

                                    name = if (null == name) "" else bean.data.name
                                    sp.put("name", name)
                                    partyPost = if (null == partyPost) "" else bean.data.partyPost
                                    sp.put("partyPost", partyPost)
                                    sp.put("token", "Bearer $token")
                                    sp.put("userId", userId)
                                    sp.put("avatar", avatar)
                                    sp.put("isManager", isManager)
                                    sp.put("isLoginOk", 1)
                                }
                            }
                        } else {
                            mvpView.resultSuccess(result)
                        }
                    }

                    override fun onError(e: ApiException?) {
                        super.onError(e)
                        if (e!!.code == ApiException.ERROR.NETWORD_ERROR) {
                            mvpView.netWorkUnAvailable()
                        } else if (e.code == ApiException.UNKNOWN) {
                            mvpView.resultFailure("请检查您的网络是否连接")
                        } else {
                            mvpView.resultFailure(e.message)
                        }
                    }
                })
    }
}
