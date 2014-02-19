package xingu.node.client.bridge.impl;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;

import xingu.node.client.bridge.BridgeConnector;
import xingu.node.commons.signal.Signal;
import xingu.node.commons.signal.bridge.ReverseBridge;
import xingu.node.commons.signal.bridge.impl.BridgeSupport;
import br.com.ibnetwork.xingu.container.Inject;

public class ReverseBridgeImpl
	extends BridgeSupport
	implements ReverseBridge
{
	@Inject
	private BridgeConnector connector;
	
	
	@Override
	public Signal query(Signal signal, ChannelFutureListener onWrite)
		throws Exception
	{
		Channel channel = connector.getAcceptedChannel();
		return query(channel, onWrite, signal);
	}

	@Override
	public ChannelFuture deliver(Signal signal)
		throws Exception
	{
		Channel channel = connector.getAcceptedChannel();
		return deliver(channel, signal);
	}
}
