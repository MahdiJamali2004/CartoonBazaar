package com.solo.cartoonbazaar.data.remote.CartoonMapper

import android.util.Log
import com.solo.cartoonbazaar.data.remote.CartoonDto.CartoonDto
import com.solo.cartoonbazaar.domain.model.Cartoon

fun CartoonDto.toCartoon(): Cartoon {

    return Cartoon(
    url = video.file_link_all.last().urls.last(),
    image = extractImageUrl(video.big_poster) ,
    duration = video.duration ,
)
}
fun extractImageUrl(imgUrl: String): String {

    return imgUrl.substringBefore('?')
}
