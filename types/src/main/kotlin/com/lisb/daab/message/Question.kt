package com.lisb.daab.message

import com.lisb.daab.*

open class Question(val question: String, val listing: Boolean?)
class QuestionWithHandler(
        question: String,
        listing: Boolean?,
        override val onSend: ((MessageSent<Question, Question>) -> Unit)?,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)?
): Question(question, listing), WithHandler<Question, Question>

open class CloseQuestion(@JsName("close_yesno") val closeYesNo: String)

abstract class CloseQuestionWithInReplyTo(closeYesNo: String): CloseQuestion(closeYesNo) {
    @JsName("in_reply_to") abstract val inReplyTo: LongValue
}

class CloseQuestionWithHandler(
        closeYesNo: String,
        override val onSend: ((MessageSent<CloseQuestionWithInReplyTo, CloseQuestionResult>) -> Unit)?,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)?
): CloseQuestion(closeYesNo), WithHandler<CloseQuestionWithInReplyTo, CloseQuestionResult>
