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

package com.gaston.cleanfirestorelogin.presentation.auth.login.presenter

import com.gaston.cleanfirestorelogin.domain.interactor.auth.logininteractor.SignInInteractor
import com.gaston.cleanfirestorelogin.presentation.auth.login.LoginContract
import com.gaston.cleanfirestorelogin.presentation.auth.login.exceptions.FirebaseLoginException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by Gastón Saillén on 04 May 2019
 */
class LoginPresenter(signInInteractor: SignInInteractor) : LoginContract.LoginPresenter, CoroutineScope {

    var view: LoginContract.LoginView? = null
    var signInInteractor: SignInInteractor? = null

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        this.signInInteractor = signInInteractor
    }


    override fun attachView(view: LoginContract.LoginView) {
        this.view = view
    }

    override fun dettachView() {
        view = null
    }

    override fun dettachJob() {
        coroutineContext.cancel()
    }

    override fun isViewAttached(): Boolean {
        return view != null
    }

    override fun signInUserWithEmailAndPassword(email: String, password: String) {

        launch {
            view?.showProgressBar()

            try{
                signInInteractor?.signInWithEmailAndPassword(email, password)

                if (isViewAttached()) {
                    view?.hideProgressBar()
                    view?.navigateToMain()
                }

            } catch (e:FirebaseLoginException){

                if(isViewAttached()){
                    view?.showError(e.message)
                    view?.hideProgressBar()
                }

            }
        }

    }

    override fun checkEmptyFields(email: String, password: String): Boolean {
        return email.isEmpty() || password.isEmpty()
    }


}