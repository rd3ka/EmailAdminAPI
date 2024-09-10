# Yet Another Email Admin API

A take in high-level system-design applying the mechanism of CRUD with persistance thanks to a backend database

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Configuration](#configuration)
- [Database Schema](#database-schema)  
- [Contributing](#contributing)

## Features

- **Create**: Add new employee records with name, email, and password.
- **Read**: Retrieve and display employee information.
- **Update**: Modify existing employee records.
- **Delete**: Remove employee records.
- **Password Management**: Securely store and manage passwords. Password is encrypted using AES-128 bit encryption
- **Email Generation**: Automatically generate email addresses based on employee information.

## Prerequisites

- **Java JDK 14 or higher**
- **MariaDB Server**
- **JDBC Driver for MariaDB** (e.g., `mariadb-java-client`)
- **Maven** (for building the project, optional)

## Configuration

1. **Clone the repository:**

   ```bash
   git clone https://github.com/yourusername/employee-crud-app.git
   cd employee-crud-app

2. **Install Maven** (if you plan to build the project using Maven)

   Use the defaut package manager for your distribution. Example, for Ubuntu
   ```bash
   sudo apt -y install maven
   ```
   For Windows
   ```powershell
   winget install maven
   ```
   alternatively, you can install the maven binaries, add it to your environment path

3. **Install MariaDB Server**

   Use the default package manager of your distribution. Example, for Ubuntu
   ```bash
      
      # STEP 1 : 
      sudo apt -y install mariadb-server #this will install the database server
      
      # STEP 2 :
      sudo systemctl status mariadb-server #this will check if the server is running

      # STEP 3:
      # Mostly, after installation, the mariadb server do not run, we first start the server and then enable it to function
      sudo systemctl start mariadb-server
      sudo systemctl enable mariadb-server

      # STEP 4:
      sudo mariadb
   ```
   It is advised to create a user and not uset the root user. To create a user and grant all privileges to that user, please find this [link](https://stackoverflow.com/questions/1720244/create-new-user-in-mysql-and-give-it-full-access-to-one-database)

4. ## Database Schema

   TO-DO

5. ## Contributing
   
   TO-DO