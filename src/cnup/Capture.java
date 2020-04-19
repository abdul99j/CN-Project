/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cnup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.packet.Packet;

/**
 *
 * @author Lenovo
 */
public class Capture implements Runnable {
    NetworkInterface []devices;
    public static int MAX_PACKET_LIMIT=500;
    List<BasePacket> packets;
    JpcapCaptor captor;
    Thread t;
    String threadName;
    int deviceNo;
    PacketAnalyzer pa;
    
    Capture(){
        devices=JpcapCaptor.getDeviceList();
        packets=new ArrayList<>();
        threadName="Capture Thread";
        pa=new PacketAnalyzer();
    }
    public void PrintDeviceList()
    {
        for(int i=0;i<devices.length;i++)
        {
            System.out.println(i+"->"+devices[i].datalink_description+" MAC= "
                    +devices[i].mac_address+ " NAME= "+devices[i].description);
        }    
    }

    public void setDevice() throws IOException
    {
       Scanner input=new Scanner(System.in);
       int option=input.nextInt();
       if(option>0&&option<=7)
       {
           deviceNo=option;
       }
    }

    @Override
    public void run() {
        try {
            captor=JpcapCaptor.openDevice(devices[deviceNo], 65536, true, 20);
        } catch (IOException ex) {
            Logger.getLogger(Capture.class.getName()).log(Level.SEVERE, null, ex);
        }
           for(int i=0;i<10;i++)
           {
               Packet p=captor.getPacket();
               BasePacket packet=pa.AnalyzePacket(p);
               //testing only
               if(packet instanceof TCPpacket)
               {
                   System.out.println(packet.getProtocol());
               }
               else if(packet instanceof UDPpacket)
               {
                   System.out.println(packet.getProtocol());
               }
               else if(packet instanceof ICMPpacket)
               {
                   System.out.println(packet.getProtocol());
               }
           }
    }
    
    public void CapturePackets(){
        System.out.println("STARTING PACKET CAPTURE");
        if(t==null)
        {
            t=new Thread(this,threadName);
        }
        t.start();
    }
}
   
