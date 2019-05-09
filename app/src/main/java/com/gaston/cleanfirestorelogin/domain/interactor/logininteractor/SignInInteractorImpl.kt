package com.gaston.cleanfirestorelogin.domain.interactor.logininteractor

import com.google.firebase.auth.FirebaseAuth

/**
 * Created by Gastón Saillén on 09 May 2019
 */
class SignInInteractorImpl: SignInInteractor {

    override fun signInWithEmailAndPassword(email: String, password: String,
        listener: SignInInteractor.SigninCallback) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                listener.onSignInSuccess()
            }else{
                listener.onSignInFailure(it.exception?.message!!)
            }
        }

    }
}