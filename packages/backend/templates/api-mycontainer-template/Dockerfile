FROM binaries.avivagroup.com/docker-virtual/amazoncorretto:11-alpine as base
ARG BUILD_HOME=/workspace
ENV BUILD_HOME=$BUILD_HOME

FROM base as builder
WORKDIR $BUILD_HOME
ARG BUILDHOME
COPY . $BUILD_HOME/application/
WORKDIR $BUILD_HOME/application
RUN chmod -R 777 $BUILD_HOME
RUN java -Djarmode=layertools -jar $BUILD_HOME/application/build/*.jar extract

# the second stage of our build will copy the extracted layers
FROM base
RUN addgroup -S spring && adduser -S spring -G spring
USER spring
WORKDIR /application
COPY --from=builder $BUILD_HOME/application/dependencies/ ./
COPY --from=builder $BUILD_HOME/application/spring-boot-loader/ ./
COPY --from=builder $BUILD_HOME/application/snapshot-dependencies/ ./
COPY --from=builder $BUILD_HOME/application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]