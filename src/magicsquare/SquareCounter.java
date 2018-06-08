package magicsquare;


public class SquareCounter {
	public boolean isMagic3Square(int[][] data, int topLeft, int topRight) {
		// check if it's null or array is too small
		if (null == data || data.length < 3 || data[0].length < 3) {
			return false;
		}
		// check that the position is correct
		if ((topLeft > data.length - 2) || (topRight > data[0].length - 2)) {
			return false;
		}
		// check uniqueness;

		for (int i = 0; i < 3; i++) {
			int horSumm = 0;
			int verSumm = 0;
			for (int j = 0; j < 3; j++) {
				horSumm += data[topLeft + i][topRight + j];
				verSumm += data[topLeft + j][topRight + i];
			}
			if(horSumm != 15 || verSumm!= 15) {
				return false;
			}
		}
		return false;
	}

	public static int count3squares(int[][] array) {
		// check distinct
		int status = 0;
		for (int i = 0; i < 3; i++) {
			int rowSum = 0;
			int colSum = 0;
			for (int j = 0; j < 3; j++) {
				int data = array[i][j];
				rowSum += data;
				colSum += array[j][i];
				if (data < 1 || data > 9) {
					return -2;
				}
				int newStatus = (1 << data - 1) ^ status;
				if (newStatus == status) {
					// we had this one already
					return -1;
				}
				status = newStatus;
			}
			if (rowSum != 15) {
				return rowSum;
			}
			if (colSum != 15) {
				return colSum;
			}
		}
		return status ^ 0x01FF;
	}
}
