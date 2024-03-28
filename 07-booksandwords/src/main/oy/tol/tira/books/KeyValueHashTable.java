package oy.tol.tira.books;

public class KeyValueHashTable< V extends Comparable <V>>  {

    // This should implement a hash table.

    private Pair<String, V>[] values = null;
    private int count = 0;
    private int collisionCount = 0;
    private int maxProbingSteps = 0;
    private int reallocationCount = 0;
    private static final double LOAD_FACTOR = 0.45;
    private static final int DEFAULT_CAPACITY = 20;

    public KeyValueHashTable(int capacity) throws OutOfMemoryError {
        ensureCapacity(capacity);
    }

    public KeyValueHashTable() throws OutOfMemoryError {
        ensureCapacity(DEFAULT_CAPACITY);
    }

    

    @SuppressWarnings("unchecked")
   
    public void ensureCapacity(int capacity) throws OutOfMemoryError {
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        }
        // Assuming capacity means the count of elements to add, so multiplying by fill
        // factor.
        values = (Pair<String, V>[]) new Pair[(int) ((double) capacity * (1.0 + LOAD_FACTOR))];
        reallocationCount = 0;
        count = 0;
        collisionCount = 0;
        maxProbingSteps = 0;
    }

   
    public int size() {
        return count;
    }

    /**
     * Prints out the statistics of the hash table.
     * Here you should print out member variable information which tell something
     * about your implementation.
     * <p>
     * For example, if you implement this using a hash table, update member
     * variables of the class (int counters) in add() whenever a collision
     * happen. Then print this counter value here.
     * You will then see if you have too many collisions. It will tell you that your
     * hash function is not good.
     */
  
    public String getStatus() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Hash table load factor is %.2f%n", LOAD_FACTOR));
        builder.append(String.format("Hash table capacity is %d%n", values.length));
        builder.append(String.format("Current fill rate is %.2f%%%n", (count / (double) values.length) * 100.0));
        builder.append(String.format("Hash table had %d collisions when filling the hash table.%n", collisionCount));
        builder.append(String.format("Hash table had to probe %d times in the worst case.%n", maxProbingSteps));
        builder.append(String.format("Hash table had to reallocate %d times.%n", reallocationCount));
        return builder.toString();
    }

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
  
    public boolean add(String key, V value) throws IllegalArgumentException, OutOfMemoryError {
        // TODO: Implement this.
        if (null == key || value == null)
            throw new IllegalArgumentException("Person or phone number cannot be null");
        // Remeber to check for null values.
        if (((double) count * (1.0 + LOAD_FACTOR)) >= values.length) {
            reallocate((int) ((double) (values.length) * (1.0 / LOAD_FACTOR)));
        }
        
        // Checks if the LOAD_FACTOR has been exceeded --> if so, reallocates to a
        // bigger hashtable.
       
        int index = (hashCode(key)) % values.length;
        int tmpStep = 0;
        // Remember to get the hash key from the Person,
        // hash table computes the index for the Person (based on the hash value),
        // if index was taken by different Person (collision), get new hash and index,
        // insert into table when the index has a null in it,
        // return true if existing Person updated or new Person inserted.
        while (values[index] != null && !values[index].getKey().equals(key)) {
            index = (index + 1) % values.length;
            collisionCount++;
            tmpStep++;
        }
        maxProbingSteps = (tmpStep> maxProbingSteps) ? tmpStep : maxProbingSteps;
        if (values[index] == null) {
            values[index] = new Pair<>(key, value);
            count++;
            return true;
        } else {
            values[index].setValue(value);
            return true;
        }
    }

    public V find(String key) throws IllegalArgumentException {
        // Remember to check for null.

        // Must use same method for computing index as add method
        if (null == key)
            throw new IllegalArgumentException("Person to find cannot be null");
       /*for (int counter = 0; counter < values.length; counter++) {
            if (values[counter] != null && key.equals(values[counter].getKey())) {
                return values[counter].getValue();
            }
        }
        return null;*/
        int idx = hashCode(key)%values.length;
        int chk = 0;
        while(true)
        {
            chk++;
            if(values[idx] !=null)
            {
                if(!values[idx].getKey().equals(key))
                    idx = (idx+1)%values.length;
                else
                    return values[idx].getValue();
            }
            
            if(chk > values.length+1)
            {
                return null;
            }
        }

    }

    
    @java.lang.SuppressWarnings({ "unchecked" })
    public Pair<String, V>[] toSortedArray() {

        Pair<String, V>[] sorted = (Pair<String, V>[]) new Pair[count];
        int newIndex = 0;
        
       
        for (int index = 0; index < values.length; index++) {
            if (values[index] != null) {
                sorted[newIndex++] = values[index];
            }
        }
        
        quickSort(sorted, 0, newIndex - 1);
        
        return sorted;
    }
    
    private void quickSort(Pair<String, V>[] array, int begin, int end) {
        if (begin < end) {
            int q = partition(array, begin, end);
            quickSort(array, begin, q);
            quickSort(array, q + 1, end);
        }
    }
    
    private int partition(Pair<String, V>[] array, int begin, int end) {
        Pair<String, V> pivot = array[begin];
        int i = begin - 1;
        int j = end + 1;
    
        while (true) {
            do {
                i++;
            } while (array[i].getValue().compareTo(pivot.getValue()) > 0);
    
            do {
                j--;
            } while (array[j].getValue().compareTo(pivot.getValue()) < 0);
    
            if (i >= j) {
                return j;
            }
    
            Pair<String, V> temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }



    @SuppressWarnings("unchecked")
    private void reallocate(int newSize) throws OutOfMemoryError {
        if (newSize < DEFAULT_CAPACITY) {
            newSize = DEFAULT_CAPACITY;
        }
        reallocationCount++;
        Pair<String, V>[] oldPairs = values;
        this.values = (Pair<String, V>[]) new Pair[(int) ((double) newSize * (1.0 + LOAD_FACTOR))];
        count = 0;
        collisionCount = 0;
        maxProbingSteps = 0;
        for (int index = 0; index < oldPairs.length; index++) {
            if (oldPairs[index] != null) {
                add(oldPairs[index].getKey(), oldPairs[index].getValue());
            }
        }
    }

   
    public void compress() throws OutOfMemoryError {
        int newCapacity = (int) (count * (1.0 / LOAD_FACTOR));
        if (newCapacity < values.length) {
            reallocate(newCapacity);
        }
    }
    public int getCollisionCount() {
        return collisionCount;
    }
    public int getMaxProbingStepst() {
        return maxProbingSteps;
    }
}
