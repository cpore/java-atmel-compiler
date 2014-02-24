#!/bin/sh

# usage: 
#   ./regress.sh

for filename in `ls *.java`
do
    echo "Regression testing MJPA2.jar $filename"

    # Make AVR assembly from our test program using our compiler
    java -jar ../MJ.jar $filename
    # Don't run tests if our code doesn't compile
    if [ $? -eq 0 ]
    then
      # Run our assembly code in the Meggy Simulator without the UI
      java -jar MJSIM.jar -b -f $filename.s > t1

      #Compile our test program with java
      javac $filename
      noext=`echo $filename| cut -d'.' -f 1`
      # Run the java-compiled program
      java $noext > t2
      diff t1 t2
    else
      echo "Skipping Tests."
    fi

    echo "DONE with $filename"
    echo "============================="
done

# I added this to clean up the files afterwards -cpore
rm t1 t2 #*.s *.class
