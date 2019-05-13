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

package com.gaston.cleanfirestorelogin.presentation.registro.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gaston.cleanfirestorelogin.R
import com.gaston.cleanfirestorelogin.base.BaseActivity
import com.gaston.cleanfirestorelogin.presentation.main.view.MainActivity
import com.gaston.cleanfirestorelogin.presentation.registro.RegisterContract

class RegisterActivity : BaseActivity(),RegisterContract.RegisterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayout(): Int {
        return R.layout.activity_register
    }

    override fun navigateToMain() {
        val intent = Intent(this,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun signUp() {
    }

    override fun showProgress() {
        TODO("not implemented")
    }

    override fun hideProgress() {
        TODO("not implemented")
    }

    override fun showError(errormsg: String) {
        toast(this,errormsg)
    }
}
