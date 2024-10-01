package com.example.scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scaffold.ui.theme.ScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldTheme {
                ScaffoldApp()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(title, color = Color.White) }, // tekstin väri
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue), // taustaväri
        actions = {
            IconButton(
                onClick = { expanded = !expanded }
            ) {

                Icon(Icons.Filled.MoreVert, contentDescription = null, tint = Color.White)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Settings", color = Color.White) },
                    onClick = { navController.navigate("settings") }
                )
                DropdownMenuItem(
                    text = { Text("Info", color = Color.White) },
                    onClick = { navController.navigate("info") }
                )
            }
        }
    )
}

            @OptIn(ExperimentalMaterial3Api::class)
            @Composable
            fun ScreenTopBar(title: String, navController: NavController) {
                TopAppBar(
                    title = { Text(title, color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = null, tint = Color.White)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
                )
            }


@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = { MainTopBar(title = "My App", navController) },
        content = { paddingValues ->
            // Apply padding values to avoid content being obscured by the app bar
            Text(
                text = "Content for Home screen",
                modifier = Modifier.padding(paddingValues)
            )
        }

    )
}

@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar(title = "Info", navController) },
        content = { paddingValues ->

            Text(
                text = "Content for Info screen",
                modifier = Modifier.padding(paddingValues)
            )
        }
    )
}

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar(title = "Settings", navController) },
        content = { paddingValues ->

            Text(
                text = "Content for Setting screen",
                modifier = Modifier.padding(paddingValues)

            )
        }

    )
}

            @Composable
            fun ScaffoldApp() {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable(route = "home") {
                        MainScreen(navController)
                    }
                    composable(route = "info") {
                        InfoScreen(navController)
                    }
                    composable(route = "settings") {
                        SettingsScreen(navController)
                    }
                }
            }

            @Preview(showBackground = true)
            @Composable
            fun GreetingPreview() {
                ScaffoldTheme {
                    MainScreen(navController = rememberNavController())
                }
            }


