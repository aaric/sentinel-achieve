# sentinel-achieve


[![license](https://img.shields.io/badge/license-MIT-green.svg?style=flat&logo=github)](https://www.mit-license.org)
[![java](https://img.shields.io/badge/java-1.8-brightgreen.svg?style=flat&logo=java)](https://www.oracle.com/java/technologies/javase-downloads.html)
[![junit](https://img.shields.io/badge/junit-5.6.2-brightgreen.svg?style=flat&logo=junit5)](https://junit.org/junit5/docs/current/user-guide)
[![gradle](https://img.shields.io/badge/gradle-7.2-brightgreen.svg?style=flat&logo=gradle)](https://docs.gradle.org/7.2/userguide/installation.html)
[![spring boot](https://img.shields.io/badge/springboot-2.3.2-brightgreen.svg?style=flat&logo=springboot)](https://docs.spring.io/spring-boot/docs/2.3.2.RELEASE/reference/htmlsingle/)
[![spring cloud alibaba](https://img.shields.io/badge/springcloud.alibaba-2.2.6-brightgreen.svg?style=flat&logo=alibabacloud)](https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/zh-cn/index.html)
[![release](https://img.shields.io/badge/release-0.5.1-blue.svg)](https://github.com/aaric/sentinel-achieve/releases)

> Alibaba Sentinel Learning.

## 1 Gradle

```groovy
dependencies {
    implementation "com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel"
}
```

## 2 Sentinel Dashboard

> [sentinel-dashboard-1.8.1.jar](https://github.com/alibaba/Sentinel/releases/download/1.8.1/sentinel-dashboard-1.8.1.jar)

### 2.1 dashboard

#### 2.1.1 Windows

```powershell
java -jar sentinel-dashboard-1.8.1.jar `
  --server.port=8710 `
  --project.name=sentinel-dashboard `
  --auth.username=sentinel `
  --auth.password=sentinel123
```

#### 2.1.2 Linux

```base
nohup \
java -jar sentinel-dashboard-1.8.1.jar \
  --server.port=8710 \
  --project.name=sentinel-dashboard \
  --auth.username=sentinel \
  --auth.password=sentinel123 \
  > /dev/null 2>&1 &
```

### 2.2 application.yml

```yaml
spring:
  cloud:
    sentinel:
      transport:
        port: 8719
        dashboard: localhost:8710
```

## 3 SkyWalking: `apm-toolkit-logback-1.x`

> [logback plugin](https://skywalking.apache.org/docs/skywalking-java/latest/en/setup/service-agent/java-agent/application-toolkit-logback-1.x)

### 3.1 Project

#### 3.1.1 build.gradle

```groovy
dependencies {
    implementation "org.apache.skywalking:apm-toolkit-logback-1.x"
}
```

#### 3.1.1 logback.xml

- [`stl-cloud-abc-backend/src/main/resources/logback.xml`](stl-cloud-abc-backend/src/main/resources/logback.xml)
- [`stl-cloud-client-backend/src/main/resources/logback.xml`](stl-cloud-client-backend/src/main/resources/logback.xml)
- [`stl-cloud-gateway-backend/src/main/resources/logback.xml`](stl-cloud-gateway-backend/src/main/resources/logback.xml)

### 3.2 Docker Image

```bash
# stl-cloud-abc-backend
cd stl-cloud-abc-backend/
docker build --build-arg APP_NAME=stl-cloud-abc-backend --build-arg APP_VERSION=0.4.0-SNAPSHOT \
  -t ik8share/stl-cloud-abc-backend:0.4.0-SNAPSHOT .
docker tag ik8share/stl-cloud-abc-backend:0.4.0-SNAPSHOT \
  ik8share/stl-cloud-abc-backend:latest
docker push ik8share/stl-cloud-abc-backend:0.4.0-SNAPSHOT
docker push ik8share/stl-cloud-abc-backend:latest

# stl-cloud-client-backend
cd stl-cloud-client-backend/
docker build --build-arg APP_NAME=stl-cloud-client-backend --build-arg APP_VERSION=0.4.0-SNAPSHOT \
  -t ik8share/stl-cloud-client-backend:0.4.0-SNAPSHOT .
docker tag ik8share/stl-cloud-client-backend:0.4.0-SNAPSHOT \
  ik8share/stl-cloud-client-backend:latest
docker push ik8share/stl-cloud-client-backend:0.4.0-SNAPSHOT
docker push ik8share/stl-cloud-client-backend:latest

# stl-cloud-gateway-backend
cd stl-cloud-gateway-backend/
docker build --build-arg APP_NAME=stl-cloud-gateway-backend --build-arg APP_VERSION=0.4.0-SNAPSHOT \
  -t ik8share/stl-cloud-gateway-backend:0.4.0-SNAPSHOT .
docker tag ik8share/stl-cloud-gateway-backend:0.4.0-SNAPSHOT \
  ik8share/stl-cloud-gateway-backend:latest
docker push ik8share/stl-cloud-gateway-backend:0.4.0-SNAPSHOT
docker push ik8share/stl-cloud-gateway-backend:latest
```

### 3.3 Kubernetes Deploy

&emsp;&emsp;[`deploy.yaml`](deploy.yaml)
