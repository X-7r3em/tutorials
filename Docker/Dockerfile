FROM openjdk:8-alpine

COPY ./JavaApp /usr/app

WORKDIR /usr/app

CMD ["java", "-jar", "continuous2-1.0-SNAPSHOT.jar"]

------------------------------------------------------

FROM openjdk:8-alpine -> Parent Image

COPY ./JavaApp /usr/app - Copy all the contents from ./ on the host to the /usr/app on the container

WORKDIR /usr/app - go to /usr/app in the container

CMD ["java", "-jar", "continuous2-1.0-SNAPSHOT.jar"] -> execute the container command - java -jar -thejar.jar. CMD takes a list of strings representing
the command followed by its parameters "command", "parameter1", "parameter2". There can be only one CMD per Dockerfile.

ENTRYPOINT ["java"] -> Specifies the entrypoint/executable command and we need to input the arguments ourselves. If we need to set
a default value for the entrypoint, then we follow it with a CMD with the default parameters. We can override the CMD parameters
when we run the image directly. Example of default value:
ENTRYPOINT ["java"]
CMD ["-jar", "continuous2-1.0-SNAPSHOT.jar"]
