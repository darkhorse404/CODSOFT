# TASK 3 - STUDENT MANAGEMENT SYSTEM

Welcome to the Student Management System.

The user may choose from the specified Main Menu of actions. He/She may ADD new Student records, VIEW, UPDATE, DELETE particular Student records, DISPLAY all Student records and/or finally EXIT the system. The program is set to be menu-driven. The user has to iterate through the Main Menu and sub menus to operate.

All the information and records enterred by the user is stored in a file named `StudentDatabase.txt` to ensure that data is not deleted while re-running the program. The data is stored in object type, so the text file is not directly readable by the user but the program itself reads the file, if the file exists, and extracts the data in appropriate format for future use. It also prompts the user incase it could not find the file in the specified destination path, or if it could not find particular records.

The project has a console-based user interface.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Usage

Following the Folder Structure, the `StudentManagementSystem.java` source file inside the `src` folder is needed to be Run for usage.
The programme ensures re-runability by storing all the data enterred by the user inside a text file named `StudentDatabase.txt`. This text file needs not to be created by the user before use as the programme itself creates the file in the current directory, i.e., `src` and uses the file.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
