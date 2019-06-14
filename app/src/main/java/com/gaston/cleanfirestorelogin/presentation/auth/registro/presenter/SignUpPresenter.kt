package com.gaston.cleanfirestorelogin.presentation.auth.registro.presenter

import androidx.core.util.PatternsCompat
import com.gaston.cleanfirestorelogin.domain.interactor.auth.registerinteractor.SignUpInteractor
import com.gaston.cleanfirestorelogin.presentation.auth.registro.RegisterContract

/**
 * Created by Gastón Saillén on 18 May 2019
 */
class SignUpPresenter(signUpInteractor:SignUpInteractor): RegisterContract.RegisterPresenter {

    var view:RegisterContract.RegisterView? = null
    var signUpInteractor:SignUpInteractor? = null

    init {
        this.signUpInteractor = signUpInteractor
    }

    override fun attachView(view: RegisterContract.RegisterView) {
        this.view = view
    }

    override fun isViewAttached(): Boolean {
        return view != null
    }

    override fun detachView() {
        view = null
    }

    override fun checkEmptyName(fullname: String): Boolean {
        return fullname.isEmpty()
    }

    override fun checkValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun checkEmptyPasswords(pw1: String, pw2: String): Boolean {
        return pw1.isEmpty() or pw2.isEmpty()
    }

    override fun checkPasswordsMatch(pw1: String, pw2: String): Boolean {
        return pw1 == pw2
    }

    override fun signUp(fullname: String, email: String, password: String) {
        view?.showProgress()
        signUpInteractor?.signUp(fullname,email,password,object: SignUpInteractor.RegisterCallback{
            override fun onRegisterSuccess() {
                view?.navigateToMain()
                view?.hideProgress()
            }

            override fun onRegisterFailure(errorMsg: String) {
                view?.showError(errorMsg)
                view?.hideProgress()
            }
        })
    }
}