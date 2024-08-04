package com.example.thenotesapp.repository

import com.example.thenotesapp.database.NoteDatabase
import com.example.thenotesapp.model.Notes

//what is the general meaning of repository?
//A place or container where something is deposited or stored, same we will be going to with this NoteRepository
// with the help of NoteDatabase which we created before

class NoteRepository(private val db : NoteDatabase) {

    suspend fun insertNote(notes: Notes) = db.getNoteDao().insertNotes(notes)
    suspend fun deleteNote(notes: Notes) = db.getNoteDao().deleteNotes(notes)
    suspend fun updateNote(notes: Notes) = db.getNoteDao().updateNotes(notes)

    fun getAllNotes() = db.getNoteDao().getAllNotes()
    fun searchNote(query : String?) = db.getNoteDao().searchNote(query)

}