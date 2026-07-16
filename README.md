# Movie & TV Series Software (Modern)

A dark-themed, Material Design-styled JavaFX desktop application designed to maintain a personal catalog of movies and TV series using a local MySQL database.

The application provides an intuitive dashboard featuring dual views (a scrollable poster gallery and a searchable hierarchical tree-table) alongside an automated database configuration wizard on first-time setup.

---

## Features

* **Material Design UI:** Dark-themed presentation styled using the JFoenix library.
* **Full Movie CRUD:** Complete creation, reading, updating, and deletion capabilities for movie entries.
* **Binary Data Management:** Direct storage of movie poster images as `BLOB` fields in MySQL.
* **Dual Perspective Views:**
* A scrollable **Poster List View** supporting smooth navigation and mouse-scroll zoom behaviors.
* A comprehensive, prefix-searchable **Tree-Table View** for tabular inspection.


* **Setup Wizard:** An automated setup sequence that prompts for local MySQL database credentials on first launch and stores configurations locally.

> 💡 **Development Note:** The internal object models include foundational definitions for `Actor`, `Character`, `Fan`, `Studio`, `Franchise`, and `Producer`. The underlying schema handles these entities, though user interface implementations are currently limited to the core Movie catalog module.

---

## Architectural Layout

The codebase loosely implements a standard Model-View-Controller (MVC) separation pattern across the following functional tiers:

```
┌─────────────────────────────────────────────────────┐
│                   JavaFX UI Layer                   │
│  Main_Frame.fxml  +  Main_FrameController.java       │
│  DatabaseSetUp.fxml + DatabaseSetUpController.java   │
└────────────────────┬────────────────────────────────┘
                     │ calls
┌────────────────────▼────────────────────────────────┐
│               Business / Coding Layer               │
│  CodingUtils.java   movieTableCodings.java           │
└────────────────────┬────────────────────────────────┘
                     │ calls
┌────────────────────▼────────────────────────────────┐
│               Database Access Layer                │
│  MyConn.java  (single static JDBC Connection)        │
│  Preferences.java  (config.txt  ←→  Gson JSON)       │
└────────────────────┬────────────────────────────────┘
                     │ JDBC
┌────────────────────▼────────────────────────────────┐
│            MySQL  –  movies_and_tv_series_database   │
│  Tables: movies, franchises, producers, studios …   │
└─────────────────────────────────────────────────────┘

```

### Component Mapping

| Component Tiers | Source Mappings |
| --- | --- |
| **View Layer** | `*.fxml` UI configurations + `dark_theme.css` |
| **Controller Layer** | Java execution handlers (`*Controller.java`) |
| **Data Models** | Standard object definitions under `classes/objectClasses/` |
| **Data Access & Services** | Core helper utilities within `Codings/` and `MyConn.java` |
| **Configurations** | Local storage properties parsed by `Preferences.java` |

---

## Directory Architecture

```
src/
├── movie_and_tv_series_software_modern/
│   └── Movie_And_TV_Series_Software_Modern.java   ← Application Entry Point
├── ui/main/
│   ├── Main_Frame.fxml                            ← Core Window Layout
│   └── Main_FrameController.java                  ← Primary UI Control Logic
├── DatabaseSetUp/
│   ├── DatabaseSetUp.fxml                         ← DB Setup Layout
│   ├── DatabaseSetUpController.java               ← Setup Business Logic
│   └── Preferences.java                           ← JSON Preferences IO Manager
├── Codings/
│   ├── CodingUtils.java                           ← General Framework Utilities
│   └── movieTableCodings.java                     ← DB Transaction Handlers
├── classes/
│   ├── objectClasses/                             ← Core Domain Entities
│   │   ├── Movie.java, Actor.java, Character.java, Fan.java, 
│   │   └── Franchise.java, Studio.java, Producer.java, MyConn.java
│   └── recursiveTreeObjectClasses/
│       └── movieTreeObject.java                   ← JFoenix Tree Mapping Class
└── resources/
    ├── images/                                    ← Assets and Visual Monograms
    └── styleSheets/
        └── dark_theme.css                         ← Material Dark CSS Configuration

```

