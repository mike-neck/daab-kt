package com.lisb.daab.message

import com.lisb.daab.CloseQuestionResult
import com.lisb.daab.MessageSent
import com.lisb.daab.User
import com.lisb.daab.WithHandler

open class Question(val question: String, val listing: Boolean?)
class QuestionWithHandler(
        question: String,
        listing: Boolean?,
        override val onSend: (MessageSent<Question, Question>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): Question(question, listing), WithHandler<Question, Question>

open class CloseQuestion(@JsName("close_yesno") val closeYesNo: String)

class CloseQuestionWithHandler(
        closeYesNo: String,
        override val onSend: (MessageSent<CloseQuestion, CloseQuestionResult>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): CloseQuestion(closeYesNo), WithHandler<CloseQuestion, CloseQuestionResult>
