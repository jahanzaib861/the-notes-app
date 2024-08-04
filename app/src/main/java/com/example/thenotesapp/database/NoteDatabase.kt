package com.example.thenotesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.thenotesapp.model.Notes


@Database(entities = [Notes::class], version = 1)
abstract class NoteDatabase :RoomDatabase(){

    abstract fun getNoteDao() : NoteDao

    companion object{
        @Volatile     // this annotation ensures that changes made by one thread are immediately visible to other thread
        private var instance : NoteDatabase? = null     //holds the Singleton instance of the NoteDatabase or null
        private val LOCK  = Any()       // LOCK object used to synchronized the database creation process, basically this block ensures that only one thread can execute the code inside the block at a time

        operator fun invoke(context: Context) = instance ?:    //this is a custom invoke operator for the companion object, basically it allows u to create an instance of NoteDatabase by calling Notedatabase context
        synchronized(LOCK){   //invoke fun follows the Singleton pattern ensuring that only one instance is created then the this (instance ?:) checks if the instance is already initialized if not then it enters in
            instance ?:   // synchronized block using the LOCK object to ensure that only one thread can create the database instance at a time, then inside the block again it checks if the instance is still null
            createDatabase(context).also{  // then create the database using the createDatabase fun..
                instance = it
            }
        }
//Q: why there is a need of invoke operator fun? ans:  because invoke operator is used for simplicity when creating an instance and also double check locking pattern is implemented to ensure thread safety during database creation process
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "note_db"
            ).build()
    }
}