package com.gaston.cleanfirestorelogin.domain.interactor.auth.passwordrecoverinteractor

import com.gaston.cleanfirestorelogin.presentation.auth.passwordrecover.exception.PasswordRecoverException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * Created by Gastón Saillén on 14 June 2019
 */
class PasswordRecoverImpl: PasswordRecover {

    override suspend fun sendPasswordResetEmail(email: String): Unit = suspendCancellableCoroutine { continuation ->
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
            if(it.isSuccessful){
               continuation.resume(Unit)
            }else{
                continuation.resumeWithException(PasswordRecoverException(it.exception?.message !!))
            }
        }
    }
}