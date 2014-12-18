package com.ddogclaw.darklight.util;

import com.ddogclaw.darklight.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class EntityHelper 
{
	public static NBTTagCompound getCustomEntityData(Entity entity)
	{
		if (entity != null && entity.getEntityData().hasKey(Reference.MODID) && entity.getEntityData().getTag(Reference.MODID) instanceof NBTTagCompound)
		{
			return entity.getEntityData().getCompoundTag(Reference.MODID);
		}
		
		return new NBTTagCompound();
	}
	
	public static void saveCustomEntityData(Entity entity, NBTTagCompound nbt)
	{
		if (entity != null)
		{
			entity.getEntityData().setTag(Reference.MODID, nbt);
		}
	}
	
}
