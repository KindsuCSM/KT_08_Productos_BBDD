plugins {
    // 2 - Comentar tambien alias : android.application
    //alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // 1 - Agregamos alias: googleServices
    //alias(libs.plugins.googleServices)

    // 2 - Quitamos/Comentamos el paso 1 y agregamos el alias que nos proporciona firebase
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.kt_08_productos_bbdd"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kt_08_productos_bbdd"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
    viewBinding {
        enable = true
    }
}

dependencies {
    // 1 - Implementamos firebase (Sincronizamos)
    implementation(platform(libs.firebase.bom))

    // 2 - Añadir implementations de firebase
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation("com.google.firebase:firebase-analytics")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}