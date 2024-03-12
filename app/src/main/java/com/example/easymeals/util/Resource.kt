package com.example.medicalapp.util

    sealed class Resource<T>(
        val data: T? = null,
        val message: String? = null
    ){
        class Success<T>(data: List<T>?): Resource<T>()
//        class Success<T>(data: List<T>?):
//            Resource<List<T>?>(data)
        class Error<T>(message: String,data: T?=null):Resource<T>(data,message)
    }