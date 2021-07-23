package ru.nobird.hse2021.sample.dialogfragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.nobird.hse2021.sample.extension.getTarget
import ru.nobird.hse2021.sample.R

class AlertDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.allow_mobile_download_title)
            .setMessage(R.string.allow_mobile_message)
            .setPositiveButton(R.string.yes) { _, _ ->
                getTarget<Callback>()
                    ?.onMobileDataStateChanged(true)
            }
            .setNegativeButton(R.string.no) { _, _ ->
                getTarget<Callback>()
                    ?.onMobileDataStateChanged(false)
            }
            .create()

    /**
     * Интерфейс, который должен быть реализован родителем
     */
    interface Callback {
        fun onMobileDataStateChanged(isMobileAllowed: Boolean)
    }

    companion object {
        const val TAG = "AlertDialogFragment"

        fun newInstance(): DialogFragment =
            AlertDialogFragment()
    }
}