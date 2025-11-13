package luke.stardew.misc;

public class PlayerEffect {

	protected float speedIncrement = 0.0f;

	public static final PlayerEffect speedBoost;

	public PlayerEffect speedIncrement(float value) {
		speedIncrement = value;
		return this;
	}

	public float getSpeedIncrement() {
		return speedIncrement;
	}

	static {
		speedBoost = new PlayerEffect().speedIncrement(0.1f);
	}
}
