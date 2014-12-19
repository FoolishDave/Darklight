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
			DarklightPlayer abilities = DarklightPlayer.get(event.entityPlayer);
			
			if (abilities != null)
			{
				LogHelper.info("Found player with ability trying to break block");
				if (abilities.isDark || abilities.isLight)
				{
					event.newSpeed = abilities.breakRate;
				}
			}
			else
			{
				LogHelper.info("Player tried break, but has no abilities");
			}
		}
	}
}
