#!/bin/bash

algorithm=$1
shift
minthreads=$1
shift
maxthreads=$1
shift
maxprimelist=$@

for ((threads = $minthreads; threads <= $maxthreads; threads++)); do
	algorithmname=${algorithm}_${threads}
	echo "Algorithm Name: $algorithmname"
	for maxprime in $maxprimelist; do
		echo "Algorithm: $algorithm"
		echo "Max Prime: $maxprime"
		echo "Threads:   $threads"
		sudo perf stat --detailed java -jar build/libs/prime-numbers.jar $maxprime $algorithmname
		echo "Sleeping..."
		sleep 10
	done
done
