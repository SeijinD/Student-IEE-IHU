package eu.seijindemon.student_iee_ihu.refactor.ui.find.semesters

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.FragmentSemestersBinding
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment

@AndroidEntryPoint
class SemestersFragment : BaseFragment<FragmentSemestersBinding>() {

    override fun getViewBinding(): FragmentSemestersBinding {
        return FragmentSemestersBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupListeners()
        
    }
    
    private fun setupListeners() {
        with(binding) {
            val semesterNumber = arrayListOf("1","2","3","4","5","6","7","8","9","10")

            firstSemester.setOnClickListener{
                findNavController().navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[0]))
            }
            secondSemester.setOnClickListener{
                findNavController().navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[1]))
            }
            thirdSemester.setOnClickListener{
                findNavController().navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[2]))
            }
            fourthSemester.setOnClickListener{
                findNavController().navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[3]))
            }
            fifthSemester.setOnClickListener{
                findNavController().navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[4]))
            }
            sixthSemester.setOnClickListener{
                findNavController().navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[5]))
            }
            seventhSemester.setOnClickListener{
                findNavController().navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[6]))
            }
            eighthSemester.setOnClickListener{
                findNavController().navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[7]))
            }
            ninthSemester.setOnClickListener{
                findNavController().navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[8]))
            }
            tenthSemester.setOnClickListener{
                findNavController().navigate(SemestersFragmentDirections.actionMenuSemestersToSemesterFragment(semesterNumber[9]))
            }
        }
    }

}