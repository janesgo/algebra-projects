package hr.project.mrcook.dao

import android.content.Context

fun getMrCookRepository(context: Context?) = MrCookSqlHelper(context)