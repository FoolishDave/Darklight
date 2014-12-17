package com.ddogclaw.darklight.abilities;

import com.ddogclaw.darklight.reference.Reference;
import com.ddogclaw.darklight.util.INBTTaggable;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerAbilities  implements INBTTaggable
{
	
	protected boolean isDark = false;
	protected boolean isLight = false;
	protected int breakRate = 0;
	protected int health = 0;
	protected int fatigue = 0;
	
	public static PlayerAbilities getPlayerInfo(Entity player)
	{
		return (PlayerAbilities)player.getExtendedProperties(Reference.IDENTIFIER);
	}
	
	public static PlayerAbilities readPlayerAbilitiesFromNBT(NBTTagCompound nbt)
	{
		PlayerAbilities playerAbilities = new PlayerAbilities();
		
		playerAbilities.readFromNBT(nbt);
		
		return playerAbilities;
	}
	
	public boolean dirty = true;
	
	@Override
	public void writeToNBT(NBTTagCompound compound) 
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setBoolean("isLight", isLight);
		nbt.setBoolean("isDark", isDark);
		nbt.setInteger("breakRate", breakRate);
		nbt.setInteger("health", health);
		nbt.setInteger("fatigue", fatigue);
		compound.setTag(Reference.IDENTIFIER, nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		NBTTagCompound nbt = compound.getCompoundTag(Reference.IDENTIFIER);
		isLight = nbt.getBoolean("isLight");
		isDark = nbt.getBoolean("isDark");
		breakRate = nbt.getInteger("breakRate");
		health = nbt.getInteger("health");
		fatigue = nbt.getInteger("fatigue");
	}

	public int getHealth()
	{
		return health;
	}
	
	public int setHealth(int newHealth)
	{
		if (newHealth != health)
		{
			health = newHealth;
			setDirty();
		}
		return health;
	}
	
	private void setDirty() 
	{
		dirty = true;
	}



}
