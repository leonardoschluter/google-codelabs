package org.schluter.compose_basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnboarding) {
        OnboardingScreen(onClick = { shouldShowOnboarding = false })
    } else {
        Greetings()
    }
}

@Composable
fun CollapseButton(expanded: Boolean, onClick: () -> Unit) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector =
            if (expanded)
                Icons.Filled.ExpandLess
            else
                Icons.Filled.ExpandMore,
            contentDescription = if (expanded) stringResource(R.string.show_more) else stringResource(
                R.string.show_less
            )

        )
    }
}

@Composable
fun Greeting(name: String = "") {
    val expanded = rememberSaveable { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        targetValue = if (expanded.value) {
            42.dp
        } else {
            0.dp
        },
        animationSpec = if (expanded.value) {
            spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessVeryLow
            )
        } else {
            spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessLow)
        }
    )
    Surface(
        color = MaterialTheme.colors.primary, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = padding_16.padding(bottom = extraPadding.coerceAtLeast(0.dp))
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Hello,")
                Text(text = "$name!")
                if (expanded.value) {
                    Text(stringResource(R.string.lorem_ipsium))
                }
            }
            CollapseButton(
                expanded = expanded.value,
                onClick = { expanded.value = !expanded.value })
        }
    }
}

@Composable
private fun Greetings(names: List<String> = List(1000) { "$it" }) {
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn {
            items(items = names) { name ->
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