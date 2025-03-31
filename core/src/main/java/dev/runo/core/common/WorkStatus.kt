package dev.runo.core.common

sealed interface WorkStatus<out T> {
    data class Success<out T>(val data: T): WorkStatus<T>
    data class Error(val error: AppError): WorkStatus<Nothing>
    data object Loading: WorkStatus<Nothing>
}