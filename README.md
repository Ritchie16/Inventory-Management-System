# Inventory Management System

A comprehensive Java-based desktop application for managing product inventory, tracking stock levels, and generating sales reports with an intuitive graphical user interface.

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Swing](https://img.shields.io/badge/GUI-Swing-blue)
![License](https://img.shields.io/badge/License-MIT-green)

## 📋 Table of Contents
- [Features](#features)
- [Screenshots](#screenshots)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Classes Overview](#classes-overview)
- [Database Format](#database-format)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

### 🏠 **Home Dashboard**
- Modern splash screen with gradient UI
- Centralized navigation with icon-based buttons
- Quick search functionality
- Responsive design with hover effects

### 📦 **Inventory Management**
- **Add Items**: Validate and add new products to inventory
- **Update Items**: Modify existing product details
- **Remove Items**: Delete products from inventory
- **Search Items**: Quick search by product ID or name

### 📊 **Reporting & Analytics**
- **Stock Reports**: Categorize items into High Stock (>800 units) and Low Stock
- **Tabbed View**: Separate tabs for different stock levels
- **Formatted Tables**: Professional data presentation with sorting

### 🔧 **Technical Features**
- Data persistence using text file database
- Input validation and error handling
- Keyboard navigation support
- Unique ID generation with gap filling
- Duplicate product prevention

## 🚀 Installation

### Prerequisites
- Java Runtime Environment (JRE) 8 or higher
- (Optional) JDK for development

### Running the Application

1. **Download the JAR file**:
   ```bash
   # Clone or download the project
   git clone https://github.com/Ritchie16/Inventory-Management-System.git
   cd Inventory-Management-System

Run the application:
bash

# Using the provided JAR file
java -jar out/artifacts/JavaProject_jar/JavaProject.jar

# Or compile and run from source
javac src/*.java
java src.Main

Building from Source

    Compile the project:
    bash

javac -d bin src/*.java

Create executable JAR:
bash

jar cfe InventorySystem.jar src.Main -C bin .

📖 Usage
Starting the Application

    Launch the application to see the splash screen

    The main dashboard appears after loading

    Use the navigation buttons on the left panel

Adding a New Product

    Click "Add Item" button

    Enter product details:

        Name (letters and spaces only)

        Quantity (positive integer)

        Price (positive number)

    Press Enter to navigate between fields

    Click "Add Item" or press Enter in price field to submit

Searching for Products

    Click "Search Item" on the header

    Enter product ID or name

    Results display instantly below the search field

Managing Inventory

    Update Items: Modify existing product information

    Remove Items: Delete products with confirmation

    View Reports: Analyze stock levels with categorized views

🏗️ Project Structure
text

Inventory-Management-System/
├── src/
│   ├── Main.java                 # Application entry point
│   ├── HomePage.java            # Main dashboard with navigation
│   ├── Database.java            # Data persistence layer
│   ├── AddItem.java             # Add new products form
│   ├── UpdateItem.java          # Update existing products
│   ├── RemoveItem.java          # Remove products functionality
│   ├── ItemSearchApp.java       # Search functionality
│   ├── ViewReport.java          # Stock reporting system
│   └── Inventory.java           # Data model class
├── icons/                       # Application icons (add your icons here)
│   ├── inventory.png
│   ├── add.png
│   ├── search.png
│   └── ...
├── out/                         # Compiled classes and JAR
│   └── artifacts/
│       └── JavaProject_jar/
│           └── JavaProject.jar
└── inventory.txt               # Database file (auto-generated)

📚 Classes Overview
🏠 HomePage.java

    Main application window with singleton pattern

    Splash screen implementation

    Navigation button management

    Gradient UI components

💾 Database.java

    File-based data persistence

    CRUD operations (Create, Read, Update, Delete)

    Unique ID generation with gap filling

    Input validation and error handling

📦 AddItem.java

    Product addition form with validation

    Rounded text field components

    Keyboard navigation support

    Duplicate product prevention

🔍 ItemSearchApp.java

    Real-time product search

    Support for both ID and name searches

    Clean results display format

📊 ViewReport.java

    Stock level categorization

    Tabbed interface for high/low stock

    Professional table formatting

    Data analysis and presentation

💾 Database Format

The application uses a text-based database (inventory.txt) with the following format:
text

ID: 1
PRODUCT NAME: Laptop
QUANTITY: 50
PRICE: K1200.50 each

ID: 2
PRODUCT NAME: Mouse
QUANTITY: 200
PRICE: K25.00 each

Data Fields

    ID: Auto-generated unique identifier

    PRODUCT NAME: Product name (string)

    QUANTITY: Stock quantity (integer)

    PRICE: Price in Malawian Kwacha (double)

🛠️ Development
Adding New Features

    Extend the Inventory class for new data fields

    Update Database.java for persistence logic

    Create new UI components following existing patterns

    Add navigation to HomePage.java

Customization

    Modify colors in HomePage.java gradient panels

    Add new icons to icons/ directory

    Adjust stock thresholds in ViewReport.java

🤝 Contributing

We welcome contributions! Please follow these steps:

    Fork the repository

    Create a feature branch (git checkout -b feature/AmazingFeature)

    Commit your changes (git commit -m 'Add some AmazingFeature')

    Push to the branch (git push origin feature/AmazingFeature)

    Open a Pull Request

Code Style

    Follow Java naming conventions

    Use descriptive variable names

    Include comments for complex logic

    Maintain consistent formatting

📄 License

This project is licensed under the MIT License - see the LICENSE file for details.
👥 Authors

    Ritchie - Initial work - Ritchie16

🙏 Acknowledgments

    Icons designed for intuitive user experience

    Java Swing framework for robust GUI development

    Open-source community for best practices
