package com.example.idddchatroom.sharedKernel

//import com.example.demo.domain.RandomIdentityFactory
//import org.jooq.DSLContext
//import org.jooq.Table
//import org.jooq.TableField
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Component
//
//@Component
//class JooqUniqueIdentityFactory(@Autowired private val dslContext: DSLContext) {
//    companion object {
//        /**
//         * RandomIdentityFactory creates enough long value.
//         * So JooqUniqueIdentityFactory typically doesn't need to try to re-create many times,
//         * like more than 100 times or infinite.
//         */
//        private const val NUMBER_OF_TRIALS = 5
//    }
//
//    fun create(table: Table<*>, field: TableField<*, String>): String {
//        for (i in 0..NUMBER_OF_TRIALS) {
//            val randomIdentity = RandomIdentityFactory.create()
//
//            /**
//             * randomIdentity is returned only when it hasn't been used.
//             */
//            if (dslContext.select().from(table).where(field.eq(randomIdentity)).count() == 0) {
//                return randomIdentity
//            }
//        }
//        throw IllegalStateException("Failed to create unique identity in JooqUniqueIdentityFactory.")
//    }
//}
