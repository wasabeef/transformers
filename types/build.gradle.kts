plugins {
  id("com.android.library")
  kotlin("android")
  ktlint
}

android {
  setCompileSdkVersion(BuildConfig.compileSdk)

  defaultConfig {
    minSdk = BuildConfig.minSdk
    targetSdk = BuildConfig.targetSdk

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
  implementation(Libraries.kotlin)
}

extra.apply {
  set("bintrayRepo", "maven")
  set("bintrayName", "transformers")
  set("bintrayUserOrg", "wasabeef")
  set("publishedGroupId", "jp.wasabeef.transformers")
  set("libraryName", "transformers")
  set("artifact", "types")
  set("libraryDescription", "An Android transformation library providing a variety of image transformations")
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

// TODO: Close JCenter on May 1st https://jfrog.com/blog/into-the-sunset-bintray-jcenter-gocenter-and-chartcenter/
//apply(from = "https://gist.githubusercontent.com/wasabeef/cf14805bee509baf7461974582f17d26/raw/bintray-v1.gradle")
//apply(from = "https://gist.githubusercontent.com/wasabeef/cf14805bee509baf7461974582f17d26/raw/install-v1.gradle")

apply(from = "https://gist.githubusercontent.com/wasabeef/2f2ae8d97b429e7d967128125dc47854/raw/maven-central-v1.gradle")