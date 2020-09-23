plugins {
  id("com.android.library")
  kotlin("android")
  ktlint
}

android {
  compileSdkVersion(BuildConfig.compileSdk)

  defaultConfig {
    minSdkVersion(BuildConfig.minSdk)
    targetSdkVersion(BuildConfig.targetSdk)

    buildConfigField("String", "Version", "\"${BuildConfig.appVersionName}\"")

    testInstrumentationRunner = BuildConfig.testRunner
    consumerProguardFile("consumer-rules.pro")
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}

dependencies {
  implementation(project(Projects.core))
  api(project(Projects.types))

  implementation(Libraries.kotlin)
  implementation(Libraries.fresco)
  implementation(Libraries.annontation)
}

extra.apply {
  set("bintrayRepo", "maven")
  set("bintrayName", "transformers")
  set("bintrayUserOrg", "wasabeef")
  set("publishedGroupId", "jp.wasabeef.transformers")
  set("libraryName", "transformers")
  set("artifact", "fresco")
  set("libraryDescription", "An Android transformation library providing a variety of image transformations for Fresco.")
  set("siteUrl", "https://github.com//wasabeef/transformers")
  set("gitUrl", "https://github.com//wasabeef/transformers.git")
  set("issueUrl", "https://github.com//wasabeef/transformers/issues")
  set("libraryVersion", BuildConfig.appVersionName)
  set("developerId", "wasabeef")
  set("developerName", "Daichi Furiya")
  set("developerEmail", "dadadada.chop@gmail.com")
  set("licenseName", "The Apache Software License, Version 2.0")
  set("licenseUrl", "http://www.apache.org/licenses/LICENSE-2.0.txt")
  set("allLicenses", arrayOf("Apache-2.0"))
}

apply(from = "https://gist.githubusercontent.com/wasabeef/cf14805bee509baf7461974582f17d26/raw/bintray-v1.gradle")
apply(from = "https://gist.githubusercontent.com/wasabeef/cf14805bee509baf7461974582f17d26/raw/install-v1.gradle")
