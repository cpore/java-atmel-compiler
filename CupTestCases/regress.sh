#!/bin/sh

# usage: 
#   ./regress.sh

for filename in `ls *.java`
do
    echo "Regression testing MJPA2.jar $filename"

    java -jar ../MJ.jar $filename
    java -jar MJSIM.jar -b -f $filename.s > t1

    javac $filename
    noext=`echo $filename| cut -d'.' -f 1`
    java $noext > t2
    diff t1 t2 
    echo "DONE with $filename"
    echo "============================="

done

# I added this to clean up the files afterwards -cpore
rm t1 t2 #*.s *.class
