package extensions.random

import java.security.SecureRandom
import java.util.Random
import java.util.concurrent.ThreadLocalRandom

/**
 * The level of security (e.g., unpredictability) of a Random Number Generator (RNG)
 * See the project's README.md for more information.
 */
enum class RNGSecurityLevel {
    /**
     * Lower-level of randomness (more predictable), but faster performance.
     * Suitable for most use cases where inability to predict generated numbers is not critical.
     * Example: Linear-congruential RNGs.
     */
    STANDARD,

    /**
     * Crytographically-strong pseudorandom number generators.
     */
    CRYPTOGRAPHICALLY_SECURE
}

/**
 * Return an instance of [Random] based on the requirements expressed by the parameters.
 */
fun getRandomNumberGenerator(securityLevel: RNGSecurityLevel, threadLocal: Boolean = false): Random = when (securityLevel) {
    RNGSecurityLevel.STANDARD -> if (threadLocal) ThreadLocalRandom.current() else Random()
    RNGSecurityLevel.CRYPTOGRAPHICALLY_SECURE -> SecureRandom()
}
