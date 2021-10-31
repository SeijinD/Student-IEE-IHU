package eu.seijindemon.student_iee_ihu.ui.find.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_physical_rooms.view.*

@AndroidEntryPoint
class PhysicalRoomsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_physical_rooms, container, false)

        val tabLayout = view.tab_layout_rooms
        val pager2 = view.view_page2_rooms

        val fm = fragmentManager
        val adapter = PhysicalRoomsAdapter(fm!!, lifecycle)
        pager2.adapter = adapter

        tabLayout.addTab(tabLayout.newTab().setText(R.string.build_p))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.build_h))

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