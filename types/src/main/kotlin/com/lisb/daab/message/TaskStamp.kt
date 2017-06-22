package com.lisb.daab.message

import com.lisb.daab.*

open class TaskStamp(val title: String, @JsName("closing_type") val closingType: Int)

class TaskStampWithHandler(
        title: String,
        closingType: Int,
        override val onSend: (MessageSent<TaskStamp, TaskStamp>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): TaskStamp(title, closingType), WithHandler<TaskStamp, TaskStamp>

open class CloseTask(@JsName("close_task") val closeTask: String)

abstract class CloseTaskWithInReplyTo(closeTask: String): CloseTask(closeTask) {
    @JsName("in_reply_to") abstract val inReplyTo: LongValue
}

class CloseTaskWithHandler(
        closeTask: String,
        override val onSend: (MessageSent<CloseTaskWithInReplyTo, CloseTaskResult>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): CloseTask(closeTask), WithHandler<CloseTaskWithInReplyTo, CloseTaskResult>
