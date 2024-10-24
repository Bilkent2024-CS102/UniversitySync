# UniversitySync
Term project for CS102, Spring 2023-2024. Meeting dates and contents, along with general time stamps in development can be found under Meetings 3I.txt
## Demo Video
https://youtu.be/ji3UqtAV1To
## IMPORTANT WARNING
If you get some import or package errors double-check that the only source path is UniversitySync\src.
You can do this by CTRL+SHIFT+P (Command palette), choose "Java: Configure Classpath" and change it to UniversitySync\src ONLY and "Apply Settings" below.
## About UML
You can find the text file for generating the whole UML diagram (uml.txt). To access the diagram easily, you may just 
1) Go to this website: https://www.planttext.com/
2) Copy-paste the text file to the website. 
3) Click on the PNG button and now you have it.

![image](https://github.com/Bilkent2024-CS102/UniversitySync/assets/74462484/4b75ca16-7f44-43be-ad8c-9a13b1c79e7d)

The current diagram is as follows:
![image](https://github.com/Bilkent2024-CS102/UniversitySync/blob/main/uml.png)

You can download and zoom it. Note that the image here is not the final version of the uml but rather a demonstration to how to display the uml.

## Requirements and steps for the database to work properly:
This project uses a MySQL database hosted locally. To be able to run the program with the database, you need to do following:
1) Download a version of MySQL server and MySQL Workbench suitable for you from https://dev.mysql.com/downloads/workbench/ . In the current project, version 8.0.x works fine.
2) During the installation process, ensure that you installed both workbench and the server.
3) Open MySQL Workbench. Create a new connection with username 'root' and password you choose. A sample tutorial can be found here: https://www.youtube.com/watch?v=b6Dme76UzmQ
4) Connect to server using that connection, save your password to vault if you choose so.
5) Click 'Open a SQL script file' option and open both 'Final Data Set.sql' and 'createInitialDatabaseTables.sql'.
6) Run first createInitialDatabaseTables and then Final Data Set. Do it only once, not before everytime before running the project code.
7) Open the project code, and in the HomePage.java file (located in src\app\controller\) main method, change 'password' variable to your own MySQL password.
8) In case you think there is something unwanted happened regarding the database or you want to reset sample data, you can repeat step 6. This will delete all the tables and reconstruct them.

## Requirements for the app and the ui to work properly:
This project uses JDK version 22 and the corresponding JavaFX version. We developed this project as a Maven project on the IntelliJ IDEA. The app runs properly in IntelliJ IDEA while other IDEs are not tested (except VSC). We HIGHLY suggest you use IntelliJ, as VSC was tested to be very unreliable.
UI works best on a 1920x1080 screen, but in case of display errors on Windows display scaling can be altered for the UI to work more fluently on other resolutions.

## Troubleshooting
In IntelliJ if you encounter problems,
1) Ensure that your source and target paths for the project are set correctly, src directory should be set as source and target directory should be set as target.
2) Make sure no classes are excluded from the compiler.

## Dependencies
1) Maven
2) JavaFX v22.0.1
3) JDK v22
4) MySQL Workbench v8.0.37
5) MySQL Connector Java v8.0.33
