plugins {
  id("com.android.application")
  kotlin("android")
  id("kotlin-kapt")
  ktlint
}

android {
  setCompileSdkVersion(BuildConfig.compileSdk)

  defaultConfig {
    applicationId = BuildConfig.appId
    minSdk = BuildConfig.minSdk
    targetSdk = BuildConfig.targetSdk
    versionCode = BuildConfig.appVersionCode
    versionName = BuildConfig.appVersionName

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
}

repositories {
  // maven(url = "https://dl.bintray.com/wasabeef/maven/")
  maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
  implementation(project(Projects.glide))
  implementation(project(Projects.glideGpu))
  implementation(project(Projects.picasso))
  implementation(project(Projects.picassoGpu))
  implementation(project(Projects.coil))
  implementation(project(Projects.coilGpu))
  implementation(project(Projects.fresco))
  implementation(project(Projects.frescoGpu))
//  implementation(Projects.FromRepo.glide)
//  implementation(Projects.FromRepo.glideGpu)
//  implementation(Projects.FromRepo.picasso)
//  implementation(Projects.FromRepo.picassoGpu)
//  implementation(Projects.FromRepo.coil)
//  implementation(Projects.FromRepo.coilGpu)
//  implementation(Projects.FromRepo.fresco)
//  implementation(Projects.FromRepo.frescoGpu)
//
  implementation(Libraries.kotlin)
  implementation(Libraries.appcompat)
  implementation(Libraries.recyclerview)
  implementation(Libraries.constraint)
  implementation(Libraries.coreKtx)

  implementation(Libraries.glide)
  kapt(Libraries.glideCompiler)
  implementation(Libraries.picasso)
  implementation(Libraries.coil)
  implementation(Libraries.fresco)
}
