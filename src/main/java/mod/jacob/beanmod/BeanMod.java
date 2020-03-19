package mod.jacob.beanmod;

import mod.jacob.beanmod.lists.BlockList;
import mod.jacob.beanmod.lists.ItemList;
import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod("beanmod")

public class BeanMod {

    public static BeanMod instance;
    public static final String modid = "beanmod";
    public static final Logger logger = LogManager.getLogger(modid);

    public static final ItemGroup bean = new BeanItemGroup();

    public BeanMod() {
        instance = this;

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        logger.info("Setup method registered.");
    }

    private void clientRegistries(final FMLClientSetupEvent event) {
        logger.info("Client registries registered. Isn't that cool?");
        RenderTypeLookup.setRenderLayer(BlockList.bean_crop, RenderType.func_228643_e_());

    }

    /*
    TODO:
    1. Bean stairs, slabs, etc.
     */

    /*
    Item Implementation Checklist:
    1. Add the texture, with the name of the item
    2. Add the new item to ItemList
    3. Add the item to the BeanMod class
    4. Add any recipes related to the item
    5. Add the texture info to assets/models/item
    6. Add info to lang folder

    And that should do it!
     */

    /*
    Block Implementation Checklist
    1. Add the texture of the block, with the name of the block
    2. Add the block to BlockList
    3. Add the item equivalent to ItemList
    4. Add the item to the BeanMod class
    5. Add the block to the BeanMod class
    6. Add the texture info to assets/models/item and assets/models/block
    7. Add info to lang folder
    8. Pray

    And that should do it!
     */

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)

        public static class RegistryEvents {

            //This is where the bean happens.
            @SubscribeEvent
            public static void registerItems(final RegistryEvent.Register<Item> event) {
                event.getRegistry().registerAll(
                        //actual items

                        ItemList.cooked_bean = new Item(new Item.Properties().food(Foods.POTATO).group(bean)).setRegistryName(location("cooked_bean")),
                        //blocks
                        ItemList.solidified_bean = new BlockItem(BlockList.solidified_bean, new Item.Properties().group(bean)).setRegistryName(BlockList.solidified_bean.getRegistryName()),
                        ItemList.bean = new BlockItem(BlockList.bean_crop, new Item.Properties().food(Foods.DRIED_KELP).group(bean)).setRegistryName(location("bean"))
                );


                logger.info("Items registered! :)");
            }

            //Solidified bean.
            @SubscribeEvent
            public static void registerBlocks(final RegistryEvent.Register<Block> event) {
                event.getRegistry().registerAll(

                        BlockList.solidified_bean = new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0f, 3.0f).lightValue(3).sound(SoundType.SLIME).harvestLevel(0).harvestTool(ToolType.SHOVEL)).setRegistryName(location("solidified_bean"))
                );

                event.getRegistry().register(BlockList.bean_crop);
                logger.info("Blocks registered! :)");
            }

            private static ResourceLocation location(String name) {

                return new ResourceLocation(modid, name);
            }
        }





}

