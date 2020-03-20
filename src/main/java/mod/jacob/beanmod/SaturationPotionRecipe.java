package mod.jacob.beanmod;

import mod.jacob.beanmod.lists.ItemList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import mod.jacob.beanmod.FeedingEffect;

import java.util.ArrayList;
import java.util.List;

import static mod.jacob.beanmod.BeanMod.modid;
import static net.minecraft.potion.EffectType.BENEFICIAL;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SaturationPotionRecipe {



    public static class PotionItem {
        @ObjectHolder(modid + ":feeding")
        public static Potion feeding;

    }



    public static class PotionEffects {

        public static final List<FeedingEffect> effects = new ArrayList<FeedingEffect>();
        @ObjectHolder(modid + ":feeding")
        public static FeedingEffect feeding;
    }

    @SubscribeEvent
    public static void onPotEffectRegistry(RegistryEvent.Register<Effect> event) {
        IForgeRegistry<Effect> r = event.getRegistry();
        register(r, new FeedingEffect(EffectType.BENEFICIAL, 0xcccc00), "feeding");

    }

    private static void register(IForgeRegistry<Effect> r, FeedingEffect pot, String name) {
        pot.setRegistryName(new ResourceLocation(modid, name));
        r.register(pot);
        PotionEffects.effects.add(pot);
    }

    @SubscribeEvent
    public static void onPotRegistry(RegistryEvent.Register<Potion> event) {
        IForgeRegistry<Potion> r = event.getRegistry();
        r.register(new Potion(modid+"_feeding", new EffectInstance(PotionEffects.feeding,3000,0)).setRegistryName(modid + ":feeding"));


    }

    public static void setup(FMLCommonSetupEvent event) {
        basicBrewing(SaturationPotionRecipe.PotionItem.feeding, ItemList.sparkling_bean);
        splashBrewing(SaturationPotionRecipe.PotionItem.feeding);
        lingerBrewing(SaturationPotionRecipe.PotionItem.feeding, ItemList.sparkling_bean);

    }

    private static void basicBrewing(Potion pot, Item item){
        ItemStack AWKWARD = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.AWKWARD);

        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.fromStacks(AWKWARD), Ingredient.fromItems(item),
                PotionUtils.addPotionToItemStack(
                        new ItemStack(Items.POTION), pot)));
    }


    private static void splashBrewing(Potion pot) {
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(
                new ItemStack(Items.POTION), pot)),
                Ingredient.fromStacks(new ItemStack(Items.GUNPOWDER)),
                PotionUtils.addPotionToItemStack(
                        new ItemStack(Items.SPLASH_POTION), pot)));
    }

    private static void lingerBrewing(Potion pot, Item item) {
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(
                new ItemStack(Items.LINGERING_POTION), Potions.AWKWARD)),
                Ingredient.fromStacks(new ItemStack(item)),
                PotionUtils.addPotionToItemStack(
                        new ItemStack(Items.LINGERING_POTION), pot)));
    }
}
