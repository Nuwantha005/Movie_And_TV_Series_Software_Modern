# Movie and TV Series Software – Codebase Review

**Author of review:** GitHub Copilot  
**Repository:** Nuwantha005/Movie_And_TV_Series_Software_Modern  
**Language / Platform:** Java 8 · JavaFX · MySQL · NetBeans IDE

---

## 1. Overview

`Movie_And_TV_Series_Software_Modern` is a **JavaFX desktop application** that lets a user maintain a personal or small-team catalog of movies and TV series stored in a local MySQL database. The application provides:

- A dark-themed, Material Design-styled UI built with the **JFoenix** library.
- Full **CRUD** (Create, Read, Update, Delete) operations for movies.
- A first-run **database setup wizard** that asks for MySQL credentials and writes them to a local configuration file.
- Two complementary views for movies: a scrollable **poster list** view and a searchable **tree-table** view.
- Image storage for movie posters, saved as a BLOB directly in the database.

> **Note:** The data model includes `Actor`, `Character`, `Fan`, `Studio`, `Franchise`, and `Producer` classes but **no UI has been built for them yet** – only the movie module is fully implemented.

---

## 2. Architecture

```
┌─────────────────────────────────────────────────────┐
│                   JavaFX UI Layer                    │
│  Main_Frame.fxml  +  Main_FrameController.java       │
│  DatabaseSetUp.fxml + DatabaseSetUpController.java   │
└────────────────────┬────────────────────────────────┘
                     │  calls
┌────────────────────▼────────────────────────────────┐
│                 Business / Coding Layer              │
│  CodingUtils.java   movieTableCodings.java           │
└────────────────────┬────────────────────────────────┘
                     │  calls
┌────────────────────▼────────────────────────────────┐
│               Database Access Layer                  │
│  MyConn.java  (single static JDBC Connection)        │
│  Preferences.java  (config.txt  ←→  Gson JSON)       │
└────────────────────┬────────────────────────────────┘
                     │  JDBC
┌────────────────────▼────────────────────────────────┐
│  MySQL  –  movies_and_tv_series_database             │
│  Tables: movies, franchises, producers, studios …   │
└─────────────────────────────────────────────────────┘
```

The project follows a rough **MVC pattern**:

| Layer | Files |
|-------|-------|
| **View** | `*.fxml` files + `dark_theme.css` |
| **Controller** | `*Controller.java` classes |
| **Model** | `classes/objectClasses/*.java` |
| **DAO / Service** | `Codings/*.java`, `MyConn.java` |
| **Config** | `DatabaseSetUp/Preferences.java`, `config.txt` |

---

## 3. Package and File Structure

```
src/
├── movie_and_tv_series_software_modern/
│   └── Movie_And_TV_Series_Software_Modern.java   ← JavaFX Application entry point
├── ui/main/
│   ├── Main_Frame.fxml                             ← Main window layout (60 KB)
│   └── Main_FrameController.java                  ← All movie UI logic (~590 lines)
├── DatabaseSetUp/
│   ├── DatabaseSetUp.fxml                          ← DB credentials form
│   ├── DatabaseSetUpController.java                ← Saves credentials, opens main window
│   ├── DatabaseSetUpLoader.java                    ← Standalone loader (not used at runtime)
│   └── Preferences.java                            ← JSON config read/write via Gson
├── Codings/
│   ├── CodingUtils.java                            ← Shared utilities (dialogs, file chooser …)
│   └── movieTableCodings.java                      ← Movie-specific DB helpers (add/update/refresh)
├── classes/
│   ├── objectClasses/
│   │   ├── Movie.java
│   │   ├── Actor.java
│   │   ├── Character.java
│   │   ├── Fan.java
│   │   ├── Franchise.java
│   │   ├── Studio.java
│   │   ├── Producer.java
│   │   └── MyConn.java                             ← Static JDBC connection + save/search helpers
│   └── recursiveTreeObjectClasses/
│       └── movieTreeObject.java                    ← JFoenix RecursiveTreeObject for the table
├── resources/
│   ├── images/                                     ← UI icons and software logo
│   └── styleSheets/
│       └── dark_theme.css                          ← Full dark Material theme (~350 lines)
test/
└── test1/                                          ← Prototype/sandbox code (not part of production)
    ├── FXML.fxml
    ├── FXMLController.java
    ├── Main.java
    └── Tese1.java
```

