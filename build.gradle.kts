plugins {
    id("java")
    id("application")
    id ("test-report-aggregation")
}

group = "org.libertex.testtask"
version = "1.0-SNAPSHOT"
java {
    sourceCompatibility = JavaVersion.VERSION_11
}
repositories {
    mavenCentral()
}
dependencies {
    implementation("org.json:json:20230227")
    testImplementation ("org.testng:testng:7.7.0")
    implementation ("org.slf4j:slf4j-api:2.0.9")
    implementation ("org.slf4j:slf4j-log4j12:2.0.9")
    implementation("ch.qos.logback:logback-classic:1.2.9")
    implementation("ch.qos.logback:logback-core:1.2.9")

    // testImplementation("com.codeborne:selenide:7.0.2")

}


tasks.test {
    useTestNG() {
        outputDirectory = file("$buildDir/test-output")
        parallel = "methods"
        threadCount = 10
    }
}


