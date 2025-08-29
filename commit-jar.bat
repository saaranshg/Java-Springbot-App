@echo off
echo ================================================
echo Git Installation and JAR Commit Guide
echo ================================================
echo.

echo Step 1: Install Git
echo Download from: https://git-scm.com/download/win
echo Choose "64-bit Git for Windows Setup"
echo.

echo Step 2: After Git Installation, run these commands:
echo.
echo cd "S:\Java Springbot App"
echo git init
echo git remote add origin https://github.com/saaranshg/Java-Springbot-App.git
echo git add .
echo git commit -m "Add compiled JAR file and complete project"
echo git branch -M main
echo git push -u origin main
echo.

echo Step 3: Verify JAR is uploaded
echo Check: https://github.com/saaranshg/Java-Springbot-App/blob/main/target/sql-solver-0.0.1-SNAPSHOT.jar
echo.

echo ================================================
echo After completion, your download link will work:
echo https://github.com/saaranshg/Java-Springbot-App/raw/main/target/sql-solver-0.0.1-SNAPSHOT.jar
echo ================================================

pause
