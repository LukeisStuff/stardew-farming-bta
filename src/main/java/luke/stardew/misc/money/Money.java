package luke.stardew.misc.money;
import luke.stardew.gui.hud.MoneyHudElement;
import net.minecraft.core.Global;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.world.World;

public interface Money {

    static Money getMoney(World world) {
        SavedDataMoney data = (SavedDataMoney) world.getSavedData(SavedDataMoney.class, "stardew:money");

        if (data == null) {
            data = new SavedDataMoney("stardew:money");
            world.setSavedData("stardew:money", data);
        }

        return data;
    }

    static void showHudElement() {
        if (!Global.isServer) {
            MoneyHudElement.SHOW_MONEY_STAMP = Math.toIntExact(System.currentTimeMillis() / 1000);
        }
    }

    int getBalanceFor(Player player);
    void addBalanceTo(int amount, Player player);
    boolean billFor(int amount, Player player);
}
