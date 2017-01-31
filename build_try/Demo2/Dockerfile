FROM java:8
ENV serveruri="http://10.209.25.38:8888"

RUN echo "$serveruri"

ADD ./demo2_redis_mongo\target\demo2_redis_mongo-0.0.1-SNAPSHOT.jar /tmp/code/demo1/demo2_redis_mongo.jar
WORKDIR /tmp/code/demo1
RUN ls -lrt
EXPOSE 8080

RUN echo "$serveruri"

CMD java -jar kafka-test.jar --spring.cloud.config.uri="$serveruri"



