package by.st.kmm.sdk.data.cache

import android.content.Context
import cache.SdkCacheDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver() : SqlDriver {
        return AndroidSqliteDriver(SdkCacheDatabase.Schema, context, "SdkCacheDatabase")
    }
}