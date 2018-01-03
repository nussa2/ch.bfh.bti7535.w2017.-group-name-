cd ..
cmd /c "bin\apache-maven-3.5.2\bin\mvn -Dfile.encoding=UTF-8 -Dexec.args='-Xms256m -Xmx1536m -server' exec:java"