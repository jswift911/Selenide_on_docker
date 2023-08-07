# Запуск тестов Selenide в Docker

Данная инструкция поможет вам запустить автоматизированные тесты, написанные с использованием Selenide, внутри контейнера Docker.

## Требования

- Установленный Docker на вашей машине.

## Шаги

### 1. Клонирование репозитория

Склонируйте данный репозиторий на свою машину:

```
git clone <URL репозитория>
```

### 2. Настройка проекта

В файле `build.gradle` добавьте следующие зависимости:

```groovy
plugins {
    id 'java'
}

group = 'org.example'
version '1.0-SNAPSHOT'

sourceCompatibility = 11

compileJava.options.encoding = "UTF-8"
compileTestJava.options.encoding = "UTF-8"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation platform('org.junit:junit-bom:5.9.1')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testImplementation 'io.qameta.allure:allure-junit5:2.21.0'
    implementation 'com.codeborne:selenide:6.17.0'
    implementation 'io.github.bonigarcia:webdrivermanager:5.3.2'
}

test {
    useJUnitPlatform()
    systemProperty 'selenide.headless', System.getProperty('selenide.headless')
}
```

### 3. Создание Dockerfile

В корневой папке проекта создайте файл с именем `Dockerfile` и добавьте в него следующий код:

```dockerfile
FROM openjdk:8
WORKDIR /app
COPY . /app
RUN apt-get update && apt-get install -y wget unzip \
  && wget https://services.gradle.org/distributions/gradle-6.7-bin.zip \
  && unzip -d /opt/gradle gradle-6.7-bin.zip \
  && rm gradle-6.7-bin.zip
ENV PATH="/opt/gradle/gradle-6.7/bin:${PATH}"
CMD ["./gradlew", "clean", "test", "--info"]
```

### 4. Сборка контейнера Docker

Откройте командную строку (терминал) и перейдите в корневую папку проекта. Затем выполните следующую команду для сборки контейнера Docker:

```
docker build -t <image_name> .
```
> Примечание: `<image_name>` - это имя, которое вы хотите присвоить вашему образу контейнера.

### 5. Запуск контейнера Docker

После успешной сборки контейнера Docker выполните следующую команду для запуска контейнера:

```
docker run <image_name>
```
> Примечание: `<image_name>` - это имя образа контейнера, который вы собрали на предыдущем шаге.

Теперь контейнер будет запущен и автоматизированные тесты Selenide будут выполнены внутри него.

## Заключение

Теперь вы можете запускать тесты Selenide в контейнере Docker с помощью данной инструкции. Удачного тестирования!