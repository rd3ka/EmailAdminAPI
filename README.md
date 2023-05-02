# Yet Another Email Administration System

## Purpose
The main development of this project started back in the year 2021. This project is a guided
project from the [Udemy](https://www.udemy.com/course/practice-java-by-building-projects/) 
Certification Course "_**Practice Java By Building Projects**_" with my own take. It is also
my attempt to understand **High Level System Design** using `java`

## File Structure
Below if the file structure of _yet another email administration system_. Individual abstractions
have packaged separately in subpackages and the main class is __App__ which resides on the root
or the top of the file tree.
``` 
.
├── main
│   └── java
│       └── com
│           └── admin
│               ├── App.java
│               ├── email
│               │   ├── Email.java
│               │   └── EnterpriseEmail.java
│               ├── password
│               │   └── Password.java
│               └── users
│                   ├── educational
│                   │   ├── Educational.java
│                   │   ├── PostSchool.java
│                   │   └── School.java
│                   ├── Enterprise.java
│                   └── Users.java
```
## Tools Used
* Intellij Idea Community Edition
* Microsoft Open JDK 17
* Git

