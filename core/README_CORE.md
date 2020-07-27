# core库
最底层的核心依赖库，主要用来放support库和通用的帮助类

## 1、涉及的support库
该库引用了下方这些support库
```
    dependencies {
        api 'com.google.android:flexbox:1.1.0'
        api 'com.google.android.material:material:1.0.0'
        api 'androidx.recyclerview:recyclerview:1.0.0'
        api 'androidx.appcompat:appcompat:1.1.0'
        api 'androidx.cardview:cardview:1.0.0'
        api 'androidx.annotation:annotation:1.1.0'
        api 'androidx.constraintlayout:constraintlayout:1.1.3'
    }
```

## 2、各类通用工具类


## 3、日志类PrintLog
PrintLog封装了日志打印开关，可以在app里的build.gradle文件里添加日志开关的变量，如下所示：
```
    android {
        ....

        def LOG_DEBUG = "LOG_DEBUG"
        defaultConfig {
            buildConfigField "boolean", LOG_DEBUG, "true"
        }

        buildTypes {
            debug {
                .....
            }
            release {
                buildConfigField "boolean", LOG_DEBUG, "false"
                .....
            }
        }
    }
```
在application的初始化中可以配置该变量，起到开关日志的效果
```
    PrintLog.setPrint(BuildConfig.LOG_DEBUG)
```