package com.example.pokai.pokaichao_comp304sec001_lab03.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pokai.pokaichao_comp304sec001_lab03.data.DataSource
import com.example.pokai.pokaichao_comp304sec001_lab03.viewmodel.Product
import com.example.pokai.pokaichao_comp304sec001_lab03.viewmodel.ProductViewModel
import java.time.LocalDate

@Composable
fun ProductScreen(
    productViewModel: ProductViewModel,
    onAddClick: (Product) -> Unit,
    product: Product? = null,
) {
    var name by remember { mutableStateOf(product?.name ?: "") }
    var price by remember { mutableStateOf(product?.price ?: 0.0) }
    var dateOfDelivery by remember { mutableStateOf(product?.dateOfDelivery ?: LocalDate.now()) }
    var category by remember { mutableStateOf(product?.category ?: "") }
    var favorite by remember { mutableStateOf(product?.favorite ?: false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
    {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = price.toString(),
            onValueChange = { price = it.toDoubleOrNull() ?: 0.0 },
            label = { Text("Price") }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = dateOfDelivery.toString(),
            onValueChange = { dateOfDelivery = LocalDate.parse(it) },
            label = { Text("Date of Delivery") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Category dropdown
        var expanded by remember {
            mutableStateOf(false)
        }
        val itemPosition = remember {
            mutableStateOf(0)
        }

        Box(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    expanded = true
                }
            ) {
                Text(text = stringResource(id = DataSource.categories[itemPosition.value]))
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "DropDown Icon"
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }) {

                DataSource.categories.forEachIndexed { index, username ->
                    DropdownMenuItem(text = {
                        Text(text = stringResource(id = username))
                    },
                        onClick = {
                            expanded = false
                            itemPosition.value = index
                        })
                }
            }

        }

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = favorite.toString(),
            onValueChange = { favorite = it.toBoolean() },
            label = { Text("Favorite") }
        )
    }
}