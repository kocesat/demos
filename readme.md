- Transactional Outbox pattern medium blog implemented under /outbox package
  https://medium.com/akbank-teknoloji/transactional-outbox-örüntüsü-nasıl-uygulanır-d1adb8128f30
  
- Proxy desing pattern medium blog implemented under /proxy package

- Aspect Oriented Programming in Spring under /aop package
  
- Custom Naming of Java Object Fields Using Lombok and Jackson under /lombok package

- Bitwise Operators & Permission Systems under /bitwise package

- How to Mimic Usage of Delegates In Java (as in C#) under /delegate package
  https://medium.com/@kocesat/how-to-mimic-usage-of-delegates-in-java-as-in-c-9b1eb183947e

- 5 Ways to Check Duplicates in Array with Benchmarks
Here are the result:
  Benchmark                                 (location)  (size)  Mode  Cnt   Score    Error  Units
  HasDuplicateBenchmark.forLoop                  START     100  avgt   10  ≈ 10⁻⁵           ms/op
  HasDuplicateBenchmark.forLoop                  START    1000  avgt   10  ≈ 10⁻⁵           ms/op
  HasDuplicateBenchmark.forLoop                  START   10000  avgt   10  ≈ 10⁻⁵           ms/op
  HasDuplicateBenchmark.forLoop          FIFTY_PERCENT     100  avgt   10  ≈ 10⁻³           ms/op
  HasDuplicateBenchmark.forLoop          FIFTY_PERCENT    1000  avgt   10   0.005 ±  0.001  ms/op
  HasDuplicateBenchmark.forLoop          FIFTY_PERCENT   10000  avgt   10   0.042 ±  0.001  ms/op
  HasDuplicateBenchmark.forLoop                    END     100  avgt   10   0.001 ±  0.001  ms/op
  HasDuplicateBenchmark.forLoop                    END    1000  avgt   10   0.009 ±  0.001  ms/op
  HasDuplicateBenchmark.forLoop                    END   10000  avgt   10   0.081 ±  0.001  ms/op
  HasDuplicateBenchmark.streamAllMatch           START     100  avgt   10  ≈ 10⁻⁴           ms/op
  HasDuplicateBenchmark.streamAllMatch           START    1000  avgt   10  ≈ 10⁻⁴           ms/op
  HasDuplicateBenchmark.streamAllMatch           START   10000  avgt   10  ≈ 10⁻⁴           ms/op
  HasDuplicateBenchmark.streamAllMatch   FIFTY_PERCENT     100  avgt   10   0.001 ±  0.001  ms/op
  HasDuplicateBenchmark.streamAllMatch   FIFTY_PERCENT    1000  avgt   10   0.006 ±  0.001  ms/op
  HasDuplicateBenchmark.streamAllMatch   FIFTY_PERCENT   10000  avgt   10   0.052 ±  0.001  ms/op
  HasDuplicateBenchmark.streamAllMatch             END     100  avgt   10   0.001 ±  0.001  ms/op
  HasDuplicateBenchmark.streamAllMatch             END    1000  avgt   10   0.012 ±  0.001  ms/op
  HasDuplicateBenchmark.streamAllMatch             END   10000  avgt   10   0.107 ±  0.001  ms/op
  HasDuplicateBenchmark.streamAnyMatch           START     100  avgt   10  ≈ 10⁻⁴           ms/op
  HasDuplicateBenchmark.streamAnyMatch           START    1000  avgt   10  ≈ 10⁻⁴           ms/op
  HasDuplicateBenchmark.streamAnyMatch           START   10000  avgt   10  ≈ 10⁻⁴           ms/op
  HasDuplicateBenchmark.streamAnyMatch   FIFTY_PERCENT     100  avgt   10   0.001 ±  0.001  ms/op
  HasDuplicateBenchmark.streamAnyMatch   FIFTY_PERCENT    1000  avgt   10   0.006 ±  0.001  ms/op
  HasDuplicateBenchmark.streamAnyMatch   FIFTY_PERCENT   10000  avgt   10   0.056 ±  0.001  ms/op
  HasDuplicateBenchmark.streamAnyMatch             END     100  avgt   10   0.001 ±  0.001  ms/op
  HasDuplicateBenchmark.streamAnyMatch             END    1000  avgt   10   0.012 ±  0.001  ms/op
  HasDuplicateBenchmark.streamAnyMatch             END   10000  avgt   10   0.109 ±  0.001  ms/op
  HasDuplicateBenchmark.streamDistinct           START     100  avgt   10   0.001 ±  0.001  ms/op
  HasDuplicateBenchmark.streamDistinct           START    1000  avgt   10   0.011 ±  0.001  ms/op
  HasDuplicateBenchmark.streamDistinct           START   10000  avgt   10   0.097 ±  0.001  ms/op
  HasDuplicateBenchmark.streamDistinct   FIFTY_PERCENT     100  avgt   10   0.001 ±  0.001  ms/op
  HasDuplicateBenchmark.streamDistinct   FIFTY_PERCENT    1000  avgt   10   0.011 ±  0.001  ms/op
  HasDuplicateBenchmark.streamDistinct   FIFTY_PERCENT   10000  avgt   10   0.095 ±  0.001  ms/op
  HasDuplicateBenchmark.streamDistinct             END     100  avgt   10   0.001 ±  0.001  ms/op
  HasDuplicateBenchmark.streamDistinct             END    1000  avgt   10   0.011 ±  0.001  ms/op
  HasDuplicateBenchmark.streamDistinct             END   10000  avgt   10   0.107 ±  0.002  ms/op
  HasDuplicateBenchmark.streamToHashSet          START     100  avgt   10   0.001 ±  0.001  ms/op
  HasDuplicateBenchmark.streamToHashSet          START    1000  avgt   10   0.011 ±  0.001  ms/op
  HasDuplicateBenchmark.streamToHashSet          START   10000  avgt   10   0.089 ±  0.004  ms/op
  HasDuplicateBenchmark.streamToHashSet  FIFTY_PERCENT     100  avgt   10   0.001 ±  0.001  ms/op
  HasDuplicateBenchmark.streamToHashSet  FIFTY_PERCENT    1000  avgt   10   0.012 ±  0.001  ms/op
  HasDuplicateBenchmark.streamToHashSet  FIFTY_PERCENT   10000  avgt   10   0.094 ±  0.001  ms/op
  HasDuplicateBenchmark.streamToHashSet            END     100  avgt   10   0.001 ±  0.001  ms/op
  HasDuplicateBenchmark.streamToHashSet            END    1000  avgt   10   0.011 ±  0.001  ms/op
  HasDuplicateBenchmark.streamToHashSet            END   10000  avgt   10   0.093 ±  0.002  ms/op
