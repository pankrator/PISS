# PISS
Scheduler server and Worker client implementation in Java for PISS project

## Structure
This repository is devided into 3 different projects:
* JobScheduler - This one contains the REST server and Job Scheduler server
* Worker - contains Worker abstraction and Connection layer for the client
* GraphWorker - contains implementation of a simple worker for graph algorithms which is not finished, but a skeleton base for what the Worker must look like

## Running the projects
In order for the project to work you have to import all of them in Eclipse. At first **JobScheduler** won't work because it needs 2 ```jar``` files which are **gson-2.5.jar** and **nanohttpd-2.2.0.jar**. Add them to **JobScheduler** build path via Eclipse. **Ask Nikolay how if you don't know how!**. The second step is to connect **Worker** project to **GraphWorker** project in order to find the needed interfaces and classes.

After these steps are done and projects in Eclipse are not RED you are ready to run **JobScheduler**. You can export runnable ```jar``` file from Eclipse for this project. Then you can run it via command line by this command: ```java -jar scheduler.jar```. There is additional parameter that you can pass via command line to adjust the port for the Job Scheduler. ```java -jar scheduler.jar 5000```
Default port is ```5000```.
