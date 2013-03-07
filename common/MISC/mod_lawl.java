package MISC;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumHelper;
import Blocks.BlockGodstone;
import Blocks.BlockMagicCauldron;
import Blocks.BlockRuneFocus;
import CreativeTabs.MachineTab;
import CreativeTabs.RuneTab;
import CreativeTabs.StaffTab;
import Items.Godsword;
import Items.Magicpowder;
import Items.Rune;
import Items.Staff;
import Items.StaffExplosion;
import Items.StaffFire;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "MagicSwords", name = "Magic Swords", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class mod_lawl {

	public static int MagicCauldronRenderingID = 500;
	public static int GodstoneID = 500, MagicCauldronID = 501,
			RuneFocusID = 502;
	public static int MagicpowderID = 500, GodswordID = 501, RuneID = 506,
			StaffID = 591, FireStaffID = 592, ExplosionStaffID = 593;
	public static int KnockbackID = 21, FieryBladeID = 22, MarkerID = 23,
			MarkedID = 24, SlowerID = 25;

	// Initialize Blocks
	public static Block Godstone;
	public static Block MagicCauldron;
	public static Block RuneFocus;

	// Initialize Items
	public static Item Magicpowder;
	public static Item Godsword1;
	public static Item Godsword2;
	public static Item Godsword3;
	public static Item Godsword4;
	public static Item Godsword5;
	public static Item BasicRune;
	public static Item BasicStaff;
	public static Item FireStaff;
	public static Item ExplosionStaff;

	// Initialize MISC

	public static CreativeTabs runesTab;
	public static CreativeTabs machinesTab;
	public static CreativeTabs staffTab;

	@Instance
	public static mod_lawl instance = new mod_lawl();

	public static HashMap<String, String> RuneKeys = new HashMap<String, String>();
	public static HashMap<Integer, Item> RuneItems = new HashMap<Integer, Item>();

	@SidedProxy(clientSide = "Tutorial.Client.ClientProxyTutorial", serverSide = "Tutorial.Common.CommonProxyTutorial")
	public static ClientProxyTutorial proxy = new ClientProxyTutorial();

	// Materials
	static EnumToolMaterial EnumToolMaterialTutorial = EnumHelper
			.addToolMaterial("Tutorial", 2, 400, 6.0F, 6, 30);

	@Init
	public void load(FMLInitializationEvent event) {
		// Declare MISC
		runesTab = new RuneTab("runeTab");
		machinesTab = new MachineTab("machinesTab");
		staffTab = new StaffTab("staffTab");

		// Declare Blocks
		Godstone = new BlockGodstone(GodstoneID, 0).setBlockName("Godstone")
				.setHardness(1F).setResistance(5F).setLightValue(1F);
		MagicCauldron = new BlockMagicCauldron(MagicCauldronID)
				.setHardness(2.0F).setBlockName("magicCauldron")
				.setRequiresSelfNotify();
		RuneFocus = new BlockRuneFocus(RuneFocusID).setBlockName("runefocus")
				.setHardness(2.0F);

		// Declare Items
		Magicpowder = new Magicpowder(MagicpowderID).setItemName("Magicpowder")
				.setIconIndex(0);
		Godsword1 = new Godsword(GodswordID, EnumToolMaterialTutorial)
				.setItemName("MSword1").setIconIndex(1);
		Godsword2 = new Godsword(GodswordID + 1, EnumToolMaterialTutorial)
				.setItemName("MSword2").setIconIndex(2);
		Godsword3 = new Godsword(GodswordID + 2, EnumToolMaterialTutorial)
				.setItemName("MSword3").setIconIndex(3);
		Godsword4 = new Godsword(GodswordID + 3, EnumToolMaterialTutorial)
				.setItemName("MSword4").setIconIndex(4);
		Godsword5 = new Godsword(GodswordID + 4, EnumToolMaterialTutorial)
				.setItemName("MSword5").setIconIndex(5);
		BasicRune = new Rune(RuneID, true).setItemName("none.none.none")
				.setIconIndex(6);
		BasicStaff = new Staff(StaffID).setItemName("basicstaff").setIconIndex(
				7);
		FireStaff = new StaffFire(FireStaffID).setItemName("firestaff")
				.setIconIndex(8);
		ExplosionStaff = new StaffExplosion(ExplosionStaffID).setItemName(
				"explosionstaff").setIconIndex(8);

		int itr = 0;
		for (int i = 1; i <= 4; i++) {
			for (int j = 0; j <= 4; j++) {
				for (int k = 0; k <= 4; k = j == 0 ? 6 : k + 1) {
					System.out.println(i + " " + j + " " + k);
					RuneItems.put(
							i + j * 10 + k * 100,
							new Rune(RuneID + 1 + itr, false)
									.setItemName(
											Rune.nameByItemIndex(i) + "."
													+ Rune.nameByItemIndex(j)
													+ "."
													+ Rune.nameByItemIndex(k))
									.setMaxStackSize(1)
									.setIconIndex(
											findPic(new int[] { i, j, k },
													false)));
					itr++;
				}
			}
		}

		// Register Blocks/Fuels/Generation
		GameRegistry.registerTileEntity(TileEntityMagicCauldron.class,
				"magicCauldronBlock");
		GameRegistry.registerFuelHandler(new TutorialFuel());
		GameRegistry.registerWorldGenerator(new WorldGeneratorTutorial());

		// Register Names
		LanguageRegistry.addName(Magicpowder, "Powder of magic");

		LanguageRegistry.addName(Godsword1, "Gawdsword: Earth");
		LanguageRegistry.addName(Godsword2, "Gawdsword: Fire");
		LanguageRegistry.addName(Godsword3, "Gawdsword: Ice");
		LanguageRegistry.addName(Godsword4, "Gawdsword: Air");
		LanguageRegistry.addName(Godsword5, "Gawdsword: Recharge");

		LanguageRegistry.addName(BasicStaff, "Staff");
		LanguageRegistry.addName(FireStaff, "Staff of Fire");
		LanguageRegistry.addName(ExplosionStaff, "Staff of Explosions");

		LanguageRegistry.instance().addStringLocalization(
				PotionEffects.Knockback.getName(), "Knockback");
		LanguageRegistry.instance().addStringLocalization(
				PotionEffects.FieryBlade.getName(), "Fiery Blade");
		LanguageRegistry.instance().addStringLocalization(
				PotionEffects.Marker.getName(), "Marker");
		LanguageRegistry.instance().addStringLocalization(
				PotionEffects.Marked.getName(), "Marked");
		LanguageRegistry.instance().addStringLocalization(
				PotionEffects.Slower.getName(), "Slower");

		LanguageRegistry.instance().addStringLocalization(
				"itemGroup." + runesTab.getTabLabel(), "Runes and Stuff");
		LanguageRegistry.instance().addStringLocalization(
				"itemGroup." + machinesTab.getTabLabel(), "Machines and stuff");
		LanguageRegistry.instance().addStringLocalization(
				"itemGroup." + staffTab.getTabLabel(), "Staffs and stuff");

		LanguageRegistry.addName(Godstone, "Gawdstone");
		LanguageRegistry.addName(MagicCauldron, "why is this in your hand?!?");
		LanguageRegistry.addName(RuneFocus, "Rune Focus");

		// Crafting recipes
		GameRegistry.addShapelessRecipe(new ItemStack(BasicRune, 2),
				new Object[] { Block.cobblestone, Magicpowder });
		GameRegistry.addRecipe(
				new ItemStack(Godsword1, 1),
				new Object[] { "ETA", "FTI", "BDB", 'B', Block.blockDiamond,
						'D', Item.swordDiamond, 'T', Magicpowder, 'E',
						RuneItems.get(2), 'A', RuneItems.get(3), 'I',
						RuneItems.get(4), 'F', RuneItems.get(1) });
		GameRegistry.addRecipe(new ItemStack(RuneFocus), new Object[] { "FSD",
				"SUS", "RSL", 'U', BasicRune, 'S', Block.stone, 'L',
				Item.bucketWater, 'R', Item.bucketLava, 'F', Item.feather, 'D',
				Block.dirt });
		GameRegistry.addRecipe(new ItemStack(BasicStaff), new Object[] { "  P",
				" S ", "S  ", 'P', Magicpowder, 'S', Item.stick });
		GameRegistry.addSmelting(GodstoneID, new ItemStack(Magicpowder, 16), 0);

		// Rendering
		RenderingRegistry.registerBlockHandler(MagicCauldronRenderingID,
				new ISimpleBlockRenderingHandler() {

					@Override
					public void renderInventoryBlock(Block block, int metadata,
							int modelID, RenderBlocks renderer) {

					}

					@Override
					public boolean renderWorldBlock(IBlockAccess world, int x,
							int y, int z, Block block, int modelId,
							RenderBlocks renderer) {

						renderer.renderStandardBlock(block, x, y, z);
						Tessellator var5 = Tessellator.instance;
						var5.setBrightness(block.getMixedBrightnessForBlock(
								renderer.blockAccess, x, y, z));
						float var6 = 1.0F;
						int var7 = block.colorMultiplier(renderer.blockAccess,
								x, y, z);
						float var8 = (var7 >> 16 & 255) / 255.0F;
						float var9 = (var7 >> 8 & 255) / 255.0F;
						float var10 = (var7 & 255) / 255.0F;
						float var12;

						if (EntityRenderer.anaglyphEnable) {
							float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
							var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
							float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
							var8 = var11;
							var9 = var12;
							var10 = var13;
						}

						var5.setColorOpaque_F(var6 * var8, var6 * var9, var6
								* var10);
						short var16 = 154;
						var12 = 0.125F;
						renderer.renderSouthFace(block, x - 1.0F + var12, y, z,
								var16);
						renderer.renderNorthFace(block, x + 1.0F - var12, y, z,
								var16);
						renderer.renderWestFace(block, x, y, z - 1.0F + var12,
								var16);
						renderer.renderEastFace(block, x, y, z + 1.0F - var12,
								var16);
						short var17 = 139;
						renderer.renderTopFace(block, x, y - 1.0F + 0.25F, z,
								var17);
						renderer.renderBottomFace(block, x, y + 1.0F - 0.75F,
								z, var17);
						int var14 = world.getBlockMetadata(x, y, z);

						if (var14 > 0) {
							short var15 = 237;

							renderer.renderTopFace(block, x, y - 1.0F
									+ (6.0F + var14 * 3.0F) / 16.0F, z, var15);
						}

						return true;
					}

					@Override
					public boolean shouldRender3DInInventory() {
						return false;
					}

					@Override
					public int getRenderId() {
						return MagicCauldronRenderingID;
					}

				});

		// Smelting recipe

		// Texture registering
		proxy.registerRenderThings();
	}

	@PreInit
	public void preinit(FMLPreInitializationEvent fmlpie) {
		RuneKeys.put("none.none.none", "Rune");
		// 0
		RuneKeys.put("fire.none.none", "Rune: Fire");
		RuneKeys.put("earth.none.none", "Rune: Earth");
		RuneKeys.put("air.none.none", "Rune: Air");
		RuneKeys.put("ice.none.none", "Rune: Ice");

		RuneKeys.put("fire.ice.none", "Rune: Water");
		RuneKeys.put("earth.ice.none", "Rune: Snow");
		RuneKeys.put("air.ice.none", "Rune: Steam");
		RuneKeys.put("ice.ice.none", "Rune: Winter");

		RuneKeys.put("fire.ice.none", "Rune: Water");
		RuneKeys.put("earth.ice.none", "Rune: Snow");
		RuneKeys.put("air.ice.none", "Rune: Steam");
		RuneKeys.put("ice.ice.none", "Rune: Winter");

		RuneKeys.put("fire.air.none", "Rune: Explosion");
		RuneKeys.put("earth.air.none", "Rune: Dust");
		RuneKeys.put("air.air.none", "Rune: Hurricane");
		RuneKeys.put("ice.air.none", "Rune: Steam");

		RuneKeys.put("fire.earth.none", "Rune: Lava");
		RuneKeys.put("earth.earth.none", "Rune: Stone");
		RuneKeys.put("air.earth.none", "Rune: Dust");
		RuneKeys.put("ice.earth.none", "Rune: Snow");

		RuneKeys.put("fire.fire.none", "Rune: Energy");
		RuneKeys.put("earth.fire.none", "Rune: Lava");
		RuneKeys.put("air.fire.none", "Rune: Explosion");
		RuneKeys.put("ice.fire.none", "Rune: Water");

		// 1
		RuneKeys.put("fire.ice.fire", "Rune: Steam");
		RuneKeys.put("earth.ice.fire", "Rune: Water");
		RuneKeys.put("air.ice.fire", "Rune: Propan");
		RuneKeys.put("ice.ice.fire", "Rune: Summer");

		RuneKeys.put("fire.air.fire", "Rune: Explosion");
		RuneKeys.put("earth.air.fire", "Rune: Explosion");
		RuneKeys.put("air.air.fire", "Rune: Flamicane");
		RuneKeys.put("ice.air.fire", "Rune: Propan");

		RuneKeys.put("fire.earth.fire", "Rune: Napalm");
		RuneKeys.put("earth.earth.fire", "Rune: Godstone extract");
		RuneKeys.put("air.earth.fire", "Rune: Explsion");
		RuneKeys.put("ice.earth.fire", "Rune: Water");

		RuneKeys.put("fire.fire.fire", "Rune: Plasma");
		RuneKeys.put("earth.fire.fire", "Rune: Napalm");
		RuneKeys.put("air.fire.fire", "Rune: Explosion");
		RuneKeys.put("ice.fire.fire", "Rune: Steam");

		// 2
		RuneKeys.put("fire.ice.earth", "Rune: Mud");
		RuneKeys.put("earth.ice.earth", "Rune: Ice");
		RuneKeys.put("air.ice.earth", "Rune: Dust");
		RuneKeys.put("ice.ice.earth", "Rune: Spring");

		RuneKeys.put("fire.air.earth", "Rune: Explosion");
		RuneKeys.put("earth.air.earth", "Rune: Dirt");
		RuneKeys.put("air.air.earth", "Rune: Stonicane");
		RuneKeys.put("ice.air.earth", "Rune: Steamsplosition");

		RuneKeys.put("fire.earth.earth", "Rune: Volcano");
		RuneKeys.put("earth.earth.earth", "Rune: Diamond extract");
		RuneKeys.put("air.earth.earth", "Rune: Dirt");
		RuneKeys.put("ice.earth.earth", "Rune: Ice");

		RuneKeys.put("fire.fire.earth", "Rune: Gravitation");
		RuneKeys.put("earth.fire.earth", "Rune: Volcano");
		RuneKeys.put("air.fire.earth", "Rune: Explosion");
		RuneKeys.put("ice.fire.earth", "Rune: Mud");

		// 5
		RuneKeys.put("fire.ice.air", "Rune: Rain");
		RuneKeys.put("earth.ice.air", "Rune: Snow");
		RuneKeys.put("air.ice.air", "Rune: Gas");
		RuneKeys.put("ice.ice.air", "Rune: Autmn");

		RuneKeys.put("fire.air.air", "Rune: Explosion");
		RuneKeys.put("earth.air.air", "Rune: Nothing");
		RuneKeys.put("air.air.air", "Rune: Airicane");
		RuneKeys.put("ice.air.air", "Rune: Gas");

		RuneKeys.put("fire.earth.air", "Rune: Napalm explosion");
		RuneKeys.put("earth.earth.air", "Rune: Sand");
		RuneKeys.put("air.earth.air", "Rune: Nothing");
		RuneKeys.put("ice.earth.air", "Rune: Snow Storm");

		RuneKeys.put("fire.fire.air", "Rune: Lightning");
		RuneKeys.put("earth.fire.air", "Rune: Napalm explosion");
		RuneKeys.put("air.fire.air", "Rune: Shockwave");
		RuneKeys.put("ice.fire.air", "Rune: Rain");

		// 6
		RuneKeys.put("fire.ice.ice", "Rune: Crushed ice");
		RuneKeys.put("earth.ice.ice", "Rune: Crushed ice");
		RuneKeys.put("air.ice.ice", "Rune: Hydrogen");
		RuneKeys.put("ice.ice.ice", "Rune: Winter");

		RuneKeys.put("fire.air.ice", "Rune: Frozen explosion");
		RuneKeys.put("earth.air.ice", "Rune: Frozen dust");
		RuneKeys.put("air.air.ice", "Rune: Icycane");
		RuneKeys.put("ice.air.ice", "Rune: Hydrogen");

		RuneKeys.put("fire.earth.ice", "Rune: Volacanic stone");
		RuneKeys.put("earth.earth.ice", "Rune: Iceblock");
		RuneKeys.put("air.earth.ice", "Rune: Frozen dust");
		RuneKeys.put("ice.earth.ice", "Rune: Crushed ice");

		RuneKeys.put("fire.fire.ice", "Rune: Frozen energy");
		RuneKeys.put("earth.fire.ice", "Rune: Volacanic stone");
		RuneKeys.put("air.fire.ice", "Rune: Frozen explosion");
		RuneKeys.put("ice.fire.ice", "Rune: Ice");
		System.out.println("Magic swords by toristen loaded");
	}

	public static int findPic(int[] el, boolean isStandard) {
		el[0] = el[0] - (el[0] == 0 ? 0 : 1);
		el[2] = el[2] - (el[2] == 0 ? 0 : 1);
		if (isStandard) {
			return 6;
		}
		int combined = el[0] + 10 * el[1] + 100 * el[2];
		int vgrade = combined < 5 && combined > -1 ? 0 : combined < 45 ? 1 : 2;
		int y = vgrade * 4 + (el[1] == 0 ? 0 : (4 - el[1]));
		int hgrade = vgrade == 2 ? el[2] : 0;
		int x = hgrade * 4 + el[0];
		return x + y * 16;
	}
}
