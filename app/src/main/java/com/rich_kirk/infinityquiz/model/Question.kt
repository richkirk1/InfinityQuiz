package com.rich_kirk.infinityquiz.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "multiple_choice_questions")//, foreignKeys=[], indices=[])
data class Question (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val Question: String,
    val Category: String,
    val Subcategory: String,
    val AnswerOne: String,
    val AnswerTwo: String,
    val AnswerThree: String,
    val AnswerFour: String,
    val CorrectAnswer: Int,
    val WasQuestionSeen: Int,
    val Wikipedia: String
): Parcelable