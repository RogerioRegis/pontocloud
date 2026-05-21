const { execSync } = require('child_process');

try {
    const javaVersion = execSync('java -version 2>&1').toString();
    console.log('Java version:', javaVersion.split('\n')[0]);

    try {
        const adbVersion = execSync('adb version').toString();
        console.log('ADB version:', adbVersion.split('\n')[0]);
    } catch (e) {
        console.log('ADB not found in path');
    }

    console.log('Environment check complete.');
} catch (error) {
    console.error('Environment check failed:', error.message);
}
