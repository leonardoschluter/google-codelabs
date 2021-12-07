package org.schluter.compose_basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.schluter.compose_basics.MainActivity.Companion.padding_16
import org.schluter.compose_basics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
    companion object {
        val padding_16: Modifier = Modifier.padding(16.dp);
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
fun Greeting(name: String = "") {
    Surface(color = MaterialTheme.colors.primary, modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Row(modifier = padding_16) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Hello,")
                Text(text = "$name!")
            }
            OutlinedButton(
                onClick = { /*TODO*/ }) {
                    Text("Show more")
            }
        }
    }
}

@Composable
private fun MyApp(names: List<String> = listOf("Leonardo", "Angella")) {
    Surface(color = MaterialTheme.colors.background) {
        Column {
            for (name in names){
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