JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 mvn $@ clean install -U package
cp target/LumosTracer.jar /tmp/LumosTracer.jar
cp target/LumosTracer-bootstrap.jar /tmp/
# cp target/lumos-tracer-1.0-SNAPSHOT.jar /tmp/LumosTracer.jar
