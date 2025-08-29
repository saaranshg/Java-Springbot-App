@echo off
echo ================================================
echo Java JDK Installation Check
echo ================================================
echo.

echo Checking for Java installation...
java -version 2>nul
if %errorlevel% neq 0 (
    echo ❌ Java is NOT installed or not in PATH
    echo.
    echo To fix this:
    echo 1. Download Java JDK 17+ from: https://adoptium.net/temurin/releases/?version=17
    echo 2. Install the JDK
    echo 3. Set JAVA_HOME environment variable
    echo 4. Add %%JAVA_HOME%%\bin to PATH
    echo 5. Restart your terminal
    echo.
    goto end
) else (
    echo ✅ Java is installed!
)

echo.
echo Checking JAVA_HOME environment variable...
if "%JAVA_HOME%"=="" (
    echo ❌ JAVA_HOME is NOT set
    echo Please set JAVA_HOME to your JDK installation directory
    echo Example: C:\Program Files\Eclipse Adoptium\jdk-17.0.x-hotspot
) else (
    echo ✅ JAVA_HOME is set to: %JAVA_HOME%
)

echo.
echo Current Java version:
java -version
echo.
echo ================================================
echo If everything shows ✅, you can run: .\mvnw.cmd clean compile
echo ================================================

:end
pause
