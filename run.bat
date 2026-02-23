@echo off
set JAVA_HOME=C:\Program Files\Java\jdk-22
echo Starting StockFlow Application...
call mvnw.cmd spring-boot:run
if %ERRORLEVEL% neq 0 (
    echo.
    echo [ERROR] Application failed to start.
)
pause
