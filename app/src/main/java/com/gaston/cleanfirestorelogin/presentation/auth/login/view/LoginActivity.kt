/*
 *
 *  * Copyright (C) 2019 Gastón Luis Saillén.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.gaston.cleanfirestorelogin.presentation.auth.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.gaston.cleanfirestorelogin.R
import com.gaston.cleanfirestorelogin.base.BaseActivity
import com.gaston.cleanfirestorelogin.domain.interactor.auth.logininteractor.SignInInteractorImpl
import com.gaston.cleanfirestorelogin.presentation.auth.login.LoginContract
import com.gaston.cleanfirestorelogin.presentation.auth.login.presenter.LoginPresenter
import com.gaston.cleanfirestorelogin.presentation.auth.passwordrecover.view.PasswordRecoverActivity
import com.gaston.cleanfirestorelogin.presentation.main.view.MainActivity
import com.gaston.cleanfirestorelogin.presentation.auth.registro.view.SignUpActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Gastón Saillén on 04 May 2019
 */

class LoginActivity : BaseActivity(), LoginContract.LoginView {

    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = LoginPresenter(SignInInteractorImpl())
        presenter.attachView(this)
        btn_signIn.setOnClickListener {
            signIn()
        }

        txt_register.setOnClickListener {
            navigateToRegister()
        }

        txt_password_recover.setOnClickListener {
            navigateToPasswordRecover()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun showError(msgError: String?) {
        toast(this, msgError)
    }

    override fun showProgressBar() {
        progressBar_signIn.visibility = View.VISIBLE
        btn_signIn.visibility = View.GONE
    }

    override fun hideProgressBar() {
        progressBar_signIn.visibility = View.GONE
        btn_signIn.visibility = View.VISIBLE
    }

    override fun signIn() {
        val email = etxt_email.text.toString().trim()
        val password = etxt_password.text.toString().trim()
        if (presenter.checkEmptyFields(email, password)) toast(this, "Uno o ambos campos son vacios")
        else
        presenter.signInUserWithEmailAndPassword(email, password)
    }

    override fun navigateToMain() {
        val intent = Intent(this,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun navigateToRegister() {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    override fun navigateToPasswordRecover() {
        startActivity(Intent(this, PasswordRecoverActivity::class.java))

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettachView()
        presenter.dettachJob()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.dettachView()
        presenter.dettachJob()
    }

}
