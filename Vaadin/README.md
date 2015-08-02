From Vaadin README.TXT
=============
This Vaadin zip contains all the jars of the Vaadin Framework. Additionally all dependencies are provided in the lib folder.

To use in a web project:
1. Copy all vaadin-* files except vaadin-client and vaadin-client-compiler to WEB-INF/lib in your project
2. Copy lib/*.jar to WEB-INF/lib in your project
3. Copy vaadin-client and vaadin-client-compiler to a lib folder which is on your classpath but will not be deployed. These files are only needed when compiling a module (widget set) to Javascript.

From Vaadin template README.md generated thru Netbeans (see more there)
==============
- Template for a simple Vaadin application that only requires a Servlet 3.0 container to run.
- To compile the entire project, run "mvn install".
- To run the application, run "mvn jetty:run" and open http://localhost:8080/ .

