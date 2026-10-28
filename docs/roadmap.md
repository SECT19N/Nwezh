# Roadmap

Nwezh is a private, offline prayer times, Qibla and Quran app for Android and iOS, built with Kotlin
and Compose Multiplatform. It has no account, ads or analytics.

## Scope

**Screens**: onboarding, Today, Quran list, Quran reader, Qibla, Calendar, Settings (with
sub-pages), home-screen widget, adhan alert and reminder, and permission/travel notices.

**Features**

- On-device prayer times: 6 calculation methods, standard or Hanafi Asr, high-latitude rules,
  elevation correction, per-prayer minute offsets, 12/24-hour clock.
- Next-prayer countdown, prayer tracking and a 7-day strip.
- Qibla bearing, distance and live compass with calibration.
- Hijri calendar (Umm al-Qura, ±1 day adjustment) with Islamic events.
- Fasting: Ramadan auto-on, no-fast days, sunnah suggestions, suhoor alarm, iftar alert, make-up day
  counter.
- Adhan and reminders: per-prayer toggles, sound choice, "remind me N min before", travel detection.
- Quran: Arabic text plus translations, bookmarks, last-read, font size, copy verse, focus mode.
- Languages: English, Sorani, Badini, Kurmanji (Latin) and Arabic, with RTL support and a
  native-digit toggle. See Releases.
- Light and dark themes (Material 3, plum/mauve primary).

## Releases

- **v0.5**: English and Arabic. Quran text (Tanzil) with English translation and Arabic tafsir.
- **v1**: adds Sorani (UI and translation), the moon-confirmation prompt, the option to switch the
  Hijri date at Maghrib, and automatic make-up day counting when a Ramadan fast is turned off.
- **Later**: Kurmanji and Badini, once translation sources are settled. Adhan audio is pinned.

## Target module layout

Clean architecture. Dependencies point inward: presentation -> domain <- data. See `architecture.md`.

```
:core            pure Kotlin KMP libraries, no app knowledge: prayer times, Qibla, Hijri, fasting rules
:domain          entities, repository and service interfaces, use cases (depends on :core)
:data            Room, DataStore, bundled Quran and city data, repository implementations (depends on :domain)
:platform        expect/actual implementations of domain services: location, compass, notifications, audio
:designsystem    Compose MP theme: color schemes, shapes, typography, fonts, icons, shared components
:app:shared      presentation: features (screen, ViewModel, state), navigation, Koin composition root
:app:androidApp  entry point, widget (Glance), alarms/services, manifest permissions
:app:iosApp      entry point, widget (WidgetKit), notification setup
```

There is no server module: the app is offline with no account.

## Phase 0: Decisions and setup

- [x] Data sources decided (see `data-sources.md`). Open: rights-holder permissions, Kurmanji
  source, adhan audio.
- [x] `:server` removed (not needed).
- [x] Libraries chosen: DataStore (settings), Room KMP (tracking, bookmarks, fasts, Quran, cities),
  Jetpack Navigation for Compose Multiplatform, Koin from the start, `kotlinx-datetime`,
  `kotlinx-serialization`.
- [x] Architecture: clean architecture with core, domain, data and presentation layers.
- [x] Domain libraries (prayer times, Qibla, Hijri, fasting, Quran data, cities) are built in-house
  as separate modules, extractable later.
- [x] Add the chosen dependencies to `gradle/libs.versions.toml`.
- [x] Create the new modules (`:domain`, `:data`, `:platform`, `:designsystem`) and register them in
  `settings.gradle.kts`.
- [x] Package convention `com.section.nwezh.<module>.<feature>`, with a Koin module per layer
  (`data/di`, `platform/di`, `app/shared/di`).
- [x] Remove template code (Greeting, Platform, sample resources, placeholder tests).

## Phase 1: `:core` (pure logic, test-first)

- [ ] Models: `City`, `Coordinates`, `PrayerTimes`, `Prayer`, settings enums.
- [ ] `PrayerTimeCalculator`: methods (MWL, Umm al-Qura, Egyptian, Karachi, Diyanet, ISNA), Asr
  factor, high-latitude rules, elevation, offsets.
- [ ] Tests against reference times (published timetables or a known library) for Erbil, Berlin and
  Stockholm in summer (high latitude).
- [ ] `QiblaCalculator`: great-circle bearing and distance.
- [ ] `HijriCalendar`: our own implementation from a generated Umm al-Qura month-length table, with a
  civil-calendar fallback and a day adjustment (see `data-sources.md`).
- [ ] `IslamicEvents` and `FastingRules` (forbidden days, sunnah suggestions, make-up counting).
- [ ] Next-prayer and countdown logic, including the wrap to tomorrow's Fajr.

## Phase 2: `:designsystem`

- [ ] M3 light and dark `ColorScheme`.
- [ ] Shapes and a connected-group component (small inner corners, large outer corners, selected
  item becomes a pill).
- [ ] Typography with bundled fonts for Latin, Arabic UI and Quran text.
- [ ] Icon set (Material Symbols Rounded, filled/outlined variants).
- [ ] Shared components: list row (nav, switch, stepper), bottom sheet, picker, toast, tab bar.

## Phase 3: Localization

- [ ] String resources for `en` and `ar` (v0.5), then `ckb` (v1), later `kmr` and `bad`.
- [ ] Verify Compose resources handle `ckb`, `bad` and `kmr`; add an in-app language override if
  needed.
- [ ] RTL layout checks and the native-digit toggle.
- [ ] Native-speaker review of all non-English strings.

## Phase 4: `:data`

- [ ] Settings store (city, method, Asr, high-latitude, elevation, Hijri adjust, clock, sound, adhan
  toggles, offsets, reminder lead, theme, language, digits, fasting options).
- [ ] Prayer tracking, bookmarks, last-read, fasts and make-up days.
- [ ] Bundle Quran text and the chosen translations; pick a storage format and loader.
- [ ] Offline city list with search.

## Phase 5: `:platform`

- [ ] Location provider (permission and denied states) for Android and iOS.
- [ ] Compass sensor with an accuracy callback.
- [ ] Notification and alarm scheduling. Android: exact alarms, boot reschedule, battery exemption.
  iOS: local notifications, refresh on app open, 64-notification cap.
- [ ] Adhan audio playback.
- [ ] Notification-permission state handling.
- [ ] Timezone and travel detection.

## Phase 6: Features (`:app:shared`)

Suggested order, so there is a usable app early:

1. [ ] Navigation shell and tab bar.
2. [ ] Onboarding.
3. [ ] Today (next prayer, list, tracking, week strip).
4. [ ] Settings and sub-pages.
5. [ ] Adhan alerts, reminders and notices.
6. [ ] Qibla.
7. [ ] Calendar.
8. [ ] Fasting.
9. [ ] Quran list, reader, bookmarks.
10. [ ] Widgets (Android Glance, iOS WidgetKit).

## Phase 7: Polish and release

- [ ] Accessibility (TalkBack/VoiceOver, text scaling, RTL).
- [ ] Verify prayer times against local mosque timetables (Kurdistan Region).
- [ ] Performance (startup, recomposition).
- [ ] Privacy statement and open-source licence notices.
- [ ] Store listings and release builds (ProGuard rules, iOS signing).
