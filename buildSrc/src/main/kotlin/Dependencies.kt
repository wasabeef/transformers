object BuildConfig {
  const val compileSdk = 31

  const val appId = "jp.wasabeef.transformers"
  const val minSdk = 21
  const val targetSdk = 31
  const val appVersionCode = 3
  const val appVersionName = "1.0.5"

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

  object FromRepo {
    const val transformersVersion = "1.0.5"
    const val glide = "jp.wasabeef.transformers:glide:$transformersVersion"
    const val glideGpu = "jp.wasabeef.transformers:glide-gpu:$transformersVersion"
    const val picasso = "jp.wasabeef.transformers:picasso:$transformersVersion"
    const val picassoGpu = "jp.wasabeef.transformers:picasso-gpu:$transformersVersion"
    const val coil = "jp.wasabeef.transformers:coil:$transformersVersion"
    const val coilGpu = "jp.wasabeef.transformers:coil-gpu:$transformersVersion"
    const val fresco = "jp.wasabeef.transformers:fresco:$transformersVersion"
    const val frescoGpu = "jp.wasabeef.transformers:fresco-gpu:$transformersVersion"
  }
}

object Ktlint {
  const val plugin = "com.pinterest:ktlint:0.42.1"
}

object Libraries {
  const val kotlinVersion = "1.5.31"
  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"

  private const val appcompatVersion = "1.3.1"
  private const val coreKtxVersion = "1.6.0"
  const val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"
  const val recyclerview = "androidx.recyclerview:recyclerview:1.2.1"
  const val constraint = "androidx.constraintlayout:constraintlayout:2.1.1"
  const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
  const val annotation = "androidx.annotation:annotation:1.2.0"

  const val picasso = "com.squareup.picasso:picasso:2.8"
  private const val glideVersion = "4.12.0"
  const val glide = "com.github.bumptech.glide:glide:$glideVersion"
  const val glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"
  const val coil = "io.coil-kt:coil:1.4.0"
  const val fresco = "com.facebook.fresco:fresco:2.5.0"
  const val gpuImage = "jp.co.cyberagent.android:gpuimage:2.1.0"
}
