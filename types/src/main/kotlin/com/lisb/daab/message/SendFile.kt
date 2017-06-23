package com.lisb.daab.message

import com.lisb.daab.LongValue
import com.lisb.daab.MessageSent
import com.lisb.daab.User
import com.lisb.daab.WithHandler

open class SendFile(
        val path: String,
        val name: String? = null,
        val type: String? = null)

class SendFileWithHandler(
        path: String,
        name: String? = null,
        type: String? = null,
        override val onSend: ((MessageSent<SendFile, SendFileContent>) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): SendFile(path, name, type), WithHandler<SendFile, SendFileContent>

interface SendFileContent {
    @JsName("file_id") val fileId: LongValue
    val url: String
    val name: String?
    @JsName("content_type") val type: String?
    @JsName("content_size") val size: Int
}

open class SendFiles(
        val path: Array<String>,
        val name: Array<String>? = null,
        val type: Array<String>? = null,
        val text: String? = null)

class SendFilesWithHandler(
        path: Array<String>,
        name: Array<String>? = null,
        type: Array<String>? = null,
        text: String? = null,
        override val onSend: ((MessageSent<SendFiles, SendFilesContent>) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): SendFiles(path, name, type, text), WithHandler<SendFiles, SendFilesContent>

interface SendFilesContent {
    val files: Array<SendFileContent>
    val text: String?
}

open class SendFileWithMessage(
        val path: String,
        val text: String,
        val name: String? = null,
        val type: String? = null)

class SendFileWithMessageAndHandler(
        path: String,
        text: String,
        type: String? = null,
        name: String? = null,
        override val onSend: ((MessageSent<SendFileWithMessage, SendFilesContent>) -> Unit)? = null,
        override val onRead: ((Array<User>, Array<User>, Array<User>) -> Unit)? = null
): SendFileWithMessage(path, text, name, type), WithHandler<SendFileWithMessage, SendFilesContent>
