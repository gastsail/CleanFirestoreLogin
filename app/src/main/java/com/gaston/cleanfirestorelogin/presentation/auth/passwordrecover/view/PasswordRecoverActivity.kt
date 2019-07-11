package com.gaston.cleanfirestorelogin.presentation.auth.passwordrecover.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.gaston.cleanfirestorelogin.CleanFirestoreLoginApp
import com.gaston.cleanfirestorelogin.R
import com.gaston.cleanfirestorelogin.base.BaseActivity
import com.gaston.cleanfirestorelogin.presentation.auth.login.view.SignInActivity
import com.gaston.cleanfirestorelogin.presentation.auth.passwordrecover.PasswordRecoverContract
import com.gaston.cleanfirestorelogin.presentation.auth.passwordrecover.presenter.PasswordRecoverPresenter
import kotlinx.android.synthetic.main.activity_password_recover.*
import javax.inject.Inject

class PasswordRecoverActivity : BaseActivity(), PasswordRecoverContract.PasswordRecoverView {

    @Inject
    lateinit var presenter: PasswordRecoverPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as CleanFirestoreLoginApp).getAppComponent()?.inject(this)
        presenter.attachView(this)
        btn_recover_pw.setOnClickListener {
            recoverPassword()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_password_recover
    }

    override fun showError(msgError: String?) {
        toast(this, msgError)
    }

    override fun showProgress() {
        progress_recover_pw.visibility = View.VISIBLE
        btn_recover_pw.visibility = View.GONE
    }

    override fun hideProgress() {
        progress_recover_pw.visibility = View.GONE
        btn_recover_pw.visibility = View.VISIBLE
    }

    override fun recoverPassword() {
        val email:String = etxt_recover_pw.text.trim().toString()
        if(!email.isEmpty()) presenter.sendPasswordRecover(email) else toast(this,"E-mail is empty")
    }

    override fun navigateToLogin() {
        startActivity(Intent(this,SignInActivity::class.java))
        toast(this,"Recover E-mail sent")
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        presenter.detachJob()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detachView()
        presenter.detachJob()
    }
}
