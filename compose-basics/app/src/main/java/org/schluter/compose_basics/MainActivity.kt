package org.schluter.compose_basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.schluter.compose_basics.MainActivity.Companion.padding_16
import org.schluter.compose_basics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
    companion object {
        val padding_16: Modifier = Modifier.padding(16.dp)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicsTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    var shouldShowOnboarding by remember { mutableStateOf(true) }
    if (shouldShowOnboarding) {
        OnboardingScreen(onClick = { shouldShowOnboarding = false })
    } else {
        Greetings()
    }
}

@Composable
fun Greeting(name: String = "") {
    val expanded = remember { mutableStateOf(false) }
    Surface(
        color = MaterialTheme.colors.primary, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = if (expanded.value) {
                padding_16.padding(bottom = 42.dp)
            } else {
                padding_16
            }
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Hello,")
                Text(text = "$name!")
            }
            OutlinedButton(
                onClick = { expanded.value = !expanded.value }) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}

@Composable
private fun Greetings(names: List<String> = listOf("Leonardo", "Angella")) {
    Surface(color = MaterialTheme.colors.background) {
        Column {
            for (name in names) {
                Greeting(name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBasicsTheme {
        MyApp()
    }
}

@Composable
fun OnboardingScreen(onClick: () -> Unit) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onClick
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun MyAppPreview() {
    ComposeBasicsTheme {
        MyApp()
    }
}