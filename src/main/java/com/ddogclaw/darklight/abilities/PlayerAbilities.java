package com.ddogclaw.darklight.abilities;

import com.ddogclaw.darklight.reference.Reference;
import com.ddogclaw.darklight.util.INBTTaggable;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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
		//NBTTagCompound nbt = new NBTTagCompound();
		//NBTTagList tagList = new NBTTagList();
		if (compound == null)
		{
			compound = new NBTTagCompound();
		}
		compound.setBoolean("isLight", isLight);
		compound.setBoolean("isDark", isDark);
		compound.setInteger("breakRate", breakRate);
		compound.setInteger("health", health);
		compound.setInteger("fatigue", fatigue);
		//tagList.appendTag(nbt);

		//compound.setTag(Reference.IDENTIFIER, tagList);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		//NBTTagCompound nbt = compound.getCompoundTag(Reference.IDENTIFIER);
		isLight = compound.getBoolean("isLight");
		isDark = compound.getBoolean("isDark");
		breakRate = compound.getInteger("breakRate");
		health = compound.getInteger("health");
		fatigue = compound.getInteger("fatigue");
	}

	public int getHealth()
	{
		return health;
	}
	
	public boolean getLight()
	{
		return isLight;
	}
	
	public boolean getDark()
	{
		return isDark;
	}
	
	public int getFatigue()
	{
		return fatigue;
	}
	
	public int getBreakRate()
	{
		return breakRate;
	}
	
	public int setBreakRate(int breakRate)
	{
		if (breakRate != this.breakRate)
		{
			this.breakRate = breakRate;
			setDirty();
		}
		return this.breakRate;
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
	
	public boolean setLight(boolean light)
	{
		if (light != isLight)
		{
			isLight = light;
			setDirty();
		}
		return isLight;
	}
	
	public boolean setDark(boolean dark)
	{
		if (dark != isDark)
		{
			isDark = dark;
			setDirty();
		}
		return isDark;
	}
	
	public int setFatigue(int fatigue)
	{
		if (fatigue != this.fatigue)
		{
			this.fatigue = fatigue;
			setDirty();
		}
		return this.fatigue;
	}
	
	private void setDirty() 
	{
		dirty = true;
	}



}
