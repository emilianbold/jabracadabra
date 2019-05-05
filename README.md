jAbracadabra
==

A (work in progress) agent to support old JARs that need the javax.* packages
in containers that only have jakarta.*


Work notes
==

The sample demo app used initially is https://github.com/emilianbold/petstore-ee7
ie. the classic pet store that now uses javax.

This required WildFly.

In order to register the agent in Wildfly bin/standalone.conf must be edited and
this line added:

JAVA_OPTS="$JAVA_OPTS -javaagent:$PATH_TO_JABRACADABRA.jar"
