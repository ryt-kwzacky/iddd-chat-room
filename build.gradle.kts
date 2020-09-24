import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springBootVersion by extra("2.3.2.RELEASE")
val mysqlConnectorVersion by extra("8.0.21")

val databaseURL by extra(System.getenv("DATASOURCE_DATABASE_URL") ?: "127.0.0.1:3306/iddd_chat_room_dev?characterEncoding=UTF8&connectionCollation=utf8mb4_bin&useSSL=false")
val databaseHost by extra(System.getenv("DATASOURCE_DATABASE_HOST") ?: "127.0.0.1")
val databasePort by extra(System.getenv("DATASOURCE_DATABASE_PORT") ?: "3306")
val databaseName by extra(System.getenv("DATASOURCE_DATABASE_NAME") ?: "iddd_chat_room_dev")
val databaseUser by extra(System.getenv("DATASOURCE_DATABASE_USERNAME") ?: "root")
val databaseMigrationUser by extra(System.getenv("DATASOURCE_DATABASE_MIGRATION_USERNAME") ?: "root")
val databasePassword by extra(System.getenv("DATASOURCE_DATABASE_PASSWORD") ?: "")
val databaseQuery by extra(System.getenv("DATASOURCE_DATABASE_QUERY") ?: "?characterEncoding=UTF8&connectionCollation=utf8mb4_bin&useSSL=false")

plugins {
	id("org.springframework.boot") version "2.3.4.RELEASE"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	/**
	 * Spring Boot Starters
	 */
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.boot:spring-boot-starter-validation:$springBootVersion")

	/**
	 * Firebase Admin SDK.
	 * This is mainly used for authentication with JWT.
	 *
	 * Firebase Official Document
	 * @link https://firebase.google.com/docs/admin/setup
	 */
	implementation("com.google.firebase:firebase-admin:7.0.0")

	/**
	 * Libraries to use Kotlin.
	 */
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	/**
	 * This is to add support for serialization/deserialization of Kotlin classes and data classes.
	 *
	 * Github
	 * @link https://github.com/FasterXML/jackson-module-kotlin
	 */
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}
