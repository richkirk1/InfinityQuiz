package com.rich_kirk.infinityquiz.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rich_kirk.infinityquiz.model.Question

@Database(entities = [Question::class], version = 2, exportSchema = true)
abstract class QuestionDatabase: RoomDatabase() {
    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile
        private var INSTANCE: QuestionDatabase? = null
        private const val PATH = "database/data_eng.db"
        private const val NAME = "multiple_choice_questions"

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, QuestionDatabase::class.java, "data_eng.db").createFromAsset(PATH).build()


        fun getDatabase(context: Context): QuestionDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = buildDatabase(context)
                INSTANCE = instance
                return instance
            }
        }
    }
}