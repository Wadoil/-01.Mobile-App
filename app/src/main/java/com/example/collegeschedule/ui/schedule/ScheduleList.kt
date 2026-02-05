package com.example.collegeschedule.ui.schedule
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.collegeschedule.data.dto.ScheduleByDateDto
@Composable
fun ScheduleList(data: List<ScheduleByDateDto>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(data) { day ->
            Text(
                "${day.lessonDate} (${day.weekday})",
                modifier = Modifier.padding(8.dp)
            )
            if (day.lessons.isEmpty()) {
                Text("Информация отсутствует",
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                )
            } else {
                day.lessons.forEach { lesson ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Column(Modifier.padding(8.dp)) {
                            // Время пары
                            Text("${lesson.lessonNumber} Пара (${lesson.time})")

                            // Перебираем все части группы
                            lesson.groupParts.forEach { (part, info) ->
                                if (info != null) {
                                    // Получаем отображаемое имя для части группы
                                    val partDisplayName = part.toDisplayName()

                                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                                        // Показываем название подгруппы, только если оно не пустое
                                        if (partDisplayName.isNotEmpty()) {
                                            Text(text = partDisplayName)
                                        }
                                        Text(text = info.subject, fontWeight = FontWeight.Bold)
                                        Text(text = info.teacher)
                                        Text(text = "${info.building}, ауд. ${info.classroom}")
                                        // Адрес
                                        if (info.address.isNotEmpty()) {
                                            Text(text = info.address)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}