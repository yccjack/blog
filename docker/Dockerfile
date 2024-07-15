FROM openjdk:17

MAINTAINER ycc@568166723@qq.com


RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /app


WORKDIR /app

RUN mkdir -p /app/conf

EXPOSE 8082

COPY blog-0.0.1-SNAPSHOT.jar /app/app.jar

CMD java -Djava.security.egd=file:/dev/./urandom -jar app.jar
