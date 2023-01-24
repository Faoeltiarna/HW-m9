import java.util.Arrays;

class MyQueue <T>{
    Object[] array = new Object[10];
    int size = 0;

    public void add(T value) {
        array[size] = value;
        size++;
        if (size == array.length) {
            Object[] newArray = new Object[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    public void clear(){
        size = 0;
        Arrays.fill(array, null);
    }

    public int getSize() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public T peek(){
        return (T) array[0];
    }

    @SuppressWarnings("unchecked")
    public T poll(){
        T first = (T) array[0];
        if (size - 1 >= 0) System.arraycopy(array, 1, array, 0, size - 1);
        size--;
        return first;
    }

}
