[ ![Download](https://api.bintray.com/packages/fangxiaole/maven/simpleconfig/images/download.svg) ](https://bintray.com/fangxiaole/maven/simpleconfig/_latestVersion)

# SimpleConfigDemo
## 1.Add the dependency
  compile 'com.lele.simpleconfiglibrary:simpleconfig:1.0.2'
## 2.使用    
* 初始化SimpleConfigM.getInstance(MyApp.app).init();    
* 开始发送
   /**
     * 开始发包
     *
     * @param ssid     WiFi的SSID
     * @param pwd      WiFi密码
     * @param phoneMac mac地址
     */
    public void send(String ssid, String pwd, String phoneMac) {
   }
* 停止发送 
  SimpleConfigM.getInstance(MyApp.app).stop();
* 退出
 SimpleConfigM.getInstance(MyApp.app).exit();
