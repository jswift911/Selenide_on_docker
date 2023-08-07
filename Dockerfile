FROM openjdk:8
WORKDIR /app
COPY . /app
RUN apt-get update && apt-get install -y wget unzip \
  && wget https://services.gradle.org/distributions/gradle-6.7-bin.zip \
  && unzip -d /opt/gradle gradle-6.7-bin.zip \
  && rm gradle-6.7-bin.zip
ENV PATH="/opt/gradle/gradle-6.7/bin:${PATH}"
CMD ["./gradlew", "clean", "test", "--info"]