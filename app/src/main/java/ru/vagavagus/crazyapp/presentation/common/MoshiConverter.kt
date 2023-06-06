package ru.vagavagus.crazyapp.presentation.common

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MoshiConverter {
    inline fun <reified T> convertJsonToListObject(json: String): List<T> {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return moshi.adapter<List<T>>(Types.newParameterizedType(List::class.java, T::class.java)).fromJson(json).orEmpty()
    }

    inline fun <reified T> convertListObjectToJson(objectData: List<T>): String {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return moshi.adapter<List<T>>(Types.newParameterizedType(List::class.java, T::class.java)).toJson(objectData)
    }
}