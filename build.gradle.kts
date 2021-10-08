// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
  repositories {
    google()
    mavenCentral()
  }

  dependencies {
    classpath("com.android.tools.build:gradle:7.0.2")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Libraries.kotlinVersion}")
  }
}

allprojects {
  repositories {
    google()
    mavenCentral()
  }
  tasks.withType(Javadoc::class) {
    enabled = false
  }
  tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).configureEach {
    kotlinOptions {
      allWarningsAsErrors = true
      jvmTarget = "1.8"
    }
  }
}