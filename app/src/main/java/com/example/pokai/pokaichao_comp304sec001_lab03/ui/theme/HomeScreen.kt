package com.example.pokai.pokaichao_comp304sec001_lab03.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pokai.pokaichao_comp304sec001_lab03.R
import com.example.pokai.pokaichao_comp304sec001_lab03.viewmodel.Product

@Composable
fun HomeScreen(
    products: List<Product>,
    onFabClick: () -> Unit,
    onProductClick: (Product) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)//without this, app crash
                .padding(12.dp)
        ) {
            itemsIndexed(items = products) { index, product ->
                ProductListItem(product = product, onClick = { onProductClick(product) })
            }
        }

        // FAB
        FloatingActionButton(
            onClick = onFabClick,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.button_new_product)
            )
        }
    }
}

@Composable
fun ProductListItem(product: Product, onClick: () -> Unit) {
    val cardSize: Dp = 400.dp

    Card(
        modifier = Modifier
            .size(cardSize)
            .padding(16.dp)
            .aspectRatio(1f)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = product.category,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = product.dateOfDelivery.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }


    }
}