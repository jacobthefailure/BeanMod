package mod.jacob.beanmod;
import mod.jacob.beanmod.lists.ItemList;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;
import static mod.jacob.beanmod.BeanMod.modid;

public class BeanCrop extends CropsBlock implements IGrowable {

    public static final IntegerProperty beanCropAge = BlockStateProperties.AGE_0_7;

    public BeanCrop(String name) {

        super(Properties.create(Material.PLANTS).doesNotBlockMovement().sound(SoundType.CROP).tickRandomly());
        this.setRegistryName(location("bean_crop"));
        this.setDefaultState(this.stateContainer.getBaseState().with(this.getAgeProperty(), Integer.valueOf(0)));
    }


    @OnlyIn(Dist.CLIENT)
    protected IItemProvider getSeedsItem()
    {
        return ItemList.bean;
    }



    @OnlyIn(Dist.CLIENT)
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state)
    {
        return new ItemStack(this.getSeedsItem());
    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return state.getBlock() instanceof FarmlandBlock;
    }


    public IntegerProperty getAgeProperty()
    {
        return beanCropAge;
    }


    public int getMaxAge()
    {
        return 7;
    }


    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
        return !this.isMaxAge(state);
    }


    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        this.grow(worldIn, pos, state);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(beanCropAge);
    }

    private static ResourceLocation location(String name) {

        return new ResourceLocation(modid, name);
    }






}