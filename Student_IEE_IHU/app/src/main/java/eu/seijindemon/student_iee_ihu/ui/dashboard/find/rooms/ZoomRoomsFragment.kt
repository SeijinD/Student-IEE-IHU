package eu.seijindemon.student_iee_ihu.ui.dashboard.find.rooms

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentZoomRoomsBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.ui.dashboard.find.rooms.adapter.ZoomRoomsAdapter

@AndroidEntryPoint
class ZoomRoomsFragment : BaseFragment<FragmentZoomRoomsBinding>() {

    override fun getViewBinding(): FragmentZoomRoomsBinding {
        return FragmentZoomRoomsBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val tabLayout = tabLayoutRooms
            val pager2 = viewPage2Rooms

            val adapter = ZoomRoomsAdapter(activity?.supportFragmentManager!!, lifecycle)
            pager2.adapter = adapter

            tabLayout.addTab(tabLayout.newTab().setText(R.string.r1_r8))
            tabLayout.addTab(tabLayout.newTab().setText(R.string.e1_e8))

            tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    pager2.currentItem = tab!!.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}

            })

            pager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    tabLayout.selectTab(tabLayout.getTabAt(position))
                }
            })
        }

    }

}