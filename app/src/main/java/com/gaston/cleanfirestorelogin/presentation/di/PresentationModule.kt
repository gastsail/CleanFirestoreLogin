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

package com.gaston.cleanfirestorelogin.presentation.di

import com.gaston.cleanfirestorelogin.domain.interactor.auth.logininteractor.SignInInteractor
import com.gaston.cleanfirestorelogin.domain.interactor.auth.logininteractor.SignInInteractorImpl
import com.gaston.cleanfirestorelogin.domain.interactor.auth.passwordrecoverinteractor.PasswordRecover
import com.gaston.cleanfirestorelogin.domain.interactor.auth.passwordrecoverinteractor.PasswordRecoverImpl
import com.gaston.cleanfirestorelogin.domain.interactor.auth.registerinteractor.SignUpInteractor
import com.gaston.cleanfirestorelogin.domain.interactor.auth.registerinteractor.SignUpInteractorImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Gastón Saillén on 11 July 2019
 */

@Module
class PresentationModule {

    @Provides
    fun provideSignInInteractor(): SignInInteractor = SignInInteractorImpl()

    @Provides
    fun providePasswordRecoverInteractor(): PasswordRecover = PasswordRecoverImpl()

    @Provides
    fun provideSignUpInteractor(): SignUpInteractor = SignUpInteractorImpl()
}