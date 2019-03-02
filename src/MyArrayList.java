
@SuppressWarnings("unchecked")
public class MyArrayList<T> {
    private int arrSize = 8;
    private int counter = 0;    
    private T[] arr = (T[]) new Object[arrSize];

    public void add(T item) {
	if (counter < arr.length) {
	    addItem(item);
	} else {
	    resizeArr();
	    addItem(item);
	}
    }

    private void addItem(T item) {
	arr[counter] = item;
	counter++;
    }

    private void resizeArr() {
	arrSize += 8;
	T[] extendedArr = (T[]) new Object[arrSize];
	System.arraycopy(arr, 0, extendedArr, 0, arr.length);
	arr = extendedArr;
    }

    public void remove(int index) {
	arr[index] = null;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("");
	for (int i = 0; i < counter; i++) {
	    if (arr[i] != null)
		sb.append(arr[i]).append(" ");
	}
	return sb.toString();
    }

}
