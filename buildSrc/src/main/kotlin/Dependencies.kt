object BuildConfig {
  const val compileSdk = 30

  const val appId = "jp.wasabeef.transformations"
  const val minSdk = 21
  const val targetSdk = 30
  const val appVersionCode = 1
  const val appVersionName = "1.0.0"

  const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Libraries {
  private const val kotlinVersion = "1.3.72"
  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"

  private const val appcompatVersion = "1.1.0"
  private const val coreKtxVersion = "1.1.0"
  const val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"
  const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"

  private const val uiVersion = "0.1.0-dev14"
  const val uiLayout = "androidx.ui:ui-layout:$uiVersion"
  const val uiMaterial = "androidx.ui:ui-material:$uiVersion"
  const val uiTooling = "androidx.ui:ui-tooling:$uiVersion"
}
