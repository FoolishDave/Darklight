package com.ddogclaw.darklight.proxy;

import net.minecraftforge.common.MinecraftForge;

import com.ddogclaw.darklight.handler.AbilityEventHandler;
import com.ddogclaw.darklight.handler.PlayerAbilityHandler;
import com.ddogclaw.darklight.handler.PlayerEventHandler;
import com.ddogclaw.darklight.util.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;

public class CommonProxy implements IProxy {

	public void registerEventHandlers() 
	{
		LogHelper.info("Registering Handlers");
		AbilityEventHandler abilityEventHandler = new AbilityEventHandler();
		PlayerEventHandler playerEventHandler = new PlayerEventHandler();
		
		MinecraftForge.EVENT_BUS.register(playerEventHandler);
		MinecraftForge.EVENT_BUS.register(abilityEventHandler);
		
		FMLCommonHandler.instance().bus().register(playerEventHandler);
		//FMLCommonHandler.instance().bus().register(abilityEventHandler);
		
	}

	@Override
	public ClientProxy getClientProxy() {
		// TODO Auto-generated method stub
		return null;
	}

}
