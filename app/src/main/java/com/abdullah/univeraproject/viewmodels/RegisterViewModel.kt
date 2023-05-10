package com.abdullah.univeraproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.abdullah.univeraproject.utils.isEmail
import com.abdullah.univeraproject.utils.isNull
import com.abdullah.univeraproject.utils.isPassword

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private var _userName: String = ""
    private var _password: String = ""
    private var _email: String = ""

    var error: MutableLiveData<RegisterMessage?> = MutableLiveData(null)


    fun setUserName(userName: String) {
        _userName = userName
    }

    fun setEmail(email: String) {
        _email = email
    }

    fun setPassword(password: String) {
        _password = password
    }

    fun submitRegister() {

        if (_userName.isNull() || _password.isNull() || _email.isNull()) {
            error.postValue(RegisterMessage(false, "Lütfen tüm alanları doldurunuz"))
        } else {

            if (!_email.isEmail()) {
                error.postValue(RegisterMessage(false, "Geçersiz Email"))
                return
            }
            if (!_password.isPassword()) {
                error.postValue(RegisterMessage(false, "Geçersiz şifre"))
                return
            }

            error.postValue(RegisterMessage(true, "Kayıt Başarılı"))
        }
    }

}

data class RegisterMessage(val success: Boolean, val message: String)