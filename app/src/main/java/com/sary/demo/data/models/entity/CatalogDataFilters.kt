package com.sary.demo.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.sary.demo.utils.Converters

@Entity(tableName = "catalog_data_filters")
@TypeConverters(Converters::class)
data class CatalogDataFilters(
    @PrimaryKey()
    var filter_id: Int,
    var name: String
)
