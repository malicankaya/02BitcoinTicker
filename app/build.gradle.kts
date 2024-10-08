plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    id ("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.malicankaya.a02bitcointicker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.malicankaya.a02bitcointicker"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String","API_KEY","${project.findProperty("COINGECKO_API_KEY")}")
        buildConfigField("String","WEB_CLIENT_ID","${project.findProperty("WEB_CLIENT_ID")}")
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
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.swiperefreshlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    val nav_version = "2.8.0"
    val room_version = "2.6.1"
    val paging_version = "3.3.2"
    val retrofit_version = "2.11.0"
    val okhttp_version = "4.12.0"
    val glide_version = "4.16.0"
    val credentialManager_version = "1.2.2"
    val googleId_version = "1.1.1"
    val kotlinstdlib_version = "2.0.20"

    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinstdlib_version")

    //navigation
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

    //room
    implementation("androidx.room:room-runtime:$room_version")
    //ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    implementation("androidx.room:room-rxjava3:$room_version")
    implementation("androidx.room:room-paging:$room_version")

    //paging
    implementation("androidx.paging:paging-runtime:$paging_version")
    implementation("androidx.paging:paging-rxjava3:$paging_version")

    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit_version")

    //logging interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttp_version")

    //glide
    implementation ("com.github.bumptech.glide:glide:$glide_version")

    //firebase
    implementation(platform("com.google.firebase:firebase-bom:33.3.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.android.gms:play-services-auth:21.2.0")
    implementation("com.google.firebase:firebase-firestore")

    //google sign in
    implementation ("androidx.credentials:credentials:$credentialManager_version")
    implementation ("androidx.credentials:credentials-play-services-auth:$credentialManager_version")
    implementation ("com.google.android.libraries.identity.googleid:googleid:$googleId_version")
}