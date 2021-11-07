package eu.seijindemon.student_iee_ihu.refactor.ui.guides

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentGuidesBinding
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment

@AndroidEntryPoint
class GuidesFragment : BaseFragment<FragmentGuidesBinding>() {

    override fun getViewBinding(): FragmentGuidesBinding {
        return FragmentGuidesBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

    }

    private fun setupListeners() {
        with(binding) {
            firstDay.setOnClickListener{
                findNavController().navigate(R.id.menuFirstDay)
            }

            zoom.setOnClickListener{
                findNavController().navigate(R.id.menuZoom)
            }

            thesis.setOnClickListener{
                findNavController().navigate(R.id.menuThesis)
            }

            feeding.setOnClickListener{
                findNavController().navigate(R.id.menuFeeding)
            }

            housing.setOnClickListener{
                findNavController().navigate(R.id.menuHousing)
            }

            library.setOnClickListener{
                findNavController().navigate(R.id.menuLibrary)
            }

            softwares.setOnClickListener{
                findNavController().navigate(R.id.menuSoftwares)
            }
        }
    }

}