plugins {
    alias(libs.plugins.android.application)

}

android {
    namespace ="hk.hkucs.financial_news"
    compileSdk = 35

    defaultConfig {
        applicationId ="hk.hkucs.financial_news"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName ="1.0"

        testInstrumentationRunner ="androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.drawerlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


    // Retrofit for API calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp for logging (optional, for debugging)
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")

    // Glide for image loading
    implementation("com.github.bumptech.glide:glide:4.14.2")
    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")

    // RecyclerView for lists
    implementation("androidx.recyclerview:recyclerview:1.3.0")

    // Material Design for modern UI
    implementation("com.google.android.material:material:1.8.0")


    implementation ("androidx.core:core:1.12.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.11.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")

    // Jetpack Compose
    implementation ("androidx.compose.ui:ui:1.6.0")
    implementation ("androidx.compose.material:material:1.6.0")
    implementation ("androidx.compose.material:material-icons-extended:1.6.0")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.6.0")
    implementation ("androidx.activity:activity-compose:1.8.0")
    implementation ("androidx.navigation:navigation-compose:2.7.7")
    implementation ("com.github.bumptech.glide:compose:1.0.0-beta01")

    // Existing dependencies
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.github.bumptech.glide:glide:4.15.1")

    // For Compose compatibility
    implementation ("androidx.compose.runtime:runtime-livedata:1.6.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    implementation ("androidx.core:core:1.12.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.11.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation ("androidx.cardview:cardview:1.0.0")

}