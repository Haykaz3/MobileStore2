plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.MobileStore"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.MobileStore"
        minSdk = 19
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("org.jsoup:jsoup:1.13.1")
    implementation("com.android.volley:volley:1.2.1")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-auth:22.3.0")
    implementation("com.google.firebase:firebase-firestore:24.10.3")
    implementation("androidx.vectordrawable:vectordrawable:1.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.19")
    implementation("com.airbnb.android:lottie:3.4.0")
    implementation("com.github.denzcoskun:ImageSlideshow:0.1.0")
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation ("androidx.navigation:navigation-ui-ktx:2.1.0-alpha02")
    implementation ("com.intuit.sdp:sdp-android:1.0.6")
    implementation("com.google.android.flexbox:flexbox:3.0.0")
}