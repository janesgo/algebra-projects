package hr.project.mrcook

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import hr.project.mrcook.databinding.ActivitySplashScreenBinding
import hr.project.mrcook.framework.getBooleanPreference
import hr.project.mrcook.framework.isOnline
import hr.project.mrcook.framework.startActivity

private const val DELAY = 3000L
const val DATA_IMPORTED = "hr.project.mrcook.data"
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startAnimations()
        redirect()
    }

    private fun startAnimations() {
        binding.ivSplash.startAnimation(AnimationUtils.loadAnimation(this, R.anim.blink))
        binding.tvLoading.startAnimation(AnimationUtils.loadAnimation(this, R.anim.blink))
    }

    private fun redirect() {
        if (getBooleanPreference(DATA_IMPORTED)) {
            Handler(Looper.getMainLooper())
                .postDelayed({ startActivity<HostActivity>() }, DELAY)
        } else {
            if (isOnline()) {
                Intent(this, MrCookService::class.java).apply {
                    MrCookService.enqueue(this@SplashScreenActivity, this)
                }
            } else {
                binding.tvLoading.text = R.string.no_internet.toString()
                Handler(Looper.getMainLooper()).postDelayed({finish()}, DELAY)
            }
        }
    }


}