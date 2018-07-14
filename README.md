# Java learning

This repo aims to store the source codes and some reflections while practicing Java programming.

## Environment

+ Java JDK 10.0.1
+ Java JRE 10.0.1

## Basic command to compile and run a Jave program

For compiling a Java source code, for example, `HelloWorld.java`:

```
$ javac HelloWorld.java
```

+ Use `javac -d {path} HelloWorld.java` to assign the output directory.
    - ensure output file to be in the package folder
    - enable other classes in the same package to use that class with `import`

And the above command will produce a new file (`HelloWorld.class`). To run this application, enter:

```
$ java HelloWorld
```

+ Notice that there is no `.class` behind the name of application.
