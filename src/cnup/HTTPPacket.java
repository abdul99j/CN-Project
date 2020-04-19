/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cnup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;

/**
 *
 * @author Lenovo
 */
public class HTTPPacket extends BasePacket{
    
    Hashtable values;
    private ArrayList<String> valuesName;
    private int APPLICATION_LAYER=1;
    HTTPPacket()
    {
        valuesName=new ArrayList<>();
        layer=APPLICATION_LAYER;
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
        valuesName.add("Method");
        valuesName.add("Header");
        values=new Hashtable();
    }
    @Override
    public Boolean isPacketof(Packet p) {
        if(p instanceof TCPPacket &&
		   (((TCPPacket)p).src_port==80 || ((TCPPacket)p).dst_port==80))
        {
            return true;
        }
        return false;
    }

     @Override
    public Object getValue(String name) {
        return values.get(name);
    }
     
    @Override
    public void setValues(Packet p){
        values.clear();
        String method="";
        ArrayList<Object> header=new ArrayList<>();
        
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
        BufferedReader in=new BufferedReader(new StringReader(new String(p.data)));
			
        try {
            
            method=in.readLine();
            if(method==null || method.indexOf("HTTP")==-1){
                method="No HTTP Header";
                return;
            }
            String l;
            while((l=in.readLine()).length()>0)
            {
                header.add(l);
            }
        } catch (IOException ex) {
           
        }
			
			
			
        
        
    }
    @Override
    public String getProtocol()
    {
        return "HTTP";
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
