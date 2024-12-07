package com.example.plantsapp.views.intro

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.plantsapp.R
import com.example.plantsapp.utils.collapseView
import com.example.plantsapp.utils.expandView
import com.example.plantsapp.databinding.FragmentIntroBinding
import com.example.plantsapp.utils.Env


class IntroFragment : Fragment() {

    private var state: Int = 0
    private var originalStartConstraint: Int = 0
    private var originalEndConstraint: Int = 0
    private var originalBottomConstraint: Int = 0

    private lateinit var binding: FragmentIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        listen()
        observe()
    }


    private fun init() {

    }

    private fun observe() {}

    private fun listen() {
        binding.apply {
            cardViewNext.alpha = 1f
            cardViewNext.setOnClickListener {
                when (state) {
                    0 -> {
                            visibleViewWithAnimation(cardViewBack)
                            state = 1
                            binding.apply {
                                imgLottie.setAnimation(R.raw.sliding_pot)
                            }
                        }
                    1 -> {
                            expandView(cardViewNext,tvCardNext)
                            state = 2
                    }
                    2 -> {

                        val handler = Handler(Looper.getMainLooper())
                        handler.postDelayed({
                            // Your code to execute after the duration
                            if (Env.store.getBoolean("isLogin"))
                            {
                                findNavController().navigate(R.id.action_introFragment_to_mainFragment)
                            }else
                            {
                                findNavController().navigate(R.id.action_introFragment_to_authFragment)
                            }

                        }, 300)

                    }
                }
            }
            cardViewBack.setOnClickListener {
                when (state) {
                    0 -> {

                    }
                    1 -> {
                        invisibleViewWithAnimation(cardViewBack)
                        state = 0
                    }
                    2 -> {
                        collapseView(cardViewNext,tvCardNext)
                        visibleViewWithAnimation(imgNext)

                        state = 1
                    }
                }
            }
        }

    }

    private fun visibleViewWithAnimation(view: View) {
        binding.apply {
            view.setVisibility(View.VISIBLE);
            view.setAlpha(0f); // Set the initial alpha to 0
            view.animate()
                .alpha(1f) // Animate the alpha to 1
                .setDuration(100) // Duration of the animation (300ms)
                .setListener(null)
        }
    }

    private fun invisibleViewWithAnimation(view: View) {
        binding.apply {
            view.animate()
                .alpha(1f)
                .setDuration(100)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        view.visibility = View.GONE
                    }
                })
        }

    }
}