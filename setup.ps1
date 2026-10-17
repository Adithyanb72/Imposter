$ErrorActionPreference = 'Stop'
$gradleVersion = '8.10.2'
$zipUrl = "https://services.gradle.org/distributions/gradle-${gradleVersion}-bin.zip"
$projectRoot = $PSScriptRoot
if (-not $projectRoot) { $projectRoot = Get-Location }
$zipPath = Join-Path $projectRoot "gradle-${gradleVersion}.zip"
$extractPath = Join-Path $projectRoot "gradle-temp"

Write-Host "Downloading Gradle $gradleVersion..."
Invoke-WebRequest -Uri $zipUrl -OutFile $zipPath

Write-Host "Extracting Gradle..."
Expand-Archive -Path $zipPath -DestinationPath $extractPath -Force

$gradleBat = Join-Path $extractPath "gradle-${gradleVersion}/bin/gradle.bat"

Write-Host "Generating Gradle wrapper..."
& $gradleBat wrapper --gradle-version $gradleVersion --distribution-type bin

# Cleanup zip only, keep extracted temp for running build or let wrapper download
Remove-Item -Path $zipPath -Force -ErrorAction SilentlyContinue

Write-Host "Gradle wrapper generated successfully!"
