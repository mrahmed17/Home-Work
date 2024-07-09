For Node JS
===============
1. First install node-js (withour checking check box)
2. node --version (for checking installed node version)
3. npm (install in the directory) (User name will show)
4. npm -v (For checking npm version)


For Angular JS
==================
Tips: Write this command on cmd. {ctrl+shift+c (For copying from cmd)}
1. npm install -g @angular/cli (For install angular)
2. ng v (For checking angular version)
3. npm fund 

Tips: For create a new project - Go to File Explorer and your desire file path
4. ng new projectname --standalone false {With module function in project (Best for all program)}

4(a).ng new projectname (without module function in this project)

Tips: If error occurs on the command (cmd)
5. Run powershell as an Administration mode and type this code "Set-ExecutionPolicy RemoteSigned"
6. Or find this error on Google.

Tips: Open VScode and Connect Bootstrap
7. npm i bootstrap@5.3.3
8. connect bootstrap css and js in angular.json
Ex: 	"node_modules/bootstrap/dist/css/bootstrap.min.css"
	"node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"

Tips: Open VS code terminal and follow
9. ng serve or ng s (For start the program)
10. ctrl+c (For Close the program)

Tips: For making component
11. ng g c anycomponent-name(For make a new component)

Tips: Connect component to app-routing.module.ts 
12. {path: 'yourcomponentname', component:YourComponentName+Component}

Tips: For reuse the program from tsp to home or home to tsp
13. npm update (for update to github)



For JSON API
===============
Link: https://v17.angular.io/tutorial/first-app/first-app-lesson-14

1. npm install -g json-server (For install json api on project)
2. db.json (create db.json if db.json is not available on project)
For db server: json-server --watch db.json


Angular Program Connection Follow:
=====================================
1. Service => Url (Controller)
2. component => service (Model)
3. Html => component (View)