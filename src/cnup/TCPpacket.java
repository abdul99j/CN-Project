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
/**
 *
 * @author Lenovo
 */
public class TCPpacket extends BasePacket {
    Hashtable values;
    private ArrayList<String> valuesName;
    private int TRANSPORT_LAYER=1;
    TCPpacket()
    {
        valuesName=new ArrayList<>();
        layer=TRANSPORT_LAYER;
        valuesName.add("Source Port");
        valuesName.add("Destination Port");
        valuesName.add("Source Address");
        valuesName.add("Destination Address");
        valuesName.add("Sequence Number");
        valuesName.add("ACK Number");
        valuesName.add("ACK");
        valuesName.add("Window Size");
        valuesName.add("FIN");
        valuesName.add("SIN");
        valuesName.add("RST");
        valuesName.add("PSH");
        values=new Hashtable();
    }

    @Override
    public Boolean isPacketof(Packet p) {
        return (p instanceof TCPPacket);
    }

    

    @Override
    public Object getValue(String name) {
        return values.get(name);
    }
     
    @Override
    public void setValues(Packet p){
        values.clear();
        TCPPacket packet=(TCPPacket) p;
        values.put(valuesName.get(0),packet.src_port);
        values.put(valuesName.get(1),packet.dst_port);
        values.put(valuesName.get(2),packet.src_ip);
        values.put(valuesName.get(3),packet.dst_ip);
        values.put(valuesName.get(4),packet.sequence);
        values.put(valuesName.get(5),packet.ack_num);
        values.put(valuesName.get(6),packet.ack);
        values.put(valuesName.get(7),packet.window);
        values.put(valuesName.get(8),packet.fin);
        values.put(valuesName.get(9),packet.syn);
        values.put(valuesName.get(10),packet.rst);
        values.put(valuesName.get(11),packet.psh);
        
    }
    @Override
    public String getProtocol()
    {
        return "TCP";
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
