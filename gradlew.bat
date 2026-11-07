@if "%DEBUG%" == "" @echo off
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

pushd "%APP_HOME%"
set APP_HOME=%CD%
popd

set JAVA_EXE=java.exe
set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

echo APP_HOME=%APP_HOME%
echo CLASSPATH=%CLASSPATH%

"%JAVA_EXE%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*

if "%ERRORLEVEL%"=="0" goto mainEnd
exit /b 1
:mainEnd
if "%OS%"=="Windows_NT" endlocal
