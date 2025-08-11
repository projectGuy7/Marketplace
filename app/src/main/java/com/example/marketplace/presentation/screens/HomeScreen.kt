package com.example.marketplace.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.ColorImage
import coil3.Image
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.compose.rememberAsyncImagePainter
import com.example.marketplace.R
import com.example.marketplace.domain.marketplace.Item
import com.example.marketplace.presentation.viewmodels.homemvi.HomeIntent
import com.example.marketplace.presentation.viewmodels.homemvi.HomeState

@Composable
fun Home(state: HomeState, onEvent: (HomeIntent) -> Unit) {
    Column {
        Row(
            modifier = Modifier.padding(5.dp)
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                value = state.searchBar,
                onValueChange = { onEvent(HomeIntent.UpdateSearchBar(it)) }
            )
            IconButton(
                onClick = {} // TODO
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            }
        }
        val itemModifier = Modifier.weight(1f)
        LazyColumn {
            for(i in 0 until state.items.size step 2) {
                item(key = state.items[i].itemId) {
                    Row(
                        modifier = Modifier.padding(start = 5.dp, top = 5.dp, end = 5.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        ItemUI(
                            modifier = itemModifier,
                            item = state.items[i],
                            onClick = {}
                        )
                        if(i + 1 < state.items.size) {
                            ItemUI(
                                modifier = itemModifier,
                                item = state.items[i + 1],
                                onClick = {}
                            )
                        } else {
                            Spacer(modifier = Modifier.weight(1f).requiredHeight(140.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 412,
    heightDp = 732
)
@Composable
fun HomePreview() {
    Home(
        state = HomeState(items = listOf(
            Item(
                name = "Black T-Shirt",
                price = 1000,
                quantity = 60,
                imageUri = "",
                itemId = 1
            ),
            Item(
                name = "Black T-Shirt",
                price = 1000,
                quantity = 60,
                imageUri = "",
                itemId = 2
            ),
            Item(
                name = "Black T-Shirt",
                price = 1000,
                quantity = 60,
                imageUri = "",
                itemId = 3
            )
        )),
        onEvent = {}
    )
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ItemUI(
    modifier: Modifier = Modifier,
    item: Item,
    onClick: (HomeIntent) -> Unit
) {
    Column(modifier = modifier) {
        val previewHandler = AsyncImagePreviewHandler {
            ColorImage(Color.Gray.toArgb())
        }

        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            AsyncImage(
                modifier = Modifier.requiredHeight(140.dp).clip(RoundedCornerShape(size = 15.dp)),
                model = item.imageUri,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Text(
            modifier = Modifier.padding(5.dp, 5.dp, 5.dp, 5.dp),
            text = "${item.price} tg",
            fontSize = 20.sp
        )
        Text(
            modifier = Modifier.padding(start = 5.dp),
            text = item.name,
            fontSize = 24.sp
        )
        Button(
            shape = RoundedCornerShape(15.dp),
            onClick = { onClick(HomeIntent.AddToCart(item)) }
        ) {
            Text("Add")
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 412,
    heightDp = 732
)
@Composable
fun ItemUIPreview() {
    Row(modifier = Modifier.padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ItemUI(
            modifier = Modifier.weight(1f),
            item = Item(
                name = "Black T-Shirt",
                price = 1000,
                quantity = 60,
                imageUri = "",
                itemId = 1
            ),
            onClick = {}
        )
        ItemUI(
            modifier = Modifier.weight(1f),
            item = Item(
                name = "Black T-Shirt",
                price = 1000,
                quantity = 60,
                imageUri = "",
                itemId = 1
            ),
            onClick = {}
        )
    }
}