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
## 4、网络状态类NetworkManager
网络管理采用单例模式，需要在application里初始化该类，如下所示
```
    NetworkManager.get().init(Context);
```
在退出应用的时候释放资源并且清除添加的网络状态回调监听器
```
    NetworkManager.get().release(Context);
    NetworkManager.get().clearNetworkListener();
```
如果你需要监听网络状态变化，可以增加一个监听器，如下所示：
```
    NetworkManager.get().addNetworkListener(NetworkManager.NetworkListener);
```
如果不需要再使用了可以移除掉这个监听器，你可以选择用完就立刻移除，也可以选择在退出app时统一移除
```
    NetworkManager.get().removeNetworkListener(NetworkManager.NetworkListener);
```
以下为常用方法
```
    NetworkManager.get().isNetworkAvailable()//网络是否可用
    NetworkManager.get().isWifi()//是否在wifi下
    NetworkManager.get().getNetType()//获取当前网络类型（未连接/WIFI/4G/3G/2G）
    NetworkManager.get().getSimOperator()//获取运营商代号（0：未知 1：移动 2：联通 3：电信）
    NetworkManager.get().getOperatorInfo()//获取运营商（基站）信息
```
记得加入下面两个权限，确保功能正常使用
```
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```
## 5、加密相关
目前只收入了4个加密相关类
- AES
- MD5
- RSA
- SHA1