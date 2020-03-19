package mod.jacob.beanmod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static mod.jacob.beanmod.BeanMod.modid;

@Mod.EventBusSubscriber(modid = "beanmod")
public class EventHandler {

    private static ResourceLocation grass = new ResourceLocation("minecraft", "blocks/grass");


    @SubscribeEvent
    public static void onLootLoad(LootTableLoadEvent event) {
        if (event.getName().equals(grass)) {
            event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(modid, "inject/grass"))).build());
        }
    }

}
