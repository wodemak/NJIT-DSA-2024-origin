1.The hash function is like 
    public int hashCode(String m) {
        int hash = 5381;
        //int mod = 994853743;
        // Implement hash function here.
        for(int i = 0; i < m.length(); ++i)
        {
            hash = ((hash * 5381) + (int)(m.charAt(i)) );
        }
        return Math.abs(hash);
    }

2.To get the top100, I first get the sorted array through TreeToArrayVisitor's method, and the sort of the
    array is using quick sort by 3 paths. And then I return the sorted array to the implementation's report.

3.My implementation is correct as it pass all the test and its output is rational.

4.For the hashTable solution
    test sapmirussian.txt... Test took 53 ms
    Count of words in total: 456
    Count of unique words:    347
    Count of words to ignore:    39
    Ignored words count:      19
    maxProbingSteps count:      4
    CollisionCount count:      110
    Test took 53 ms

    test small.txt...
    Count of words in total: 2308
    Count of unique words:    233
    Count of words to ignore:    39
    Ignored words count:      425
    maxProbingSteps count:      7
    CollisionCount count:      541
    Test took 47 ms


    test Bulk.txt...

    Count of words in total: 2378668
    Count of unique words:    97115
    Count of words to ignore:    39
    Ignored words count:      368775
    maxProbingSteps count:      28
    CollisionCount count:      122110
    Test took 6913 ms
    This test is relatively slow.

    WarPeace.txt...

    Count of words in total: 480967
    Count of unique words:    17560
    Count of words to ignore:    39
    Ignored words count:      95180
    maxProbingSteps count:      40
    CollisionCount count:      69520
    Test took 350 ms


    test emptybook.txt...

    Count of words in total: 0
    Count of unique words:    0
    Count of words to ignore:    39
    Ignored words count:      0
    maxProbingSteps count:      0
    CollisionCount count:      0



    test tiny.txt...
    Count of words in total: 38
    Count of unique words:    29
    Count of words to ignore:    39
    Ignored words count:      6
    maxProbingSteps count:      1
    CollisionCount count:      7
    Test took 21 ms


    test word.txt...

    Count of words in total: 1
    Count of unique words:    1
    Count of words to ignore:    39
    Ignored words count:      0
    maxProbingSteps count:      0
    CollisionCount count:      0
    
    book tiny.txt

    Count of words in total: 38
    Count of unique words:    29
    Count of words to ignore:    39
    Ignored words count:      6
    maxProbingSteps count:      1
    CollisionCount count:      7
   
   
    book small.txt

    Count of words in total: 2308
    Count of unique words:    233
    Count of words to ignore:    39
    Ignored words count:      425
    maxProbingSteps count:      7
    CollisionCount count:      541


    ==> Starting to analyse book MetaMorph.txt

    Count of words in total: 22128
    Count of unique words:    2967
    Count of words to ignore:    39
    Ignored words count:      3404
    maxProbingSteps count:      8
    CollisionCount count:      1581



    ==> Starting to analyse book Species.txt

    Count of words in total: 174145
    Count of unique words:    8991
    Count of words to ignore:    39
    Ignored words count:      37889
    maxProbingSteps count:      9
    CollisionCount count:      19724

    ==> Starting to analyse book Ulysses.txt

    Count of words in total: 225598
    Count of unique words:    29127
    Count of words to ignore:    39
    Ignored words count:      46290
    maxProbingSteps count:      12
    CollisionCount count:      25347

    ==> Starting to analyse book WarPeace.txt

    Count of words in total: 480967
    Count of unique words:    17560
    Count of words to ignore:    39
    Ignored words count:      95180
    maxProbingSteps count:      40
    CollisionCount count:      69520



    ==> Starting to analyse book Bulk.txt

    Count of words in total: 2378668
    Count of unique words:    97115
    Count of words to ignore:    39
    Ignored words count:      368775
    maxProbingSteps count:      28
    CollisionCount count:      122110


    ==> Starting to analyse book sapmirussian.txt

    Count of words in total: 456
    Count of unique words:    347
    Count of words to ignore:    39
    Ignored words count:      19
    maxProbingSteps count:      4
    CollisionCount count:      110

    ==> Starting to analyse book japanese.txt

    Count of words in total: 72
    Count of unique words:    39
    Count of words to ignore:    39
    Ignored words count:      1
    maxProbingSteps count:      3
    CollisionCount count:      26

