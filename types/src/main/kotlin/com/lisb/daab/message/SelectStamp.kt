package com.lisb.daab.message

import com.lisb.daab.*

open class SelectStamp(val question: String, val options: Array<String>, val listing: Boolean?)

class SelectStampWithHandler(
        question: String,
        options: Array<String>,
        listing: Boolean?,
        override val onSend: ((MessageSent<SelectStamp, SelectStamp>) -> Unit)?,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)?
): SelectStamp(question, options, listing), WithHandler<SelectStamp, SelectStamp>

open class CloseSelect(@JsName("close_select") val closeSelect: String)

abstract class CloseSelectWithInReplyTo(closeSelect: String): CloseSelect(closeSelect) {
    @JsName("in_reply_to") abstract val inReplyTo: LongValue
}

class CloseSelectWithHandler(
        closeSelect: String,
        override val onSend: ((MessageSent<CloseSelectWithInReplyTo, CloseQuestionResult>) -> Unit)?,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)?
): CloseSelect(closeSelect), WithHandler<CloseSelectWithInReplyTo, CloseQuestionResult>
