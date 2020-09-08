package com.rich_kirk.infinityquiz

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.rich_kirk.infinityquiz.model.Question
import com.rich_kirk.infinityquiz.viewmodel.QuestionViewModel
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var mQuestionViewModel: QuestionViewModel
    private var mSelectedOptionPosition: Int = 0
    private lateinit var question: Question
    private var isSubmit: Boolean = true

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_main)

        // mQuestionViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(QuestionViewModel::class.java)
        mQuestionViewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)

        question = mQuestionViewModel.readRandomQuestion()

        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        // Toast.makeText(this, question.Question, Toast.LENGTH_SHORT).show()
        when (v?.id) {

            R.id.tv_option_one -> {

                selectedOptionView(tv_option_one, 1)
            }

            R.id.tv_option_two -> {

                selectedOptionView(tv_option_two, 2)
            }

            R.id.tv_option_three -> {

                selectedOptionView(tv_option_three, 3)
            }

            R.id.tv_option_four -> {

                selectedOptionView(tv_option_four, 4)
            }

            R.id.btn_submit -> {
                /*
                If an answer hasn't been submitted yet
                 */
                if(isSubmit) {
                    /*
                    If an answer is selected
                    */
                    if(mSelectedOptionPosition != 0) {
                        /*
                        Check if the answer is wrong
                         */
                        if (question.CorrectAnswer != mSelectedOptionPosition) {
                            answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                        }
                        answerView(mSelectedOptionPosition, R.drawable.correct_option_border_bg)

                        btn_submit.text = "GO TO NEXT QUESTION"
                        isSubmit = false

                        mSelectedOptionPosition = 0
                    }
                } else { // The answer has been submitted
                    mSelectedOptionPosition = 0

                    setQuestion()
                }
            }
        }
    }

    /**
     * A function to set the view of selected option view.
     */
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.selected_option_border_bg
        )
    }

    /**
     * A function for setting the question to UI components.
     */
    private fun setQuestion() {
        defaultOptionsView()

        btn_submit.text = "SUBMIT"

        isSubmit = true

        tv_question.text = question.Question
        tv_option_one.text = question.AnswerOne
        tv_option_two.text = question.AnswerTwo
        tv_option_three.text = question.AnswerThree
        tv_option_four.text = question.AnswerFour
    }

    /**
     * A function to set default options view when the new question is loaded or when the answer is reselected.
     */
    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@MainActivity,
                R.drawable.default_option_border_bg
            )
        }
    }

    /**
     * A function for answer view which is used to highlight the answer is wrong or right.
     */
    private fun answerView(answer: Int, drawableView: Int) {

        when (answer) {

            1 -> {
                tv_option_one.background = ContextCompat.getDrawable(
                    this@MainActivity,
                    drawableView
                )
            }
            2 -> {
                tv_option_two.background = ContextCompat.getDrawable(
                    this@MainActivity,
                    drawableView
                )
            }
            3 -> {
                tv_option_three.background = ContextCompat.getDrawable(
                    this@MainActivity,
                    drawableView
                )
            }
            4 -> {
                tv_option_four.background = ContextCompat.getDrawable(
                    this@MainActivity,
                    drawableView
                )
            }
        }
    }
}