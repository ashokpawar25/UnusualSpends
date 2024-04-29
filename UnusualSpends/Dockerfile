FROM openjdk:latest

WORKDIR /app

COPY . /app

RUN javac -d /app/build/classes -cp /app/src/main/java /app/src/main/java/com/amaap/unusualspends/*.java

CMD ["java", "-cp", "/app/build/classes:/app/build/libs/*", "com.amaap.unusualspends.Main"]