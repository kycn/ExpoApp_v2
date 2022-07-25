apply(plugin = "com.android.application")
apply(plugin = "dagger.hilt.android.plugin")

plugins {
    kotlin("kapt")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.expoapp_v2"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.bundles.androidx.lifecycle)
    implementation(libs.bundles.androidx.compose)

    implementation(libs.io.coil.compose)

    implementation(libs.google.accompanist.swipe.refresh)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.bundles.square.retrofit)

    testImplementation(libs.testing.coroutines)
    testImplementation(libs.testing.junit)
    testImplementation(libs.testing.mockk)
    testImplementation(libs.testing.turbine)

    debugImplementation(libs.testing.compose.manifest)

    androidTestImplementation(libs.testing.androidx.junit)
    androidTestImplementation(libs.testing.espresso.core)
    androidTestImplementation(libs.testing.compose.ui)
}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}