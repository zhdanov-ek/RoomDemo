package com.example.roomdemo.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomdemo.db.Address

@Entity
data class Employee(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    var name: String,
    var salary: String?
    //@Embedded var address: Address?
)
