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