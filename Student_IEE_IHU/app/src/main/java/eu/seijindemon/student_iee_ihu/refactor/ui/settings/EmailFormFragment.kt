package eu.seijindemon.student_iee_ihu.refactor.ui.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import dagger.hilt.android.AndroidEntryPoint
import eu.seijindemon.student_iee_ihu.R
import eu.seijindemon.student_iee_ihu.databinding.FragmentEmailFormBinding
import eu.seijindemon.student_iee_ihu.refactor.ui.base.BaseFragment
import www.sanju.motiontoast.MotionToast

@AndroidEntryPoint
class EmailFormFragment : BaseFragment<FragmentEmailFormBinding>() {

    override fun getViewBinding(): FragmentEmailFormBinding {
        return FragmentEmailFormBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

    }

    fun setupListeners() {
        with(binding) {
            sendButton.setOnClickListener{
                val subject = subjectForm.text.toString().trim()
                val message = messageForm.text.toString().trim()
                val email = "georgekara2010@yahoo.gr"

                sendEmail(email, subject, message)
            }
        }
    }

    private fun sendEmail(email: String, subject: String, message: String) {

        val mIntent = Intent(Intent.ACTION_SEND)

        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"

        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            startActivity(Intent.createChooser(mIntent, getString(R.string.choose_email_client)))
        } catch (e: Exception) {
            MotionToast.Companion.createColorToast(
                requireActivity(),
                getString(R.string.error),
                "${getString(R.string.error_message)} ${e.message}",
                MotionToast.Companion.TOAST_ERROR,
                MotionToast.Companion.GRAVITY_BOTTOM,
                MotionToast.Companion.LONG_DURATION,
                ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular))
        }
    }

}