---

## 4. Key Modules

### 4.1 `Movie_And_TV_Series_Software_Modern` (Entry Point)
Extends `javafx.application.Application`. On startup it attempts to open a MySQL connection via `MyConn.getMyConn()`. If that succeeds it loads `Main_Frame.fxml`; otherwise it shows the database setup screen (`DatabaseSetUp.fxml`).

### 4.2 `Main_FrameController`
The single controller for the entire main window. Responsibilities:
- On `initialize`: populates combo boxes (franchise, studio, producer), auto-generates the next movie ID, and sets up the tree-table columns.
- `refreshMovies(keyword)`: queries the `movies` table (filtered by name prefix), then for each row issues three further look-up queries to resolve franchise, producer, and studio names.
- Handles **add**, **edit**, **delete**, and **search** actions for movies.
- Manages image zoom-on-scroll for the poster view.
- Manages **Next / Previous** navigation in the poster list.

### 4.3 `MyConn`
Holds a single `public static Connection c`. Provides two helper methods:
- `save(String sql)` – executes a DML statement.
- `search(String sql)` – executes a query and returns a `ResultSet`.

### 4.4 `movieTableCodings`
- `addMovie(Movie)`: converts the JavaFX `Image` to JPEG via `SwingFXUtils`, writes it to a temp file (`~/testImg.jpg`), inserts the movie row via a plain SQL statement, then updates the `image` column with a `PreparedStatement` BLOB stream.
- `updateMovie(Movie)`: same image-writing approach, then updates the existing row.
- `refreshMovies(keyword, list)`: populates the `ObservableList<movieTreeObject>` used by the tree-table.

### 4.5 `CodingUtils`
Shared utilities used across controllers:
| Method | Purpose |
|--------|---------|
| `update_Combo_Box(table, combo)` | Populates a JFXComboBox with `id - name` rows from a table |
| `browse_Image(root)` | Opens a file chooser and returns the selected `Image` |
| `getID(table)` | Returns the last row's ID + 1 (auto-ID generation) |
| `delete_from_Database(table, id)` | Deletes a row by ID |
| `showMaterialDialog(…)` | Shows a JFoenix modal dialog with optional blur effect |

### 4.6 `Preferences`
Stores MySQL username and password in `config.txt` (working directory) as a JSON object using **Gson 2.8.1**. Provides `getPreferences()` (read) and `writePreferenceToFile(pref)` (write). If the file is missing, `initConfig()` creates an empty placeholder.

---

## 5. Data Models

| Class | Fields |
|-------|--------|
| `Movie` | `id`, `name`, `catagery` (category), `description`, `franchise`, `studio`, `producer`, `image`, `imdbRating` |
| `Actor` | `id`, `name`, `birthday`, `gender`, `situation` (active/retired) , `image` |
| `Character` | `id`, `name`, `franchise`, `description`, `image` |
| `Fan` | `fan_ID`, `fan_Name`, `fan_Franchise_Name`, `panti_Level` (likely typo for "panic_Level"), `attack_Level`, `patience_Level`, `comic_Knowledge`, `special_Abilities`, `fanImage` |
| `Franchise` | `franchise_ID`, `franchise_Name`, `franchise_Description` |
| `Studio` | `studio_ID`, `studio_Name`, `Studio_Description`, `studio_Started_Date`, `studio_Badge` (Image) |
| `Producer` | `id`, `name`, `description`, `image` |
| `movieTreeObject` | Same fields as `Movie`; extends `RecursiveTreeObject<movieTreeObject>` for JFoenix tree-table binding |

**Assumed database schema (inferred from code):**

