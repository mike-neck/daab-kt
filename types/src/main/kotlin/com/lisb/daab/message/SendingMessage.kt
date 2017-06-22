package com.lisb.daab.message

import com.lisb.daab.MessageSent
import com.lisb.daab.User
import com.lisb.daab.WithHandler

open class SendingMessage(val text: String)

class SendingMessageWithHandler(
        text: String,
        override val onSend: (MessageSent<SendingMessage, SendingMessage>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): SendingMessage(text), WithHandler<SendingMessage, SendingMessage>