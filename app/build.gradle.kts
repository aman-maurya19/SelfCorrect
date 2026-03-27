plugins {
    alias(libs.plugins.android.application)
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")

}

android {
    namespace = "com.am.sarojselfcorrect"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.am.sarojselfcorrect"
        minSdk = 24
        targetSdk = 36
        versionCode = 2
        versionName = "2.0"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.common.jvm)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("androidx.recyclerview:recyclerview:1.3.2")


    // 🔹 ROOM Database
    implementation("androidx.room:room-runtime:2.7.0-beta01")
    implementation("androidx.room:room-ktx:2.7.0-beta01")
    kapt("androidx.room:room-compiler:2.7.0-beta01")

}