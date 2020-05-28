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
 
* Verify your configuration.
<script src="https://gist.github.com/calvincodes/ad75043c35e8e548c8bdf8a015f91ca1.js"></script>

* Start jetty from jetty-base (`war-packaging` module)
<script src="https://gist.github.com/calvincodes/2aa7fda9994eab96b7a29cdf066d82b2.js"></script>

## References
* [jetty-project/servlet-error-page-handling](https://github.com/jetty-project/servlet-error-page-handling) 