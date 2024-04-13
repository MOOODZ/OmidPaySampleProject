package ir.moodz.omidpaysampleproject.presentation.product_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun ProductDetailScreen(
    id: Int,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        state.productDetail?.let { product ->

            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(IntrinsicSize.Max)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Price: ${product.price} $",
                    fontStyle = FontStyle.Normal,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(ProductEvents.BookmarkProduct(product.id))
                    },
                ) {
                    val isBookmarked = state.isBookmarked == 0
                    Icon(
                        imageVector = if (isBookmarked) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                        contentDescription = "Bookmark product",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Category: ${product.category}",
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.labelSmall,
                color = Color.LightGray,
                textAlign = TextAlign.Right
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Description:\n${product.description}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis
            )

        }

    }

}
