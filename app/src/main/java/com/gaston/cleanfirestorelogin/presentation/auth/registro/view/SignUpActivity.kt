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

package com.gaston.cleanfirestorelogin.presentation.auth.registro.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.gaston.cleanfirestorelogin.CleanFirestoreLoginApp
import com.gaston.cleanfirestorelogin.R
import com.gaston.cleanfirestorelogin.base.BaseActivity
import com.gaston.cleanfirestorelogin.domain.interactor.auth.registerinteractor.SignUpInteractorImpl
import com.gaston.cleanfirestorelogin.presentation.main.view.MainActivity
import com.gaston.cleanfirestorelogin.presentation.auth.registro.RegisterContract
import com.gaston.cleanfirestorelogin.presentation.auth.registro.presenter.SignUpPresenter
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject

class SignUpActivity : BaseActivity(),RegisterContract.RegisterView {

    @Inject
    lateinit var presenter: SignUpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as CleanFirestoreLoginApp).getAppComponent()?.inject(this)
        presenter.attachView(this)

        btn_signUp.setOnClickListener {
            signUp()
        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_register
    }

    override fun navigateToMain() {
        val intent = Intent(this,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun signUp() {

        val fullname:String = etx_fullname.text.toString().trim()
        val email:String = etxt_email_register.text.toString().trim()
        val pw1:String = etx_pw1.text.toString().trim()
        val pw2:String = etx_pw2.text.toString().trim()

        if(presenter.checkEmptyName(fullname)){
            etx_fullname.error = "The name is empty."
            return
        }

        if(!presenter.checkValidEmail(email)){
            etxt_email_register.error = "The email is invalid"
            return
        }

        if(presenter.checkEmptyPasswords(pw1,pw2)){
            etx_pw1.error = "Empty field"
            etx_pw2.error = "Empty field"
            return
        }

        if(!presenter.checkPasswordsMatch(pw1,pw2)){
            etx_pw1.error = "Passwords dont match"
            etx_pw2.error = "Passwords dont match"
            return
        }

        presenter.signUp(fullname,email,pw1)

    }

    override fun showProgress() {
        progress_signup.visibility = View.VISIBLE
        btn_signUp.visibility = View.GONE
    }

    override fun hideProgress() {
        progress_signup.visibility = View.GONE
        btn_signUp.visibility = View.VISIBLE
    }

    override fun showError(errormsg: String) {
        toast(this,errormsg)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
