package oy.tol.tira.books;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale; 

public class HashTableBookImplementation implements Book {

   
    private KeyValueHashTable<Integer> wordCountHash;
  
    private static final int MAX_WORDS = 100000;
    private static final int MAX_WORD_LEN = 100;
    private String bookFile = null;
    private String wordsToIgnoreFile = null;
    private WordFilter filter;
    private int uniqueWordCount = 0;
    private int totalWordCount = 0;
    private int ignoredWordsTotal = 0;
    private long loopCount = 0;

    public HashTableBookImplementation(){
        wordCountHash = new KeyValueHashTable<>();
        filter = new WordFilter();
    }

    @Override
    public void setSource(String fileName, String ignoreWordsFile) throws FileNotFoundException {
        // Check if both files exist. If not, throw an exception.
        boolean success = false;
        if (checkFile(fileName)) {
            bookFile = fileName;
            if (checkFile(ignoreWordsFile)) {
                wordsToIgnoreFile = ignoreWordsFile;
                success = true;
            }
        }
        if (!success) {
            throw new FileNotFoundException("Cannot find the specified files");
        }
    }
   

   
@Override
public void countUniqueWords() throws IOException, OutOfMemoryError {
    if (bookFile == null || wordsToIgnoreFile == null) {
        throw new IOException("No file(s) specified");
    }
    uniqueWordCount = 0;
    totalWordCount = 0;
    loopCount = 0;
    ignoredWordsTotal = 0;
    if(wordCountHash == null || filter == null)
    {
        wordCountHash = new KeyValueHashTable<>();
        filter = new WordFilter();
    }
    
    filter.readFile(wordsToIgnoreFile);
    FileReader reader = new FileReader(bookFile, StandardCharsets.UTF_8);
    int c;
    int[] array = new int[MAX_WORD_LEN];
    int currentIndex = 0;
    while ((c = reader.read()) != -1) {
        // If the char is a letter, then add it to the array...
        if (Character.isLetter(c)) {
            array[currentIndex] = c;
            currentIndex++;
        } else {
            // ...otherwise a word break was met, so handle the word just read.
            if (currentIndex > 0) {
                // If a word was actually read, then create a string out of the codepoints,
                // normalizing the word to lowercase.
                String word = new String(array, 0, currentIndex).toLowerCase(Locale.ROOT);
                // Reset the counter for the next word read.
                currentIndex = 0;
                addToHash(word);
            }
        }
    }

    if (currentIndex > 1) {
        String word = new String(array, 0, currentIndex).toLowerCase(Locale.ROOT);
        addToHash(word);
    }
    reader.close();
}

private void addToHash(String word) throws OutOfMemoryError {
    // Filter out too short words or words in filter list.
    if (!filter.shouldFilter(word) && word.length() >= 2) {
        Integer count = wordCountHash.find(word);
        boolean handled = false;
        if (count == null) {
            wordCountHash.add(word, 1);
            uniqueWordCount++;
            handled = true;
            if (uniqueWordCount >= MAX_WORDS) {
                throw new OutOfMemoryError("No room for more words in array");
            }
        } else {
            wordCountHash.add(word, count + 1);
            handled = true;
        }
        totalWordCount++;
        if (!handled) {
            throw new OutOfMemoryError("No room for more words in array");
        }
    } else {
        ignoredWordsTotal++;
    }
}
@Override
public void report() {
    if (wordCountHash == null) {
        System.out.println("*** No words to report! ***");
        return;
    }
    System.out.println("Listing words from a file: " + bookFile);
    System.out.println("Ignoring words from a file: " + wordsToIgnoreFile);
    System.out.println("Sorting the results...");
    // First sort the array
    Pair<String, Integer>[] sortedWordCounts = wordCountHash.toSortedArray();
    System.out.println("...sorted.");

    for(int i = 0; i < Math.min(100, sortedWordCounts.length); ++i) {
        String word = String.format("%-20s", sortedWordCounts[i].getKey()).replace(' ', '.');
        System.out.format("%4d. %s %6d%n", i+1, word, sortedWordCounts[i].getValue());
    }
    System.out.println("Count of words in total: " + totalWordCount);
    System.out.println("Count of unique words:    " + uniqueWordCount);
    System.out.println("Count of words to ignore:    " + filter.ignoreWordCount());
    System.out.println("Ignored words count:      " + ignoredWordsTotal);
    System.out.println("maxProbingSteps count:      " + wordCountHash.getMaxProbingStepst());
    System.out.println("CollisionCount count:      " + wordCountHash.getCollisionCount());
}
    @Override
    public void close() {
        bookFile = null;
        wordsToIgnoreFile = null;
        wordCountHash = null;
        if (filter != null) {
            filter.close();
        }
        filter = null;
    }

    @Override
    public int getUniqueWordCount() {
        return uniqueWordCount;
    }

    @Override
    public int getTotalWordCount() {
        return totalWordCount;
    }

    @Override
    public String getWordInListAt(int position) {
        if(wordCountHash!=null)
        {
            Pair<String, Integer>[] sortedWordCounts = wordCountHash.toSortedArray();
            if (wordCountHash!=null&&position >= 0 && position < uniqueWordCount) {
                return sortedWordCounts[position].getKey();
            }
        }
             
        
        return null;
    }
    @Override
    public int getWordCountInListAt(int position) {
        if(wordCountHash!=null)
        {
             Pair<String, Integer>[] sortedWordCounts = wordCountHash.toSortedArray();
            if (position >= 0 && position < uniqueWordCount) {
                return sortedWordCounts[position].getValue();
            }
        }
           
        return -1;
    }
    private boolean checkFile(String fileName) {
        if (fileName != null) {
            File file = new File(fileName);
            if (file.exists() && !file.isDirectory()) {
                return true;
            }
        }
        return false;
    }

}

