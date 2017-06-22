package com.lisb.daab.message

import com.lisb.daab.LongValue
import com.lisb.daab.MessageSent
import com.lisb.daab.User
import com.lisb.daab.WithHandler

open class Stamp(
        @JsName("stamp_set") val stampSet: Int,
        @JsName("stamp_index") val stampIndex: LongValue,
        val text: String?)

class StampWithHandler(
        stampSet: Int,
        stampIndex: LongValue,
        text: String?,
        override val onSend: (MessageSent<Stamp, Stamp>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): Stamp(stampSet, stampIndex, text), WithHandler<Stamp, Stamp>
