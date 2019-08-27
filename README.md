[ ![Download](https://api.bintray.com/packages/fangxiaole/maven/simpleconfig/images/download.svg) ](https://bintray.com/fangxiaole/maven/simpleconfig/_latestVersion)

# SimpleConfigDemo
## 版本记录 
###  64位库更新 (2019.8.27)
* 【新增】arm64-v8a添加64位库 

##使用说明
#### 1.Add the dependency
  compile 'com.lele.simpleconfiglibrary:simpleconfig:1.0.2'
#### 2.使用    
* 初始化SimpleConfigM.getInstance(MyApp.app).init();    
* 开始发送
```java
     * 开始发包
     *
     * @param ssid     WiFi的SSID
     * @param pwd      WiFi密码
     * @param phoneMac mac地址
     */
    public void send(String ssid, String pwd, String phoneMac) {
   }
```
* 停止发送 
  SimpleConfigM.getInstance(MyApp.app).stop();
* 退出
 SimpleConfigM.getInstance(MyApp.app).exit();
