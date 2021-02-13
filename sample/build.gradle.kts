plugins {
  id("com.android.application")
  kotlin("android")
  id("kotlin-kapt")
  ktlint
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
}

repositories {
  // maven(url = "https://dl.bintray.com/wasabeef/maven/")
  // maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
//  implementation(project(Projects.glide))
//  implementation(project(Projects.glideGpu))
//  implementation(project(Projects.picasso))
//  implementation(project(Projects.picassoGpu))
//  implementation(project(Projects.coil))
//  implementation(project(Projects.coilGpu))
//  implementation(project(Projects.fresco))
//  implementation(project(Projects.frescoGpu))
  implementation(Projects.FromBintray.glide)
  implementation(Projects.FromBintray.glideGpu)
  implementation(Projects.FromBintray.picasso)
  implementation(Projects.FromBintray.picassoGpu)
  implementation(Projects.FromBintray.coil)
  implementation(Projects.FromBintray.coilGpu)
  implementation(Projects.FromBintray.fresco)
  implementation(Projects.FromBintray.frescoGpu)

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
