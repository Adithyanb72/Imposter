$paths = @(
    'C:\Program Files\Android\Android Studio\jbr',
    'C:\Users\adith\AppData\Local\Programs\Android Studio\jbr',
    'C:\Program Files\Android\Android Studio\jre'
)
foreach ($p in $paths) {
    if (Test-Path $p) {
        Write-Host "Found JBR at: $p"
    }
}
