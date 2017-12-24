package djsrv.geneticengineering.common.item;

import djsrv.geneticengineering.GeneticEngineering;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class ItemBacterium extends Item {

    public static String[] species = {
            "escherichia_coli",
            "thermus_aquaticus",
            "bacillus_subtilis"
    };

    public static HashMap<String, ModelResourceLocation> models = new HashMap<>();

    public ItemBacterium() {
        setRegistryName("bacterium");
        setUnlocalizedName(GeneticEngineering.MODID + ".bacterium");
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelResourceLocation[] modelArray = new ModelResourceLocation[species.length];
        for (int i = 0; i < species.length; i++) {
            String speciesID = species[i];
            ModelResourceLocation model = new ModelResourceLocation(getRegistryName() + "_" + speciesID, "inventory");
            models.put(speciesID, model);
            modelArray[i] = model;
        }
        ModelBakery.registerItemVariants(this, (ModelResourceLocation[]) modelArray);

        ModelLoader.setCustomMeshDefinition(this, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return models.get(getSpecies(stack));
            }
        });
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        tooltip.add(TextFormatting.ITALIC + I18n.format("geneticengineering.species." + getSpecies(stack)));
    }

    private String getSpecies(ItemStack stack) {
        return getTagCompoundSafe(stack).getString("species");
    }

    private NBTTagCompound getTagCompoundSafe(ItemStack stack) {
        NBTTagCompound tagCompound = stack.getTagCompound();
        if (tagCompound == null) {
            tagCompound = new NBTTagCompound();
            stack.setTagCompound(tagCompound);
        }
        return tagCompound;
    }

}
