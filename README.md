# Adding a custom handler to jetty

## Local Setup
### Prerequisites
1. Maven
2. Java 8+
3. Jetty

### Running Locally
1. `cd custom-handler-jetty`
2. Build. `mvn clean install`
3. `cd war-packaging`
4. Prepare your jetty-base. `java -jar /path/to/jetty-home/start.jar --create-files`
5. `java -jar /path/to/jetty-home/start.jar --add-to-start=http,jmx,deploy,ext,resources`
6. Copy handler jar. `cp ../jar-packaging/target/jar-packaging-1.0-SNAPSHOT.jar lib/ext/.`
7. Uncomment the required handler in `start.d/my-handler.ini`
8. Verify your configuration. `java -jar /path/to/jetty-home/start.jar --list-config`
9. Start jetty. `java -jar /path/to/jetty-home/start.jar`

## Running with handler written in war-packaged module
1. Verify only `etc/my-war-packaged-handler.xml` is uncommented in `start.d/my-handler.ini`
2. Verify `start.ini` contains `--lib=target/classes/com/github/calvincodes`. This adds 
`CustomHandlerWarPackaged` class to the server classpath.
 
* Verify your configuration. [Gist Link](https://gist.github.com/calvincodes/ad75043c35e8e548c8bdf8a015f91ca1)
```
➜  war-packaging git:(master) ✗ java -jar ~/Desktop/jetty-home/start.jar --list-config

Java Environment:
-----------------
 java.home = /Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre (null)
 java.vm.vendor = Oracle Corporation (null)
 java.vm.version = 25.181-b13 (null)
 java.vm.name = Java HotSpot(TM) 64-Bit Server VM (null)
 java.vm.info = mixed mode (null)
 java.runtime.name = Java(TM) SE Runtime Environment (null)
 java.runtime.version = 1.8.0_181-b13 (null)
 java.io.tmpdir = /var/folders/r3/qlf7p4l54ng5g7hxfj1pxfdr0000gn/T/ (null)
 user.dir = /Users/arpit/Desktop/personal_projects/custom-handler-jetty/war-packaging (null)
 user.language = en (null)
 user.country = US (null)

Jetty Environment:
-----------------
 jetty.version = 9.4.29.v20200521
 jetty.tag.version = master
 jetty.home = /Users/arpit/Desktop/jetty-home
 jetty.base = /Users/arpit/Desktop/personal_projects/custom-handler-jetty/war-packaging

Config Search Order:
--------------------
 <command-line>
 ${jetty.base} -> /Users/arpit/Desktop/personal_projects/custom-handler-jetty/war-packaging
 ${jetty.home} -> /Users/arpit/Desktop/jetty-home


JVM Arguments:
--------------
 -Djava.util.logging.config.file?=${jetty.base}/etc/java-util-logging.properties
 -Dorg.eclipse.jetty.util.log.class?=org.eclipse.jetty.util.log.Slf4jLog

System Properties:
------------------
 (no system properties specified)

Properties:
-----------
 java.version = 1.8.0_181
 java.version.major = 1
 java.version.micro = 0
 java.version.minor = 8
 java.version.platform = 8
 jetty.base = /Users/arpit/Desktop/personal_projects/custom-handler-jetty/war-packaging
 jetty.base.uri = file:///Users/arpit/Desktop/personal_projects/custom-handler-jetty/war-packaging
 jetty.home = /Users/arpit/Desktop/jetty-home
 jetty.home.uri = file:///Users/arpit/Desktop/jetty-home
 jetty.http.port = 8080
 jetty.webapp.addServerClasses = ${jetty.base.uri}/lib/slf4j/
 slf4j.version = 1.7.30

Jetty Server Classpath:
-----------------------
Version Information on 28 entries in the classpath.
Note: order presented here is how they would appear on the classpath.
      changes to the --module=name command line options will be reflected here.
 0:                    (dir) | ${jetty.base}/target/classes/com/github/calvincodes
 1:      1.4.1.v201005082020 | ${jetty.home}/lib/mail/javax.mail.glassfish-1.4.1.v201005082020.jar
 2:             1.0-SNAPSHOT | ${jetty.base}/lib/ext/jar-packaging-1.0-SNAPSHOT.jar
 3:                   1.7.30 | ${jetty.base}/lib/slf4j/slf4j-api-1.7.30.jar
 4:                   1.7.30 | ${jetty.base}/lib/slf4j/slf4j-jdk14-1.7.30.jar
 5:                    (dir) | ${jetty.base}/resources
 6:                    3.1.0 | ${jetty.home}/lib/servlet-api-3.1.jar
 7:                 3.1.0.M0 | ${jetty.home}/lib/jetty-schemas-3.1.jar
 8:         9.4.29.v20200521 | ${jetty.home}/lib/jetty-http-9.4.29.v20200521.jar
 9:         9.4.29.v20200521 | ${jetty.home}/lib/jetty-server-9.4.29.v20200521.jar
10:         9.4.29.v20200521 | ${jetty.home}/lib/jetty-xml-9.4.29.v20200521.jar
11:         9.4.29.v20200521 | ${jetty.home}/lib/jetty-util-9.4.29.v20200521.jar
12:         9.4.29.v20200521 | ${jetty.home}/lib/jetty-io-9.4.29.v20200521.jar
13:         9.4.29.v20200521 | ${jetty.home}/lib/jetty-jndi-9.4.29.v20200521.jar
14:         9.4.29.v20200521 | ${jetty.home}/lib/jetty-security-9.4.29.v20200521.jar
15:                      1.3 | ${jetty.home}/lib/transactions/javax.transaction-api-1.3.jar
16:         9.4.29.v20200521 | ${jetty.home}/lib/jetty-servlet-9.4.29.v20200521.jar
17:         9.4.29.v20200521 | ${jetty.home}/lib/jetty-webapp-9.4.29.v20200521.jar
18:         9.4.29.v20200521 | ${jetty.home}/lib/jetty-plus-9.4.29.v20200521.jar
19:         9.4.29.v20200521 | ${jetty.home}/lib/jetty-annotations-9.4.29.v20200521.jar
20:                    7.3.1 | ${jetty.home}/lib/annotations/asm-7.3.1.jar
21:                    7.3.1 | ${jetty.home}/lib/annotations/asm-analysis-7.3.1.jar
22:                    7.3.1 | ${jetty.home}/lib/annotations/asm-commons-7.3.1.jar
23:                    7.3.1 | ${jetty.home}/lib/annotations/asm-tree-7.3.1.jar
24:                      1.3 | ${jetty.home}/lib/annotations/javax.annotation-api-1.3.jar
25:         9.4.29.v20200521 | ${jetty.home}/lib/jetty-deploy-9.4.29.v20200521.jar
26:         9.4.29.v20200521 | ${jetty.home}/lib/jetty-jmx-9.4.29.v20200521.jar
27:         9.4.29.v20200521 | ${jetty.home}/lib/jetty-rewrite-9.4.29.v20200521.jar

Jetty Active XMLs:
------------------
 ${jetty.home}/etc/jetty-bytebufferpool.xml
 ${jetty.home}/etc/jetty-threadpool.xml
 ${jetty.home}/etc/jetty.xml
 ${jetty.home}/etc/jetty-webapp.xml
 ${jetty.home}/etc/jetty-plus.xml
 ${jetty.home}/etc/jetty-annotations.xml
 ${jetty.home}/etc/jetty-deploy.xml
 ${jetty.home}/etc/jetty-http.xml
 ${jetty.home}/etc/jetty-jmx.xml
 ${jetty.home}/etc/jetty-rewrite.xml
 ${jetty.base}/etc/my-war-packaged-handler.xml
```

* Start jetty from jetty-base (`war-packaging` module) [Gist Link](https://gist.github.com/calvincodes/2aa7fda9994eab96b7a29cdf066d82b2)
```
➜  war-packaging git:(master) ✗ java -jar ~/Desktop/jetty-home/start.jar
05/28/20 11:05:21 [INFO] Logging initialized @167ms to org.eclipse.jetty.util.log.Slf4jLog
05/28/20 11:05:22 [WARNING] Config error at <Call name="insertHandler"><Arg>
            <New id="CustomJettyHandler" class="com.github.calvincodes.CustomHandlerWarPackaged"/>
        </Arg></Call>
05/28/20 11:05:22 [WARNING]
java.security.PrivilegedActionException: java.lang.ClassNotFoundException: com.github.calvincodes.CustomHandlerWarPackaged
	at java.security.AccessController.doPrivileged(Native Method)
	at org.eclipse.jetty.xml.XmlConfiguration.main(XmlConfiguration.java:1878)
Caused by: java.lang.ClassNotFoundException: com.github.calvincodes.CustomHandlerWarPackaged
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at org.eclipse.jetty.util.Loader.loadClass(Loader.java:64)
	at org.eclipse.jetty.xml.XmlConfiguration$JettyXmlConfiguration.newObj(XmlConfiguration.java:1027)
	at org.eclipse.jetty.xml.XmlConfiguration$JettyXmlConfiguration.itemValue(XmlConfiguration.java:1561)
	at org.eclipse.jetty.xml.XmlConfiguration$JettyXmlConfiguration.value(XmlConfiguration.java:1462)
	at org.eclipse.jetty.xml.XmlConfiguration$JettyXmlConfiguration.access$600(XmlConfiguration.java:416)
	at org.eclipse.jetty.xml.XmlConfiguration$JettyXmlConfiguration$Args.<init>(XmlConfiguration.java:1720)
	at org.eclipse.jetty.xml.XmlConfiguration$JettyXmlConfiguration$Args.<init>(XmlConfiguration.java:1707)
	at org.eclipse.jetty.xml.XmlConfiguration$JettyXmlConfiguration.call(XmlConfiguration.java:963)
	at org.eclipse.jetty.xml.XmlConfiguration$JettyXmlConfiguration.configure(XmlConfiguration.java:536)
	at org.eclipse.jetty.xml.XmlConfiguration$JettyXmlConfiguration.configure(XmlConfiguration.java:489)
	at org.eclipse.jetty.xml.XmlConfiguration.configure(XmlConfiguration.java:401)
	at org.eclipse.jetty.xml.XmlConfiguration.lambda$main$1(XmlConfiguration.java:1915)
	... 2 more

Exception in thread "main" java.security.PrivilegedActionException: java.lang.ClassNotFoundException: com.github.calvincodes.CustomHandlerWarPackaged
	at java.security.AccessController.doPrivileged(Native Method)
	at org.eclipse.jetty.xml.XmlConfiguration.main(XmlConfiguration.java:1878)
Caused by: java.lang.ClassNotFoundException: com.github.calvincodes.CustomHandlerWarPackaged
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at org.eclipse.jetty.util.Loader.loadClass(Loader.java:64)
	at org.eclipse.jetty.xml.XmlConfiguration$JettyXmlConfiguration.newObj(XmlConfiguration.java:1027)
	at org.eclipse.jetty.xml.XmlConfiguration$JettyXmlConfiguration.itemValue(XmlConfiguration.java:1561)
	at org.eclipse.jetty.xml.XmlConfiguration$JettyXmlConfiguration.value(XmlConfiguration.java:1462)
	at org.eclipse.jetty.xml.XmlConfiguration$JettyXmlConfiguration.access$600(XmlConfiguration.java:416)
	at org.eclipse.jetty.xml.XmlConfiguration$JettyXmlConfiguration$Args.<init>(XmlConfiguration.java:1720)
	at org.eclipse.jetty.xml.XmlConfiguration$JettyXmlConfiguration$Args.<init>(XmlConfiguration.java:1707)
	at org.eclipse.jetty.xml.XmlConfiguration$JettyXmlConfiguration.call(XmlConfiguration.java:963)
	at org.eclipse.jetty.xml.XmlConfiguration$JettyXmlConfiguration.configure(XmlConfiguration.java:536)
	at org.eclipse.jetty.xml.XmlConfiguration$JettyXmlConfiguration.configure(XmlConfiguration.java:489)
	at org.eclipse.jetty.xml.XmlConfiguration.configure(XmlConfiguration.java:401)
	at org.eclipse.jetty.xml.XmlConfiguration.lambda$main$1(XmlConfiguration.java:1915)
	... 2 more
```

## References
* [jetty-project/servlet-error-page-handling](https://github.com/jetty-project/servlet-error-page-handling) 