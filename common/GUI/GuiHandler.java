package GUI;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import Entities.TileEntityRuneFocus;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {

		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		if (tile_entity instanceof TileEntityRuneFocus) {

			return new ContainerRuneFocus((TileEntityRuneFocus) tile_entity,
					player.inventory);

		}

		return null;

	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {

		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		if (tile_entity instanceof TileEntityRuneFocus) {

			return new GuiRuneFocus(player.inventory,
					(TileEntityRuneFocus) tile_entity);

		}

		return null;

	}

}
