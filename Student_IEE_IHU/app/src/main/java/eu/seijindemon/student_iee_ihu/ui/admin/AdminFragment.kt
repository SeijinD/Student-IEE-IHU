package eu.seijindemon.student_iee_ihu.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import kotlinx.android.synthetic.main.fragment_admin.view.*

@AndroidEntryPoint
class AdminFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_admin, container, false)

        view.inserts.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.menuAdminInserts)
        }

        view.updates.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.menuAdminUpdates)
        }

        view.deletes.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.menuAdminDeletes)
        }

        return view
    }


}