package test.dev.withings.common.utils

import timber.log.Timber

fun logException(throwable: Throwable) {
    Timber.e(throwable)
}