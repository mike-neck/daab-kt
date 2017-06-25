package com.lisb.daab.message

import com.lisb.daab.*

open class SelectStamp(val question: String, val options: Array<String>, val listing: Boolean? = null)

class SelectStampWithHandler(
        question: String,
        options: Array<String>,
        listing: Boolean? = null,
        override val onSend: ((MessageSent<SelectStamp, SelectStamp>) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): SelectStamp(question, options, listing), WithHandler<SelectStamp, SelectStamp>

open class CloseSelect(@JsName("close_select") val closeSelect: String)

abstract class CloseSelectWithInReplyTo(closeSelect: String): CloseSelect(closeSelect) {
    @JsName("in_reply_to") abstract val inReplyTo: LongValue
}

class CloseSelectWithHandler(
        closeSelect: String,
        override val onSend: ((MessageSent<CloseSelectWithInReplyTo, CloseQuestionResult>) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): CloseSelect(closeSelect), WithHandler<CloseSelectWithInReplyTo, CloseQuestionResult>

external interface ReceivedSelect {
    val question: String
    val options: Array<String>
    @JsName("closing_type") val closingType: Int
}

open class AnswerForSelect(@JsName("in_reply_to") val inReplyTo: String, val response: Int)

external interface AnswerSelect {
    @JsName("in_reply_to") val inReplyTo: LongValue
    val response: Int
}

external interface AnswerSelectResult : ReceivedSelect, AnswerSelect

class AnswerSelectWithHandler(
        inReplyTo: String,
        response: Int,
        override val onSend: ((MessageSent<AnswerSelect, AnswerSelectResult>) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): AnswerForSelect(inReplyTo, response), WithHandler<AnswerSelect, AnswerSelectResult>
