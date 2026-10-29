# Architecture

Clean architecture on Kotlin Multiplatform. Dependencies point inward, so the business rules never
depend on Room, Compose, Android or iOS.

```
presentation (:app:shared) ---> domain (:domain) <--- data (:data)
                                      |                  :platform
                                      v
                                   :core
```

## Layers

**`:core`** (pure Kotlin, no dependencies on the app)

- Stateless algorithms packaged as small libraries: prayer times, Qibla, Hijri calendar, fasting
  rules.
- Takes plain inputs (coordinates, dates, parameters) and returns plain values.
- Built to be extracted and published on its own later, so it must never import anything from the
  other modules.

**`:domain`** (the app's business rules)

- Entities: `Prayer`, `PrayerDay`, `City`, `Surah`, `Verse`, `Bookmark`, `FastRecord`,
  `UserSettings`.
- Interfaces implemented elsewhere: `SettingsRepository`, `PrayerLogRepository`, `QuranRepository`,
  `CityRepository`, `FastingRepository`, `LocationService`, `CompassService`, `ReminderScheduler`,
  `AdhanPlayer`.
- Use cases, one action each, for example `GetTodayPrayerTimes`, `ObserveNextPrayer`,
  `MarkPrayerDone`, `ResolveHijriDate`, `GetFastingDay`, `ToggleBookmark`, `ScheduleReminders`.
- Depends only on `:core`, `kotlinx-datetime` and coroutines. No Android, iOS, Room or Compose.

**`:data`**

- Repository implementations on top of Room and DataStore.
- Bundled data loaders for the Quran text, translations and city database.
- Mappers between database rows and domain entities, so Room types never leave this module.

**`:platform`**

- `expect/actual` implementations of the domain service interfaces: location, compass, notification
  scheduling, audio, permissions.

**`:designsystem`**

- Theme, components and fonts. It knows nothing about the domain.

**`:app:shared`** (presentation)

- One package per feature: `onboarding`, `today`, `quran`, `reader`, `qibla`, `calendar`,
  `settings`, `alerts`.
- Each feature has a screen, a ViewModel and an immutable UI state class. ViewModels call use cases
  only, never repositories directly.
- Navigation with Jetpack Navigation for Compose Multiplatform.
- Koin composition root: the only place that sees both `:domain` and `:data`.

## Rules

1. Domain and core never import from data, platform, designsystem or the app.
2. ViewModels depend on use cases, not on Room, DataStore or platform classes.
3. Time is injected through `kotlin.time.Clock` (from the standard library), so everything that
   depends on "now" can be tested.
4. UI state is a single immutable object per screen. One-off events (toasts, navigation) are modeled
   separately.
5. Strings come from resources and are never built in ViewModels. Domain returns data, and
   presentation formats it for the locale.

## Dependency injection (Koin)

Koin modules live next to what they wire, so `:domain` stays free of any DI library:

- `dataModule` in `:data` (database, DataStore, repositories).
- `platformModule` in `:platform` (location, compass, notifications, audio).
- `domainModule` and `presentationModule` in `:app:shared` under `di/` (use cases, ViewModels).
- `:core` and `:domain` have no Koin module.

Koin starts outside Compose, so alarms, notification receivers and widgets can use it too:

- Android: a real `Application` subclass registered in the manifest calls `startKoin`.
- iOS: a shared `initKoin()` function called from the Swift app entry.
- ViewModels use Koin's Compose Multiplatform integration.

## Persistence

| Data                                                  | Store                                           |
|-------------------------------------------------------|-------------------------------------------------|
| Settings (city, method, theme, language, toggles)     | DataStore                                       |
| Prayer log, bookmarks, last read, fasts, make-up days | Room                                            |
| Quran text and translations                           | Room, seeded from bundled files on first launch |
| Cities                                                | Room, seeded from the generated city file       |

## Testing

- `:core`: exhaustive unit tests against published reference data (prayer times, Hijri dates).
- `:domain`: unit tests with fake repositories and a fake `Clock`.
- `:data`: Room tests against an in-memory database.
- `:app:shared`: ViewModel tests with fake use cases.
