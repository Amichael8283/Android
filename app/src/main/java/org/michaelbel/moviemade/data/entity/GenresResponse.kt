package org.michaelbel.moviemade.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenresResponse(
    @SerializedName("genres") val genres: List<Genre>
) : Serializable