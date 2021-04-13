package by.st.crypto.sdk

import by.st.kmm.sdk.data.currency.repository.CoinCapCryptoCurrencyRepository
import by.st.kmm.sdk.data.currency.source.local.CryptoCurrencyLocalDataSource
import by.st.kmm.sdk.data.currency.source.local.memory.CryptoCurrencyMemorySource
import by.st.kmm.sdk.data.currency.source.remote.CryptoCurrencyNetworkSource
import by.st.kmm.sdk.data.currency.source.remote.CryptoCurrencyRemoteDataSource
import by.st.kmm.sdk.domain.currency.CryptoCurrencyModel
import by.st.kmm.sdk.domain.currency.CryptoCurrencyRepository

private const val DEFAULT_CACHE_LIFETIME = 10000L

/**
 * SDK is designed to receive and process data about cryptocurrencies.
 *
 * @property dataRepository cryptocurrencies data repository
 *
 * @author RamashkaAE
 */
class CryptoSDK(private val dataRepository: CryptoCurrencyRepository) : CryptoSDKProtocol {

    override suspend fun getCryptoCurrenciesList(countOfItems: Int): List<CryptoCurrencyModel> {
        return dataRepository.getCryptoCurrenciesList(countOfItems)
    }

}

/**
 * Builds crypto SDK instance with preconfigured parameters
 *
 * @author RamashkaAE
 */
class CryptoSDKBuilder {
    private lateinit var apiEndpoint: String

    private lateinit var apiToken: String

    private var isLogEnabled: Boolean = false

    private var cachingType: CachingType = CachingType.NONE

    private var cacheLifetime: Long = DEFAULT_CACHE_LIFETIME

    /**
     * Configures [apiEndpoint] SDK endpoint
     * This is a required parameter
     */
    fun withApiEndpoint(apiEndpoint: String) = apply {
        this.apiEndpoint = apiEndpoint
    }

    /**
     * Configures SDK [apiToken] access token
     * This is a required parameter
     */
    fun withApiToken(apiToken: String) = apply {
        this.apiToken = apiToken
    }

    /**
     * Enables all-level http logging
     * False by default
     */
    fun enableLogging(enable: Boolean) = apply {
        this.isLogEnabled = enable
    }

    /**
     * Configures SDK data caching type. [CachingType.NONE] by default
     */
    fun withCachingType(cachingType: CachingType) = apply {
        this.cachingType = cachingType
    }

    /**
     * Configures cache lifetime in milliseconds. Default is [DEFAULT_CACHE_LIFETIME]
     * If [cachingType] == [CachingType.NONE] this parameters will not be used
     */
    fun withCacheLifeTime(milliseconds: Long) = apply {
        this.cacheLifetime = milliseconds
    }

    /**
     * Builds [CryptoSDK] instance with preconfigured parameters.
     * If one of the required parameters was not specified, throws [ConfigurationException]
     */
    @Throws(ConfigurationException::class)
    fun build(): CryptoSDKProtocol {
        checkConfiguration()

        val localSource: CryptoCurrencyLocalDataSource? =
            if (cachingType == CachingType.IN_DATABASE || cachingType == CachingType.NONE)
                null
            else
                CryptoCurrencyMemorySource(cacheLifetime)

        val remoteDataSource: CryptoCurrencyRemoteDataSource =
            CryptoCurrencyNetworkSource(apiEndpoint, apiToken, isLogEnabled)

        return CryptoSDK(CoinCapCryptoCurrencyRepository(localSource, remoteDataSource))
    }

    private fun checkConfiguration() {
        if (!::apiEndpoint.isInitialized) {
            throw ConfigurationException(ConfigurationException.getMessage(::apiEndpoint.name))
        }

        if (!::apiToken.isInitialized) {
            throw ConfigurationException(ConfigurationException.getMessage(::apiToken.name))
        }
    }
}

/**
 * Thrown error, If one of the required parameters was not specified
 *
 * @author RamashkaAE
 */
class ConfigurationException(errorMessage: String) : Exception(errorMessage) {

    companion object {
        fun getMessage(parameterName: String): String {
            return "\'$parameterName\' isn't specified!"
        }
    }
}

/**
 * Describes data caching type
 *
 * @author RamshkaAE
 */
enum class CachingType {

    /**
     * Without caching
     */
    NONE,

    /**
     * Saves data into device RAM (High performance)
     */
    IN_MEMORY,

    /**
     * Saves data into local database instance (Persistence storage)
     */
    @Deprecated("Not available in current time. Please use IN_MEMORY type")
    IN_DATABASE
}