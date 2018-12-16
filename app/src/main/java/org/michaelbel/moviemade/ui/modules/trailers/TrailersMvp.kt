package org.michaelbel.moviemade.ui.modules.trailers

import com.arellomobile.mvp.MvpView
import org.michaelbel.moviemade.utils.EmptyViewMode
import org.michaelbel.moviemade.data.entity.Video

interface TrailersMvp : MvpView {

    fun setTrailers(trailers: List<Video>)

    fun setError(@EmptyViewMode mode: Int)
}