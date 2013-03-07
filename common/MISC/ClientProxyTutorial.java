package MISC;

import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxyTutorial extends CommonProxyTutorial {
	@Override
	public void registerRenderThings() {
		MinecraftForgeClient.preloadTexture("/MOG_resources/Blocks.png");
		MinecraftForgeClient.preloadTexture("/MOG_resources/Items.png");
		MinecraftForgeClient.preloadTexture("/MOG_resources/Aspects.png");
	}

}
