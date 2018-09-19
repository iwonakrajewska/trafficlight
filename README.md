# TrafficLigth 
**!!! Unpack application to folder: !!!**
```sh
C:/iwona
```
This location is required for H2-server database access:

jdbc:h2:tcp://localhost/c:/iwona/trafficlight/h2_database/dbcontent

Otherwise application.properties needs to be modified and application repacked
### Run
To run application, start database first, then run app. 
  - 1_runDatabase.bat
  - 2_runTrafficLightsApp.bat

See output in logs folder.

Review doc folder for more info
