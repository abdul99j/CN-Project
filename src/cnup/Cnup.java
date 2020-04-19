/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cnup;
import java.io.IOException;
import jpcap.NetworkInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpcap.JpcapCaptor;
import net.sourceforge.jpcap.capture.CaptureDeviceNotFoundException;
import net.sourceforge.jpcap.capture.CaptureDeviceOpenException;
import net.sourceforge.jpcap.capture.PacketCapture;
import java.util.Scanner;
import jpcap.PacketReceiver;
import jpcap.packet.Packet;
/**
 *
 * @author Lenovo
 */
public class Cnup {

    /**
     * @param args the command line arguments
     * 
     */
          static NetworkInterface [] devices ;
public static void main (String[] args) throws IOException, CaptureDeviceNotFoundException, CaptureDeviceOpenException
{
        Capture c=new Capture();
        c.PrintDeviceList();
        c.setDevice();
        c.CapturePackets();
        
        
      
}
}


       


