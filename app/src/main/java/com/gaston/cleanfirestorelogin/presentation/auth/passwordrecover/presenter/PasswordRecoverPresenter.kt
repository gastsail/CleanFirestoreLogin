package com.gaston.cleanfirestorelogin.presentation.auth.passwordrecover.presenter

import com.gaston.cleanfirestorelogin.domain.interactor.auth.passwordrecoverinteractor.PasswordRecover
import com.gaston.cleanfirestorelogin.presentation.auth.passwordrecover.PasswordRecoverContract
import com.gaston.cleanfirestorelogin.presentation.auth.passwordrecover.exception.PasswordRecoverException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by Gastón Saillén on 14 June 2019
 */
class PasswordRecoverPresenter(passwordRecoverInteractor: PasswordRecover) :
    PasswordRecoverContract.PasswordRecoverPresenter, CoroutineScope {

    var view: PasswordRecoverContract.PasswordRecoverView? = null
    var passwordRecoverInteractor: PasswordRecover? = null

    var job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        this.passwordRecoverInteractor = passwordRecoverInteractor
    }

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
                passwordRecoverInteractor?.sendPasswordResetEmail(email)
                view?.hideProgress()
                view?.navigateToLogin()
            } catch (e:PasswordRecoverException){
                view?.hideProgress()
                view?.showError(e.message)
            }
        }

    }
}