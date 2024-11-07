FROM gradle:7.6.2-jdk17 AS builder

WORKDIR /app
COPY . /app

RUN gradle bootJar -x test --no-daemon

FROM openjdk:17-jdk

WORKDIR /app


COPY --from=builder /app/build/libs/DesafioVotacao-0.0.1-SNAPSHOT.jar /app/DesafioVotacao-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "DesafioVotacao-0.0.1-SNAPSHOT.jar"]