package MISC;

import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxyTutorial extends CommonProxyTutorial {
	@Override
	public void registerRenderThings() {
		MinecraftForgeClient.preloadTexture("/Tutorial/Blocks.png");
		MinecraftForgeClient.preloadTexture("/Tutorial/Items.png");
		MinecraftForgeClient.preloadTexture("/Tutorial/Aspects.png");
	}

}
