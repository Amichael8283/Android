@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package org.michaelbel.movies.persistence.database

import kotlinx.coroutines.flow.Flow
import org.michaelbel.movies.persistence.database.entity.ImagePojo

expect class ImagePersistence {

    fun imagesFlow(movieId: Int): Flow<List<ImagePojo>>

    suspend fun insert(images: List<ImagePojo>)
}