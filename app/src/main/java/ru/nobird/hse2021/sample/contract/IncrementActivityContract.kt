package ru.nobird.hse2021.sample.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import ru.nobird.hse2021.sample.SecondActivity

class IncrementActivityContract : ActivityResultContract<Int, Int>() {
    override fun createIntent(context: Context, input: Int?): Intent =
        Intent(context, SecondActivity::class.java)
            .putExtra(SecondActivity.KEY_DELTA, input ?: 0)

    override fun parseResult(resultCode: Int, intent: Intent?): Int? =
        if (resultCode == Activity.RESULT_OK) {
            intent?.getIntExtra(SecondActivity.KEY_DELTA, 0)
        } else {
            null
        }
}