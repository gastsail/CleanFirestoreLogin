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

package com.gaston.cleanfirestorelogin.presentation.auth.passwordrecover

/**
 * Created by Gastón Saillén on 14 June 2019
 */
interface PasswordRecoverContract {

    interface PasswordRecoverView{
        fun showError(msgError:String?)
        fun showProgress()
        fun hideProgress()
        fun recoverPassword()
        fun navigateToLogin()
    }

    interface PasswordRecoverPresenter{
        fun attachView(passwordRecoverView:PasswordRecoverView)
        fun detachView()
        fun detachJob()
        fun isViewAttached():Boolean
        fun sendPasswordRecover(email:String)
    }
}