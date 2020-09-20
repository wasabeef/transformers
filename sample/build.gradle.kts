plugins {
  id("com.android.application")
  kotlin("android")
  id("kotlin-android-extensions")
  id("kotlin-kapt")
}

android {
  compileSdkVersion(BuildConfig.compileSdk)

  defaultConfig {
    applicationId(BuildConfig.appId)
    minSdkVersion(BuildConfig.minSdk)
    targetSdkVersion(BuildConfig.targetSdk)
    versionCode(BuildConfig.appVersionCode)
    versionName(BuildConfig.appVersionName)

    testInstrumentationRunner = BuildConfig.testRunner
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
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
  implementation(project(Projects.Glide))
  implementation(project(Projects.GlideGPU))
  implementation(project(Projects.Picasso))
  implementation(project(Projects.Coil))
  implementation(project(Projects.Fresco))

  implementation(Libraries.kotlin)
  implementation(Libraries.appcompat)
  implementation(Libraries.recyclerview)
  implementation(Libraries.constraint)
  implementation(Libraries.coreKtx)
  implementation(Libraries.uiLayout)
  implementation(Libraries.uiMaterial)
  implementation(Libraries.uiTooling)

  implementation(Libraries.glide)
  kapt(Libraries.glideCompiler)
  implementation(Libraries.picasso)
  implementation(Libraries.coil)
  implementation(Libraries.fresco)
}
