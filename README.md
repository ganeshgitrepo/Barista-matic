Barista-matic
=============

Barista-matic is a simple Java J2EE application which makes use of Spring-MVC, Spring Security, Hiberate, and Angular.JS.

Installation
============
To install the project, clone the Github repository and import the project into Eclipse. Make sure the OJBDC driver is installed in your local Maven repository (ojbdc6.jar) by navigating to the directory where the file is contained, and issue the following command in your Windows or Bash command line:

mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3 -Dpackaging=jar -Dfile=ojdbc6.jar -DgeneratePom=true
