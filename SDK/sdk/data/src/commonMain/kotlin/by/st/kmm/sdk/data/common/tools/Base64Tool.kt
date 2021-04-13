package by.st.kmm.sdk.data.common.tools

private const val BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
private val BASE64_INVERSE_ALPHABET = IntArray(256) { BASE64_ALPHABET.indexOf(it.toChar()) }

/**
 * Encodes current [ByteArray] to Base64 like [ByteArray]
 */
fun ByteArray.getEncodedBase64Value() = Base64Tool.encode(this)

/**
 * Decodes current Base64 encoded [ByteArray] to original [ByteArray]
 */
fun ByteArray.getDecodedBase64Value() = Base64Tool.decode(this)

/**
 * Encodes current [String] to Base64 like [String]
 */
fun String.getEncodedBase64Value() = Base64Tool.encode(this.encodeToByteArray()).decodeToString()

/**
 * Decodes current Base64 encoded [String] to original [String]
 */
fun String.getDecodedBase64Value() = Base64Tool.decode(this.encodeToByteArray()).decodeToString()

/**
 * Contains support methods for Base64 encoding algorithm
 *
 * todo: not used yet
 *
 * @author RamashkaAE
 */
internal object Base64Tool {

    /**
     * Encode [source] byte array via Base64 encoding algorithm and returns result as [ByteArray]
     */
    fun encode(source: ByteArray) : ByteArray {
        val output = mutableListOf<Byte>()
        var padding = 0
        var position = 0
        while (position < source.size) {
            var b = source[position].toPositiveInt() shl 16 and 0xFFFFFF
            if (position + 1 < source.size) b = b or (source[position + 1].toPositiveInt() shl 8) else padding++
            if (position + 2 < source.size) b = b or (source[position + 2].toPositiveInt()) else padding++
            for (i in 0 until 4 - padding) {
                val c = b and 0xFC0000 shr 18
                output.add(BASE64_ALPHABET[c].toByte())
                b = b shl 6
            }
            position += 3
        }
        for (i in 0 until padding) {
            output.add('='.toByte())
        }
        return output.toByteArray()
    }

    /**
     * Decodes encoded [source] source byte array and returns result as [ByteArray]
     */
    fun decode(source: ByteArray) : ByteArray {
        val output = mutableListOf<Byte>()
        var position = 0
        while (position < source.size) {
            var b: Int
            if (BASE64_INVERSE_ALPHABET[source[position].toPositiveInt()] != -1) {
                b = BASE64_INVERSE_ALPHABET[source[position].toPositiveInt()] and 0xFF shl 18
            } else {
                position++
                continue
            }
            var count = 0
            if (position + 1 < source.size && BASE64_INVERSE_ALPHABET[source[position + 1].toPositiveInt()] != -1) {
                b = b or (BASE64_INVERSE_ALPHABET[source[position + 1].toPositiveInt()] and 0xFF shl 12)
                count++
            }
            if (position + 2 < source.size && BASE64_INVERSE_ALPHABET[source[position + 2].toPositiveInt()] != -1) {
                b = b or (BASE64_INVERSE_ALPHABET[source[position + 2].toPositiveInt()] and 0xFF shl 6)
                count++
            }
            if (position + 3 < source.size && BASE64_INVERSE_ALPHABET[source[position + 3].toPositiveInt()] != -1) {
                b = b or (BASE64_INVERSE_ALPHABET[source[position + 3].toPositiveInt()] and 0xFF)
                count++
            }
            while (count > 0) {
                val c = b and 0xFF0000 shr 16
                output.add(c.toChar().toByte())
                b = b shl 8
                count--
            }
            position += 4
        }
        return output.toByteArray()

    }

    /**
     * Returns unsigned int from byte value
     */
   private fun Byte.toPositiveInt() = toInt() and 0xFF
}