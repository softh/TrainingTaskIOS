package by.st.kmm.currencies.androidApp

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import by.st.crypto.sdk.CryptoSDKBuilder
import by.st.crypto.sdk.InitializationProgressListener
import by.st.kmm.sdk.data.cache.DatabaseDriverFactory
import by.st.kmm.sdk.data.currency.source.local.memory.CryptoCurrencyMemorySource
import by.st.kmm.sdk.data.currency.source.remote.CryptoCurrencyNetworkSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch


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


        findViewById<View>(R.id.button).setOnClickListener {
            val sdk = CryptoSDKBuilder()
                .withApiEndpoint("https://pro-api.coinmarketcap.com/v1")
                .withApiToken("07c16939-e6cc-4446-8053-283b35eb91fa")
                .enableLogging(true)
                .withDatabaseDriverFactory(DatabaseDriverFactory(this))
                .build()


            sdk.getCryptoCurrenciesList(20).subscribe(isThreadLocal = false, onSuccess = { result ->
                val a = 1
            }, onError = { error ->
                val c = 0
            })

//            GlobalScope.launch(Dispatchers.Main) {
//                sdk.startInitialization(object : InitializationProgressListener {
//                    override fun onProgress(currentStep: Int, totalSteps: Int) {
//
//                        findViewById<Button>(R.id.button).text = "$currentStep/$totalSteps"
//
//                    }
//                })
//
//                val a = sdk.getCryptoCurrenciesList()
//                val b = 0
//            }
        }
    }


    fun setImageViewWithByteArray(view: ImageView, data: ByteArray) {
        val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
        view.setImageBitmap(bitmap)
    }
}
