package eu.seijindemon.student_iee_ihu.refactor.ui.guides.guide

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentHousingBinding
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment

@AndroidEntryPoint
class HousingFragment : BaseFragment<FragmentHousingBinding>() {

    override fun getViewBinding(): FragmentHousingBinding {
        return FragmentHousingBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

    }

    private fun setupListeners() {
        with(binding) {
            moreHousing.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ihu.gr/wp-content/uploads/2020/10/%CE%94%CE%99%CE%9A%CE%91%CE%99%CE%9F%CE%9B%CE%9F%CE%93%CE%97%CE%A4%CE%99%CE%9A%CE%91_%CE%95%CE%99%CE%A3%CE%91%CE%93%CE%A9%CE%93%CE%97%CE%A3_%CE%A3%CE%A4%CE%97_%CE%A6%CE%9F%CE%99%CE%A4%CE%97%CE%A4%CE%99%CE%9A%CE%97_%CE%95%CE%A3%CE%A4%CE%99%CE%91-1.pdf")))
            }

            siteHousing.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.teithe.gr/monades/foititiki-estia/")))
            }
        }
    }

}