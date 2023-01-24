import java.util.Arrays;

class MyArrayList <T>{
    Object[] array = new Object[10];
    int size = 0;

    public void add(T value) {
        array[size] = value;
        size++;
        if (size == array.length) {
            Object[] newArray = new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    public void remove(int index) {
        if (index >= 0 && index < size) {
            if (size - 1 - index >= 0) System.arraycopy(array, index + 1, array, index, size - 1 - index);
            size--;
        }
        else {
            throw new IndexOutOfBoundsException();
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
    public T get(int index) {
        if (index >= 0 && index < size){
            return (T) array[index];
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }
}