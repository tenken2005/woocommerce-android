package com.woocommerce.android.ui.feedback

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.navArgs
import com.woocommerce.android.R
import com.woocommerce.android.analytics.AnalyticsTracker
import com.woocommerce.android.analytics.AnalyticsTracker.Companion.KEY_FEEDBACK_ACTION
import com.woocommerce.android.analytics.AnalyticsTracker.Companion.KEY_FEEDBACK_CONTEXT
import com.woocommerce.android.analytics.AnalyticsTracker.Companion.VALUE_FEEDBACK_COMPLETED
import com.woocommerce.android.analytics.AnalyticsTracker.Companion.VALUE_FEEDBACK_GENERAL_CONTEXT
import com.woocommerce.android.analytics.AnalyticsTracker.Companion.VALUE_FEEDBACK_PRODUCT_M3_CONTEXT
import com.woocommerce.android.analytics.AnalyticsTracker.Stat.SURVEY_SCREEN
import com.woocommerce.android.support.HelpActivity
import com.woocommerce.android.support.HelpActivity.Origin
import com.woocommerce.android.ui.feedback.SurveyType.MAIN
import com.woocommerce.android.widgets.WooClickableSpan
import kotlinx.android.synthetic.main.fragment_feedback_completed.*

class FeedbackCompletedFragment : androidx.fragment.app.Fragment() {
    companion object {
        const val TAG = "survey_completed"
    }

    private val feedbackContext by lazy {
        (navArgs<FeedbackCompletedFragmentArgs>().value).let {
            if (it.surveyType == MAIN) VALUE_FEEDBACK_GENERAL_CONTEXT
            else VALUE_FEEDBACK_PRODUCT_M3_CONTEXT
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_feedback_completed, container, false)
    }

    override fun onResume() {
        super.onResume()

        trackSurveyCompletedScreenAnalytics()

        activity?.let {
            it.invalidateOptionsMenu()
            it.title = getString(R.string.feedback_completed_title)
            (it as? AppCompatActivity)
                ?.supportActionBar
                ?.setHomeAsUpIndicator(R.drawable.ic_gridicons_cross_white_24dp)
        }

        val contactUsText = getString(R.string.feedback_completed_contact_us)
        getString(R.string.feedback_completed_description, contactUsText)
            .configureStringClick(
                clickableContent = contactUsText,
                clickAction = WooClickableSpan { activity?.startHelpActivity() }
            )
        btn_back_to_store.setOnClickListener { activity?.onBackPressed() }
    }

    override fun onStop() {
        super.onStop()
        activity?.invalidateOptionsMenu()
    }

    private fun String.configureStringClick(clickableContent: String, clickAction: WooClickableSpan) {
        SpannableString(this)
            .buildClickableUrlSpan(clickableContent, this, clickAction)
            .let {
                completion_help_guide.apply {
                    setText(it, TextView.BufferType.SPANNABLE)
                    movementMethod = LinkMovementMethod.getInstance()
                }
            }
    }

    private fun SpannableString.buildClickableUrlSpan(
        clickableContent: String,
        fullContent: String,
        clickAction: WooClickableSpan
    ) = apply {
        setSpan(
            clickAction,
            (fullContent.length - clickableContent.length),
            fullContent.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    private fun FragmentActivity.startHelpActivity() =
        startActivity(
            HelpActivity.createIntent(
                this,
                Origin.FEEDBACK_SURVEY,
                null
            )
        )

    private fun trackSurveyCompletedScreenAnalytics() {
        AnalyticsTracker.trackViewShown(this)
        AnalyticsTracker.track(
            SURVEY_SCREEN, mapOf(
            KEY_FEEDBACK_CONTEXT to feedbackContext,
            KEY_FEEDBACK_ACTION to VALUE_FEEDBACK_COMPLETED
        ))
    }
}
