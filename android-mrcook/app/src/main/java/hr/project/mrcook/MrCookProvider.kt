package hr.project.mrcook

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.net.Uri
import hr.project.mrcook.dao.MrCookRepository
import hr.project.mrcook.dao.getMrCookRepository
import java.lang.IllegalArgumentException

private const val AUTHORITY = "hr.project.mrcook.api.provider"
private const val PATH = "recipes"

public val RECIPES_PROVIDER_URI = Uri.parse("content://$AUTHORITY/$PATH")

private const val RECIPES = 10
private const val RECIPE_ID = 20

private val URI_MATCHER = with(UriMatcher(UriMatcher.NO_MATCH)) {
    addURI(AUTHORITY, PATH, RECIPES)
    addURI(AUTHORITY, "$PATH/#", RECIPE_ID)
    this
}

class MrCookProvider : ContentProvider() {

    private lateinit var mrCookRepository: MrCookRepository

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        when(URI_MATCHER.match(uri)) {
            RECIPES -> return mrCookRepository.delete(selection, selectionArgs)
            RECIPE_ID -> {
                uri.lastPathSegment?.let {
                    return mrCookRepository.delete("_id=?", arrayOf(it))
                }
            }
        }
        throw IllegalArgumentException("Wrong URI")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = mrCookRepository.insert(values)
        return ContentUris.withAppendedId(RECIPES_PROVIDER_URI, id)
    }

    override fun onCreate(): Boolean {
        mrCookRepository = getMrCookRepository(context)
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ) = mrCookRepository.query(projection, selection, selectionArgs, sortOrder)

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        when(URI_MATCHER.match(uri)) {
            RECIPES -> return mrCookRepository.update(values, selection, selectionArgs)
            RECIPE_ID -> {
                uri.lastPathSegment?.let {
                    return mrCookRepository.update(values, "_id=?", arrayOf(it))
                }
            }
        }
        throw IllegalArgumentException("Wrong URI")
    }
}