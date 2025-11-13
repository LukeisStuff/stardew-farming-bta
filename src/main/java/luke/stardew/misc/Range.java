package luke.stardew.misc;

import java.util.Random;

public class Range {
	public static final Range EMPTY = new Range(0, 0);
	public static final Range ONE = new Range(1, 0);
	public final int min;
	public final int max;

	public Range(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public int get(Random r) {
		return r.nextInt(Math.max(1, max - min + 1)) + min;
	}
}
