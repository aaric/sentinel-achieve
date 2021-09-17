# sentinel-achieve


[![license](https://img.shields.io/badge/license-MIT-green.svg?style=flat&logo=github)](https://www.mit-license.org)
[![gradle](https://img.shields.io/badge/gradle-7.1.1-brightgreen.svg?style=flat&logo=gradle)](https://docs.gradle.org/7.1/userguide/installation.html)
[![java](https://img.shields.io/badge/java-1.8-brightgreen.svg?style=flat&logo=java)](https://www.oracle.com/java/technologies/javase-downloads.html)
[![spring boot](https://img.shields.io/badge/springboot-2.3.2-brightgreen.svg?style=flat&logo=springboot)](https://docs.spring.io/spring-boot/docs/2.3.2.RELEASE/reference/htmlsingle/)
[![spring cloud alibaba](https://img.shields.io/badge/springcloud.alibaba-2.2.6-brightgreen.svg?style=flat&logo=alibabacloud)](https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/zh-cn/index.html)
[![release](https://img.shields.io/badge/release-0.3.0-blue.svg)](http://gitlab.incarcloud.com/saic/emo-project/releases)

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
  --csp.sentinel.dashboard.server=localhost:8710 `
  --project.name=sentinel-dashboard `
  --auth.username=sentinel `
  --auth.password=sentinel123
```

#### 2.1.2 Linux

```base
nohup \
java -jar sentinel-dashboard-1.8.1.jar \
  --server.port=8710 \
  --csp.sentinel.dashboard.server=localhost:8710 \
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
