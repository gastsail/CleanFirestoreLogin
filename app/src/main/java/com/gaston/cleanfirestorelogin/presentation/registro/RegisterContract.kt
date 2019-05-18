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

package com.gaston.cleanfirestorelogin.presentation.registro

/**
 * Created by Gastón Saillén on 13 May 2019
 */
interface RegisterContract {

    interface RegisterView{
        fun navigateToMain()
        fun signUp()
        fun showProgress()
        fun hideProgress()
        fun showError(errormsg:String)
    }

    interface RegisterPresenter{
        fun attachView(view:RegisterView)
        fun isViewAttached():Boolean
        fun detachView()
        fun checkEmptyName(fullname:String):Boolean
        fun checkValidEmail(email:String):Boolean
        fun checkEmptyPasswords(pw1:String,pw2:String):Boolean
        fun checkPasswordsMatch(pw1:String,pw2:String):Boolean
        fun signUp(fullname:String,email:String,password:String)
    }
}