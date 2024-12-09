package com.example.sql_lite_final_project

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "contacts")
class Contact {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "contactId")
    var id: Int = 0

    @ColumnInfo(name = "contactName")
    var name: String? = null

    @ColumnInfo(name = "contactPhone")
    var phone: String? = null

    constructor() {}
    constructor(name: String, phone: String) {
        this.name = name
        this.phone = phone
    }
}