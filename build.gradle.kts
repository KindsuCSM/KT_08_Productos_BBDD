// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    // 1 - Añadimos alias googleServices : apply false (Sincronizamos)
    //alias(libs.plugins.googleServices) apply false

    // 2 - Quitamos el 1 y agregamos codigo que nos proporciona firebase (Sincronizamos)
    id("com.google.gms.google-services") version "4.4.2" apply false
}