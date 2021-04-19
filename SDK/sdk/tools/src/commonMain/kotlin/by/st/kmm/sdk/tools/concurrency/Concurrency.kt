package by.st.kmm.sdk.tools.concurrency

import kotlinx.coroutines.CoroutineDispatcher

expect val MainDispatcher: CoroutineDispatcher

expect val BackgroundDispatcher: CoroutineDispatcher