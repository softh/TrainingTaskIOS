package by.st.kmm.sdk.tools.concurrency

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val MainDispatcher: CoroutineDispatcher = Dispatchers.Main

actual val BackgroundDispatcher: CoroutineDispatcher = Dispatchers.Default