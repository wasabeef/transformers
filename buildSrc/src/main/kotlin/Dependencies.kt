object BuildConfig {
  const val compileSdk = 30

  const val appId = "jp.wasabeef.transformers"
  const val minSdk = 21
  const val targetSdk = 30
  const val appVersionCode = 1
  const val appVersionName = "1.0.0"

  const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Projects {
  const val core = ":core"
  const val types = ":types"
  const val glide = ":transformers:glide"
  const val glideGpu = ":transformers:glide-gpu"
  const val picasso = ":transformers:picasso"
  const val picassoGpu = ":transformers:picasso-gpu"
  const val coil = ":transformers:coil"
  const val coilGpu = ":transformers:coil-gpu"
  const val fresco = ":transformers:fresco"
  const val frescoGpu = ":transformers:fresco-gpu"
}

object Ktlint {
  const val plugin = "com.pinterest:ktlint:0.39.0"
}

object Libraries {
  const val kotlinVersion = "1.4.10"
  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"

  private const val appcompatVersion = "1.2.0"
  private const val coreKtxVersion = "1.2.0"
  const val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"
  const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
  const val constraint = "androidx.constraintlayout:constraintlayout:2.0.1"
  const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
  const val annontation = "androidx.annotation:annotation:1.2.0-alpha01"

  object Compose {
    const val composeVersion = "1.0.0-alpha03"
    const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
    const val foundation = "androidx.compose.foundation:foundation:${composeVersion}"
    const val layout = "androidx.compose.foundation:foundation-layout:${composeVersion}"

    const val ui = "androidx.compose.ui:ui:${composeVersion}"
    const val material = "androidx.compose.material:material:${composeVersion}"

    const val tooling = "androidx.ui:ui-tooling:${composeVersion}"
  }


  const val picasso = "com.squareup.picasso:picasso:2.8"
  private const val glideVersion = "4.11.0"
  const val glide = "com.github.bumptech.glide:glide:$glideVersion"
  const val glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"
  const val coil = "io.coil-kt:coil:0.13.0"
  const val fresco = "com.facebook.fresco:fresco:2.3.0"
  const val gpuImage = "jp.co.cyberagent.android:gpuimage:2.0.4"
}
