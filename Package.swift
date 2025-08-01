// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "Maximilien0405CapacitorAndroidLauncher",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "Maximilien0405CapacitorAndroidLauncher",
            targets: ["AndroidLauncherPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "AndroidLauncherPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/AndroidLauncherPlugin"),
        .testTarget(
            name: "AndroidLauncherPluginTests",
            dependencies: ["AndroidLauncherPlugin"],
            path: "ios/Tests/AndroidLauncherPluginTests")
    ]
)
