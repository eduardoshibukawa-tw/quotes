#/bin/bash

cd config-server
./gradlew bootRun &

cd ..
cd julius
./gradlew bootRun &

cd ..
cd yoda
./gradlew bootRun &
