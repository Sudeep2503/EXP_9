# Experiment 9: Data Persistence Using SharedPreferences and SQLite

## 1. Aim

To demonstrate data persistence in Android applications using **SharedPreferences** and **SQLite Database**.

## 2. Objective

- To understand the concept of data persistence in Android.
- To store and retrieve key-value data using SharedPreferences.
- To create and manage a local SQLite database.
- To perform insert, retrieve, and delete operations on student records.
- To verify that stored data remains available after closing and reopening the application.

## 3. Technologies Used

- **IDE:** Android Studio
- **Programming Language:** Kotlin
- **UI Design:** XML
- **Data Storage:** SharedPreferences and SQLite
- **Platform:** Android
- **Minimum SDK:** API 24 (Android 7.0)

## 4. Theory

### 4.1 Data Persistence

Data persistence is the ability of an application to save data so that it remains available even after the application is closed or the device is restarted. Android provides different storage mechanisms depending on the type and amount of data to be stored.

### 4.2 SharedPreferences

SharedPreferences is an Android storage mechanism used to store small amounts of data in key-value pairs. It is suitable for saving simple values such as usernames, application settings, and preferences.

In this experiment, SharedPreferences is used to save and retrieve a username.

**Important methods:**

- `getSharedPreferences()` — Opens or creates a preferences file.
- `edit()` — Creates an editor to modify preferences.
- `putString()` — Stores a string value.
- `apply()` — Saves changes asynchronously.
- `getString()` — Retrieves a stored string value.

### 4.3 SQLite Database

SQLite is a lightweight relational database integrated into Android. It is used to store structured data in tables on the device.

In this experiment, SQLite stores student records containing a student ID, name, and USN.

The application uses `SQLiteOpenHelper` to create and manage the database.

**Important operations:**

- `onCreate()` — Creates the database table.
- `insert()` — Inserts a student record.
- `query()` — Retrieves stored records.
- `delete()` — Deletes records from the database.
- `onUpgrade()` — Handles database schema upgrades.

## 5. Scenario

Consider a student management application that needs to remember a user's name and maintain student records.

The application uses SharedPreferences to save the username so that it can be retrieved later. SQLite is used to store student details, display saved records, and delete records when required.

This scenario demonstrates the difference between storing simple key-value data and storing structured relational data.

## 6. Application Features

### SharedPreferences Module

- Accepts a username from the user.
- Saves the username using SharedPreferences.
- Retrieves the previously saved username.
- Displays the retrieved value on the screen.

### SQLite Module

- Accepts student name and USN.
- Inserts student details into the SQLite database.
- Displays all stored student records.
- Deletes all student records when requested.
- Prevents duplicate USNs using a unique database constraint.

## 7. Project Folder Structure

```text
EXP_9/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── exp_9/
│   │   │   │               ├── MainActivity.kt
│   │   │   │               └── DatabaseHelper.kt
│   │   │   ├── res/
│   │   │   │   └── layout/
│   │   │   │       └── activity_main.xml
│   │   │   └── AndroidManifest.xml
│   │   └── build.gradle.kts
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── gradle/
│   └── libs.versions.toml
└── README.md
```

*Note: The structure above shows the main files relevant to this experiment. Android Studio may generate additional files and folders.*

## 8. Implementation

### Step 1: Design the User Interface

The XML layout contains two sections:

1. SharedPreferences section with a username field and Save Preference and Load Preference buttons.
2. SQLite section with student name and USN fields and Insert Student, View Students, and Clear Student Records buttons.

### Step 2: Implement SharedPreferences

The application obtains a SharedPreferences instance and saves the username using a key-value pair.

```kotlin
val preferences = getSharedPreferences(
    "UserPreferences",
    MODE_PRIVATE
)

preferences.edit()
    .putString("username", username)
    .apply()
```

The saved username is retrieved using:

```kotlin
val username = preferences.getString("username", null)
```

### Step 3: Implement SQLite

A helper class extending `SQLiteOpenHelper` manages the student database.

The database contains a `students` table with the following columns:

| Column | Data Type | Description |
|---|---|---|
| id | INTEGER | Primary key with auto-increment |
| name | TEXT | Student name |
| usn | TEXT | Student USN, which must be unique |

The application uses SQLite operations to insert student records, retrieve records, and delete all records.

### Step 4: Display Results

The retrieved username and student records are displayed using TextView elements. Toast messages provide feedback for selected operations.

## 9. Test Cases

### Test Case 1: SharedPreferences Save and Retrieve

**Objective:** To verify that a username can be saved and retrieved.

**Procedure:**
1. Enter a username.
2. Click Save Preference.
3. Click Load Preference.
4. Verify that the saved username is displayed.
5. Close and reopen the application and retrieve the username again.

**Expected Result:** The previously saved username should be displayed, including after reopening the application.

**Screenshot:** ![alt text](<Screenshot 2026-10-16 121551.png>)

**Actual Result:** To be recorded after execution.

**Status:** Pending execution.

### Test Case 2: SQLite Insert and Retrieve

**Objective:** To verify that student records can be inserted and retrieved.

**Procedure:**
1. Enter a student name and USN.
2. Click Insert Student.
3. Click View Students.
4. Verify that the student details are displayed.
5. Reopen the application and view the records again.

**Expected Result:** The inserted student record should be displayed and remain available after reopening the application.

**Screenshot:** ![alt text](<Screenshot 2026-10-16 121640.png>)

**Actual Result:** To be recorded after execution.

**Status:** Pending execution.

### Test Case 3: SQLite Delete

**Objective:** To verify that student records can be deleted.

**Procedure:**
1. Insert at least one student record.
2. Click Clear Student Records.
3. Click View Students.
4. Verify that no student records remain.

**Expected Result:** All student records should be deleted, and the application should display a message indicating that no records were found.

**Screenshot:** ![alt text](<Screenshot 2026-10-16 121706.png>)

**Actual Result:** To be recorded after execution.

**Status:** Pending execution.

## 10. Output Screenshot

The main application output should show the SharedPreferences and SQLite sections with their input fields, buttons, and result areas.

**Screenshot:** `screenshots/Experiment9_Output.png`

*Add the actual screenshot of the running application here.*

## 11. Result

The experiment demonstrates how Android applications can persist simple key-value data using SharedPreferences and structured records using SQLite. The successful execution of the test cases verifies the save, retrieve, and delete operations and confirms data persistence across application restarts.

*Update the result and test-case statuses after successful execution.*

## 12. Conclusion

SharedPreferences is suitable for storing small amounts of simple application data, while SQLite is useful for storing and managing structured records. Both mechanisms support local data persistence and help Android applications retain information between sessions.



**Submitted By:** Sudeep J S (25MCAR0134).

**Experiment:** 9 — Data Persistence Using SharedPreferences and SQLite
