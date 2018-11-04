FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN

COPY . /tmp/

WORKDIR /tmp/

RUN mvn clean package -Pdocker

WORKDIR ubanh-info-share-rest/target

COPY service-start.sh .

RUN ls -la

RUN chmod +x service-start.sh

CMD ./service-start.sh
