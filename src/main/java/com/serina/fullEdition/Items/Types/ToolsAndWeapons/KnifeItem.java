package com.serina.fullEdition.Items.Types.ToolsAndWeapons;

import com.serina.fullEdition.Helpers.KnifeItemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import static com.serina.fullEdition.Helpers.KnifeItemHelper.outputRecordList;

public class KnifeItem extends Item{

    public int time=0;
    public KnifeItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        /*Vec3 whereIlookfrom=player.getEyePosition();
        Vec3 whereIenduplooking=whereIlookfrom.add(player.getViewVector(1.0f)).scale(5);
        AABB box = new AABB(whereIlookfrom, whereIenduplooking).inflate(1.0);
        EntityHitResult whosInFrontofme=ProjectileUtil.getEntityHitResult(player,whereIlookfrom,whereIenduplooking,box,entity -> entity instanceof LivingEntity,10);

        DamageSource source=new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(DamageTypes.GENERIC));

        if (time>0)
        {
            time++;
            System.out.println(time);
            if(time==1000&&whosInFrontofme!=null)
            {
                Entity Target=whosInFrontofme.getEntity();
                Target.hurt(source,5);
                time=1;

            }
            else if(time==1000&&whosInFrontofme==null)
            {time=0;Entity Target=whosInFrontofme.getEntity();((Mob) Target).setNoAi(true);}
            else if(time==2000){time=0;}

            if(time==0)
            {
                System.out.println("time 0");
            if(whosInFrontofme!=null)
            {
                Entity Target=whosInFrontofme.getEntity();
                Target.hurt(source,5);
                time=1;
                ((Mob) Target).setNoAi(true);
                ((Mob) Target).tick();
            }
                else {
                System.out.println("so sally can wait");

            }
        }

        }*/
        for(KnifeItemHelper.SharpenItems sharpenItems:KnifeItemHelper.SharpenItemsList())
        {
            if(player.getMainHandItem().is(sharpenItems.input()))
            {
                player.getMainHandItem().shrink(1);
                player.getOffhandItem().hurtAndBreak(1,player,InteractionHand.MAIN_HAND);
                player.addItem(new ItemStack(sharpenItems.output()));
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level=context.getLevel();
        BlockPos pos=context.getClickedPos();
        BlockState state=level.getBlockState(pos);
        ItemStack stack=context.getItemInHand();
        Player player=context.getPlayer();
        if(!level.isClientSide())
        {
            for(KnifeItemHelper.outputRecord outputRecord:outputRecordList())
            {
                if(outputRecord.blockinput().test(state))
                {
                    stack.hurtAndBreak(1,player, InteractionHand.MAIN_HAND);
                    level.setBlock(pos,outputRecord.blockout().apply(context),Block.UPDATE_ALL);
                    if(outputRecord.item()!=null&&outputRecord.amount()!=null)
                    {Block.popResource(level,pos,new ItemStack(outputRecord.item(),outputRecord.amount()));}else{}
                    break;
                }
            }
        }
        return InteractionResult.SUCCESS;
    }
}

