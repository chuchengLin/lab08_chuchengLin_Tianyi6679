
# parsing-starter-02

This is the starter code for the changes to the tokenizer for a lab on recursive descent parsing.

See https://ucsb-cs56-f17.github.io/lab/lab08/ for more explanation.

* [javadoc](http://ucsb-cs56-f17.github.io/parsing-starter-02/javadoc/)

# Using Maven instead of Ant.

The `pom.xml` file allows you to use Maven (`mvn`) rather than ant.

The main advantage for this project is the addition of test coverage measurement via `jacoco`.

| To do this ... | type this...  | Notes |
|----------------|---------------|-------|
| compile        | `mvn compile` |       |
| clean up       | `mvn clean`   |       |
| test           | `mvn test`    |       |
| build an executable jar    | `mvn package` | Builds a jar in `target/cs56Parser-1.0-SNAPSHOT.jar` |
| generate javadoc    | `mvn javadoc:javadoc` | Javadoc goes into `target/site/javadoc` |
| generate a test coverage report   | `mvn jacoco:report` | Report goes into `target/site/jacoco` |	
| run            | see below     | You first generate a jar, then you run the jar |

# To run

```
mvn package
java -jar target/cs56Parser-1.0-SNAPSHOT.jar
```

# Jacoco test coverage

To check test coverage:

```
mvn test
mvn jacoco:report
```

Then, in a web broswer, open up the file `./target/site/jacoco/index.html`

On CSIL, you can use this command if you are in the root directory of the repo.  If you get `X11 DISPLAY` errors, be sure you use `ssh -X`(Mac/Linux) or MobaXTerm (Windows).

```
firefox file://`pwd`/target/site/jacoco/index.html
```

On Mac, you can just type the following to open the file in your default web browser:

```
open target/site/jacoco/index.html
```