As to the BSTsolution
    sapmirussian.txt...

    Count of words in total: 456
    Count of unique words:    347
    Count of words to ignore:    39
    Ignored words count:      19
    maxProbingSteps count:      Tree has max depth of 0.
    Longest collision chain in a tree node is 0
    Min path height to bottom: 3
    Max path height to bottom: 15
    Ideal height if balanced: 7.0

    Test took 52 ms


    Starting to test small.txt...

    Count of words in total: 2308
    Count of unique words:    233
    Count of words to ignore:    39
    Ignored words count:      425
    maxProbingSteps count:      Tree has max depth of 0.
    Longest collision chain in a tree node is 0
    Min path height to bottom: 3
    Max path height to bottom: 17
    Ideal height if balanced: 8.0

    Test took 23 ms

    test Bulk.txt...

    Count of words in total: 2378668
    Count of unique words:    97115
    Count of words to ignore:    39
    Ignored words count:      368775
    maxProbingSteps count:      Tree has max depth of 0.
    Longest collision chain in a tree node is 1
    Min path height to bottom: 5
    Max path height to bottom: 41
    Ideal height if balanced: 15.0

    Test took 1533 ms

    This process is a little bit slow.

    test WarPeace.txt...

    Count of words in total: 480967
    Count of unique words:    17560
    Count of words to ignore:    39
    Ignored words count:      95180
    maxProbingSteps count:      Tree has max depth of 0.
    Longest collision chain in a tree node is 1
    Min path height to bottom: 6
    Max path height to bottom: 36
    Ideal height if balanced: 14.0

    Test took 247 ms


    Starting to test emptybook.txt...

    Count of words in total: 0
    Count of unique words:    0
    Count of words to ignore:    39
    Ignored words count:      0
    maxProbingSteps count:      null root


    Starting to test tiny.txt...

    Count of words in total: 38
    Count of unique words:    29
    Count of words to ignore:    39
    Ignored words count:      6
    maxProbingSteps count:      Tree has max depth of 0.
    Longest collision chain in a tree node is 1
    Min path height to bottom: 3
    Max path height to bottom: 7
    Ideal height if balanced: 4.0

    Test took 4 ms


    Starting to test word.txt...

    Count of words in total: 1
    Count of unique words:    1
    Count of words to ignore:    39
    Ignored words count:      0
    maxProbingSteps count:      Tree has max depth of 0.
    Longest collision chain in a tree node is 1
    Min path height to bottom: 0
    Max path height to bottom: 0
    Ideal height if balanced: 0.0

    ==> Starting to analyse book tiny.txt

    Count of words in total: 38
    Count of unique words:    29
    Count of words to ignore:    39
    Ignored words count:      6
    maxProbingSteps count:      Tree has max depth of 0.
    Longest collision chain in a tree node is 1
    Min path height to bottom: 3
    Max path height to bottom: 7
    Ideal height if balanced: 4.0

    
==> Starting to analyse book small.txt

    Count of words in total: 2308
    Count of unique words:    233
    Count of words to ignore:    39
    Ignored words count:      425
    maxProbingSteps count:      Tree has max depth of 0.
    Longest collision chain in a tree node is 1
    Min path height to bottom: 3
    Max path height to bottom: 17
    Ideal height if balanced: 8.0



    ==> Starting to analyse book MetaMorph.txt

    Count of words in total: 22128
    Count of unique words:    2967
    Count of words to ignore:    39
    Ignored words count:      3404
    maxProbingSteps count:      Tree has max depth of 0.
    Longest collision chain in a tree node is 1
    Min path height to bottom: 4
    Max path height to bottom: 27
    Ideal height if balanced: 11.0



    ==> Starting to analyse book Species.txt

    Count of words in total: 174145
    Count of unique words:    8991
    Count of words to ignore:    39
    Ignored words count:      37889
    maxProbingSteps count:      Tree has max depth of 0.
    Longest collision chain in a tree node is 1
    Min path height to bottom: 5
    Max path height to bottom: 32
    Ideal height if balanced: 13.0

    ==> Starting to analyse book Ulysses.txt

    Count of words in total: 225598
Count of unique words:    29127
Count of words to ignore:    39
Ignored words count:      46290
maxProbingSteps count:      Tree has max depth of 0.
Longest collision chain in a tree node is 1
Min path height to bottom: 5
Max path height to bottom: 36
Ideal height if balanced: 13.0


==> Starting to analyse book WarPeace.txt

    Count of words in total: 480967
    Count of unique words:    17560
    Count of words to ignore:    39
    Ignored words count:      95180
    maxProbingSteps count:      Tree has max depth of 0.
    Longest collision chain in a tree node is 1
    Min path height to bottom: 6
    Max path height to bottom: 36
    Ideal height if balanced: 14.0

    ==> Starting to analyse book Bulk.txt

    Count of words in total: 2378668
    Count of unique words:    97115
    Count of words to ignore:    39
    Ignored words count:      368775
    maxProbingSteps count:      Tree has max depth of 0.
    Longest collision chain in a tree node is 1
    Min path height to bottom: 5
    Max path height to bottom: 41
    Ideal height if balanced: 15.0

    ==> Starting to analyse book sapmirussian.txt

    Count of words in total: 456
    Count of unique words:    347
    Count of words to ignore:    39
    Ignored words count:      19
    maxProbingSteps count:      Tree has max depth of 0.
    Longest collision chain in a tree node is 1
    Min path height to bottom: 3
    Max path height to bottom: 15
    Ideal height if balanced: 7.0



    ==> Starting to analyse book japanese.txt

    Count of words in total: 72
    Count of unique words:    39
    Count of words to ignore:    39
    Ignored words count:      1
    maxProbingSteps count:      Tree has max depth of 0.
    Longest collision chain in a tree node is 1
    Min path height to bottom: 2
    Max path height to bottom: 10
    Ideal height if balanced: 5.0

5. I found that the extremely difficult thing in the task is to find the suitable algorithm and to 
    satisfy the demand of test file and to modify the file writted in the previous exercise to fulfill the exercise.
    

6. I learned a lot by writting the exercise. Like the suitable algorithm ,java language and the process of understanding
    the exception that throw and figure out the probable reason.S