package eu.seijindemon.student_iee_ihu.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel>(clazz: Class<VM>) : AppCompatActivity() {

    protected lateinit var binding: VB

    abstract fun getViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getViewBinding()
        setContentView(binding.root)

    }

    protected val viewModel: VM by lazy { ViewModelProvider(this).get(clazz) }

}