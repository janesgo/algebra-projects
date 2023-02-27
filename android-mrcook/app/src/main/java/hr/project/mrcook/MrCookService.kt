package hr.project.mrcook

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import hr.project.mrcook.api.RecipesFetcher

private const val JOB_ID = 1

class MrCookService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        RecipesFetcher(this).getRecipes("")
    }

    companion object {
        fun enqueue(context: Context, intent: Intent) {
            enqueueWork(context, MrCookService::class.java, JOB_ID, intent)
        }
    }
}