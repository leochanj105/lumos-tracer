export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
javac ../src/main/java/com/lumos/tracer/HindsightJNI.java -h .
gcc -fpic -I/usr/lib/jvm/java-1.8.0-openjdk-amd64/include -I/usr/lib/jvm/java-1.8.0-openjdk-amd64/include/linux/ -I./ -I/home/jingyuan/hindsight/client/include -I/home/jingyuan/hindsight/client/src -c com_lumos_tracer_HindsightJNI.c -o HindsightJNI.o

gcc -shared -o libHS.so HindsightJNI.o -Wl,-rpath,/home/jingyuan/hindsight/client/lib -L/home/jingyuan/hindsight/client/lib/ -ltracer
