# H06 Tracker

![Platform](https://img.shields.io/badge/platform-Android-3DDC84?logo=android&logoColor=white)
![Min SDK](https://img.shields.io/badge/minSdk-23-blue)
![Target SDK](https://img.shields.io/badge/targetSdk-37-blue)
![Version](https://img.shields.io/badge/version-3.0-orange)
![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk&logoColor=white)
![License](https://img.shields.io/badge/license-unlicensed-lightgrey)

Android app to control and monitor H06 (a common GT06-clone) GPS trackers
over SMS — no carrier data plan required on the tracker's side, and no
third-party platform account needed.

## Features

- **Location** — request the tracker's current position
- **Lock / unlock** — cut or restore the vehicle's ignition remotely
- **Overspeed alarm** — set a speed-threshold alarm
- **Virtual fences** — clear configured geo-fences
- **Device configuration** — change password, pair/remove authorized
  numbers, set APN, set server IP/port, tracker language, product
  registration, GPS/tracker restart, factory reset
- **Device info** — firmware version, tracker info, paired numbers list
- **Server log** — history of commands sent and responses received
- **User manual** — quick reference for the tracker's commands

## Tech stack

- Java, Android Views
- [OrmLite](https://ormlite.com/) for local persistence
- Google Play Services (Ads)
- Gradle 9.5.0 / Android Gradle Plugin 9.3.2

## Requirements

- Android Studio (Ladybug or newer recommended)
- JDK 17
- Android SDK Platform 37

## Getting started

```bash
git clone git@github.com:pilovieira/ath06.git
cd ath06
./gradlew assembleDebug
```

Or open the project in Android Studio and run the `app` configuration on a
device or emulator.

### Useful Gradle tasks

| Task | Description |
| --- | --- |
| `./gradlew assembleDebug` | Build the debug APK |
| `./gradlew assembleRelease` | Build the release APK |
| `./gradlew installDebug` | Install the debug build on a connected device/emulator |

## Project structure

```
app/src/main/java/br/com/pilovieira/ath06/
├── business/     # Command building, H06 SMS protocol
├── comm/         # SMS sending/receiving
├── log/          # Server log screen and storage
├── persist/      # OrmLite DAOs, preferences
├── utils/        # Notifications
└── view/         # Activities and fragments
```

## Contributing

Issues and pull requests are welcome. Please open an issue describing the
change before submitting a large PR.

## License

No license has been specified for this project yet. All rights reserved by
the author unless stated otherwise.
