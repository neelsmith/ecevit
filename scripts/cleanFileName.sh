#!/bin/sh
#
# script to convert spaces in file names to underscore _

for file in *; do newfile=$( echo "$file" | tr -d \\n | sed 's/ /_/g' );
   test "$file" != "$newfile" && mv "$file" "$newfile"; done
