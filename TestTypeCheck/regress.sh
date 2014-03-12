#!/bin/sh

# usage: 
#   ./regress.sh
function createPng()
{
    for filename in `find . -name '*.dot' -type f`
    do 
        echo "Creating $filename.png..."
        dot -Tpng $filename > $filename.png
    done
}


for filename in `ls *.java`
do
    echo "Regression testing MJ.jar $filename"

    # Make AVR assembly from our test program using our compiler
    java -jar ../MJ.jar $filename >& t1

    # Make AVR assembly from our test program using reference compiler
    java -jar MJ_PA3.jar $filename >& t2

    results=$(diff t1 t2)
    echo "$results"
    if [ -z "$results" ]
    then
      echo -e "Test \e[0;32mPASSED\e[0m"
    else
      echo -e "Test \e[0;31mFAILED\e[0m"
    fi


    echo "DONE with $filename"
    echo "============================="
done

createPng
# clean up the files afterwards
rm t1 t2 #*.s *.png *.class *.dot
