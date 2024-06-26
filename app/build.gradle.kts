plugins {
    id("com.android.application")
}

android {
    namespace = "py.edu.githubsearch"
    compileSdk = 34

    defaultConfig {
        applicationId = "py.edu.githubsearch"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

    dependencies {
        implementation("androidx.appcompat:appcompat:1.6.1")
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")
        implementation("com.google.android.material:material:1.8.0")
        implementation("com.squareup.retrofit:retrofit:1.9.0")
            implementation("com.squareup.picasso:picasso:2.8")
        implementation("de.hdodenhof:circleimageview:3.1.0")
        implementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("junit:junit:4.12")
        androidTestImplementation("junit:junit:4.12")
    }
