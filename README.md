# Jacksondemo

A minimal JSON API application with a vulnerable version of Jackson Databind library, plus attack scripts, for demonstrating remote code execution exploitation of untrusted deserialization. Default exploit uses org.apache.xalan.xsltc.trax.TemplatesImpl gadget class in Xalan 2.7.2.

Jackson Databind gadget class blacklist is available here
[https://github.com/FasterXML/jackson-databind/blob/master/src/main/java/com/fasterxml/jackson/databind/jsontype/impl/SubTypeValidator.java]

## Instructions

Warning: this is an intentionally vulnerable application that, when run, allows remote users to run arbitrary code on the deployed machine. Use with caution.

The setup has been tested on a 64-bit MacOS X and java 1.8.

### Clone repo

```
git clone [https://github.com/lampska/jacksonsploit]
```

### Launch Victim application

```
cd victim
mvn package
java -Dserver.address=127.0.0.1 -Dserver.port=4040 -jar target/jacksondemo-*-SNAPSHOT.jar
```

### Client and exploit scripts

Quickstart with pre-built payload (when victim application is running):
 * list entries
 * add an entry
 * list entries to confirm add worked
 * run exploit 

```
cd attacker/
./01-list.sh
./02-add.sh
./01-list.sh
./03-exploit.sh
```

### Create custom exploit payload

 * Customize the payload in the following file: attacker/exploit/Exploit.java
 * Create new exploitfile.json by running:

```
cd attacker/
./04-create-exploitfile.sh
```

## License

This project is licensed under Apache License, Version 2.0 [http://www.apache.org/licenses/LICENSE-2.0].
