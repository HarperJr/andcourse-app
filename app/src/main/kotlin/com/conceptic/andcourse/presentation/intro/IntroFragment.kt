package com.conceptic.andcourse.presentation.intro

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.conceptic.andcourse.R
import kotlinx.android.synthetic.main.fragment_intro.*

class IntroFragment : Fragment(R.layout.fragment_intro) {
    private val introPages = listOf(
        IntroPageItem(R.drawable.test_description, R.string.intro_test_description),
        IntroPageItem(R.drawable.test_description, R.string.intro_test_description),
        IntroPageItem(R.drawable.test_description, R.string.intro_test_description),
        IntroPageItem(R.drawable.test_description, R.string.intro_test_description)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        intro_view_pager.adapter = IntroPagerAdapter(requireContext(), introPages)
        intro_pass_btn.setOnClickListener { findNavController().navigate(R.id.action_introFragment_to_signinFragment) }
    }
}
