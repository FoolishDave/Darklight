package com.ddogclaw.darklight.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.ddogclaw.darklight.abilities.PlayerAbilities;
import com.ddogclaw.darklight.util.LogHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerAbilityHandler 
{
	public static File playerDataDirectory;
	
	public static final String ABILITY_EXTENSION = "DarklightAbilities";
	
	public static void writeAbilityData(EntityPlayer player)
	{
		writeAbilityData(player, null);
	}
	
	public static void writeAbilityData(EntityPlayer player, PlayerAbilities abilities)
	{
		LogHelper.info("Trying to write ability data");
		if (playerDataDirectory != null && playerDataDirectory.isDirectory())
		{
			NBTTagCompound playerAbilityCompound = null;
			if (abilities == null)
			{
				playerAbilityCompound = readAbilityFile(player);
			}
			else
			{
				abilities.writeToNBT(playerAbilityCompound);
			}
			try
			{
				LogHelper.info("Trying to save player ability data");
				File file1 = new File(playerDataDirectory, player.getUniqueID().toString() + ABILITY_EXTENSION + ".tmp");
				File file2 = new File(playerDataDirectory, player.getUniqueID().toString() + ABILITY_EXTENSION);
				CompressedStreamTools.writeCompressed(playerAbilityCompound, new FileOutputStream(file1));
				
				if (file2.exists())
				{
					file2.delete();
				}
				
				file1.renameTo(file2);
			} catch (Exception exception)
			{
				LogHelper.warn("Failed saving player abillities for: " + player.getCommandSenderName());
			}
		}
		
	}
	
	public static NBTTagCompound readAbilityFile(EntityPlayer player)
	{
		if (playerDataDirectory != null && playerDataDirectory.isDirectory())
		{
			File playerDataFile = new File(playerDataDirectory, player.getUniqueID().toString() + ABILITY_EXTENSION);
			if (playerDataFile.exists() && playerDataFile.isFile())
			{
				try
				{
					return CompressedStreamTools.readCompressed(new FileInputStream(playerDataFile));
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		new PlayerAbilities().writeToNBT(nbtTagCompound);
		return nbtTagCompound;
	}
}
