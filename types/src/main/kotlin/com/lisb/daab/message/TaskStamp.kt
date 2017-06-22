package com.lisb.daab.message

import com.lisb.daab.LongValue
import com.lisb.daab.MessageSent
import com.lisb.daab.User
import com.lisb.daab.WithHandler

open class TaskStamp(val title: String, @JsName("closing_type") val closingType: Int)
class TaskStampWithHandler(
        title: String,
        closingType: Int,
        override val onSend: (MessageSent<TaskStamp, TaskStamp>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): TaskStamp(title, closingType), WithHandler<TaskStamp, TaskStamp>

open class CloseTask(@JsName("close_task") val closeTask: LongValue)
