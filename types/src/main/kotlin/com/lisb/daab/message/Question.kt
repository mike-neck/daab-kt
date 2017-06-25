package com.lisb.daab.message

import com.lisb.daab.*

open class Question(val question: String, val listing: Boolean? = null)

class QuestionWithHandler(
        question: String,
        listing: Boolean? = null,
        override val onSend: ((MessageSent<Question, Question>) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): Question(question, listing), WithHandler<Question, Question>

open class CloseQuestion(@JsName("close_yesno") val closeYesNo: String)

abstract class CloseQuestionWithInReplyTo(closeYesNo: String): CloseQuestion(closeYesNo) {
    @JsName("in_reply_to") abstract val inReplyTo: LongValue
}

class CloseQuestionWithHandler(
        closeYesNo: String,
        override val onSend: ((MessageSent<CloseQuestionWithInReplyTo, CloseQuestionResult>) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): CloseQuestion(closeYesNo), WithHandler<CloseQuestionWithInReplyTo, CloseQuestionResult>

external interface ReceivedQuestion {
    val question: String
    @JsName("closing_type")val closingType: Int
}

open class AnswerForQuestion(@JsName("in_reply_to") val inReplyTo: String, val response: Boolean)

external interface AnsweredQuestion {
    @JsName("in_reply_to") val inReplyTo: LongValue
    val response: Boolean
}

external interface AnswerQuestionResult : ReceivedQuestion, AnsweredQuestion

class AnswerQuestionWithHandler(
        inReplyTo: String,
        response: Boolean,
        override val onSend: ((MessageSent<AnsweredQuestion, AnswerQuestionResult>) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): AnswerForQuestion(inReplyTo, response), WithHandler<AnsweredQuestion, AnswerQuestionResult>
