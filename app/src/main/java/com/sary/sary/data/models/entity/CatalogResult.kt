package com.sary.sary.data.models.entity

import androidx.room.Entity

@Entity(tableName = "catalog_result")
data class CatalogResult(
    var result: List<Catalog>
)
