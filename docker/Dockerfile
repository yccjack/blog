FROM openjdk:17

MAINTAINER ycc@568166723@qq.com

ARG PROJECT_BUILD_NAME

ENV APP_NAME $PROJECT_BUILD_NAME
RUN echo $APP_NAME

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /app


WORKDIR /app

RUN mkdir -p /app/conf

EXPOSE 8082

COPY ./$PROJECT_BUILD_NAME.jar /app/app.jar

CMD java -Djava.security.egd=file:/dev/./urandom -jar app.jar
