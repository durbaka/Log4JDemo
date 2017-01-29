FROM java:8

ENV url="http://192.168.89.39:8125/git/root/Demo1_spring_boot_app.git/demo2.properties"
RUN echo "$url"

WORKDIR /tmp/code/demo1

ADD ./log-demo-ms2/target/log-demo-ms2-0.0.1-SNAPSHOT.jar /tmp/code/demo1/log-demo-ms2.jar
ADD run.sh /tmp/code/demo1/run.sh

RUN chmod +x /tmp/code/demo1/run.sh

EXPOSE 9002

CMD /tmp/code/demo1/run.sh
