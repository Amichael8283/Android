@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package org.michaelbel.movies.common.exceptions

actual data class CreateRequestTokenException(
    val loginViaTmdb: Boolean
): Exception()