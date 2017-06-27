package com.lisb.daab.message

import com.lisb.daab.*

open class TaskStamp(val title: String, @JsName("closing_type") val closingType: Int)

class TaskStampAfterMessageHandler(
        title: String,
        closingType: Int,
        override val onSend: ((MessageSent<TaskStamp, TaskStamp>) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): TaskStamp(title, closingType), AfterMessageHandler<TaskStamp, TaskStamp>

open class CloseTask(@JsName("close_task") val closeTask: String)

external interface AfterTaskHandler: OnReadHandler {
    @JsName("onsend") val onSend: ((TaskSent) -> Unit)?
}

external interface TaskSent: MessageSent<CloseTaskWithInReplyTo, CloseTaskResult> {
    fun answer(taskStatus: (Array<User>, Array<User>) -> Unit): Unit
}

abstract class CloseTaskWithInReplyTo(closeTask: String): CloseTask(closeTask) {
    @JsName("in_reply_to") abstract val inReplyTo: LongValue
}

class CloseTaskAfterMessageHandler(
        closeTask: String,
        override val onSend: ((TaskSent) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): CloseTask(closeTask), AfterTaskHandler

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

class AnswerTaskAfterMessageHandler(
        inReplyTo: String,
        done: Boolean = true,
        override val onSend: ((MessageSent<AnsweredTask, AnswerTaskResult>) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): AnswerForTask(inReplyTo, done), AfterMessageHandler<AnsweredTask, AnswerTaskResult>
