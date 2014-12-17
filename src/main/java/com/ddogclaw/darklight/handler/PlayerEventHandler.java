package com.ddogclaw.darklight.handler;

import java.io.File;

import com.ddogclaw.darklight.reference.Reference;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerEventHandler 
{
	@SubscribeEvent
	public void onPlayerLoadFromFileEvent(PlayerEvent.LoadFromFile event)
	{
		if (!event.entityPlayer.worldObj.isRemote)
		{
			// Get dir to read/write data
			if (PlayerAbilityHandler.playerDataDirectory == null || !PlayerAbilityHandler.playerDataDirectory.getAbsolutePath().equalsIgnoreCase(event.playerDirectory.getAbsolutePath()))
			{
				PlayerAbilityHandler.playerDataDirectory = new File(event.playerDirectory, Reference.MODID);
			}
		}
	}
}
