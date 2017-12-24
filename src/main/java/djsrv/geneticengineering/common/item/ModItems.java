package djsrv.geneticengineering.common.item;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

    @GameRegistry.ObjectHolder("geneticengineering:bacterium")
    public static ItemBacterium bacterium;

    @GameRegistry.ObjectHolder("geneticengineering:cotton_swab")
    public static ItemCottonSwab cottonSwab;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        bacterium.initModel();
        cottonSwab.initModel();
    }

}
