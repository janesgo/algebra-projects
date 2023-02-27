package hr.project.mrcook.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import hr.project.mrcook.model.Recipe

private const val DB_NAME = "mrcook.db"
private const val DB_VERSION = 1
private const val INGREDIENTS = "ingredients"
private const val RECIPES = "recipes"
private const val MEASURES = "measures"
private const val DROP = "drop table $RECIPES"

private val CREATE_RECIPES = "create table $RECIPES(" +
        "${Recipe::_id.name} integer primary key autoincrement," +
        "${Recipe::title.name} text not null," +
        "${Recipe::category.name} text not null," +
        "${Recipe::area.name} text not null," +
        "${Recipe::instructions.name} text not null," +
        "${Recipe::imagePath.name} text not null," +
        "${Recipe::ingredients.name} text not null," +
        "${Recipe::measures.name} text not null," +
        "${Recipe::fav.name} int not null" + ")"

class MrCookSqlHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION),
    MrCookRepository {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_RECIPES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP)
        onCreate(db)
    }

    override fun delete(selection: String?, selectionArgs: Array<String>?) =
        writableDatabase.delete(RECIPES, selection, selectionArgs)

    override fun update(
        values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ) = writableDatabase.update(RECIPES, values, selection, selectionArgs)

    override fun insert(values: ContentValues?) =
        writableDatabase.insert(RECIPES, null, values)

    override fun query(
        projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ) = readableDatabase
        .query(RECIPES, projection, selection, selectionArgs, null, null, sortOrder)
}