package dev.runo.core.network.annotation

/**
 * Indicates that the API key will be included if it is provided.
 * If no API key is available, the operation will proceed without it.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class OptionalApiKey
