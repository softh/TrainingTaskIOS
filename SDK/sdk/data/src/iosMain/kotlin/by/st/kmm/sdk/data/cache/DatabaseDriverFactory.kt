package by.st.kmm.sdk.data.cache

import cache.SdkCacheDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver() : SqlDriver {
        return NativeSqliteDriver(SdkCacheDatabase.Schema, "SdkCacheDatabase")
    }
}