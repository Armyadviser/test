JAVA_HOME=/home/falcon/Programmer/jdk1.8.0_111
CLASSPATH=.:$JAVA_HOME/lib:$JAVA_HOME/lib/tools.jar
PATH=$PATH:$JAVA_HOME/bin:$JAVA_HOME/jre/bin

export JAVA_HOME CLASSPATH PATH

CLASSPATH=$CLASSPATH:./jpcap.jar

export CLASSPATH

echo $CLASSPATH

java -cp jpcap.jar -jar color-picker.jar
