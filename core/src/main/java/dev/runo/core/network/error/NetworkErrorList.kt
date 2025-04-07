package dev.runo.core.network.error

import dev.runo.core.common.AppError

/**
 * List of possible errors in all data modules.
 *
 * @property TIMEOUT timeout connect to server
 * @property NO_CONNECTION No internet connection.
 * @property UNAUTHORIZED requires api token or token expired
 * @property FORBIDDEN forbidden content
 * @property NOT_FOUND content deleted, not found
 * @property LEGAL_REASON content removed due to legal problems
 * @property INTERNAL_SERVER internal server error
 */
enum class NetworkErrorList : AppError {
    UNEXPECTED,
    TIMEOUT,
    NO_CONNECTION,

    UNAUTHORIZED,
    FORBIDDEN,
    NOT_FOUND,
    LEGAL_REASON,

    INTERNAL_SERVER
}