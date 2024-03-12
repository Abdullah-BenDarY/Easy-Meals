package com.example.medicalapp.util

import android.content.Context
import android.content.SharedPreferences


object SharedPrefs {
    private const val PREFS_NAME = "user_data"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setUserToken(id: String) {
        with(sharedPreferences.edit()) {
            putString("USER_TOKEN", id)
            commit()
        }
    }

    fun getUserToken(): String? {
        return sharedPreferences.getString("USER_TOKEN", "")
    }

    fun setUserType(id: String) {
        with(sharedPreferences.edit()) {
            putString("USER_TYPE", id)
            commit()
        }
    }

    fun getUserType(): String? {
        return sharedPreferences.getString("USER_TYPE", "")
    }

    fun setUserName(id: String) {
        with(sharedPreferences.edit()) {
            putString("USER_NAME", id)
            commit()
        }
    }

    fun getUserName(): String? {
        return sharedPreferences.getString("USER_NAME", "")
    }

    fun setUserGender(id: String) {
        with(sharedPreferences.edit()) {
            putString("USER_GENDER", id)
            commit()
        }
    }

    fun getUserGender(): String? {
        return sharedPreferences.getString("USER_GENDER", "")
    }

    fun setUserSpesialist(id: String) {
        with(sharedPreferences.edit()) {
            putString("", id)
            commit()
        }
    }

    fun getUserSpesialist(): String? {
        return sharedPreferences.getString("USER_SPESIALIST", "")
    }

    fun setUserBirthDate(id: String) {
        with(sharedPreferences.edit()) {
            putString("USER_BIRTH_DATE", id)
            commit()
        }
    }

    fun getUserBirthDate(): String? {
        return sharedPreferences.getString("USER_BIRTH_DATE", "")
    }

    fun setUserStatus(id: String) {
        with(sharedPreferences.edit()) {
            putString("USER_STATUS", id)
            commit()
        }
    }

    fun getUserStatus(): String? {
        return sharedPreferences.getString("USER_STATUS", "")
    }

    fun setUserPhone(id: String) {
        with(sharedPreferences.edit()) {
            putString("USER_PHONE", id)
            commit()
        }
    }

    fun getUserPhone(): String? {
        return sharedPreferences.getString("USER_PHONE", "")
    }


    fun setUserEmail(id: String) {
        with(sharedPreferences.edit()) {
            putString("USER_EMAIL", id)
            commit()
        }
    }

    fun getUserEmail(): String? {
        return sharedPreferences.getString("USER_EMAIL", "")
    }


    fun setUserAdress(id: String) {
        with(sharedPreferences.edit()) {
            putString("USER_ADRESS", id)
            commit()
        }
    }

    fun getUserAdress(): String? {
        return sharedPreferences.getString("USER_ADRESS", "")
    }


}
