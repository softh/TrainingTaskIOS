package by.st.crypto.sdk.tools.concurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SuspendWrapper<T>(private val suspender: suspend () -> T) {
    init {
        freeze()
    }
    fun subscribe(
        scope: CoroutineScope,
        onSuccess: (item: T) -> Unit,
        onThrow: (error: Throwable) -> Unit
    ): Job = scope.launch {
        try {
            onSuccess(suspender().freeze())
        } catch (error: Throwable) {
            onThrow(error.freeze())
        }
    }.freeze()
}