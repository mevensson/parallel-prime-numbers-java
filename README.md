# parallel-prime-numbers-java

## Naive

| Algorithm        | threads |   10^5 | 2*10^5 | 3*10^5 | 4*10^5 | Speedup |
|------------------|--------:|-------:|-------:|-------:|-------:|--------:|
| naive            |     n/a |    3.6 |     14 |     29 |     50 |
| future_naive     |       1 |    3.8 |     13 |     28 |     49 |
| future_naive     |       2 |    2.0 |    7.0 |     15 |     25 |    x1.9 |
| future_naive     |       3 |    1.4 |    4.9 |     10 |     17 |    x2.8 |
| future_naive     |       4 |    1.1 |    3.7 |    7.8 |     13 |    x3.6 |
| future_naive     |       5 |    1.0 |    3.4 |    7.3 |     12 |    x3.9 |
| future_naive     |       6 |    0.9 |    3.2 |    6.9 |     12 |    x4.1 |
| future_naive     |       7 |    0.9 |    3.0 |    6.4 |     11 |    x4.3 |
| future_naive     |       8 |    0.8 |    2.8 |    6.1 |     11 |    x4.6 |
| forkjoin_naive   |       1 |    3.6 |     13 |     29 |     50 |
| forkjoin_naive   |       2 |    1.9 |    6.7 |     14 |     25 |    x2.0 |
| forkjoin_naive   |       3 |    1.3 |    4.7 |    9.9 |     17 |    x2.9 |
| forkjoin_naive   |       4 |    1.1 |    3.7 |    7.9 |     13 |    x3.6 |
| forkjoin_naive   |       5 |    1.0 |    3.4 |    7.3 |     13 |    x3.8 |
| forkjoin_naive   |       6 |    0.9 |    3.2 |    6.8 |     12 |    x4.1 |
| forkjoin_naive   |       7 |    0.9 |    3.1 |    6.3 |     11 |    x4.3 |
| forkjoin_naive   |       8 |    0.9 |    2.8 |    6.2 |     10 |    x4.6 |
| stream_naive     |     n/a |    3.6 |     13 |     28 |     49 |
| par_stream_naive |       1 |    3.6 |     13 |     28 |     49 |
| par_stream_naive |       2 |    1.9 |    6.8 |     14 |     25 |    x1.9 |
| par_stream_naive |       3 |    1.4 |    4.7 |     10 |     17 |    x2.8 |
| par_stream_naive |       4 |    1.1 |    4.0 |    8.4 |     14 |    x3.3 |
| par_stream_naive |       5 |    1.1 |    3.9 |    7.4 |     13 |    x3.5 |
| par_stream_naive |       6 |    1.0 |    3.5 |    7.1 |     12 |    x3.8 |
| par_stream_naive |       7 |    0.9 |    3.3 |    6.7 |     11 |    x4.1 |
| par_stream_naive |       8 |    0.9 |    3.0 |    6.2 |     11 |    x4.3 |

## Optimized Naive

| Algorithm     |   10^6 |   10^7 | 2*10^7 | 3*10^7 |
| ------------- |-------:|-------:|-------:|-------:|
| opt_naive     |    0.6 |     14 |     36 |     65 |

## Remembering

| Algorithm     |   10^6 |   10^7 | 2*10^7 | 3*10^7 | 4*10^7 | 6*10^7 | 8*10^7 |   10^8 |
| ------------- |-------:|-------:|-------:|-------:|-------:|-------:|-------:|-------:|
| remembering   |    0.2 |    2.7 |    6.7 |     12 |     17 |     31 |     44 |     62 |

## Bitset Sieve

| Algorithm     |   10^8 |   10^9 | 2*10^9 |   2^31 |
| ------------- |-------:|-------:|-------:|-------:|
| bitset_sieve  |    0.7 |      9 |     18 |     19 |

## Boolean Array Sieve

| Algorithm             | threads |   10^8 |   10^9 | 2*10^9 | Speedup |
| --------------------- |--------:|-------:|-------:|-------:|--------:|
| boolarr_sieve         |     n/a |    1.0 |     11 |     24 |
| future2_boolarr_sieve |       1 |    1.0 |     12 |     25 |
| future2_boolarr_sieve |       2 |    0.9 |    9.0 |     19 |   x1.25 |
| future2_boolarr_sieve |       3 |    0.9 |    8.5 |     18 |   x1.30 |
| future2_boolarr_sieve |       4 |    0.9 |    9.0 |     19 |   x1.25 |
| future2_boolarr_sieve |       5 |    0.9 |    8.9 |     19 |   x1.25 |

## Long Array Sieve

| Algorithm     |   10^8 |   10^9 | 2*10^9 |   2^31 | 4*10^9 | 6*10^9 |
| ------------- |-------:|-------:|-------:|-------:|-------:|-------:|
| longarr_sieve |    0.8 |      9 |     19 |     21 |     41 |     64 |
