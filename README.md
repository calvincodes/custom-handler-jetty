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
* Verify only `etc/my-war-packaged-handler.xml` is uncommented in `start.d/my-handler.ini`
* Verify `start.ini` contains `--lib=target/classes/com/`. This adds 
`CustomHandlerWarPackaged` class to the server classpath.
 
* Verify your configuration.
```text
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
 jetty.version = 9.4.31.v20200723
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
 0:                    (dir) | ${jetty.base}/target/classes
 1:      1.4.1.v201005082020 | ${jetty.home}/lib/mail/javax.mail.glassfish-1.4.1.v201005082020.jar
 2:             1.0-SNAPSHOT | ${jetty.base}/lib/ext/jar-packaging-1.0-SNAPSHOT.jar
 3:                   1.7.30 | ${jetty.base}/lib/slf4j/slf4j-api-1.7.30.jar
 4:                   1.7.30 | ${jetty.base}/lib/slf4j/slf4j-jdk14-1.7.30.jar
 5:                    (dir) | ${jetty.base}/resources
 6:                    3.1.0 | ${jetty.home}/lib/servlet-api-3.1.jar
 7:                 3.1.0.M0 | ${jetty.home}/lib/jetty-schemas-3.1.jar
 8:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-http-9.4.31.v20200723.jar
 9:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-server-9.4.31.v20200723.jar
10:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-xml-9.4.31.v20200723.jar
11:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-util-9.4.31.v20200723.jar
12:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-io-9.4.31.v20200723.jar
13:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-jndi-9.4.31.v20200723.jar
14:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-security-9.4.31.v20200723.jar
15:                      1.3 | ${jetty.home}/lib/transactions/javax.transaction-api-1.3.jar
16:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-servlet-9.4.31.v20200723.jar
17:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-webapp-9.4.31.v20200723.jar
18:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-plus-9.4.31.v20200723.jar
19:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-annotations-9.4.31.v20200723.jar
20:                    7.3.1 | ${jetty.home}/lib/annotations/asm-7.3.1.jar
21:                    7.3.1 | ${jetty.home}/lib/annotations/asm-analysis-7.3.1.jar
22:                    7.3.1 | ${jetty.home}/lib/annotations/asm-commons-7.3.1.jar
23:                    7.3.1 | ${jetty.home}/lib/annotations/asm-tree-7.3.1.jar
24:                      1.3 | ${jetty.home}/lib/annotations/javax.annotation-api-1.3.jar
25:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-deploy-9.4.31.v20200723.jar
26:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-jmx-9.4.31.v20200723.jar
27:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-rewrite-9.4.31.v20200723.jar

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

* Start jetty from jetty-base (`war-packaging` module) and access `http://localhost:8080/demo/`
```text
➜  war-packaging git:(master) ✗ java -jar ~/Desktop/jetty-home/start.jar
08/21/20 01:50:03 [INFO] Logging initialized @173ms to org.eclipse.jetty.util.log.Slf4jLog
08/21/20 01:50:04 [INFO] jetty-9.4.31.v20200723; built: 2020-07-23T17:57:36.812Z; git: 450ba27947e13e66baa8cd1ce7e85a4461cacc1d; jvm 1.8.0_181-b13
08/21/20 01:50:04 [INFO] Deployment monitor [file:///Users/arpit/Desktop/personal_projects/custom-handler-jetty/war-packaging/webapps/] at interval 1
08/21/20 01:50:04 [INFO] Started ServerConnector@20322d26{HTTP/1.1, (http/1.1)}{0.0.0.0:8080}
08/21/20 01:50:04 [INFO] Started @1003ms
This is a custom WAR packaged handler
```

## Running with handler written in jar-packaged module

* Verify only `etc/my-jar-packaged-handler.xml` is uncommented in `start.d/my-handler.ini`
* Verify `lib/ext` contains `jar-packaging-1.0-SNAPSHOT.jar`.

