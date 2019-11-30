# Prerequisites
  - Tomcat 9.0

# Steps
  - Change logback.xml
  - Change gradle.properties
  - Execute cmd "./gradlew clean deployWar"

# Descriptions
  - All requests will be redirected to http://localhost:8080/session/login.html before login
  - All requests will be redirected to http://localhost:8080/session/home after login