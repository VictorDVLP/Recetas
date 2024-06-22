package com.kkm.recipes

import android.database.sqlite.SQLiteException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.io.IOException

sealed interface ResultCall <out T> {
    data class Success<T>(val data: T) : ResultCall<T>
    data class Error(val exception: Throwable) : ResultCall<Nothing>
    data object Loading : ResultCall<Nothing>
}

//inline fun <T> ResultCall<T>.onSuccess(action: (T) -> Unit): ResultCall<T> {
//    if (this is ResultCall.Success) {
//        action(data)
//    }
//    return this
//}

fun <T> Flow<T>.stateAsResultIn(scope: CoroutineScope): StateFlow<ResultCall<T>> =
    map<T, ResultCall<T>> { ResultCall.Success(it) }
        .handleError()
        .stateIn(
        scope = scope,
        started = WhileSubscribed(5000),
        initialValue = ResultCall.Loading
    )

private fun <T> Flow<ResultCall<T>>.handleError(): Flow<ResultCall<T>> =
    catch { e -> val errorMessage = when (e) {
        is IOException -> "Network error"
        is SQLiteException -> "Database error"
        is RuntimeException -> "Timeout error"
        else -> "Unknown error"
    }
        emit(ResultCall.Error(Exception(errorMessage)))

    }