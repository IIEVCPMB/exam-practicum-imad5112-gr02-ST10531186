# Campsite Commander 🏕️

**Campsite Commander** is an Android inventory management application for outdoor adventure gear. Built with Kotlin and Android Views, it helps campers track their packing list with a clean, professional red-and-white themed interface.

---

## Features

- **Splash Screen** — Branded welcome screen with campfire icon and "GET STARTED" navigation
- **Main Dashboard** — Displays total packed items count in a styled summary card with quick-action buttons
- **Gear Management** — Add, view, and manage camping gear items with categories and quantities
- **Detail View** — Per-item data entry with spinner selection, category input, and quantity/comments fields
- **Data Operations** — Save individual items, view all items, view comments, and clear all data
- **8 Predefined Gear Items** — Tent, Marshmallows, Flashlight, Sleeping Bag, Camp Stove, First Aid Kit, Water Bottle, Compass

## Visual Design

| Element | Specification |
|---------|--------------|
| **Theme** | Modern Red & White Professional |
| **Primary Color** | `#D32F2F` (Red) |
| **Dark Red** | `#B71C1C` |
| **Secondary Red** | `#EF5350` |
| **Background** | `#FFFFFF` (White) |
| **Surface** | `#F5F5F5` (Light Grey) |
| **Button Radius** | 16dp rounded corners |
| **Card Radius** | 16dp rounded corners with elevation |
| **Text Primary** | `#212121` (Dark Grey) |
| **Text Secondary** | `#616161` (Medium Grey) |

## Screens

### 1. Splash Screen
- Centered campfire vector icon
- "Campsite Commander" title in bold red (36sp)
- "Outdoor Adventure Inventory" subtitle
- Red accent bars (top & bottom)
- "GET STARTED" red button → navigates to Main Screen

### 2. Main Screen (Packing List)
- Header with red accent strip + campfire icon
- **Total Items Packed** card with large red count (56sp)
- Action buttons:
  - **ADD GEAR ITEM** — Navigates to detail view for data entry
  - **VIEW ALL ITEMS** — Shows all saved gear items
  - **CLEAR ALL DATA** — Resets all gear data

### 3. Detail View (Camp Checklist)
- **Gear Items** display card — Shows saved items with category, quantity, and divider
- **Add New Item** card:
  - Gear item spinner (8 predefined items)
  - Category input field (outlined style)
  - Quantity/Comment input field (outlined style)
  - **SAVE ITEM** button with toast confirmation
  - **VIEW ITEMS** outlined button
- **VIEW COMMENTS** button
- **CLEAR DATA** outlined button
- **BACK TO BASE** button — Returns to main screen

## Technical Architecture

- **Language:** Kotlin
- **UI Framework:** Android Views (XML layouts)
- **Minimum SDK:** API 25 (Android 7.1)
- **Target SDK:** API 37
- **Build System:** Gradle with Kotlin DSL
- **Architecture:** Single-Activity with visibility-based screen navigation
- **Data Storage:** In-memory arrays (per session)
- **Dependencies:** AndroidX Core KTX, Activity Compose, Material3 (Compose)

## Project Structure

```
app/
├── src/main/
│   ├── java/com/example/campsitecommander/
│   │   ├── MainActivity.kt          # Main activity with all logic
│   │   └── ui/theme/                 # Compose theme files (optional)
│   ├── res/
│   │   ├── drawable/
│   │   │   ├── ic_campfire.xml       # Campfire vector icon
│   │   │   ├── bg_red_button.xml     # Red button background
│   │   │   ├── bg_outlined_button.xml # Outlined button background
│   │   │   ├── bg_card_white.xml     # Card shadow/surface
│   │   │   ├── bg_edit_text.xml      # Outlined edit text background
│   │   │   └── bg_spinner.xml        # Spinner dropdown background
│   │   ├── layout/
│   │   │   ├── activity_main.xml     # Root layout (includes all screens)
│   │   │   ├── splash_screen.xml     # Welcome/splash screen
│   │   │   ├── main_screen.xml       # Dashboard with total count
│   │   │   └── detail_view.xml       # Gear management screen
│   │   └── values/
│   │       ├── colors.xml            # Red & white color palette
│   │       ├── strings.xml           # App strings
│   │       └── themes.xml            # Material Light theme
```

## Building the Project

1. Open the project in **Android Studio**
2. Sync Gradle (File → Sync Project with Gradle Files)
3. Select a device/emulator (API 25+)
4. Click **Run** (▶) or use:
   ```bash
   ./gradlew assembleDebug
   ```

## Development Notes

- The app uses `android:Theme.Material.Light.NoActionBar` — a platform theme (API 21+) that requires no extra libraries
- All styling is applied via custom XML drawables for maximum compatibility
- Gear data is stored in-memory and resets when the app is closed
- The Compose theme files (`Color.kt`, `Theme.kt`, `Type.kt`) are from the project template and unused

---

*Built with Kotlin & Android — Academic Assessment Project*
