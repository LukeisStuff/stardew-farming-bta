package luke.stardew.misc;

public class PlayerEffect {
	public float speedIncrement = 0.0f;

	public static final PlayerEffect speedBoost;

	public PlayerEffect() {
	}

	public PlayerEffect speedIncrement(float value) {
		speedIncrement = value;

		return this;
	}

	public boolean isSpeedChange() {
		return speedIncrement != 0.0f;
	}

	public float getSpeedIncrement() {
		return speedIncrement;
	}

	static {
		speedBoost = new PlayerEffect().speedIncrement(0.1f);
	}
}
