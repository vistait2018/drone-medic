
---

### Description



Name of Company is Drone Medic
We have a fleet of 10 drones who deliver medical supplies to different locations
the drone history is in dronelog.txt in C:\Users\Jide\Documents\drone-medic every 10sec
the drone history is also persisted into the database every 60secs
the is a drone automation task the change the state of the drone and also charge and dicharges the drone 2mins


 the Api exposed are
1. http://localhost:9090/api/drones --get all drones
2. http://localhost:9090/api/drones/id ----get a particular drone
3.http://localhost:9090/api/add-drone   ---to add a new drone
4.http://localhost:9090/api/drone/id/check-medication  -- check medication on a particular drone {id} is drone Id
5. http://localhost:9090/api/drone/{droneId}/add-medications/{medicationId} --To load a drone with medication
6. http://localhost:9090/api/drone-to-land -- to check available drones to land
7. http://localhost:9090/api/drone-to-load -- to check drones availabble for loading
8.  http://localhost:9090/api/medical/upload-photo -- to upload medical images
9. http://localhost:9090/api/drone-histories   -- to get all drone histories after logging start
10. http://localhost:9090/api/drone-history/{droneId}    -- to get history of a particular drone
11. http://localhost:9090/api/medications/all-medications -- get all medications
12. http://localhost:9090/api/medications/add-medication   -- to add ameidcation
13. http://localhost:9090/api/medications/medication/{id}     -- search for a particular medication


Database
H2 management console
url is http://localhost:9090/h2 on browser
JDBC Url jdbc:h2:mem:medicDb
user name sa
password none
Building jar: C:\Users\Jide\Documents\drone-medic\drone-medic\target\drone-medic-0.0.1-SNAPSHOT.jar

