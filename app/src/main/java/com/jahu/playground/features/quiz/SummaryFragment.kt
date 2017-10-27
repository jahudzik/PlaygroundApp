package com.jahu.playground.features.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jahu.playground.R
import com.jahu.playground.mvp.BaseFragment
import kotlinx.android.synthetic.main.fragment_summary.*

class SummaryFragment : BaseFragment() {

    companion object {
        private const val CORRECT_ANSWERS_COUNT_BUNDLE = "correct-answers"
        private const val QUESTIONS_COUNT_BUNDLE = "questions"

        fun newInstance(correctAnswersCount: Int, questionsCount: Int) : SummaryFragment {
            val fragment = SummaryFragment()
            val bundle = Bundle()
            bundle.putInt(CORRECT_ANSWERS_COUNT_BUNDLE, correctAnswersCount)
            bundle.putInt(QUESTIONS_COUNT_BUNDLE, questionsCount)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var eventListener: EventListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        eventListener = context as EventListener
    }

    override fun onResume() {
        super.onResume()
        val correctAnswersCount = arguments.getInt(CORRECT_ANSWERS_COUNT_BUNDLE)
        val questionsCount = arguments.getInt(QUESTIONS_COUNT_BUNDLE)
        scoreTextView.text = activity.getString(R.string.score, correctAnswersCount, questionsCount)
        returnButton.setOnClickListener { eventListener?.onReturnClicked() }
    }

    override fun onDetach() {
        eventListener = null
        super.onDetach()
    }

    interface EventListener {

        fun onReturnClicked()

    }
}
