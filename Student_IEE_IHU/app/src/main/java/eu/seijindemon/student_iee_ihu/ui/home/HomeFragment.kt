package eu.seijindemon.student_iee_ihu.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.utils.LoadLanguage
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val tabLayout = view.tab_layout_home
        val pager2 = view.view_pager2_home

        var language = "en"
        when(LoadLanguage.loadLanguage()) {
            "el" -> language = "el"
            "en" -> language = "en"
        }

        val adapter = HomeAdapter(requireContext(), language)
        pager2.adapter = adapter

//        TabLayoutMediator(tabLayout, pager2, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
//
//        }).attach()

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pager2.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        pager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

        return view
    }


}