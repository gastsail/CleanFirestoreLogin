package com.gaston.cleanfirestorelogin.domain.interactor.logininteractor

/**
 * Created by Gastón Saillén on 09 May 2019
 */
interface SignInInteractor {

    interface SigninCallback{
        fun onSignInSuccess()
        fun onSignInFailure(errorMsg:String)
    }

    fun signInWithEmailAndPassword(email:String,password:String,listener:SigninCallback)
}