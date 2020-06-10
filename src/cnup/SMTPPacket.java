/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cnup;

import java.util.ArrayList;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;

/**
 *
 * @author Lenovo
 */
public class SMTPPacket extends TCPpacket{

    @Override
    public Boolean isPacketof(Packet p) {
        if(p instanceof TCPPacket &&
		   (((TCPPacket)p).src_port==25 || ((TCPPacket)p).dst_port==25))
			return true;
		else return false;
    }

    
    
}
