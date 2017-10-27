package com.jahu.playground.features.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.jahu.playground.R
import com.jahu.playground.extensions.setHtmlText
import com.jahu.playground.mvp.BaseFragment
import kotlinx.android.synthetic.main.fragment_question.*

class QuestionFragment : BaseFragment() {

    companion object {
        private const val QUESTION_BUNDLE = "question"
        private const val ANSWERS_BUNDLE = "answers"

        fun newInstance(question: String, answers: List<String>) : QuestionFragment {
            val fragment = QuestionFragment()
            val bundle = Bundle()
            bundle.putString(QUESTION_BUNDLE, question)
            bundle.putStringArray(ANSWERS_BUNDLE, answers.toTypedArray())
            fragment.arguments = bundle
            return fragment
        }
    }

    private var eventListener: EventListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        eventListener = context as EventListener
    }

    @SuppressWarnings("MagicNumber")
    override fun onResume() {
        super.onResume()
        questionTextView.setHtmlText(arguments.getString(QUESTION_BUNDLE))
        val answers = arguments.getStringArray(ANSWERS_BUNDLE)
        initAnswerButton(answer1Button, 0, answers)
        initAnswerButton(answer2Button, 1, answers)
        initAnswerButton(answer3Button, 2, answers)
        initAnswerButton(answer4Button, 3, answers)
    }

    private fun initAnswerButton(button: Button, index: Int, answers: Array<String>) {
        button.setHtmlText(answers[index])
        button.setOnClickListener { eventListener?.onAnswerChosen(index) }
    }

    override fun onDetach() {
        eventListener = null
        super.onDetach()
    }

    interface EventListener {

        fun onAnswerChosen(index: Int)

    }

}
