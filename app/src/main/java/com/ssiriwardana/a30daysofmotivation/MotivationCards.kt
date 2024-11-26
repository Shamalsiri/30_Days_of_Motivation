package com.ssiriwardana.a30daysofmotivation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ssiriwardana.a30daysofmotivation.model.MotivationalCardsRepository
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MotivationCard(modifier: Modifier = Modifier, onDismiss: () -> Unit, date: LocalDate) {
    val year = date.year
    val month = date.month
    val dayOfTheMonth = date.dayOfMonth
    val dayOfTheWeek = date.dayOfWeek
    val quote = MotivationalCardsRepository.cards[dayOfTheMonth - 1].quote
    val author = MotivationalCardsRepository.cards[dayOfTheMonth - 1].author
    val image = MotivationalCardsRepository.cards[dayOfTheMonth - 1].image

    ElevatedCard(
        modifier = modifier.fillMaxWidth(.7f).fillMaxHeight(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onDismiss, modifier = Modifier) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.2f)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    painter = painterResource(id = image),
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight(.5f)
                    .fillMaxWidth()
            ) {
                Text(text = "$dayOfTheWeek",
                    style = TextStyle(fontSize = 20.sp), modifier = Modifier
                        .padding(bottom = 4.dp)
                        .drawBehind {
                            val strokeWidthPx = 1.dp.toPx()
                            val verticalOffset = size.height - 2.sp.toPx()
                            drawLine(
                                color = androidx.compose.ui.graphics.Color.Black,
                                strokeWidth = strokeWidthPx,
                                start = Offset(0f, verticalOffset),
                                end = Offset(size.width, verticalOffset)
                            )
                        }
                )

                Text(
                    text = "$dayOfTheMonth $month $year",
                    style = TextStyle(fontSize = 24.sp),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "\"${stringResource(id = quote)}\"",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                )
                Row {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f),
                        text = "- ${stringResource(id = author)}",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontStyle = FontStyle.Italic
                        ),
                        textAlign = TextAlign.Right,
                    )
                }

            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = false)
@Composable
fun PreviewCard() {
    val now = LocalDate.now()
    MotivationCard(onDismiss = {}, date = now)
}
