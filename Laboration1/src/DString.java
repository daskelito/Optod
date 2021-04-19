public class DString implements DynamicString { // implementera metoderna i DynamicString - se lab
	private char[] text;
	private int length = 0;
	
	public DString() {
		this(10);
	}
	
	public DString(int size) {
		size = (size<=0) ? 10 : size;
		text = new char[size];
		length = 0;
	}
	
	public DString(String str) {
		text = str.toCharArray();
		length = text.length;
	}
	
	public DString(DString str) {
		text = new char[str.length()];
		for(int i=0; i<str.length(); i++) {
			text[i] = str.charAt(i);
		}
		length = text.length;
	}
	
	private void grow() {
		char[] newArr = new char[length*2];
		System.arraycopy(text, 0, newArr, 0, text.length);
		text = newArr;
	}

	@Override
	public int length() {
		return 0;
	}

	@Override
	public char charAt(int index) {
		return 0;
	}

	public void append(char chr) {
		if(length==text.length) {
			grow();
		}
		text[length++] = chr;
	}

	@Override
	public void append(DString str) {

	}

	@Override
	public void delete(int start, int end) {

	}

	@Override
	public void delete(int index) {

	}

	@Override
	public String substring(int start, int end) {
		return null;
	}

	@Override
	public String substring(int start) {
		return null;
	}

	@Override
	public int indexOf(char chr) {
		return 0;
	}
}