package com.ssiriwardana.a30daysofmotivation

import android.annotation.SuppressLint
import android.content.res.Resources.Theme
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ssiriwardana.a30daysofmotivation.ui.theme.A30DaysOfMotivationTheme
import com.ssiriwardana.a30daysofmotivation.ui.theme.tertiaryContainerLightMediumContrast

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A30DaysOfMotivationTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(modifier = Modifier,
                            title = {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        stringResource(id = R.string.app_name),
                                        modifier = Modifier,
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 28.sp
                                        )
                                    )
                                }
                            })
                    },
                    content = {
                        Calendar()
                    }
                )
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CalendarView()
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    A30DaysOfMotivationTheme {
        Calendar()
    }
}