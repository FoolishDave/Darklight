package com.ddogclaw.darklight.handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.ddogclaw.darklight.reference.Reference;
import com.ddogclaw.darklight.util.EntityHelper;
import com.ddogclaw.darklight.util.LogHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerEventHandler 
{
	@SubscribeEvent
	public void onPlayerLoadFromFileEvent(PlayerEvent.LoadFromFile event)
	{
		LogHelper.info("Loading player from file");
		if (!event.entityPlayer.worldObj.isRemote)
		{
			// Get dir to read/write data
			if (PlayerAbilityHandler.playerDataDirectory == null || !PlayerAbilityHandler.playerDataDirectory.getAbsolutePath().equalsIgnoreCase(event.playerDirectory.getAbsolutePath()))
			{
				PlayerAbilityHandler.playerDataDirectory = new File(event.playerDirectory, Reference.MODID);
				
				if (!PlayerAbilityHandler.playerDataDirectory.exists())
				{
					PlayerAbilityHandler.playerDataDirectory.mkdir();
				}
			}
			
			File playerDataFile = new File(PlayerAbilityHandler.playerDataDirectory, event.entityPlayer.getUniqueID() + PlayerAbilityHandler.ABILITY_EXTENSION);
			if (!playerDataFile.exists())
			{
				try
				{
					PrintWriter out = new PrintWriter(new BufferedWriter( new FileWriter( new File(PlayerAbilityHandler.playerDataDirectory, "playerLegend.txt"), true)));
					
					out.println(String.format("Player Name = %s", event.entityPlayer.getCommandSenderName()));
					out.println(String.format("Ability File = %s", event.entityPlayer.getUniqueID() + PlayerAbilityHandler.ABILITY_EXTENSION));
					out.println();
					out.close();
				}
				catch (IOException e)
				{
					LogHelper.warn("Failed to add player");
				}
				PlayerAbilityHandler.writeAbilityData(event.entityPlayer);
			}
		}
	}

	@SubscribeEvent
	public void initPlayerCustomData(PlayerLoggedInEvent event)
	{
		LogHelper.info("Initializing custom data");
		NBTTagCompound playerCustomData = EntityHelper.getCustomEntityData(event.player);
	
		EntityHelper.saveCustomEntityData(event.player, playerCustomData);
	}

}
