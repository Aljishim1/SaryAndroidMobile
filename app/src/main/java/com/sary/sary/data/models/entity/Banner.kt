package com.sary.sary.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "banners")
data class Banner(
    @PrimaryKey
    var id: Int,
    var description: String,
    var button_text: String,
    var expiry_status: Boolean,
    var created_at: String,
    var start_date: String,
    var expiry_date: String,
    var image: String,
    var priority: Int,
    var photo: String,
    var link: String,
    var level: String,
    var is_available: Boolean,
    var branch: Int
)