package com.example.idddchatroom.core.domain.userAccount

import com.example.idddchatroom.crossCuttingConcern.file.FileStoragePathDefinition
import com.example.idddchatroom.dddFoundation.ValueObject

/**
 * ユビキタス言語:
 * アイコンイメージ
 */
class IconImage(private val path: String) : ValueObject<IconImage.DTO> {
    companion object {
        const val PATH_FORMAT = FileStoragePathDefinition.UserAccount.IconImage.filePathFormat
    }

    init {
        validateState()
    }

    private fun validateState() {
        require(PATH_FORMAT.toRegex().matches(path)) { "$path is invalid path format." }
    }

    override fun toDTO(): DTO = DTO(path)

    data class DTO(val path: String)
}
