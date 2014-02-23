#!/bin/sh

# usage: 
#   ./regress.sh

for filename in `ls *.in`
do
    echo "Regression testing MJPA2.jar $filename"

    # run the input file with the MJPA2 compiler
    java -jar ../MJPA2.jar $filename  >&t

    diff t $filename.OK
    echo "DONE with MJPA2.jar $filename"
    echo "============================="

done

# I added this to clean up the file afterwards -cpore
rm t
