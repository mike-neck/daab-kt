package com.lisb.daab.message

import com.lisb.daab.LongValue
import com.lisb.daab.MessageSent
import com.lisb.daab.User
import com.lisb.daab.WithHandler

open class SendFile(
        val path: String,
        val name: String?,
        val type: String?,
        val contentSize: Int?)

class SendFileWithHandler(
        path: String,
        name: String?,
        type: String?,
        contentSize: Int?,
        override val onSend: (MessageSent<SendFile, SendFileContent>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): SendFile(path, name, type, contentSize), WithHandler<SendFile, SendFileContent>

interface SendFileContent {
    @JsName("file_id") val fileId: LongValue
    val url: String
    val name: String?
    @JsName("content_type") val type: String?
    @JsName("content_size") val size: Int
}

open class SendFiles(
        val path: Array<String>,
        val name: Array<String>?,
        val type: Array<String>?,
        val text: String?)

// TODO it's inspecting now
class SendFilesWithHandler(
        path: Array<String>,
        name: Array<String>?,
        type: Array<String>?,
        text: String?,
        override val onSend: (MessageSent<SendFiles>) -> Unit,
        override val onRead: (Array<User>, Array<User>, Array<User>) -> Unit
): SendFiles(path, name, type, text), WithHandler<SendFiles>