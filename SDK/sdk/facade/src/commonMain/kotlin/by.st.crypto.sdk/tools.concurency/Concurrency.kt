package by.st.crypto.sdk.tools.concurency

import kotlinx.coroutines.CoroutineDispatcher

internal expect val Main: CoroutineDispatcher

internal expect val Background: CoroutineDispatcher