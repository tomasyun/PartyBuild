package www.dico.cn.partybuild.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View

import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*

import www.dico.cn.partybuild.AppConfig
import www.dico.cn.partybuild.MainActivity
import www.dico.cn.partybuild.R
import www.dico.cn.partybuild.bean.LoginBean
import www.dico.cn.partybuild.modleview.LoginView
import www.dico.cn.partybuild.mvp.factory.CreatePresenter
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity
import www.dico.cn.partybuild.presenter.LoginPresenter

//登录
@CreatePresenter(LoginPresenter::class)
class LoginActivity : AbstractMvpActivity<LoginView, LoginPresenter>(), LoginView {
    private var login_name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun login(view: View) {
        login_name = et_name_login!!.text.toString().trim { it <= ' ' }
        val password = et_password_login!!.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(login_name)) {
            showToast("用户名不能为空")
        } else if (TextUtils.isEmpty(password)) {
            showToast("密码不能为空")
        } else {
            mvpPresenter.clickRequest(dialog, et_name_login!!.text.toString().trim { it <= ' ' }, et_password_login!!.text.toString().trim { it <= ' ' })
        }
    }

    override fun resultSuccess(result: String) {
        val bean = Gson().fromJson<LoginBean>(result, LoginBean::class.java!!)
        if (bean.code == "0000") {
            if (null != bean.data) {
                var name: String? = bean.data.name
                var partyPost: String? = bean.data.partyPost//党内职务
                val token = bean.data.token
                val userId = bean.data.userId//用户id
                val avatar = bean.data.avatar//头像
                val isManager = bean.data.IsManager()//是否为管理员

                name = if (null == name) "" else bean.data.name
                AppConfig.getSpUtils().put("name", name)
                partyPost = if (null == partyPost) "" else bean.data.partyPost
                AppConfig.getSpUtils().put("partyPost", partyPost)
                AppConfig.getSpUtils().put("token", "Bearer $token")
                AppConfig.getSpUtils().put("userId", userId)
                AppConfig.getSpUtils().put("avatar", avatar)
                AppConfig.getSpUtils().put("isManager", isManager)
                AppConfig.getSpUtils().put("isLoginOk", 1)
                goTo(MainActivity::class.java, null)
                finish()
            }
        } else {
            showToast(bean.msg)
        }
    }

    override fun resultFailure(result: String) {
        showToast(result)
    }

    override fun netWorkUnAvailable() {
        showToast("网络出现异常")
    }
}
