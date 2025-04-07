plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "dev.runo.core"
    compileSdk = 35
    buildFeatures.buildConfig = true

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            buildConfigField("String", "API_URL", "\"https://mydomain.com/api\"")

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "API_URL", "\"https://remedia.local/api/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {
    api(libs.androidx.core.ktx)

    implementation(libs.dagger.lib)
    ksp(libs.dagger.compiler)
    implementation(libs.hilt.lib)
    ksp(libs.hilt.compiler)

    api(libs.datastore)

    api(libs.retrofit.core)
    api(libs.retrofit.gson)
    api(libs.gson)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}