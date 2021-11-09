package eu.seijindemon.student_iee_ihu.ui.app

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.databinding.ActivityAppBinding
import eu.seijindemon.student_iee_ihu.ui.base.BaseActivity

@AndroidEntryPoint
class AppActivity : BaseActivity<ActivityAppBinding, AppViewModel>(AppViewModel::class.java) {

    override fun getViewBinding(): ActivityAppBinding {
        return ActivityAppBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

}