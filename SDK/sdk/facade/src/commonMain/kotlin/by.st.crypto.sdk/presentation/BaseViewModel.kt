package by.st.crypto.sdk.presentation

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel {

    protected val errorHandler = CoroutineExceptionHandler { _, error ->
        _errorState.value = error
    }

    private val _progressState = MutableStateFlow(OperationStatus.FINISHED)
    val progressState: StateFlow<OperationStatus> = _progressState

    private val _errorState = MutableStateFlow<Throwable?>(null)
    val errorState: StateFlow<Throwable?> = _errorState

    fun postStartOperation() {
        _progressState.value = OperationStatus.STARTED
    }

    fun postFinishOperation() {
        _progressState.value = OperationStatus.FINISHED
    }

    fun postError(error: Throwable) {
        _errorState.value = error
    }

    enum class OperationStatus {
        STARTED,
        IN_PROGRESS,
        FINISHED
    }
}