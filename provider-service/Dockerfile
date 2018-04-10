FROM openjdk:8u151-jre-alpine
LABEL maintainer="Dev Productivity Team <dev-productivity@appdirect.com>" \
      description="Microservice image example, to be used as an example on tutorial" \
      version="1.0"

ENV WORKSPACE=/workspace

RUN mkdir -p $WORKSPACE
WORKDIR $WORKSPACE

ENV XMS="-Xms256m"
ENV XMX="-Xmx256m"
ENV METASPACE="-XX:MaxMetaspaceSize=96m"
ENV BASE_JAVA_OPTS="-XX:NativeMemoryTracking=summary"
ENV PACT_TEST=false

ENV JAVA_OPTS="$BASE_JAVA_OPTS $XMS $XMX $METASPACE"
ENV EXTRA_ARGS=""

ARG ARTIFACT_VERSION=latest
ADD build/libs/provider-service-${ARTIFACT_VERSION}.jar $WORKSPACE/provider-service.jar

# Expose endpoints on port 9090
EXPOSE 9090

# Expose Management endpointson port 9091
EXPOSE 9091
ENTRYPOINT [ "sh", "-c", "exec java -server $JAVA_OPTS -DPactTest=$PACT_TEST -jar $WORKSPACE/provider-service.jar $EXTRA_ARGS" ]
