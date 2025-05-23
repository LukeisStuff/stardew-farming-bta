package luke.stardew.helper;

import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.util.dispatch.Dispatcher;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class ModelBuilder<O, M, B> {
    protected final String modID;
    private final Dispatcher<O, M> dispatcher;
    protected Function<O, M> modelSupplier;
	protected Consumer<M> modelConsumer;

    protected ModelBuilder(String modID, Dispatcher<O, M> dispatcher) {
        this.dispatcher = dispatcher;
        this.modID = modID;
    }

    public static BlockModelBuilder block(String modID, BlockModelDispatcher dispatcher) {
        return new BlockModelBuilder(modID, dispatcher);
    }

    public static ItemModelBuilder item(String modID, ItemModelDispatcher dispatcher) {
        return new ItemModelBuilder(modID, dispatcher);
    }

    //TODO change object to interface

    @SuppressWarnings({"unchecked", "unused"})
    public B buildsModel(Function<O, M> modelSupplier) {
        this.modelSupplier = modelSupplier;
        return (B) this;
    }

	@SuppressWarnings({"unchecked", "unused"})
	public B postBuild(Consumer<M> modelConsumer) {
		this.modelConsumer = modelConsumer;
		return (B) this;
	}

    protected abstract void onBuild(O block, M model, String namespaceValue);

	public void build(O target, String key) {
		M model = this.modelSupplier.apply(target);

		this.onBuild(target, model, key);

		if (this.modelConsumer != null) this.modelConsumer.accept(model);

		this.dispatcher.addDispatch(target, model);
	}

    public void build(O target) {
        NamespaceObject object = (NamespaceObject) target;
        String namespaceValue = object.cleanValue();
		build(target, namespaceValue);
    }
}
