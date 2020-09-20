plugins {
  id("com.android.library")
  kotlin("android")
  id("kotlin-kapt")
}

android {
  compileSdkVersion(BuildConfig.compileSdk)

  defaultConfig {
    minSdkVersion(BuildConfig.minSdk)
    targetSdkVersion(BuildConfig.targetSdk)
    versionCode(BuildConfig.appVersionCode)
    versionName(BuildConfig.appVersionName)

    testInstrumentationRunner = BuildConfig.testRunner
    consumerProguardFile("consumer-rules.pro")
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  api(project(Projects.Core))

  implementation(Libraries.kotlin)
  implementation(Libraries.glide)
  kapt(Libraries.glideCompiler)
}
