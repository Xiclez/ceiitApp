plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    compileSdk = 34 // Actualizado para utilizar el SDK 34

    defaultConfig {
        applicationId = "com.example.ceiitApp"
        minSdk = 21 // Puedes ajustar esto si lo necesitas
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }

    packagingOptions {
        excludes += "META-INF/AL2.0"
        excludes += "META-INF/LGPL2.1"
    }
}

dependencies {

    // App dependencies
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)
    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.annotation)
    implementation(libs.coil.compose.v200)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.timber)
    implementation(libs.androidx.core)

    // Jetpack Compose
    val composeBom = platform(libs.androidx.compose.bom)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.compiler)
    implementation(composeBom)
    implementation(libs.androidx.compose.foundation.core)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.material.core)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.navigation.compose)

    debugImplementation(composeBom)
    debugImplementation(libs.androidx.compose.ui.tooling.core)

}
