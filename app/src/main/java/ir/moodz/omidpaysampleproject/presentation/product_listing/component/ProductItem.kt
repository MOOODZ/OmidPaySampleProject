package ir.moodz.omidpaysampleproject.presentation.product_listing.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ir.moodz.omidpaysampleproject.domain.model.ProductListing

@Composable
fun ProductItem(
    product: ProductListing,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        ),
        border = (BorderStroke(
            width = 0.5.dp, color = Color.LightGray
        ))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(3f),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Category: ${product.category}",
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Price: ${product.price} $",
                    fontStyle = FontStyle.Normal,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.End
                )

            }

        }
    }
}

@Preview
@Composable
fun ProductItemPreview() {
    ProductItem(
        product = ProductListing(
            id = 1,
            title = "Product",
            price = 56.42,
            description = "This is a very expensive one\n This is cool",
            image = "",
            category = "Shirts",
            isBookmarked = 0
        ),
        modifier = Modifier.fillMaxWidth()
    )
}