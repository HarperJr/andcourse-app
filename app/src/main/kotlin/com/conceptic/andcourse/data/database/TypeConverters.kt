package com.conceptic.andcourse.data.database

import androidx.room.TypeConverter
import com.conceptic.andcourse.data.model.FeatureType

class TypeConverters {
    @TypeConverter
    fun fromFeatureType(featureType: FeatureType): Int = featureType.ordinal

    @TypeConverter
    fun toFeatureType(value: Int): FeatureType = FeatureType.of(value)
}