```sql
CREATE DATABASE movies_and_tv_series_database CHARACTER SET utf8;

CREATE TABLE franchises (id INT PRIMARY KEY, name VARCHAR(255), description TEXT);
CREATE TABLE producers  (id INT PRIMARY KEY, name VARCHAR(255), description TEXT);
CREATE TABLE studios    (id INT PRIMARY KEY, name VARCHAR(255), description TEXT, started_date DATE);
CREATE TABLE movies (
    ID            INT PRIMARY KEY,
    Name          VARCHAR(255),
    Description   TEXT,
    IMDB_Rating   VARCHAR(10),
    Catagery      VARCHAR(100),
    Franchises_ID INT REFERENCES franchises(id),
    Producers_ID  INT REFERENCES producers(id),
    Studios_ID    INT REFERENCES studios(id),
    image         LONGBLOB
);
```

---

## 6. Third-Party Libraries

| Library | Version | Purpose |
|---------|---------|---------|
| JFoenix | 8.0.7 (+ 8.0.4) | Material Design controls for JavaFX 8 |
| FontAwesomeFX | 4.7.0-5 (commons 8.15) | Icon fonts |
| Gson | 2.8.1 | JSON serialization of `Preferences` |
| mysql-connector-java | 5.0.7 | JDBC driver for MySQL |

---

## 7. Build and Run Instructions

### Prerequisites
- **Java 8 JDK** (the project targets `javac.source=1.8`)
- **MySQL 5.x or 8.x** running on `localhost:3306`
- A MySQL database named `movies_and_tv_series_database` with the tables above created
- **NetBeans 8+** (recommended) or Apache Ant

