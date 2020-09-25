package com.example.idddchatroom.crossCuttingConcern.file

object FileStoragePathDefinition {
    private const val fileNameFormat =
        """[a-fA-F0-9]{32}+"""

    private const val allowedImageExtensions = "PNG|png|GIF|gif|JPG|jpg|JPEG|jpeg"
    private const val allowedFileExtensions = "$allowedImageExtensions|PDF|pdf"

    private const val imageUniquePartFormat =
        """$fileNameFormat\.($allowedImageExtensions)"""
    private const val fileUniquePartFormat =
        """$fileNameFormat\.($allowedFileExtensions)"""

    /**
     * Definition of file in UserAccount Aggregate
     */
    object UserAccount {
        private const val aggregateName = "user-account"

        object UserProfileImage {
            /**
             * Private fields
             */
            private const val directoryName = "icon-image"

            /**
             * Public fields
             */
            const val baseDirectoryPath = "$aggregateName/$directoryName/"
            const val filePathFormat =
                """^$baseDirectoryPath$imageUniquePartFormat$"""
        }
    }

    /**
     * Definition of file in Message Aggregate
     */
    object UserProfile {
        private const val aggregateName = "message"

        object OutputFile {
            /**
             * Private fields
             */
            private const val directoryName = "attached-image"

            /**
             * Public fields
             */
            const val baseDirectoryPath = "$aggregateName/$directoryName/"
            const val filePathFormat =
                """^$baseDirectoryPath$fileUniquePartFormat$"""
        }
    }
}
