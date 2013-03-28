package MISC;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ServerPacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload payload, Player player) {

		@SuppressWarnings("unused")
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(
				payload.data));

		@SuppressWarnings("unused")
		EntityPlayer sender = (EntityPlayer) player;
	}

}
