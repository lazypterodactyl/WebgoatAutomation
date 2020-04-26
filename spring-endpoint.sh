#~/bin/bash
rm Controllers.txt
find . -name "*.java" | while read fname; do
 grep -l '@*Controller' $fname | while read gname; do
 grep '@.*Mapping(\"/.*)' $gname | cut -d'"' -f 2 >> Controllers.txt
 done
done