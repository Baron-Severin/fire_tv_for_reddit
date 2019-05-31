plugins {
    id("com.android.library")
    id("kotlin-android-extensions")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(BuildConstants.compileSdkVersion)
    defaultConfig {
        minSdkVersion(BuildConstants.minSdkVersion)
        targetSdkVersion(BuildConstants.targetSdkVersion)
        versionCode = BuildConstants.versionCode
        versionName = BuildConstants.versionName
        testInstrumentationRunner = BuildConstants.testInstrumentationRunner
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = BuildConstants.minifyEnabled
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dependencies.kotlin_stdlib)

    implementation(Dependencies.appCompat)

    implementation(Dependencies.dagger)
    kapt(Dependencies.dagger_apt)

    testImplementation(Dependencies.robolectric)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.testRunner)
    androidTestImplementation(Dependencies.espressoCore)
}