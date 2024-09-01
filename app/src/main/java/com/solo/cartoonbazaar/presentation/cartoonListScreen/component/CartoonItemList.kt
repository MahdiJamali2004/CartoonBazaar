package com.solo.cartoonbazaar.presentation.cartoonListScreen.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.solo.cartoonbazaar.R
import com.solo.cartoonbazaar.domain.model.Cartoon

@Composable
fun CartoonItemList(
    modifier: Modifier = Modifier,
    cartoon: Cartoon,
    persianCartoonName: String,
    @DrawableRes cartoonPlaceHolder: Int,
    index: Int ,
    onClick : () -> Unit
) {


    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .clickable {
                onClick()
            }
            ,

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {


        Column(
            modifier= Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                color = MaterialTheme.colorScheme.onPrimary,

                text = "$persianCartoonName قسمت $index",
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )

            Text(
                color = MaterialTheme.colorScheme.onPrimary,

                text = "مدت زمان ${secondDurationToString(cartoon.duration)}",
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )
        }
        AsyncImage(
            modifier = Modifier
                .width(180.dp)
                .height(90.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(cartoon.image)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.cartoon_description),
            placeholder = painterResource(id = cartoonPlaceHolder),
            contentScale = ContentScale.FillHeight,
        )

    }


}

fun secondDurationToString(duration : Int) : String {
    val min = duration / 60
    val sec = duration % 60
    return "$min:$sec"
}