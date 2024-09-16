package com.example.composeshoppinglist

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeshoppinglist.ui.theme.ComposeShoppingListTheme
import java.nio.file.Files.delete

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeShoppingListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShoppingList(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun ShoppingList(modifier: Modifier = Modifier) {
    val shoppingItems = remember { mutableStateListOf<String>() }
    var shoppingItem by remember { mutableStateOf("") }
    var showItems by remember { mutableStateOf(true) }

    Column(modifier = modifier.padding(10.dp)) {
        Text(text = "Shopping List", style = MaterialTheme.typography.headlineLarge)
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = shoppingItem,
                onValueChange = { shoppingItem = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                // modifier = modifier.fillMaxWidth(),
                label = { Text("Enter your shopping item") }
            )
            Button(onClick = {
                if (shoppingItem.isNotBlank()) {
                    shoppingItems.add(shoppingItem)
                    shoppingItem = ""
                }
                modifier.padding(8.dp)
                    .size(height = 50.dp, width = 50.dp)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Item")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (showItems) {
            LazyColumn(modifier = modifier.fillMaxHeight()) {
                items(shoppingItems) { item ->
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                    ) {
                        Row(
                            modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = item,
                                fontSize = 20.sp

                            )
                            Icon(
                                Icons.Default.Delete,

                                contentDescription = "remove item",
                                modifier
                                    .clickable { shoppingItems.remove(item) }
                                    .size(32.dp)
                            )

                        }
                    }
                    Spacer(modifier = modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ShoppingPreview() {
    ComposeShoppingListTheme {
        ShoppingList()
    }
}