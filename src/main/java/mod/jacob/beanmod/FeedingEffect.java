package mod.jacob.beanmod;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class FeedingEffect extends Effect
{
    protected FeedingEffect(EffectType typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier)
    {
        Effects.SATURATION.performEffect(entityLivingBaseIn, amplifier);
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        int interval = 50 >> amplifier;
        return interval <= 0 || duration % interval == 0;
    }
}