* Verify your configuration [Gist Link](https://gist.github.com/calvincodes/26a9af0e8cb424c042df5d14f73ff246)
```text
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
 jetty.version = 9.4.31.v20200723
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
 0:                    (dir) | ${jetty.base}/target/classes
 1:      1.4.1.v201005082020 | ${jetty.home}/lib/mail/javax.mail.glassfish-1.4.1.v201005082020.jar
 2:             1.0-SNAPSHOT | ${jetty.base}/lib/ext/jar-packaging-1.0-SNAPSHOT.jar
 3:                   1.7.30 | ${jetty.base}/lib/slf4j/slf4j-api-1.7.30.jar
 4:                   1.7.30 | ${jetty.base}/lib/slf4j/slf4j-jdk14-1.7.30.jar
 5:                    (dir) | ${jetty.base}/resources
 6:                    3.1.0 | ${jetty.home}/lib/servlet-api-3.1.jar
 7:                 3.1.0.M0 | ${jetty.home}/lib/jetty-schemas-3.1.jar
 8:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-http-9.4.31.v20200723.jar
 9:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-server-9.4.31.v20200723.jar
10:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-xml-9.4.31.v20200723.jar
11:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-util-9.4.31.v20200723.jar
12:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-io-9.4.31.v20200723.jar
13:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-jndi-9.4.31.v20200723.jar
14:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-security-9.4.31.v20200723.jar
15:                      1.3 | ${jetty.home}/lib/transactions/javax.transaction-api-1.3.jar
16:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-servlet-9.4.31.v20200723.jar
17:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-webapp-9.4.31.v20200723.jar
18:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-plus-9.4.31.v20200723.jar
19:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-annotations-9.4.31.v20200723.jar
20:                    7.3.1 | ${jetty.home}/lib/annotations/asm-7.3.1.jar
21:                    7.3.1 | ${jetty.home}/lib/annotations/asm-analysis-7.3.1.jar
22:                    7.3.1 | ${jetty.home}/lib/annotations/asm-commons-7.3.1.jar
23:                    7.3.1 | ${jetty.home}/lib/annotations/asm-tree-7.3.1.jar
24:                      1.3 | ${jetty.home}/lib/annotations/javax.annotation-api-1.3.jar
25:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-deploy-9.4.31.v20200723.jar
26:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-jmx-9.4.31.v20200723.jar
27:         9.4.31.v20200723 | ${jetty.home}/lib/jetty-rewrite-9.4.31.v20200723.jar

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
 ${jetty.base}/etc/my-jar-packaged-handler.xml
```

* Start jetty from jetty-base (war-packaging module) and access `http://localhost:8080/demo/`
```text
➜  war-packaging git:(master) ✗ java -jar ~/Desktop/jetty-home/start.jar
08/21/20 01:54:54 [INFO] Logging initialized @144ms to org.eclipse.jetty.util.log.Slf4jLog
08/21/20 01:54:55 [INFO] jetty-9.4.31.v20200723; built: 2020-07-23T17:57:36.812Z; git: 450ba27947e13e66baa8cd1ce7e85a4461cacc1d; jvm 1.8.0_181-b13
08/21/20 01:54:55 [INFO] Deployment monitor [file:///Users/arpit/Desktop/personal_projects/custom-handler-jetty/war-packaging/webapps/] at interval 1
08/21/20 01:54:55 [INFO] Started ServerConnector@20322d26{HTTP/1.1, (http/1.1)}{0.0.0.0:8080}
08/21/20 01:54:55 [INFO] Started @817ms
This is a custom JAR packaged handler
```

## References
* [jetty-project/servlet-error-page-handling](https://github.com/jetty-project/servlet-error-page-handling) 
* [Stack Overflow Discussion](https://stackoverflow.com/questions/62053941/adding-custom-handler-to-jetty-throws-classnotfoundexception)
