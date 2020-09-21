object BuildConfig {
  const val compileSdk = 30

  const val appId = "jp.wasabeef.transformations"
  const val minSdk = 21
  const val targetSdk = 30
  const val appVersionCode = 1
  const val appVersionName = "1.0.0"

  const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Projects {
  const val core = ":core"
  const val types = ":types"
  const val glide = ":transformations:glide"
  const val glideGpu = ":transformations:glide-gpu"
  const val picasso = ":transformations:picasso"
  const val picassoGpu = ":transformations:picasso-gpu"
  const val coil = ":transformations:coil"
  const val coilGpu = ":transformations:coil-gpu"
  const val fresco = ":transformations:fresco"
  const val frescoGpu = ":transformations:fresco-gpu"
}

object Ktlint {
  const val plugin = "com.pinterest:ktlint:0.39.0"
}

object Libraries {
  const val kotlinVersion = "1.3.72"
  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"

  private const val appcompatVersion = "1.1.0"
  private const val coreKtxVersion = "1.1.0"
  const val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"
  const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
  const val constraint = "androidx.constraintlayout:constraintlayout:2.0.1"
  const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
  const val annontation = "androidx.annotation:annotation:1.2.0-alpha01"

  private const val uiVersion = "0.1.0-dev14"
  const val uiLayout = "androidx.ui:ui-layout:$uiVersion"
  const val uiMaterial = "androidx.ui:ui-material:$uiVersion"
  const val uiTooling = "androidx.ui:ui-tooling:$uiVersion"

  const val picasso = "com.squareup.picasso:picasso:2.71828"
  private const val glideVersion = "4.11.0"
  const val glide = "com.github.bumptech.glide:glide:$glideVersion"
  const val glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"
  const val coil = "io.coil-kt:coil:0.13.0"
  const val fresco = "com.facebook.fresco:fresco:2.3.0"
  const val gpuImage = "jp.co.cyberagent.android:gpuimage:2.0.4"
}
