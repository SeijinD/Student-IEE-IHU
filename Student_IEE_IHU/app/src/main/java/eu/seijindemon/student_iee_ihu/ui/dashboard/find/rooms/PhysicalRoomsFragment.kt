package eu.seijindemon.student_iee_ihu.ui.dashboard.find.rooms

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentPhysicalRoomsBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseFragment
import eu.seijindemon.student_iee_ihu.ui.dashboard.find.rooms.adapter.PhysicalRoomsAdapter

@AndroidEntryPoint
class PhysicalRoomsFragment : BaseFragment<FragmentPhysicalRoomsBinding>() {

    override fun getViewBinding(): FragmentPhysicalRoomsBinding {
        return FragmentPhysicalRoomsBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            val tabLayout = tabLayoutRooms
            val pager2 = viewPage2Rooms

            val adapter = PhysicalRoomsAdapter(activity?.supportFragmentManager!!, lifecycle)
            pager2.adapter = adapter

            tabLayout.addTab(tabLayout.newTab().setText(R.string.build_p))
            tabLayout.addTab(tabLayout.newTab().setText(R.string.build_h))

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