### Building with NetBeans
1. Open the project folder in NetBeans (`File → Open Project`).
2. Fix the library references in `nbproject/project.properties` to point to the JAR files on your machine (they currently point to the developer's Windows paths).  
   Alternatively, copy the JARs from `dist/lib/` and update the references.
3. Press **F6** (Run Project) or **Shift+F11** (Clean and Build).

### Building with Ant (command line)
```bash
# Ensure ANT_HOME and JAVA_HOME are set
ant clean jar
```
The distributable JAR is produced at `dist/Movie_And_TV_Series_Software_Modern.jar`.

### Running
```bash
java -jar dist/Movie_And_TV_Series_Software_Modern.jar
```
- **First run:** A setup screen prompts for the MySQL username and password. These are written to `config.txt` in the working directory.
- **Subsequent runs:** The application connects automatically using the saved credentials.

### config.txt format
```json
{"userName":"root","Password":"your_password"}
```

---

## 8. Notable Issues and Improvement Opportunities

### 8.1 Security Issues ⚠️

| # | Issue | Location | Recommendation |
|---|-------|----------|----------------|
| S1 | **SQL Injection** – all queries use string concatenation | `MyConn.save/search`, `CodingUtils.getID/delete_from_Database`, `movieTableCodings.refreshMovies`, `Main_FrameController.refreshMovies` | Replace with `PreparedStatement` for every parameter |
| S2 | **Plain-text credentials** – MySQL password stored unencrypted in `config.txt` | `Preferences.java`, `config.txt` | Encrypt the config or use the OS credential store |
| S3 | **Publicly exposed static Connection** – `MyConn.c` is `public static` | `MyConn.java` | Make the field `private`; expose only controlled methods |

### 8.2 Correctness Issues

| # | Issue | Location |
|---|-------|----------|
| C1 | **Temp-file race condition** – `addMovie` and `updateMovie` both write to the same hard-coded path `~/testImg.jpg`; concurrent saves would corrupt the image | `movieTableCodings.java:28,66` |
| C2 | **ID generation is not safe** – `getID()` reads the last row's ID and adds 1; if rows are deleted or inserted concurrently the same ID could be produced twice | `CodingUtils.java:66` |
| C3 | **N+1 query pattern** – for every movie row, three additional look-up queries are fired (franchise, producer, studio) | `Main_FrameController.refreshMovies`, `movieTableCodings.refreshMovies` |
| C4 | **Hardcoded database URL** – `localhost:3306/movies_and_tv_series_database` cannot be configured | `MyConn.java:31` |
| C5 | **`movieList1`, `moviePoster1`, etc.** – duplicate `@FXML` fields (suffixed `1`) suggest copy-paste code for a second tab but the data bindings are not wired up | `Main_FrameController.java` |
| C6 | **JVM crash log committed** – `hs_err_pid11004.log` is a Windows JVM crash dump left in the repository root | – |

### 8.3 Code Quality / Style

| # | Issue | Location |
|---|-------|----------|
| Q1 | **Typos in field/variable names and UI text** – `catagery` (→ category), `moviePreviusImage` (→ previous), `movieSearchKeyowrd` (→ keyword), `"Yse"` button label (→ Yes), `"Confrimation"` (→ Confirmation), `"usernam"` in error message | multiple files |
| Q2 | **Raw types** – `ComboBox` without generic type parameter | `CodingUtils.update_Combo_Box` |
| Q3 | **Duplicate library versions** – both `jfoenix-8.0.4.jar` and `jfoenix-8.0.7.jar` are on the classpath | `nbproject/project.properties` |
| Q4 | **Hardcoded absolute paths in project.properties** – `C:\Users\Nuwantha Kumara\...` paths break the build on any other machine | `nbproject/project.properties` |
| Q5 | **No logging framework** – all error handling uses `e.printStackTrace()` | throughout |
| Q6 | **Prototype test code left in `test/`** – `test1/` contains sandbox experiments (a `User` tree-table demo) that are not actual unit tests | `test/test1/` |
| Q7 | **Unimplemented data models** – `Actor`, `Character`, `Fan`, `Studio`, `Franchise`, `Producer` classes exist but have no corresponding UI or DAO | `classes/objectClasses/` |
| Q8 | **`DatabaseSetUpLoader`** is an unused `Application` subclass; the real startup is handled by `Movie_And_TV_Series_Software_Modern` | `DatabaseSetUp/DatabaseSetUpLoader.java` |
| Q9 | **CSS typo** – `.errror` (triple-r) selector never matches; was likely meant to be `.error` | `dark_theme.css:55` |
| Q10 | **`config.txt` committed with real credentials** (`root`/`123`) | `config.txt`, `dist/config.txt` |

### 8.4 Improvement Opportunities

1. **Replace raw SQL with an ORM or at minimum PreparedStatements** to eliminate SQL injection and simplify the DAO layer.
2. **Use `AUTO_INCREMENT` primary keys** in MySQL instead of the manual `getID()` approach.
3. **Connection pooling** (e.g. HikariCP) instead of a single static `Connection` that is never closed.
4. **Add a `.gitignore`** to exclude build artifacts (`build/`, `dist/`, `nbproject/private/`), crash logs, and the `config.txt` credentials file.
5. **Complete the remaining modules** – Actor, Character, Franchise, Studio, and Producer management screens are missing but the domain objects are already defined.
6. **Write unit tests** – the `test/` directory exists but contains only prototype JavaFX experiments. True unit tests for business logic (ID generation, input validation, etc.) should be added.
7. **Extract the database URL** (host, port, schema) into `config.txt` so the application can connect to remote databases without recompilation.
8. **Internationalization** – the UI strings are hard-coded in English; extracting them to a resource bundle would simplify future translations.

---

## 9. Summary

The project is a **functional, single-developer JavaFX CRUD application** that demonstrates solid use of JFoenix Material Design controls, JavaFX FXML layouts, and JDBC-based MySQL persistence. The dark-themed UI is polished and the core movie management workflow (add / edit / delete / browse / search) is complete.

The primary areas requiring attention before any production or shared use are:

- **SQL injection vulnerabilities** across all database operations.
- **Credentials stored in plain text** in the working directory.
- Hardcoded local file paths that prevent the project from building on other machines without manual configuration changes.

Addressing those three concerns, fixing the typos, and adding a `.gitignore` would bring the codebase to a much healthier baseline for collaborative development.
