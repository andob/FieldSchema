set -o allexport

echo "Type Archiva username:"
read -r MAVEN_PUBLISH_USERNAME
echo "Type Archiva password:"
read -s -r MAVEN_PUBLISH_PASSWORD

echo "Publishing..."

./gradlew :annotations:publish
./gradlew :processor:publish
./gradlew :processor-jpa:publish
./gradlew :processor-room:publish

set +o allexport
