package com.app.task.Database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Query
import com.app.task.Model.PostInfo

class UserRepository(context: Context) {

    var db: UserDao = AppDatabase.getInstance(context)?.userDao()!!


    //Fetch All the Users
    fun getAllUsers(): List<PostInfo> {
        return db.gelAllUsers()
    }

    // Insert new user
    fun insertUser(users: PostInfo) {
        insertAsyncTask(db).execute(users)
    }

    // update user
    fun updateUser(users: PostInfo) {
        db.updateUser(users)
    }

    // Delete user
    fun deleteUser(users: PostInfo) {
        db.deleteUser(users)
    }

    fun deleteAll(){
        db.deleteAll()
    }

    private class insertAsyncTask internal constructor(private val usersDao: UserDao) :
        AsyncTask<PostInfo, Void, Void>() {

        override fun doInBackground(vararg params: PostInfo): Void? {
            usersDao.insertUser(params[0])
            return null
        }
    }
}