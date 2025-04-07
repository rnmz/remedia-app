package dev.runo.core.network.annotation

/**
 * Indicates that the operation will only be executed if a valid API key is provided.
 * If no API key is available, the operation will not be executed.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class RequiresApiKey