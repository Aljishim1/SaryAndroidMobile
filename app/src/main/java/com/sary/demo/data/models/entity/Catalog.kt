package com.sary.demo.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catalogs")
data class Catalog(
    @PrimaryKey
    var id: Int,
    var title: String,
    var data: List<CatalogData>
)
