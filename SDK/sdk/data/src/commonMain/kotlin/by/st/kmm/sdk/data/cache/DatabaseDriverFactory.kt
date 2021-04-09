package by.st.kmm.sdk.data.cache

import com.squareup.sqldelight.db.SqlDriver

/**
 * @author RamashkaAE
 */
expect class DatabaseDriverFactory {
    fun createDriver() : SqlDriver
}