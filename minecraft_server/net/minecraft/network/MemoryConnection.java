package net.minecraft.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.logging.ILogAgent;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet;

public class MemoryConnection implements INetworkManager
{
    private static final SocketAddress mySocketAddress = new InetSocketAddress("127.0.0.1", 0);
    private final List readPacketCache = Collections.synchronizedList(new ArrayList());
    private final ILogAgent field_98214_c;
    private MemoryConnection pairedConnection;
    private NetHandler myNetHandler;

    /** set to true by {server,network}Shutdown */
    private boolean shuttingDown;
    private String shutdownReason = "";
    private Object[] field_74439_g;
    @SideOnly(Side.CLIENT)
    private boolean field_74445_h;

    @SideOnly(Side.CLIENT)
    public MemoryConnection(ILogAgent par1ILogAgent, NetHandler par2NetHandler)
    {
        this.myNetHandler = par2NetHandler;
        this.field_98214_c = par1ILogAgent;
    }

    /**
     * Sets the NetHandler for this NetworkManager. Server-only.
     */
    public void setNetHandler(NetHandler par1NetHandler)
    {
        this.myNetHandler = par1NetHandler;
    }

    /**
     * Adds the packet to the correct send queue (chunk data packets go to a separate queue).
     */
    public void addToSendQueue(Packet par1Packet)
    {
        if (!this.shuttingDown)
        {
            this.pairedConnection.processOrCachePacket(par1Packet);
        }
    }

    /**
     * Wakes reader and writer threads
     */
    public void wakeThreads() {}

    @SideOnly(Side.CLIENT)
    public void func_74431_f()
    {
        this.pairedConnection = null;
        this.myNetHandler = null;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_74435_g()
    {
        return !this.shuttingDown && this.pairedConnection != null;
    }

    /**
     * Checks timeouts and processes all pending read packets.
     */
    public void processReadPackets()
    {
        int var1 = 2500;

        while (var1-- >= 0 && !this.readPacketCache.isEmpty())
        {
            Packet var2 = (Packet)this.readPacketCache.remove(0);
            var2.processPacket(this.myNetHandler);
        }

        if (this.readPacketCache.size() > var1)
        {
            this.field_98214_c.logWarning("Memory connection overburdened; after processing 2500 packets, we still have " + this.readPacketCache.size() + " to go!");
        }

        if (this.shuttingDown && this.readPacketCache.isEmpty())
        {
            this.myNetHandler.handleErrorMessage(this.shutdownReason, this.field_74439_g);
        }
    }

    /**
     * Return the InetSocketAddress of the remote endpoint
     */
    public SocketAddress getSocketAddress()
    {
        return mySocketAddress;
    }

    /**
     * Shuts down the server. (Only actually used on the server)
     */
    public void serverShutdown()
    {
        this.shuttingDown = true;
    }

    /**
     * Shuts down the network with the specified reason. Closes all streams and sockets, spawns NetworkMasterThread to
     * stop reading and writing threads.
     */
    public void networkShutdown(String par1Str, Object ... par2ArrayOfObj)
    {
        this.shuttingDown = true;
        this.shutdownReason = par1Str;
        this.field_74439_g = par2ArrayOfObj;
    }

    /**
     * returns 0 for memoryConnections
     */
    public int packetSize()
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public void func_74434_a(MemoryConnection par1MemoryConnection)
    {
        this.pairedConnection = par1MemoryConnection;
        par1MemoryConnection.pairedConnection = this;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_74433_h()
    {
        return this.field_74445_h;
    }

    @SideOnly(Side.CLIENT)
    public void func_74437_a(boolean par1)
    {
        this.field_74445_h = par1;
    }

    @SideOnly(Side.CLIENT)
    public MemoryConnection func_74432_i()
    {
        return this.pairedConnection;
    }

    /**
     * acts immiditally if isWritePacket, otherwise adds it to the readCache to be processed next tick
     */
    public void processOrCachePacket(Packet par1Packet)
    {
        if (par1Packet.canProcessAsync() && this.myNetHandler.canProcessPacketsAsync())
        {
            par1Packet.processPacket(this.myNetHandler);
        }
        else
        {
            this.readPacketCache.add(par1Packet);
        }
    }
}
