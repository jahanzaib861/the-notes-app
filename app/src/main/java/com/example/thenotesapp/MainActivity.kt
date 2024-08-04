package com.example.thenotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.thenotesapp.database.NoteDatabase
import com.example.thenotesapp.repository.NoteRepository
import com.example.thenotesapp.viewModel.NoteViewModel
import com.example.thenotesapp.viewModel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()

    }

    private fun setupViewModel(){
        val noteRepository = NoteRepository(NoteDatabase(this))
        val viewModelProviderFactory = NoteViewModelFactory(application, noteRepository) //now once we have repository and factory initialized, then set it up in the NoteViewModel like this way
        noteViewModel = ViewModelProvider(this, viewModelProviderFactory)[NoteViewModel::class.java] // ViewModelProvider with 'this' and 'viewModelProviderFactory' which is used to create
    // an instance of NoteViewModel, then 'NoteViewModel' class specify the class of the ViewModel that needs to be retrieved
    }
}