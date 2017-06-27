package com.lisb.daab.message

import com.lisb.daab.*

open class SelectStamp(val question: String, val options: Array<String>, val listing: Boolean? = null)

external interface AfterSelectHandler: OnReadHandler {
    @JsName("onsend") val onSend: ((SelectSent) -> Unit)?
}

external interface SelectSent: MessageSent<SelectStamp, SelectStamp> {
    fun answer(selectAnswers: (Array<Array<User>>) -> Unit): Unit
}

class SelectStampAfterMessageHandler(
        question: String,
        options: Array<String>,
        listing: Boolean? = null,
        override val onSend: ((SelectSent) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): SelectStamp(question, options, listing), AfterSelectHandler

open class CloseSelect(@JsName("close_select") val closeSelect: String)

abstract class CloseSelectWithInReplyTo(closeSelect: String): CloseSelect(closeSelect) {
    @JsName("in_reply_to") abstract val inReplyTo: LongValue
}

class CloseSelectAfterMessageHandler(
        closeSelect: String,
        override val onSend: ((MessageSent<CloseSelectWithInReplyTo, CloseQuestionResult>) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): CloseSelect(closeSelect), AfterMessageHandler<CloseSelectWithInReplyTo, CloseQuestionResult>

external interface ReceivedSelect {
    val question: String
    val options: Array<String>
    @JsName("closing_type") val closingType: Int
}

open class AnswerForSelect(@JsName("in_reply_to") val inReplyTo: String, val response: Int)

external interface AnsweredSelect {
    @JsName("in_reply_to") val inReplyTo: LongValue
    val response: Int
}

external interface AnswerSelectResult : ReceivedSelect, AnsweredSelect

class AnswerSelectAfterMessageHandler(
        inReplyTo: String,
        response: Int,
        override val onSend: ((MessageSent<AnsweredSelect, AnswerSelectResult>) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): AnswerForSelect(inReplyTo, response), AfterMessageHandler<AnsweredSelect, AnswerSelectResult>
