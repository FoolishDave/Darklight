package com.ddogclaw.darklight.handler;

import com.ddogclaw.darklight.abilities.PlayerAbilities;
import com.ddogclaw.darklight.util.LogHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;

public class AbilityEventHandler 
{
	@SubscribeEvent
	public void breakSpeed(BreakSpeed event)
	{
		if (event.entityPlayer != null)
		{
			PlayerAbilities abilities = PlayerAbilities.getPlayerInfo(event.entityPlayer);
			
			if (abilities != null)
			{
				LogHelper.debug("Found player with ability trying to break block");
				if (abilities.getDark() || abilities.getLight())
				{
					event.newSpeed = abilities.getBreakRate();
				}
			}
		}
	}
}
