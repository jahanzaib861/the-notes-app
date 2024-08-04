package com.example.thenotesapp.database

import androidx.lifecycle.LiveData
import com.example.thenotesapp.model.Notes
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)  //if same PK is already in table then old data will be replaced by new data
    suspend fun insertNotes(note: Notes)

    @Update
    suspend fun updateNotes(note: Notes)

    @Delete
    suspend fun deleteNotes(notes: Notes)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM notes WHERE noteTitle LIKE :query OR noteDesc LIKE :query") //means if the keywords matches either with title or desc then store the result in LiveData containing the list of Notes entities
    fun searchNote(query:String?):LiveData<List<Notes>> // and this(String?) question mark means it can be null if no specific search criteria are provided


}