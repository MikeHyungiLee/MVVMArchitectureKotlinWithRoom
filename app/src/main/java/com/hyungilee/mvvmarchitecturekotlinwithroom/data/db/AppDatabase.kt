package com.hyungilee.mvvmarchitecturekotlinwithroom.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hyungilee.mvvmarchitecturekotlinwithroom.data.db.entities.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getUserDao() : UserDao

    companion object{
        // Volatile : this variable is immediately visible to the all the other tracks.
        @Volatile
        private var instance: AppDatabase? = null
        // LOCK : we don't create more than two instance of database.
        // 데이터베이스 객체가 두 개 이상 생성되는 것을 방지
        private val LOCK = Any()

        // Database 객체를 만들때, invoke 를 통해 context 를 넘겨서 만들어 줄 것이다.
        // instance가 NULL인 경우, synchronized block을 통해서 초기화 시켜준다.
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?:buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                // Context
                context.applicationContext,
                // abstract class
                AppDatabase::class.java,
                // database name
                "MyDatabase.db"
            ).build()
    }
}