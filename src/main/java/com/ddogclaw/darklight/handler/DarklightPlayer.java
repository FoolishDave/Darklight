package com.ddogclaw.darklight.handler;

import com.ddogclaw.darklight.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class DarklightPlayer implements IExtendedEntityProperties
{
	private final EntityPlayer player;
	
	protected boolean isDark = false;
	protected boolean isLight = false;
	protected int breakRate = 0;
	protected int maxHealth = 0;
	protected int fatigue = 0;
	protected int maxFatigue = 0;
	
	
	
	public DarklightPlayer(EntityPlayer player)
	{
		this.player = player;
	}
	
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(Reference.PROP_NAME, new DarklightPlayer(player));
	}
	
	public static final DarklightPlayer get(EntityPlayer player)
	{
		return (DarklightPlayer) player.getExtendedProperties(Reference.PROP_NAME);
	}
	
	
	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		
		nbt.setInteger("breakRate", breakRate);
		nbt.setInteger("maxHealth", maxHealth);
		nbt.setInteger("fatigue", fatigue);
		nbt.setInteger("maxFatigue", maxFatigue);
		nbt.setBoolean("isDark", isDark);
		nbt.setBoolean("isLight", isLight);
		
		nbt.setTag(Reference.PROP_NAME, nbt);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(Reference.PROP_NAME);
		
		this.isDark = properties.getBoolean("isDark");
		this.isLight = properties.getBoolean("isLight");
		this.maxFatigue = properties.getInteger("maxFatigue");
		this.fatigue = properties.getInteger("fatigue");
		this.maxHealth = properties.getInteger("maxHealth");
		this.breakRate = properties.getInteger("breakRate");
	}
	
	@Override
	public void init(Entity entity, World world)
	{
	}
	
}
