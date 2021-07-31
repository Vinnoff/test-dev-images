package test.dev.withings.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import test.dev.withings.common.utils.CustomCoroutineExceptionHandler
import test.dev.withings.common.utils.logException
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
    private val dispatcher: CoroutineDispatcher
) : ViewModel(), CoroutineScope {

    private var handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable -> baseHandleException(throwable) }

    private var job: Job = SupervisorJob()

    private var debounceJob: Job? = null

    override val coroutineContext: CoroutineContext
        get() = dispatcher + job + handler


    override fun onCleared() {
        super.onCleared()
        job.cancelChildren() // Cancel job on activity destroy. After destroy all children jobs will be cancelled automatically
    }


    private fun baseHandleException(throwable: Throwable) {
        logException(throwable)
    }

    protected fun CoroutineScope.debounce(exceptionHandler: CustomCoroutineExceptionHandler, l: Long = 300L, function: suspend CoroutineScope.() -> Unit) {
        debounceJob?.cancel()
        debounceJob = launch(exceptionHandler) {
            delay(l)
            function.invoke(this)
        }
    }
}