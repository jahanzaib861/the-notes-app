package com.example.thenotesapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class Notes (
@PrimaryKey(autoGenerate = true)
    val id:Int,
    val noteTitle:String,
    val noteDesc:String
):Parcelable


//it is difficult to transfer this type of data so in such cases we go for Parcelization
// now what is parcelization?
//Parcelization is a mechanism that convert complex data objects into a simple format that can be easily
//transfer between activities or fragments