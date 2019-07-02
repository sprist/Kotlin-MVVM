# StatusBar
![version](https://img.shields.io/badge/version-1.0.0-brightgreen.svg)

沉浸式导航栏

### 更新日志
#### v1.0.0
初始化版本，主要功能已经完成。

### build.gradle配置
```java
allprojects {
    repositories {
        maven { url "http://192.168.45.164:8081/nexus/content/repositories/dayan_android_lib" } 
    }
}
```
### maven配置
```xml
<dependency>
  <groupId>com.dayan.finance</groupId>
  <artifactId>statusbar</artifactId>
  <version>1.0.0</version>
  <type>aar</type>
</dependency>
```
### 依赖关系
```xml
    ext.kotlin_version = '1.3.21'
    ext.support_version = '1.0.0'
    ext.androidx_appcompat = '1.1.0-alpha04'

    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    implementation "androidx.appcompat:appcompat:$androidx_appcompat"
    implementation"androidx.legacy:legacy-support-v4:$support_version"
    implementation "androidx.annotation:annotation:$support_version"
```
### 使用
```java
        //状态栏暗色
        ImmersionBar.with(this).statusBarDarkFont(true).init()
```


