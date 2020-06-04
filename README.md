# GWT Regular Expression

![GWT3/J2CL compatible](https://img.shields.io/badge/GWT3/J2CL-compatible-brightgreen.svg)  [![License](https://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html) [![Chat on Gitter](https://badges.gitter.im/hal/elemento.svg)](https://gitter.im/gwtproject/gwt-modules) ![CI](https://github.com/gwtproject/gwt-regexp/workflows/CI/badge.svg)

A future-proof port of the `com.google.gwt.regexp.RegExp` GWT module, with no dependency on `gwt-user` (besides the Java Runtime Emulation), to prepare for GWT 3 / J2Cl.

##  Migrating from `com.google.gwt.regexp.RegExp`


1. Add the dependency to your build.

   For Maven:

   ```xml
   <dependency>
     <groupId>org.gwtproject.regexp</groupId>
     <artifactId>gwt-regexp</artifactId>
     <version>HEAD-SNAPSHOT</version>
   </dependency>
   ```

   For Gradle:

   ```gradle
   implementation("org.gwtproject.regexp:gwt-regexp:HEAD-SNAPSHOT")
   ```

2. Update your GWT module to use

   ```xml
   <inherits name="org.gwtproject.regexp.RegExp" />
   ```

3. Change the `import`s in your Java source files:

   ```java
   import org.gwtproject.timer.client.Timer;
   ```

## Instructions

To build gwt-regexp:

* run `mvn clean install`

on the parent directory.

To run the j2cl tests:

* switch to the 'gwt-regexp-j2cl-tests' directory
* run `mvn j2cl:clean` & `mvn j2cl:test`

**Note: To build the module you need Maven 3.6.3 or newer**

## System Requirements

**GWT Regular Expression requires GWT 2.9.0 or newer!**


## Dependencies

GWT Regular Expression does not depend on any other module.
