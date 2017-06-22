package com.lisb.daab.message

import com.lisb.daab.LongValue
import com.lisb.daab.MessageSent
import com.lisb.daab.User
import com.lisb.daab.WithHandler

open class SelectStamp(val question: String, val options: Array<String>, val listing: Boolean?)
class SelectStampWithHandler(
        question: String,
        options: Array<String>,
        listing: Boolean?,
        override val onSend: (MessageSent<SelectStamp, SelectStamp>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): SelectStamp(question, options, listing), WithHandler<SelectStamp, SelectStamp>

open class CloseSelect(@JsName("close_select") val closeSelect: LongValue)
