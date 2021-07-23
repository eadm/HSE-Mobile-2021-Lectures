package ru.nobird.hse2021.sample.dialogfragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import ru.nobird.hse2021.sample.R
import ru.nobird.hse2021.sample.databinding.DialogFullscreenBinding
import ru.nobird.hse2021.sample.extension.getTarget

class FullscreenDialogFragment : DialogFragment() {
    private var viewBinding: DialogFullscreenBinding? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.ThemeOverlay_AppTheme_Dialog_Fullscreen)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_fullscreen, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = DialogFullscreenBinding.bind(view)

        viewBinding?.apply {
            toolbar.setNavigationIcon(R.drawable.ic_close)
            toolbar.setNavigationOnClickListener { dismiss() }
            toolbar.inflateMenu(R.menu.menu_fullscreen_dialog)
            toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.review_submit -> {
                        getTarget<Callback>()
                            ?.onReviewCreated(reviewEditText.text.toString(), reviewRating.rating.toInt())
                        dismiss()
                        true
                    }

                    else ->
                        false
                }
            }
        }
    }

    interface Callback {
        fun onReviewCreated(text: String, rate: Int)
    }

    companion object {
        const val TAG = "FullscreenDialogFragment"

        fun newInstance(): DialogFragment =
            FullscreenDialogFragment()
    }
}