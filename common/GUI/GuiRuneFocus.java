package GUI;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import Entities.TileEntityRuneFocus;

public class GuiRuneFocus extends GuiContainer {

	TileEntityRuneFocus te = null;

	public GuiRuneFocus(InventoryPlayer player_inventory,
			TileEntityRuneFocus tile_entity) {

		super(new ContainerRuneFocus(tile_entity, player_inventory));
		te = tile_entity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {

		fontRenderer.drawString("Rune Focus", 6, 6, 0);

		fontRenderer.drawString(
				StatCollector.translateToLocal("container.inventory"), 6,
				ySize - 96, 0);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		int picture = mc.renderEngine.getTexture("/gui/furnace.png");

		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

		this.mc.renderEngine.bindTexture(picture);

		int x = (width - xSize) / 2;

		int y = (height - ySize) / 2;

		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		if (te.timeInfuse > TileEntityRuneFocus.totalTime
				|| te.inventory[0] == null || te.inventory[1] == null) {
			te.timeInfuse = 0;
		}

		if (te.isInfusing()) {
			this.drawTexturedModalRect(x + 79, y + 34, 176, 14, te.timeInfuse
					* 24 / TileEntityRuneFocus.totalTime + 1, 16);
		}

	}
}
