FROM openjdk:8-jre-slim

RUN mkdir /app

WORKDIR /app

ADD ./api/target/brewers-api-1.0.0-SNAPSHOT.jar /app

EXPOSE 5100

CMD java -jar brewers-api-1.0.0-SNAPSHOT.jar