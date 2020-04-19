/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cnup;

import java.util.ArrayList;
import jpcap.packet.Packet;

/**
 *
 * @author Lenovo
 */
public class PacketAnalyzer {
    ArrayList<BasePacket> packetTypes;
    ArrayList<BasePacket> analyzedPkt;
    
    PacketAnalyzer()
    {
        packetTypes=new ArrayList<>();
        packetTypes.add(new TCPpacket());
        packetTypes.add(new UDPpacket());
    }
    public BasePacket AnalyzePacket(Packet p)
    {
        BasePacket pkt=new TCPpacket();
        for(int i=0;i<packetTypes.size();i++)
        {
            if(packetTypes.get(i).isPacketof(p))
            {
                String protocol=packetTypes.get(i).getProtocol();
                switch(protocol)
                {
                    case "TCP":
                        pkt=new TCPpacket();
                        pkt.setValues(p);
                        break;
                    case "UDP":
                        pkt=new UDPpacket();
                        pkt.setValues(p);
                    
                    case "ICMP":
                        pkt=new ICMPpacket();
                        pkt.setValues(p);
                    default:
                        
                        
                }
            }
        }
        return pkt;
    }
}
