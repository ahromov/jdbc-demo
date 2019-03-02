
public class Main {

    public static void main(String[] args) {
	MyArray<String> arr = new MyArray<>();

	arr.add("string 1");
	arr.add("string 1");
	arr.add("string 1");
	arr.add("string 1");
	arr.add("string 1");

	System.out.println(arr);

	arr.remove(3);

	System.out.println(arr);
    }

}
