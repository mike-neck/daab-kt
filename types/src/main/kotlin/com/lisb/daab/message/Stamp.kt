package com.lisb.daab.message

import com.lisb.daab.LongValue
import com.lisb.daab.MessageSent
import com.lisb.daab.User
import com.lisb.daab.WithHandler

open class Stamp(
        @JsName("stamp_set") val stampSet: Int,
        @JsName("stamp_index") val stampIndex: String,
        val text: String? = null)

class StampWithHandler(
        stampSet: Int,
        stampIndex: String,
        text: String? = null,
        override val onSend: ((MessageSent<StampContent, StampContent>) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): Stamp(stampSet, stampIndex, text), WithHandler<StampContent, StampContent>

interface StampContent {
    @JsName("stamp_set") val stampSet: Int
    @JsName("stamp_index") val stampIndex: LongValue
}
