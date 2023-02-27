package hr.project.mrcook.framework

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.preference.PreferenceManager
import hr.project.mrcook.RECIPES_PROVIDER_URI
import hr.project.mrcook.model.Recipe

inline fun <reified T : Activity> Context.startActivity() =
    startActivity(Intent(this, T::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    })

inline fun <reified T : Activity> Context.startActivity(key: String, value: Int) =
    startActivity(Intent(this, T::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        putExtra(key, value)
    })

inline fun<reified T: BroadcastReceiver> Context.sendBroadcast()
        = sendBroadcast(Intent(this, T::class.java))

fun Context.setBooleanPreference(key: String, value: Boolean) {
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit().putBoolean(key, value).apply()
}

fun Context.getBooleanPreference(key: String) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .getBoolean(key, false)

fun Context.isOnline(): Boolean {
    val systemService = getSystemService<ConnectivityManager>()
    systemService?.activeNetwork?.let { net ->
        systemService.getNetworkCapabilities(net)?.let { netCap ->
            return netCap.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    netCap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
    }
    return false
}

fun Context.fetchRecipes(): MutableList<Recipe> {
    val recipes = mutableListOf<Recipe>()
    val query = contentResolver?.query(RECIPES_PROVIDER_URI, null, null, null, null)
    while (query != null && query.moveToNext()) {
        recipes.add(Recipe(
            query.getLong(query.getColumnIndexOrThrow(Recipe::_id.name)),
            query.getString(query.getColumnIndexOrThrow(Recipe::title.name)),
            query.getString(query.getColumnIndexOrThrow(Recipe::category.name)),
            query.getString(query.getColumnIndexOrThrow(Recipe::area.name)),
            query.getString(query.getColumnIndexOrThrow(Recipe::instructions.name)),
            query.getString(query.getColumnIndexOrThrow(Recipe::imagePath.name)),
            query.getString(query.getColumnIndexOrThrow(Recipe::ingredients.name)),
            query.getString(query.getColumnIndexOrThrow(Recipe::measures.name)),
            query.getInt(query.getColumnIndexOrThrow(Recipe::fav.name)) == 1,
        ))
    }
    return recipes
}

fun Activity.hideSoftKeyboard() {
    currentFocus?.let {
        val inputMethodManager = ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}