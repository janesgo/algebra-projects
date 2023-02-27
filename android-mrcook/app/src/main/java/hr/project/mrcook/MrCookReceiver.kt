package hr.project.mrcook

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.project.mrcook.framework.startActivity

class MrCookReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.startActivity<HostActivity>()
    }
}