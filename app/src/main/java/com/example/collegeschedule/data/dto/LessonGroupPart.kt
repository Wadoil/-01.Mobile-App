package com.example.collegeschedule.data.dto

enum class LessonGroupPart {
    FULL, SUB1, SUB2;

    fun toDisplayName(): String = when (this) {
        FULL -> ""
        SUB1 -> "Первая подгруппа"
        SUB2 -> "Вторая подгруппа"
    }
}