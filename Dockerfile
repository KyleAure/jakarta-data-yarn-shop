FROM openliberty/open-liberty:full-java11-openj9-ubi
COPY src/main/liberty/config /config/
ADD target/jakarta-data-yarn-shop-1.0.0-SNAPSHOT.war /config/dropins/