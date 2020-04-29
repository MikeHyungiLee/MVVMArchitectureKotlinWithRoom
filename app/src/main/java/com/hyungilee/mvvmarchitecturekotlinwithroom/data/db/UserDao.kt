package com.hyungilee.mvvmarchitecturekotlinwithroom.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hyungilee.mvvmarchitecturekotlinwithroom.data.db.entities.CURRENT_USER_ID
import com.hyungilee.mvvmarchitecturekotlinwithroom.data.db.entities.User

@Dao
interface UserDao{

    // insert update (return user id)
    // override the user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User) : Long

    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getuser() : LiveData<User>

}