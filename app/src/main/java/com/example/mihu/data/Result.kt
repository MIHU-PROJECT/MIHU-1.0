package com.example.mihu.data

//sealed class Result<out R> private constructor() {
//    data class Success<out T>(val data: T) : Result<T>()
//    data class Error(val error: String) : Result<Nothing>()
//    data object Loading : Result<Nothing>()
//}


sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: String?) : Result<Nothing>()
    data object Loading : Result<Nothing>()
}