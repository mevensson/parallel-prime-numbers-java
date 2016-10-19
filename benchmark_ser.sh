#!/bin/bash

algorithm=$1
shift
maxprimelist=$@

for maxprime in $maxprimelist; do
	echo "Algorithm: $algorithm"
	echo "Max Prime: $maxprime"
	sudo perf stat java -jar build/libs/prime-numbers.jar $maxprime $algorithm
done
