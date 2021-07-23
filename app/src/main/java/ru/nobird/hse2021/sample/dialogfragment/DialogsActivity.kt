package ru.nobird.hse2021.sample.dialogfragment

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import ru.nobird.hse2021.sample.R
import ru.nobird.hse2021.sample.databinding.ActivityDialogsBinding
import ru.nobird.hse2021.sample.extension.snackbar

class DialogsActivity :
    AppCompatActivity(),
    AlertDialogFragment.Callback,
    FullscreenDialogFragment.Callback {

    private lateinit var viewBinding: ActivityDialogsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDialogsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportActionBar?.apply {
            title = getString(R.string.topic_dialogfragment)
            setDisplayHomeAsUpEnabled(true)
        }

        viewBinding.alertDialogButton.setOnClickListener {
            AlertDialogFragment
                .newInstance()
                .show(supportFragmentManager, AlertDialogFragment.TAG)
        }

        viewBinding.fullScreenDialogButton.setOnClickListener {
            FullscreenDialogFragment
                .newInstance()
                .show(supportFragmentManager, FullscreenDialogFragment.TAG)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }

    override fun onMobileDataStateChanged(isMobileAllowed: Boolean) {
        viewBinding.root.snackbar(message = isMobileAllowed.toString())
    }

    override fun onReviewCreated(text: String, rate: Int) {
        viewBinding.root.snackbar(message = text)
    }
}