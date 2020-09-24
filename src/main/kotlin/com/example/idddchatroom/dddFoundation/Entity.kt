package com.example.idddchatroom.dddFoundation

abstract class Entity<DTO> {
    abstract val id: AbstractId

    abstract fun toDTO(): DTO

    override fun equals(other: Any?): Boolean {
        if (other !== null && other::class.java == this::class.java)
            return (other as Entity<*>).id == this.id
        return false
    }

    override fun hashCode(): Int {
        /**
         * 計算で使う奇数の素数（慣習として 31 を使うことが一般的。）
         */
        val prime = 31

        /**
         * ハッシュ値が他クラスと被らないようにするための適当な数字。
         */
        val result = 123

        return prime * result + this.id.hashCode()
    }
}
