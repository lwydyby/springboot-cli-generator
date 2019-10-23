FROM java:8-alpine
WORKDIR /
ADD target/wwmxd-generator-1.0.0.jar  /generator.jar
EXPOSE 8777
CMD java -jar generator.jar
