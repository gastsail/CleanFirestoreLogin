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