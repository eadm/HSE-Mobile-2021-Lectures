package ru.nobird.hse2021.sample.navigation.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import ru.nobird.hse2021.sample.navigation.SecondActivity

class IncrementActivityContract : ActivityResultContract<Nothing, Int>() {
    override fun createIntent(context: Context, input: Nothing?): Intent =
        Intent(context, SecondActivity::class.java)

    override fun parseResult(resultCode: Int, intent: Intent?): Int? =
        if (resultCode == Activity.RESULT_OK) {
            intent?.getIntExtra(SecondActivity.KEY_DELTA, 0)
        } else {
            null
        }
}