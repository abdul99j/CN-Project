/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cnup;

import java.util.ArrayList;
import java.util.Hashtable;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

/**
 *
 * @author Lenovo
 */
public class DNSPacket extends BasePacket {
    Hashtable values;
    private ArrayList<String> valuesName;
    
    DNSPacket()
    {
        valuesName=new ArrayList<>();
        valuesName.add("Source Port");
        valuesName.add("Destination Port");
        valuesName.add("Source Address");
        valuesName.add("Destination Address");
        valuesName.add("Length");
        valuesName.add("TTL");
        values=new Hashtable();
    }

    @Override
    public Boolean isPacketof(Packet p) {
        if(p instanceof UDPPacket)
        {
            UDPPacket pkt=(UDPPacket)p;
            if(pkt.src_port==53&&pkt.dst_port==53)
            {
                return true;
            }
        }
        else if(p instanceof TCPPacket){
            TCPPacket pkt=(TCPPacket)p;
            if(pkt.src_port==53&&pkt.dst_port==53)
            {
                return true;
            }
            
        }
        return false;
    }

    @Override
    public Object getValue(String name) {
        return values.get(name);
    }

    @Override
    public void setValues(Packet p){
        UDPPacket packet=(UDPPacket)p;
        values.put(valuesName.get(0),packet.src_port);
        values.put(valuesName.get(1),packet.dst_port);
        values.put(valuesName.get(2),packet.src_ip);
        values.put(valuesName.get(3),packet.dst_ip);
        values.put(valuesName.get(4),packet.length);
        values.put(valuesName.get(5),packet.hop_limit);
    }
    @Override
    public String getProtocol()
    {
        return "DNS";
    }

    @Override
    public Object[] getValues() {
        Object[] value = new Object[valuesName.size()];
        for(int i=0;i<valuesName.size();i++)
        {
            value[i]=values.get(valuesName.get(i));
        }
        return value;
    }
    
}
