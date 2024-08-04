package com.example.thenotesapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.thenotesapp.model.Notes
import com.example.thenotesapp.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app : Application, private val noteRepository: NoteRepository):AndroidViewModel(app) {

    fun addNote(note: Notes) =
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }

    fun updateNote(note: Notes) =
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }

    fun deleteNote(note: Notes) =
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }

    fun getAllNotes() = noteRepository.getAllNotes()

    fun searchNotes(query:String?) =
        noteRepository.searchNote(query)

}