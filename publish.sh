set -o allexport

echo "Publishing..."

./gradlew :annotations:publishToMavenLocal
./gradlew :processor:publishToMavenLocal
./gradlew :processor-jpa:publishToMavenLocal
./gradlew :processor-room:publishToMavenLocal
