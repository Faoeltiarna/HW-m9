import java.util.Arrays;

class MyStack <T>{
    Object[] array = new Object[10];
    int size = 0;
    public void push(T value) {
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

    public void remove(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
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
    public T peek(){
        return (T) array[size - 1];
    }

    @SuppressWarnings("unchecked")
    public T pop(){
        T first = (T) array[size -1];
        System.arraycopy(array, 0, array, 0, size - 1);
        size--;
        return first;
    }

}
