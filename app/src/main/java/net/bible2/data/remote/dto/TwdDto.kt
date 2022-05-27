package net.bible2.data.remote.dto

import com.google.gson.annotations.SerializedName
import net.bible2.domain.model.Twd

data class TwdDto(
    @SerializedName("year") val year: Int,
    @SerializedName("lang") val language: String,
    @SerializedName("bible") val bible: String,
    @SerializedName("biblename") val bibleName: String,
    @SerializedName("url") val url: String
)

fun TwdDto.toTwd() = Twd(
    year = year,
    lang = language,
    key = bible,
    title = bibleName,
    url = url
)
