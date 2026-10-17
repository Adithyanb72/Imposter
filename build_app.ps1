$ErrorActionPreference = 'Stop'
$projectRoot = $PSScriptRoot
if (-not $projectRoot) { $projectRoot = Get-Location }

$jdkDir = Join-Path $projectRoot "jdk17"
$env:JAVA_HOME = $jdkDir
$env:Path = "$env:JAVA_HOME\bin;" + $env:Path

Remove-Item Env:\ANDROID_PREFS_ROOT -ErrorAction SilentlyContinue

Write-Host "JAVA_HOME is set to: $env:JAVA_HOME"
& "$env:JAVA_HOME\bin\java.exe" -version

$gradleBat = Join-Path $projectRoot "gradle-temp/gradle-8.10.2/bin/gradle.bat"

Write-Host "Running Gradle build..."
& $gradleBat assembleDebug
