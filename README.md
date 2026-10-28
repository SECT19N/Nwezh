# Nwezh

Prayer times, Qibla and the Quran for Android and iOS. Private and offline: no account, no ads, no
analytics. Your location, bookmarks and settings stay on your phone.

Built with Kotlin Multiplatform and Compose Multiplatform. Planned languages: English, Sorani,
Badini, Kurmanji (Latin) and Arabic.

**Status:** early development. See the [roadmap](./docs/roadmap.md).

## Features (planned)

- Prayer times calculated on-device, with several calculation methods, high-latitude rules and
  per-prayer adjustments
- Adhan and reminders
- Qibla compass
- Hijri calendar with Islamic events
- Fasting mode (suhoor, iftar, sunnah suggestions, make-up days)
- Quran reader with translations, bookmarks and last-read position
- Home-screen widget
- Light and dark themes

## Project structure

* [/app/androidApp](./app/androidApp) is the Android entry point.
* [/app/iosApp](./app/iosApp/iosApp) is the iOS entry point. It is needed even though the UI is
  shared with Compose Multiplatform.
* [/app/shared](./app/shared/src) is the shared Compose Multiplatform UI and app logic.
    - [commonMain](./app/shared/src/commonMain/kotlin) is code common to all targets.
    - `androidMain`, `iosMain` and `jvmMain` hold platform-specific code.
* [/core](./core/src) is pure Kotlin logic shared between all targets (prayer times, Qibla, Hijri
  calendar, fasting rules).
* [/domain](./domain/src) holds entities, repository and service interfaces, and use cases.
* [/data](./data/src) holds Room, DataStore and the repository implementations.
* [/platform](./platform/src) holds the platform implementations (location, compass, notifications).
* [/designsystem](./designsystem/src) holds the theme and shared UI components.

See [architecture](./docs/architecture.md) and the [roadmap](./docs/roadmap.md).

## Running the apps

- Android app: `./gradlew :app:androidApp:assembleDebug`
- iOS app: open the [/app/iosApp](./app/iosApp) directory in Xcode and run it from there.

## Running tests

- Pure logic: `./gradlew :core:jvmTest :domain:jvmTest`
- Android tests: `./gradlew :app:shared:testAndroidHostTest`
- iOS tests: `./gradlew :app:shared:iosSimulatorArm64Test`

## License

Apache-2.0 (the `LICENSE` file has not been added yet).
