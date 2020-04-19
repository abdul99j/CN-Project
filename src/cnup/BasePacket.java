/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cnup;

import jpcap.packet.Packet;

/**
 *
 * @author Lenovo
 */
public abstract class BasePacket {
    public int layer;
    
    public abstract Boolean isPacketof(Packet p);
    public byte[] getData(Packet p){
        return p.data;
    }
    public byte[] getHeader(Packet p){
        return p.header;
    }
    public abstract Object getValue(String name);
    public abstract Object[] getValues();
    public abstract void setValues(Packet p);
    public abstract String getProtocol();
    
}
