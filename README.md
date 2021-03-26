# EE417_Group_K_Project (Application Name?)

## Importing the project into Eclipse

Here is how to import the project into Eclipse so that everything works properly. There are a lot of steps, because I created the project just before a new version of eclipse (`2021-3`) was released. This new version expects different default paths, so the location of web pages and Java source files must be changed manually in the project settings. If you are still using an older version of Eclipse, you may not need to do all of these steps.

1. First clone the repo.
    - `git clone https://github.com/WalJT/EE417_Group_K_Project.git`
    - Or use your git client of choise
2. Open Eclipse, and click `File -> Open Projects from File System`

![](https://i.imgur.com/O7fRqXm.png)

3. Click `Directory` and choose the root folder of the repo in the file picker. Then, click `Finish`

![](https://i.imgur.com/bo5Fy1L.png)

4. Right-click on the project in the sidebar, go to properties

![](https://i.imgur.com/IUphbzR.png)

5. Go to `Project Facets`, and enable the `Dynamic Web Module`. You may also need to change the version of the `Java` plugin to 1.8.

![](https://i.imgur.com/PgcvDFT.png)

6. Click `Additional Settings Available` which should appear below, and choos the correct location for web pages.

![](https://i.imgur.com/4DbW5w2.png)

7. In the project properties dialog, go to `Java Build Path`
8. Under the `Source` tab, click `Add Folder` and specify the correct folder, which is `EE_417_Group_K_Project/src/`, and remove the new default folder; `EE_417_Group_K_Project/src/main/java/`

![](https://i.imgur.com/mUIpOfm.png)
![](https://i.imgur.com/XWbGu3m.png)

9. Finally, under the `Libraries` tab, add the `jsp-api.jar` and `servlet-api.jar` files from your installation of Tomcat9 or web server of choice.