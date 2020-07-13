/*
    Pocket Knowledge. Application for educational purposes
    Copyright (C) 2020. Empresaurios

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

    Contact: empresaurios2020@gmail.com
 */
package com.jaiser.pocketknowledgeapp

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    enum class AuthenticationState {
        UNAUTHENTICATED,        // Initial state, the user needs to authenticate
        AUTHENTICATED  ,        // The user has authenticated successfully
        INVALID_AUTHENTICATION  // Authentication failed
    }

    val authenticationState = MutableLiveData<AuthenticationState>()
    var username: String
    var password : String

    init {
        // In this example, the user is always unauthenticated when MainActivity is launched
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
        username = ""
        password = ""
    }

    fun refuseAuthentication() {
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
    }

    fun authenticate(username: String, password: String) {
        if (passwordIsValidForUsername(username, password)) {
            this.username = username
            this.password = password
            authenticationState.value = AuthenticationState.AUTHENTICATED
        } else {
            authenticationState.value = AuthenticationState.INVALID_AUTHENTICATION

        }
    }

    private fun passwordIsValidForUsername(username: String, password: String): Boolean {
        Log.i("info", "$username y $password")
        if (username == "jimbo" && password == "jimbo") {
            Log.i("info", "Se ha ingresado a la App")
            return true
        }
        Log.i("info", "No se ha ingresado a la App" + username + password)
        return false
    }

}