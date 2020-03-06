FROM java:8
VOLUME /tmp
ADD  user-provider.jar user-provider.jar
RUN bash -c 'touch /user-provider.jar'

EXPOSE 8000

ENTRYPOINT ["java","-jar","/user-provider.jar"]
