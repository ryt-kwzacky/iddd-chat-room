package com.example.idddchatroom.core.application.userAccount

import com.example.idddchatroom.core.application.userAccount.command.CreateUserAccountCommand
import com.example.idddchatroom.core.domain.userAccount.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

// TODO: delete comment out after Impl JooqRepository
//@Service
class CreateUserAccountCommandHandler(
    @Autowired private val userAccountRepository: UserAccountRepository
) {
    fun handle(
        command: CreateUserAccountCommand
    ): UniversalUserId {
        val newUserAccount = UserAccount.create(
            universalUserId = userAccountRepository.nextIdentity(),
            userName = command.userName,
            iconImage = command.iconImage
        )
        userAccountRepository.store(newUserAccount)
        return newUserAccount.id
    }
}
// 選択した画像はいったんアップロードされる
// アップロードしたパスを返す
// ここで登録する
// ↑のやり方だと画像を切り替えるたびに関係ない画像がいっぱい残る

// サーバーを通さないのが主流
// S3に直接アップロードする