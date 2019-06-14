package com.gaston.cleanfirestorelogin.domain.interactor.auth.passwordrecoverinteractor

/**
 * Created by Gastón Saillén on 14 June 2019
 */
interface PasswordRecover {

    suspend fun sendPasswordResetEmail(email: String)

}