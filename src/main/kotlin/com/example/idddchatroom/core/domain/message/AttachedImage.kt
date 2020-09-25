package com.example.idddchatroom.core.domain.message

import com.example.idddchatroom.crossCuttingConcern.file.FileStoragePathDefinition
import com.example.idddchatroom.dddFoundation.ValueObject

/**
 * ユビキタス言語:
 * 添付画像
 */
class AttachedImage(private val path: String): ValueObject<AttachedImage.DTO> {
    companion object {
        const val PATH_FORMAT = FileStoragePathDefinition.Message.AttachedImage.filePathFormat
    }

    init {
        validateState()
    }

    private fun validateState() {
        require(PATH_FORMAT.toRegex().matches(path)) { "$path is invalid path format." }
    }

    override fun toDTO(): DTO = DTO(path = path)

    data class DTO(val path: String)
}
