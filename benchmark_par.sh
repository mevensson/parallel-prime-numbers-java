#!/bin/bash

algorithm=$1
shift
maxthreads=$1
shift
maxprimelist=$@

for ((threads=1; threads <= $maxthreads; threads++)); do
	algorithmname=${algorithm}_${threads}
	echo "Algorithm Name: $algorithmname"
	for maxprime in $maxprimelist; do
		echo "Algorithm: $algorithm"
		echo "Max Prime: $maxprime"
		echo "Threads:   $threads"
		sudo perf stat java -jar build/libs/prime-numbers.jar $maxprime $algorithmname
	done
done
