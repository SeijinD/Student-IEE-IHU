package eu.seijindemon.student_iee_ihu.ui.dashboard.find.rooms.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import eu.seijindemon.student_iee_ihu.ui.dashboard.find.rooms.ERoomsFragment
import eu.seijindemon.student_iee_ihu.ui.dashboard.find.rooms.RRoomsFragment

class ZoomRoomsAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            1 -> {
                ERoomsFragment()
            }
            else -> {
                RRoomsFragment()
            }
        }
    }
}