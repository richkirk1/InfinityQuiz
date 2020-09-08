package com.rich_kirk.infinityquiz.data

import androidx.room.Dao
import androidx.room.Query
import com.rich_kirk.infinityquiz.model.Question

/*
private const val TABLE_NAME = "multiple_choice_questions"
private const val QUERY = "SELECT * FROM $TABLE_NAME ORDER BY RANDOM() LIMIT 1"
*/

@Dao
interface QuestionDao {
    @Query("SELECT * FROM multiple_choice_questions ORDER BY RANDOM() LIMIT 1")
    suspend fun readRandomQuestion(): Question
}