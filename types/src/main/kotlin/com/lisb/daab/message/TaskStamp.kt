package com.lisb.daab.message

import com.lisb.daab.*

open class TaskStamp(val title: String, @JsName("closing_type") val closingType: Int)

class TaskStampWithHandler(
        title: String,
        closingType: Int,
        override val onSend: ((MessageSent<TaskStamp, TaskStamp>) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): TaskStamp(title, closingType), WithHandler<TaskStamp, TaskStamp>

open class CloseTask(@JsName("close_task") val closeTask: String)

abstract class CloseTaskWithInReplyTo(closeTask: String): CloseTask(closeTask) {
    @JsName("in_reply_to") abstract val inReplyTo: LongValue
}

class CloseTaskWithHandler(
        closeTask: String,
        override val onSend: ((MessageSent<CloseTaskWithInReplyTo, CloseTaskResult>) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): CloseTask(closeTask), WithHandler<CloseTaskWithInReplyTo, CloseTaskResult>

external interface ReceivedTask {
    val title: String
    @JsName("closing_type") val closingType: Int
}

open class AnswerForTask(@JsName("in_reply_to") val inReplyTo: String, val done: Boolean = true)

external interface AnsweredTask {
    @JsName("in_reply_to") val inReplyTo: LongValue
    val done: Boolean
}

external interface AnswerTaskResult: ReceivedTask, AnsweredTask

class AnswerTaskWithHandler(
        inReplyTo: String,
        done: Boolean = true,
        override val onSend: ((MessageSent<AnsweredTask, AnswerTaskResult>) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): AnswerForTask(inReplyTo, done), WithHandler<AnsweredTask, AnswerTaskResult>
