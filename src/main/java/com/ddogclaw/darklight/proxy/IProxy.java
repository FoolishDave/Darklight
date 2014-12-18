package com.ddogclaw.darklight.proxy;

public interface IProxy 
{
	public abstract ClientProxy getClientProxy();
	
	public abstract void registerEventHandlers();
}
