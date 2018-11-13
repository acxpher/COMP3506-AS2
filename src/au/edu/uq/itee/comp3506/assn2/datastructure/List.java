package au.edu.uq.itee.comp3506.assn2.datastructure;

import java.util.Arrays;

public class List<T> {

    /**
     * A dynamic array, growable array, resizable array, dynamic table, or array
     * list is a random access, variable-size list data structure that allows
     * elements to be added or removed.
     * <p>
     * @see <a href="https://en.wikipedia.org/wiki/Dynamic_array">Dynamic Array (Wikipedia)</a>
     * <br>
     */
  

        private static final int MINIMUM_SIZE = 1024;

        private int size = 0;
        @SuppressWarnings("unchecked")
		private T[] array = (T[]) new Object[MINIMUM_SIZE];

        /**
         * {@inheritDoc}
         */
        public boolean add(T value) {
            return add(size,value);
        }

        /**
         * Add value to list at index.
         * 
         * @param index to add value.
         * @param value to add to list.
         */
        public boolean add(int index, T value) {
           
            if (index==size) {
                array[size] = value;
            } else {
                // Shift the array down one spot
                System.arraycopy(array, index, array, index+1, size - index);
                array[index] = value;
            }
            size++;
            return true;
        }

        /**
         * {@inheritDoc}
         */
        public boolean remove(T value) {
            for (int i = 0; i < size; i++) {
                T obj = array[i];
                if (obj.equals(value)) {
                    if (remove(i)!=null) return true;
                    return false;
                }
            }
            return false;
        }

        /**
         * Remove value at index from list.
         * 
         * @param index of value to remove.
         * @return value at index.
         */
        public T remove(int index) {
            if (index<0 || index>=size) return null;

            T t = array[index];
            if (index != --size) {
                // Shift the array down one spot
                System.arraycopy(array, index + 1, array, index, size - index);
            }
            array[size] = null;
            return t;
        }

      
        /**
         * Set value at index.
         * 
         * @param index of value to set.
         * @param value to set.
         * @return value previously at index.
         */
        public T set(int index, T value) {
            if (index<0 || index>=size) return null;
            T t = array[index];
            array[index] = value;
            return t;
        }

        /**
         * Get value at index.
         * 
         * @param index of value to get.
         * @return value at index.
         */
        public T get(int index) {
            if (index<0 || index>=size) return null;
            return array[index];
        }

        /**
         * {@inheritDoc}
         */
      
        public void clear() {
            size = 0;
        }

        /**
         * {@inheritDoc}
         */
       
        public boolean contains(T value) {
            for (int i = 0; i < size; i++) {
                T obj = array[i];
                if (obj.equals(value)) return true;
            }
            return false;
        }

        /**
         * {@inheritDoc}
         */
      
        public int size() {
            return size;
        }

        /**
         * {@inheritDoc}
         */
     
        public boolean validate() {
            int localSize = 0;
            for (int i=0; i<array.length; i++) {
                T t = array[i];
                if (i<size) {
                    if (t==null) return false;
                    localSize++;
                } else {
                    if (t!=null) return false;
                }
            }
            return (localSize==size);
        }

     
  
        public String toString() {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < size; i++) {
                builder.append(array[i]).append(", ");
            }
            return builder.toString();
        }
    }
