package djsrv.geneticengineering.common.item;

import djsrv.geneticengineering.GeneticEngineering;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

public class ItemCottonSwab extends Item {

    public ItemCottonSwab() {
        setRegistryName("cotton_swab");
        setUnlocalizedName(GeneticEngineering.MODID + ".cotton_swab");
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        RayTraceResult raytraceresult = this.rayTrace(world, player, true);
        if (raytraceresult == null || raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
            return new ActionResult<>(EnumActionResult.PASS, stack);
        } else {
            return new ActionResult<>(EnumActionResult.SUCCESS, swabMicrobe(world, player, stack));
        }
    }

    private ItemStack swabMicrobe(World world, EntityPlayer player, ItemStack swabs) {
        if (!player.capabilities.isCreativeMode) {
            swabs.shrink(1);
        }

        if (!world.isRemote) {
            ArrayList<WeightedRandom.Item> possibleSpecies = new ArrayList<>();
            for (String speciesID : ItemBacterium.species) {
                possibleSpecies.add(new SpeciesRandomItem(1, speciesID));
            }

            String speciesID = ((SpeciesRandomItem) WeightedRandom.getRandomItem(player.world.rand, possibleSpecies)).id;
            ItemStack stack = new ItemStack(ModItems.bacterium);
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.setString("species", speciesID);
            stack.setTagCompound(tagCompound);

            if (!player.inventory.addItemStackToInventory(stack)) {
                player.dropItem(stack, false);
            }
        }

        return swabs;
    }

    private static class SpeciesRandomItem extends WeightedRandom.Item {

        public final String id;

        public SpeciesRandomItem(int weight, String id) {
            super(weight);
            this.id = id;
        }

    }

}
