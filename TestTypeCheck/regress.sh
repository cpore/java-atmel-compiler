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


if [[ -d $1 ]]; then
    FILES=$(ls $1*.java)
elif [[ -f $1 ]]; then
    FILES=$1
else
    echo "$PASSED is not a valid file or directory"
    exit 1
fi

for filename in $FILES
do
    echo "Regression testing MJ.jar $filename"

    # Make AVR assembly from our test program using our compiler
    java -jar ../MJ.jar $filename >& t1

    # Make AVR assembly from our test program using reference compiler
    java -jar MJ_PA5.jar $filename >& t2

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

#createPng
# clean up the files afterwards
rm t1 t2 PA4Tests/*.s *.s *.png #*.class PA4Tests/*.dot
