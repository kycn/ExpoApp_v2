package com.example.expoapp_v2.characterdetail.domain

import javax.inject.Inject

class ExtractEpisodeIdFromUrlUseCase @Inject constructor() {
    operator fun invoke(url: String): Int {
        val lastIndexOfSlash = url.lastIndexOf('/')
        return url.substring(lastIndexOfSlash + 1).toInt()
    }
}