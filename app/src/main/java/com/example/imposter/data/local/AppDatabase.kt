package com.example.imposter.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [WordEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "imposter_database"
                )
                    .addCallback(WordDatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class WordDatabaseCallback : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        populateInitialWords(database.wordDao())
                    }
                }
            }

            suspend fun populateInitialWords(wordDao: WordDao) {
                val initialWords = listOf(
                    WordEntity(word = "മാങ്ങ", category = "FOOD", difficulty = "EASY"),
                    WordEntity(word = "ചക്ക", category = "FOOD", difficulty = "EASY"),
                    WordEntity(word = "തേങ്ങ", category = "FOOD", difficulty = "EASY"),
                    WordEntity(word = "പഴം", category = "FOOD", difficulty = "EASY"),
                    WordEntity(word = "പുട്ട്", category = "FOOD", difficulty = "EASY"),
                    WordEntity(word = "അപ്പം", category = "FOOD", difficulty = "EASY"),
                    WordEntity(word = "ദോശ", category = "FOOD", difficulty = "EASY"),
                    WordEntity(word = "ഇഡലി", category = "FOOD", difficulty = "EASY"),
                    WordEntity(word = "ബിരിയാണി", category = "FOOD", difficulty = "MEDIUM"),
                    WordEntity(word = "കായൽ", category = "KERALA", difficulty = "EASY"),
                    WordEntity(word = "ഓണം", category = "KERALA", difficulty = "EASY"),
                    WordEntity(word = "വള്ളംകളി", category = "KERALA", difficulty = "MEDIUM"),
                    WordEntity(word = "ചെണ്ട", category = "KERALA", difficulty = "MEDIUM"),
                    WordEntity(word = "കഥകളി", category = "KERALA", difficulty = "HARD"),
                    WordEntity(word = "ആന", category = "ANIMALS", difficulty = "EASY"),
                    WordEntity(word = "പുലി", category = "ANIMALS", difficulty = "EASY"),
                    WordEntity(word = "സിംഹം", category = "ANIMALS", difficulty = "EASY"),
                    WordEntity(word = "മഴ", category = "NATURE", difficulty = "EASY"),
                    WordEntity(word = "മരം", category = "NATURE", difficulty = "EASY"),
                    WordEntity(word = "പുഴ", category = "NATURE", difficulty = "EASY"),
                    WordEntity(word = "കടൽ", category = "NATURE", difficulty = "EASY"),
                    WordEntity(word = "കസേര", category = "HOME", difficulty = "EASY"),
                    WordEntity(word = "മേശ", category = "HOME", difficulty = "EASY"),
                    WordEntity(word = "വാതിൽ", category = "HOME", difficulty = "EASY"),
                    WordEntity(word = "ബസ്", category = "TRANSPORT", difficulty = "EASY"),
                    WordEntity(word = "കാർ", category = "TRANSPORT", difficulty = "EASY"),
                    WordEntity(word = "ട്രെയിൻ", category = "TRANSPORT", difficulty = "EASY"),
                    WordEntity(word = "വിമാനം", category = "TRANSPORT", difficulty = "MEDIUM")
                )
                wordDao.insertWords(initialWords)
            }
        }
    }
}
