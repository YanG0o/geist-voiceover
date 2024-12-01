// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
//    alias(libs.plugins.kotlin.compose) apply false
}
ext{
    rootProject.ext.set("isLoginModule",true)
    val isLoginModule = false
    val isRLM1Module = false
    val isRLM2Module = false
}