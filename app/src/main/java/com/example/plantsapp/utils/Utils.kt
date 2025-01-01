package com.example.plantsapp.utils

import com.example.plantsapp.data.roomDb.model.NoInternetModel
import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.plantsapp.R
import com.example.plantsapp.utils.Env.selectedChipColor
import com.google.android.material.chip.Chip
import java.util.Locale

var _noInternet = MutableLiveData<NoInternetModel?>(null)
val noInternet: MutableLiveData<NoInternetModel?> get() = _noInternet
fun resetNoInternet() = _noInternet.postValue(null)
/*TODO Reed about changing Currency To Other Languages

    NumberFormat numberFormat = NumberFormat.getInstance(new Locale("fa"));
    String formattedNumber = numberFormat.format(1234);

    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, new Locale("fa"));
    String formattedDate = dateFormat.format(new Date());
*/

fun setLocale(lang: String?, context: Context, activity: AppCompatActivity) {
    val locale = Locale(lang)
    Locale.setDefault(locale)
    val config: Configuration = Configuration()
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
    activity.recreate() // Recreate the activity to apply the language change
}

fun logger(string: String) {
    Log.d("PlantsApp", string)
}


fun expandView(outerView: View, innerViewView: View) {
    innerViewView.visibility = View.VISIBLE

    // Measure the width of the TextView
    innerViewView.measure(
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    )
    val targetWidth = innerViewView.measuredWidth

    // Animate the CardView's width
    val initialWidth = outerView.measuredWidth
    val animation = ValueAnimator.ofInt(initialWidth, 500).apply {
        duration = 300 // duration of the animation
        addUpdateListener { animator ->
            val value = animator.animatedValue as Int
            outerView.layoutParams.width = value
            outerView.requestLayout()
        }
    }
    animation.start()
}

fun collapseView(outerView: View, innerViewView: View) {
    val initialWidth = outerView.measuredWidth
    val targetWidth = 0 // or any other desired collapsed width

    // Animate the CardView's width
    val animation = ValueAnimator.ofInt(initialWidth, targetWidth).apply {
        duration = 300 // duration of the animation
        addUpdateListener { animator ->
            val value = animator.animatedValue as Int
            outerView.layoutParams.width = value
            outerView.requestLayout()
        }
    }

    // Set TextView visibility to GONE after the animation ends
    animation.addListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animator: Animator) {}
        override fun onAnimationEnd(animator: Animator) {
            innerViewView.visibility = View.GONE
        }

        override fun onAnimationCancel(animator: Animator) {}
        override fun onAnimationRepeat(animator: Animator) {}
    })

    animation.start()
}

fun internetCheck(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            ?.let { capabilities ->
                return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            }
    } else {
        connectivityManager.activeNetworkInfo?.let { networkInfo ->
            return networkInfo.isConnectedOrConnecting
        }
    }
    return false
}

fun setupChipClickListener(chip: Chip, colorResId: Int, chips: List<Chip>, context: Context) {
    chip.setOnClickListener {
        if (chip.isSelected) {
            resetChipColors(chips, context)
            selectedChipColor = 0
            chip.setTextColor(context.getColor(R.color.black))
            chip.isSelected = false
        } else {
            resetChipColors(chips, context)
            chip.chipBackgroundColor = ColorStateList.valueOf(context.getColor(colorResId))
            selectedChipColor = colorResId
            chip.setTextColor(context.getColor(R.color.white))
            chip.isSelected = true
        }
    }
}

fun resetChipColors(chips: List<Chip>, context: Context) {
    for (chip in chips) {
        chip.chipBackgroundColor = ColorStateList.valueOf(context.getColor(android.R.color.white)) // Default color
        chip.isSelected = false
        chip.setTextColor(context.getColor(R.color.black))
    }
}

fun rotateViewWithAnimation(view:View)
{
    view.animate().apply {
        duration = 1000
        rotationYBy(360f)
        rotationXBy(360f)
    }
}