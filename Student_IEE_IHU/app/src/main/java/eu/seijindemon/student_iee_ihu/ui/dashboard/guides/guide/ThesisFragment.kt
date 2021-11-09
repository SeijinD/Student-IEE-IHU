package eu.seijindemon.student_iee_ihu.ui.dashboard.guides.guide

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentThesisBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment

@AndroidEntryPoint
class ThesisFragment : BaseFragment<FragmentThesisBinding>() {

    override fun getViewBinding(): FragmentThesisBinding {
        return FragmentThesisBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

    }

    private fun setupListeners() {
        with(binding) {
            moreThesis.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.iee.ihu.gr/wp-content/uploads/2021/03/%CE%9A%CE%B1%CE%BD%CE%BF%CE%BD%CE%B9%CF%83%CE%BC%CF%8C%CF%82-%CE%94%CE%B9%CF%80%CE%BB%CF%89%CE%BC%CE%B1%CF%84%CE%B9%CE%BA%CF%8E%CE%BD-%CE%95%CF%81%CE%B3%CE%B1%CF%83%CE%B9%CF%8E%CE%BD-R1.pdf")))
            }

            siteThesis.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.iee.ihu.gr/course/1999")))
            }
        }
    }

}