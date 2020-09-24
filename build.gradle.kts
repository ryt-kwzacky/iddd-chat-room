import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springBootVersion by extra("2.3.2.RELEASE")
val mysqlConnectorVersion by extra("8.0.21")

val projectName by extra("idddchatroom")
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
	id("org.flywaydb.flyway") version "6.5.5"
	id("nu.studer.jooq") version "5.0.2"
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
	 * Java Database Connectivity (JDBC) is an application programming interface (API) for the programming language Java,
	 * which defines how a client may access a database.
	 * This is used for migration, etc.
	 */
	implementation("org.springframework.boot:spring-boot-starter-jdbc:$springBootVersion")

	/**
	 * This is JDBC Type 4 driver for MySQL
	 *
	 * mysql-connector-j - Github
	 * @link https://github.com/mysql/mysql-connector-j
	 */
	implementation("mysql:mysql-connector-java:$mysqlConnectorVersion")
	jooqGenerator("mysql:mysql-connector-java:${mysqlConnectorVersion}")

	/**
	 * A migration tool.
	 *
	 * Flyway Document
	 * @link https://flywaydb.org/documentation/
	 */
	implementation("org.flywaydb:flyway-core:6.5.5")

	/**
	 * jOOQ, a fluent API for typesafe SQL query construction and execution.
	 *
	 * Documentation - JOOQ
	 * @link https://www.jooq.org/
	 *
	 * Github
	 * @link https://github.com/spring-projects/spring-boot/blob/master/spring-boot-project/spring-boot-starters/spring-boot-starter-jooq/build.gradle
	 */
	implementation("org.springframework.boot:spring-boot-starter-jooq:${springBootVersion}")

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

/**
 * Flyway is a migration tool.
 *
 * Gradle Task: flywayMigrate
 * @link https://flywaydb.org/documentation/gradle/migrate
 */
flyway {
	// The jdbc url to use to connect to the database
	url = "jdbc:mysql://${databaseURL}"
	// The user to use to connect to the database
	user = databaseMigrationUser
	// The password to use to connect to the database
	password = databasePassword
	/**
	 * Locations to scan recursively for migrations.
	 * We use plain SQL files for migrating DB schema and java based ones for migrating data.
	 */
	locations = arrayOf("filesystem:src/main/resources/db/migration")
	// Allows migrations to be run "out of order".
	// If you already have versions 1 and 3 applied, and now a version 2 is found, it will be applied too instead of being ignored.
}

jooq {
	version.set("3.13.4")  // default (can be omitted)
	edition.set(nu.studer.gradle.jooq.JooqEdition.OSS)  // default (can be omitted)

	configurations {
		create("main") {  // name of the jOOQ configuration
			generateSchemaSourceOnCompilation.set(true)  // default (can be omitted)

			jooqConfiguration.apply {
				logging = org.jooq.meta.jaxb.Logging.DEBUG
				jdbc.apply {
					driver = "com.mysql.cj.jdbc.Driver"
					url = "jdbc:mysql://${databaseURL}"
					user = databaseUser
					password = databasePassword
				}
				generator.apply {
					name = "org.jooq.codegen.DefaultGenerator"
					database.apply {
						name = "org.jooq.meta.mysql.MySQLDatabase"
						inputSchema = databaseName
						includes = ".*"
						excludes = "flyway_schema_history"
						isOutputSchemaToDefault = true
					}
					generate.apply {
						isRecords = false
					}
					target.apply {
						/**
						 * Package name for generated files.
						 */
						packageName = "com.example.$projectName.db.generatedJooqCode"
						/**
						 * Directory to put generated files on.
						 */
						directory = "src/main/kotlin"
					}
					/**
					 * This strategy is to add word "J" as prefix to each generated classes.
					 * It prevents name conflict from other ones made by developers.
					 */
					strategy.name = "org.jooq.codegen.example.JPrefixGeneratorStrategy"
				}
			}
		}
	}
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
