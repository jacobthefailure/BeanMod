package mod.jacob.beanmod;

import mod.jacob.beanmod.lists.ItemList;
import  net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class BeanItemGroup extends ItemGroup {

    public BeanItemGroup(){

        super("bean");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemList.bean);

    }
}
