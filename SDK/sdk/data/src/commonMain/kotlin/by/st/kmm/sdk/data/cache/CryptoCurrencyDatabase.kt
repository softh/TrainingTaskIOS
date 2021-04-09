package by.st.kmm.sdk.data.cache

import by.st.kmm.sdk.data.currency.CryptoCurrencyDto
import by.st.kmm.sdk.data.currency.CurrencyDto
import by.st.kmm.sdk.data.currency.QuoteDto
import cache.SdkCacheDatabase

/**
 * @author RamashkaAE
 */
class CryptoCurrencyDatabase(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = SdkCacheDatabase(databaseDriverFactory.createDriver())
    private val databaseQuery = database.sdkCacheDatabaseQueries

    internal fun clearDatabase() {
        databaseQuery.transaction {
            databaseQuery.removeAllCryptoCurrency()
        }
    }

    internal fun getAllCryptoCurrencies() : List<CryptoCurrencyDto> {
        return databaseQuery.getAllCryptoCurrency(::mapEntity).executeAsList()
    }

    private fun mapEntity(id: Long,
                          currencyId: Long,
                          name: String,
                          symbol: String,
                          currentPrice: Double,
                          percentChangeByHour: Double,
                          percentChangeByDay: Double,
                          percentChangeByWeek: Double,
                          percentChangeByMonth: Double,
                          totalCapitalization: Double,
                          updatedDate: String) : CryptoCurrencyDto {

        return CryptoCurrencyDto(
            currencyId.toInt(),
            name,
            symbol,
            QuoteDto(
                CurrencyDto(
                    currentPrice,
                    percentChangeByHour,
                    percentChangeByDay,
                    percentChangeByWeek,
                    percentChangeByMonth,
                    totalCapitalization,
                    updatedDate
                )
            )
        )
    }
}