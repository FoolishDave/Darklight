package com.ddogclaw.darklight.util;

import net.minecraft.nbt.NBTTagCompound;

public interface INBTTaggable 
{
	void readFromNBT(NBTTagCompound nbtTabCompound);
	
	void writeToNBT(NBTTagCompound nbtTagCompound);
}
