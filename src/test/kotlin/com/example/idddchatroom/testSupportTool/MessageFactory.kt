package com.example.idddchatroom.testSupportTool

import com.example.idddchatroom.core.domain.message.*
import com.example.idddchatroom.core.domain.room.RoomId
import com.example.idddchatroom.crossCuttingConcern.file.FileStoragePathDefinition
import com.example.idddchatroom.sharedKernel.RandomIdentityFactory

object MessageFactory {
    fun genMessage(
        id: MessageId = genMessageId(),
        text: MessageText = genText(),
        image: AttachedImage = genImage(),
        roomId: RoomId,
        sender: MessageSender,
        targetMessageId: MessageId?
    ): Message = Message.create(
        id = id,
        text = text,
        image = image,
        roomId = roomId,
        sender = sender,
        targetMessageId = targetMessageId
    )

    fun genMessageId(): MessageId = MessageId(RandomIdentityFactory.create())
    fun genText(): MessageText = MessageText(TestDataGenerator.genRandomLengthString(1..1000))
    fun genImage(): AttachedImage =
        AttachedImage(
            FileStoragePathDefinition.Message.AttachedImage.baseDirectoryPath +
                TestDataGenerator.genRandomFileName() +
                ".jpeg"
        )
}
