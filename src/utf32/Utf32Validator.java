package utf32;

public class Utf32Validator {
	public int getOffset(byte current) {
		int mask = 0x80;
		int offset = 0;
		for (; offset < 8; offset++) {
			if ((mask & current) == 0) {
				break;
			}
			mask >>= 1;
		}
		return offset;
	}

	public boolean isUtf32(byte [] data) {
		for (int i = 0; i < data.length; i++) {
			
		}
		return true;
	}

	public static void main(String[] args) {
		Utf32Validator val = new Utf32Validator();
		System.out.println(val.getOffset((byte) 0xF1));
	}
}

