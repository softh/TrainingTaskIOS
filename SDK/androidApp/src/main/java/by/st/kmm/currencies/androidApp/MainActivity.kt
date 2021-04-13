package by.st.kmm.currencies.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import by.st.crypto.sdk.CryptoSDKBuilder
import by.st.crypto.sdk.CryptoViewModel
import by.st.kmm.sdk.data.currency.repository.CoinCapCryptoCurrencyRepository
import by.st.kmm.sdk.data.currency.source.local.memory.CryptoCurrencyMemorySource
import by.st.kmm.sdk.data.currency.source.remote.CryptoCurrencyNetworkSource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val remoteSource = CryptoCurrencyNetworkSource(
            apiEndpoint = "https://pro-api.coinmarketcap.com/v1",
            apiToken = "07c16939-e6cc-4446-8053-283b35eb91fa",
            isLogEnabled = true
        )

        val localSource = CryptoCurrencyMemorySource(10000)


        val repository = CoinCapCryptoCurrencyRepository(localSource, remoteSource)

        findViewById<View>(R.id.button).setOnClickListener {
            val sdk = CryptoSDKBuilder()
                .withApiEndpoint("https://pro-api.coinmarketcap.com/v1")
                .withApiToken("07c16939-e6cc-4446-8053-283b35eb91fa")
                .enableLogging(true)
                .build()
        }
    }
}
