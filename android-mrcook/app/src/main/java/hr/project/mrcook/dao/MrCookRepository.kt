package hr.project.mrcook.dao

import android.content.ContentValues
import android.database.Cursor

interface MrCookRepository {
    fun delete(selection: String?, selectionArgs: Array<String>?): Int
    fun update(
        values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int

    fun insert(values: ContentValues?): Long
    fun query(
        projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor?
}