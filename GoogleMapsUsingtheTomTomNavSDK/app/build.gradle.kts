plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.googlemapsusingthetomtomnavsdk"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.googlemapsusingthetomtomnavsdk"
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
}

dependencies {
    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation ("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation(libs.play.services.maps)
    implementation(libs.maps)
    implementation("com.tomtom.online:sdk-maps:2.4467")


    // ADD TOMTOM libraries here
    val version = "0.3.1014"
    implementation ("com.tomtom.sdk:maps-display:$version")
    implementation ("com.tomtom.sdk:routing-client-online:$version")
    implementation ("com.tomtom.sdk:location-android:$version")
    implementation ("com.tomtom.sdk:location-simulation:$version")
    implementation ("com.tomtom.sdk:location-mapmatched:$version")
    implementation ("com.tomtom.sdk:navigation:$version")
    implementation ("com.tomtom.sdk:navigation-ui:$version")
    implementation ("com.tomtom.sdk:navigation-dynamic-routing-online:$version")
    implementation ("com.tomtom.sdk:search-client-online:$version")
    implementation ("com.tomtom.sdk:search-ui:$version")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}