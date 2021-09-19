package com.sary.demo.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.sary.demo.utils.Converters

@Entity(tableName = "catalog_data")
@TypeConverters(Converters::class)
data class CatalogData(
    @PrimaryKey
    var group_id: Int,
    var filters: List<CatalogDataFilters>,
    var name: String,
    var image: String,
    var empty_content_image: String,
    var empty_content_message: String,
    var has_data: Boolean,
    var show_unavailable_items: Boolean,
    var show_in_brochure_link: Boolean,
)
