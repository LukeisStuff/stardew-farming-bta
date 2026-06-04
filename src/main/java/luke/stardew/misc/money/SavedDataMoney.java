package luke.stardew.misc.money;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.world.World;
import net.minecraft.core.world.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SavedDataMoney extends SavedData implements Money{

    public SavedDataMoney(String s) {
        super(s);
    }

    @Override
    public void load(CompoundTag compoundTag) {
        this.money.clear();

        compoundTag.getCompound("money").getValue().forEach(
            (uuid, amount) -> this.money.put(UUID.fromString(uuid), (Integer) amount.getValue())
        );
    }

    @Override
    public void save(CompoundTag compoundTag) {
        var moneyTag = new CompoundTag();

        money.forEach(
            (uuid, amount) -> moneyTag.putInt(uuid.toString(), amount)
        );

        compoundTag.put("money", moneyTag);
    }

    protected final Map<UUID, Integer> money = new HashMap<>();

    @Override
    public int getBalanceFor(Player player) {
        return this.money.getOrDefault(player.uuid, 0);
    }

    @Override
    public void addBalanceTo(int amount, Player player) {
        this.money.put(player.uuid, this.getBalanceFor(player) + amount);
    }

    @Override
    public boolean billFor(int amount, Player player) {
        if (this.getBalanceFor(player) < amount) return false;

        this.addBalanceTo(amount * -1, player);
        return true;
    }
}
