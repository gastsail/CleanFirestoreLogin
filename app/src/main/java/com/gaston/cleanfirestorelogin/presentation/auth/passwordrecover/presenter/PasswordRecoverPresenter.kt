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

package com.gaston.cleanfirestorelogin.presentation.auth.passwordrecover.presenter

import com.gaston.cleanfirestorelogin.domain.interactor.auth.passwordrecoverinteractor.PasswordRecover
import com.gaston.cleanfirestorelogin.presentation.auth.passwordrecover.PasswordRecoverContract
import com.gaston.cleanfirestorelogin.presentation.auth.passwordrecover.exception.PasswordRecoverException
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by Gastón Saillén on 14 June 2019
 */
class PasswordRecoverPresenter @Inject constructor(private val passwordRecoverInteractor: PasswordRecover) :
    PasswordRecoverContract.PasswordRecoverPresenter, CoroutineScope {

    var view: PasswordRecoverContract.PasswordRecoverView? = null

    var job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun attachView(passwordRecoverView: PasswordRecoverContract.PasswordRecoverView) {
        this.view = passwordRecoverView
    }

    override fun detachView() {
        view = null
    }

    override fun detachJob() {
        coroutineContext.cancel()
    }

    override fun isViewAttached(): Boolean {
        return view != null
    }

    override fun sendPasswordRecover(email: String) {

        launch {
            try {
                view?.showProgress()
                passwordRecoverInteractor.sendPasswordResetEmail(email)
                view?.hideProgress()
                view?.navigateToLogin()
            } catch (e:PasswordRecoverException){
                view?.hideProgress()
                view?.showError(e.message)
            }
        }

    }
}