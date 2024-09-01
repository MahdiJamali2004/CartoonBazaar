package com.solo.cartoonbazaar.presentation.MainScreen.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.solo.cartoonbazaar.domain.model.toPersianName
import com.solo.cartoonbazaar.presentation.MainScreen.CartoonPoster


@Composable
fun CartoonItem(
    modifier: Modifier = Modifier,
    poster: CartoonPoster,
    onClick: () -> Unit
) {


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .width(280.dp)
                .height(380.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable { onClick() }
                ,

            painter = painterResource(id = poster.drawableRes), //poster.bitmap.asImageBitmap()
            contentDescription = "cartoon",
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            color = MaterialTheme.colorScheme.onPrimary,
            text = poster.cartoonName.value.toPersianName(),
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
        )


    }


}
