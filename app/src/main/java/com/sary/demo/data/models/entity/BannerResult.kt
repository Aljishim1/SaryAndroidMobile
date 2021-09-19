package com.sary.demo.data.models.entity

import androidx.room.Entity

@Entity(tableName = "banner_result")
data class BannerResult(
    var result: List<Banner>
)