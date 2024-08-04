package com.example.thenotesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.thenotesapp.databinding.NoteLayoutBinding
import com.example.thenotesapp.fragments.HomeFragmentDirections
import com.example.thenotesapp.model.Notes

class NoteAdapter :RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder (val binding: NoteLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object  : DiffUtil.ItemCallback<Notes>(){  //this is a differCallBack implementing DiffUtil.ItemCallback interface, this interface is used by DiffUtils to determine the difference between two lists
        // the first fun areItemsTheSame, this called to check whether the item represents the object in Note Data Class, here it compels the id,noteDesc, noteTitle of the oldItems with the newItems if all these properties are same it considers the items as same
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.noteDesc == newItem.noteDesc &&
                    oldItem.noteTitle == newItem.noteTitle
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {  //this method is called to check whether the content of two items are the same in the Notes data class
            return oldItem == newItem //if all properties of Notes are same then the Content is considered the same
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)  // then this lines createa a AsyncListDiffer instance using differCallBack, basically AsyncListDiffer determines differences between two lists on background thread which helps in smooth UI update

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        holder.binding.noteTitle.text = currentNote.noteTitle
        holder.binding.noteDesc.text = currentNote.noteDesc

        holder.itemView.setOnClickListener {
            val directions = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
            it.findNavController().navigate(directions)
        }
    }

}