---

## Dependencies & Technical Stack

The application targets **Java 8 (v1.8)** environments and depends on the following specific library variations:

* **JFoenix (v8.0.7):** Open-source Java library implementing Google Material Design components.
* **FontAwesomeFX (v4.7.0-5):** Comprehensive iconography fonts collection mapping.
* **Google Gson (v2.8.1):** High-performance JSON parser for reading configurations.
* **MySQL Connector/J (v5.0.7):** Native JDBC client driver targeting MySQL instances.

---

## Environment Setup & Initialization

### Prerequisites

* **Java Development Kit (JDK) 8** configured on system path variables.
* **MySQL Server (v5.x or v8.x)** running locally at `localhost:3306`.
* **Apache Ant** or **NetBeans IDE 8+** for compiling and packaging.

### 1. Database Initialization

Execute the following structural definition script inside your local MySQL server instance to configure the runtime schema environment:

```sql
CREATE DATABASE movies_and_tv_series_database CHARACTER SET utf8;
USE movies_and_tv_series_database;

CREATE TABLE franchises (id INT PRIMARY KEY, name VARCHAR(255), description TEXT);
CREATE TABLE producers  (id INT PRIMARY KEY, name VARCHAR(255), description TEXT);
CREATE TABLE studios    (id INT PRIMARY KEY, name VARCHAR(255), description TEXT, started_date DATE);

CREATE TABLE movies (
    ID            INT PRIMARY KEY,
    Name          VARCHAR(255),
    Description   TEXT,
    IMDB_Rating   VARCHAR(10),
    Catagery      VARCHAR(100),
    Franchises_ID INT,
    Producers_ID  INT,
    Studios_ID    INT,
    image         LONGBLOB,
    FOREIGN KEY (Franchises_ID) REFERENCES franchises(id),
    FOREIGN KEY (Producers_ID) REFERENCES producers(id),
    FOREIGN KEY (Studios_ID) REFERENCES studios(id)
);

```

### 2. Compilation and Build Instructions

#### Option A: Building via Command Line (Apache Ant)

Ensure environment path entries for `JAVA_HOME` and `ANT_HOME` point directly to your functional tooling setups, then execute:

```bash
ant clean jar

```

The deployable binary archive will compile out directly to `dist/Movie_And_TV_Series_Software_Modern.jar`.

#### Option B: Building via NetBeans IDE

1. Open the project inside NetBeans (`File -> Open Project`).
2. Right-click the project root, navigate to Properties, and clean up any broken reference paths inside `nbproject/project.properties` by re-linking the dependencies located under your machine's filesystem paths or matching project folders.
3. Clean and build the target executable binary suite via **Shift + F11** (or press **F6** to compile and run instantly).

### 3. Execution Mechanics

Fire up the executable environment inside the built compilation context using:

```bash
java -jar dist/Movie_And_TV_Series_Software_Modern.jar

```

* **Initial Startup Phase:** The system detects missing storage files and prompts a local GUI screen requesting targeting parameters. Properties write directly down into an active `config.txt` instance within the deployment environment.
* **Standard Operations Phase:** The system reads the active JSON string layout automatically on launch to initialize JDBC connections without displaying onboarding workflows.

---

## Development Roadmap & Key Improvements

Contributions and patches addressing the following priority tracks are highly welcome:

* **Security hardening:** Transition existing inline raw query string concatenations into robust `PreparedStatement` mappings to mitigate SQL injection vectors.
* **Configuration management:** Implement credential encryption methods for the local configuration setup files rather than relying on standard plain-text file formatting.
* **Database optimization:** Transition manual transactional record index fetches out towards robust, native database-side engine configurations using `AUTO_INCREMENT`.
* **Architecture refinements:** Introduce connection pooling patterns (e.g., HikariCP) to phase out global static state persistence models.

---

## License

This application is distributed under the terms defined within the repository structure. For further technical insights or code verification logs, review active issues tabs or pull request branches.
