package com.example.shoppinglist.utils

import kotlinx.coroutines.flow.*

fun <ResultType> networkBoundResource(
    query: suspend () -> Flow<ResultType>,
    fetch: suspend () -> Flow<ResultType>,
    saveFetchResult: suspend (ResultType) -> Unit = { },
    shouldFetch: (ResultType) -> Boolean = { true }
) = flow<Resource<ResultType>> {
    val localFlow = query()
    val data = localFlow.firstOrNull()

    emit(Resource.Loading())

    if (data != null) {
        emit(Resource.Success(data))
    }
    if (data?.let(shouldFetch) != false) {

        try {
            emitAll(fetch().onEach {
                saveFetchResult(it)
            }.map { Resource.Success(it) })
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable, data))
        }
    } else {
        emit(Resource.Success(data))
    }
}