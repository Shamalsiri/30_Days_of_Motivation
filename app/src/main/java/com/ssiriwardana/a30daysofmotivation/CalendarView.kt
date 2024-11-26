package com.ssiriwardana.a30daysofmotivation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.DateFormatSymbols
import java.time.LocalDate
import java.time.YearMonth
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarView(modifier: Modifier = Modifier) {
    var month by remember { mutableIntStateOf(Calendar.getInstance().get(Calendar.MONTH)) }
    var year by remember { mutableIntStateOf(Calendar.getInstance().get(Calendar.YEAR)) }
    val days = loadCalendarRows(month, year)
    var date by remember { mutableStateOf(LocalDate.now()) }
    Column {
        var showDialog by remember { mutableStateOf(false) }

        if (showDialog) {
            Box(modifier = modifier.fillMaxSize()) {
                MotivationCard(
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .align(Alignment.Center)
                        .fillMaxHeight(.7f),
                    onDismiss = { showDialog = false },
                    date = date
                )
            }
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                year--
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
            Text(year.toString())
            IconButton(onClick = {
                year++
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        }

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                if (month != 0) month--
                else {
                    month = 11
                    year--
                }
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
            Text(DateFormatSymbols().months[month])
            IconButton(onClick = {
                if (month != 11) month++
                else {
                    month = 0
                    year++
                }
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        }
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CalendarCell("Sun")
                CalendarCell("Mon")
                CalendarCell("Tue")
                CalendarCell("Wed")
                CalendarCell("Thu")
                CalendarCell("Fri")
                CalendarCell("Sat")
            }

            for (i in 0..5) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (k in 0..6) {
                        CalendarCell(days[i][k], onClick = {
                            showDialog = true
                            date = LocalDate.of(year, month + 1, days[i][k].toInt())
                        }, month = month, year = year)
                    }
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                month = Calendar.getInstance().get(Calendar.MONTH)
                year = Calendar.getInstance().get(Calendar.YEAR)
            }) {
                Text(text = "Today")
            }
        }


    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun loadCalendarRows(month: Int, year: Int): Array<ArrayList<String>> {
    val currentMonth = DateFormatSymbols().months[month]
    val dayOfWeek = (LocalDate.of(year, month + 1, 1).dayOfWeek).toString()
    val numOfDays = YearMonth.of(year, month + 1).lengthOfMonth()
    val leadingZeros = when (dayOfWeek) {
        "MONDAY" -> 1
        "TUESDAY" -> 2
        "WEDNESDAY" -> 3
        "THURSDAY" -> 4
        "FRIDAY" -> 5
        "SATURDAY" -> 6
        else -> 0
    }
    val days = Array(42) { "" }
    var incrementer = leadingZeros
    for (day in 1..numOfDays) {
        days[incrementer] = "$day"
        if (incrementer < 41) incrementer++
    }

    return buildCalendarRow(days)
}


fun buildCalendarRow(days: Array<String>): Array<ArrayList<String>> {
    val rows = Array(6) { ArrayList<String>() }
    var rowCount = 0
    for (startDay in 0..41 step (7)) {
        var c = 0
        val r = ArrayList<String>(7)
        for (d in startDay..startDay + 6) {
            Log.d("SSiri", "Row: ${days[d]}")
            r.add(c, days[d])
            c++
        }
        rows[rowCount] = r
        rowCount++
        Log.d("SSiri", "---------------")
    }

    return rows
}

@Composable
fun CalendarCell(
    cellVal: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    month: Int = -1,
    year: Int = -1
) {
    val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val isClickable = cellVal.isNotEmpty() && cellVal.all { it.isDigit() }
    val boxModifier = modifier
        .width(35.dp)
        .let {
            if (isClickable) it
                .clickable { onClick() }
                .aspectRatio(1f)
            else
                it
        }

    Row(
        modifier = boxModifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (isClickable && currentDay == cellVal.toInt() && currentMonth == month && currentYear == year) {
            Row(
                modifier = modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier,
                    text = cellVal,
                    style = if (cellVal.isNotEmpty() && cellVal[0].isLetter()) {
                        TextStyle(fontWeight = FontWeight.Bold)
                    } else {
                        TextStyle(fontWeight = FontWeight.Normal)
                    }
                )
            }

        } else {
            Text(
                text = cellVal,
                style = if (cellVal.isNotEmpty() && cellVal[0].isLetter()) {
                    TextStyle(fontWeight = FontWeight.Bold)
                } else {
                    TextStyle(fontWeight = FontWeight.Normal)
                }
            )
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewCalendar() {
    CalendarView()
}