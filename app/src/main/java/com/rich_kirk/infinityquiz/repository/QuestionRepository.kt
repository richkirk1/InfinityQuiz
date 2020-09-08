package com.rich_kirk.infinityquiz.repository

import com.rich_kirk.infinityquiz.data.QuestionDao
import com.rich_kirk.infinityquiz.model.Question

class QuestionRepository(private val questionDao: QuestionDao) {
    // val question: Question = questionDao.readRandomQuestion()
    suspend fun readRandomQuestion(): Question {
        return questionDao.readRandomQuestion()
    }
}