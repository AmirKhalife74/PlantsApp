

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id ("dagger.hilt.android.plugin")

}

android {
    namespace = "com.example.plantsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.plantsapp"
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
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}


dependencies {
    implementation(libs.smoothbottombar)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)

    //Dependency injection
    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)

    //Retrofit
    implementation (libs.retrofit)

    implementation (libs.retrofit)
    implementation (libs.converter.moshi)
    implementation (libs.moshi.kotlin)
    //noinspection KaptUsageInsteadOfKsp
    kapt (libs.moshi.kotlin.codegen)

    //Glide
    implementation (libs.glide)

    //Ok Http
    implementation (libs.okhttp)

    //Lottie
    implementation(libs.lottie)

    implementation (libs.gson)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.fragment)
    //noinspection GradleDependency
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //Room
    implementation (libs.androidx.room.runtime)
    implementation (libs.material)
    //noinspection KaptUsageInsteadOfKsp
    kapt (libs.androidx.room.compiler)  // For Kotlin projects
    implementation (libs.androidx.room.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}