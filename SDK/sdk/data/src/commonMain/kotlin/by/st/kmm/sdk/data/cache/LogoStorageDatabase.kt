package by.st.kmm.sdk.data.cache

import by.st.kmm.sdk.data.common.tools.getDecodedBase64Value
import by.st.kmm.sdk.data.common.tools.getEncodedBase64Value
import by.st.kmm.sdk.data.currency.*
import cache.SdkCacheDatabase
import kotlin.math.log

class LogoStorageDatabase(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = SdkCacheDatabase(databaseDriverFactory.createDriver())
    private val databaseQuery = database.sdkImageStorageDatabaseQueries

     fun clearStorage() {
        databaseQuery.transaction {
            databaseQuery.clearStorage()
        }
    }

     fun insertLogo(logo: CurrencyLogoPayload) {
        database.transaction {
            databaseQuery.insertImage(logo.currencyId, logo.logoPayload)
        }
    }

     fun getAllLogos(): List<CurrencyLogoPayload> {
        return databaseQuery.getAllImages(::mapEntity).executeAsList()
    }

     fun getLogoByCurrencyId(currencyId: Int) : CurrencyLogoPayload? {
        return databaseQuery.getImageByCurrencyId(currencyId.toLong(), ::mapEntity).executeAsOneOrNull()
    }

    private fun mapEntity(
        id: Long,
        currencyPayload: ByteArray,
    ): CurrencyLogoPayload {

        return CurrencyLogoPayload(
            id,
            currencyPayload
        )
    }
}