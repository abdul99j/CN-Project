/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cnup;
import static java.awt.Frame.NORMAL;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import jpcap.NetworkInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpcap.JpcapCaptor;
import net.sourceforge.jpcap.capture.CaptureDeviceNotFoundException;
import net.sourceforge.jpcap.capture.CaptureDeviceOpenException;
import net.sourceforge.jpcap.capture.PacketCapture;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import jpcap.JpcapWriter;
import jpcap.PacketReceiver;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import sun.rmi.runtime.Log;
/**
 *
 * @author Lenovo
 */
public class Cnup extends javax.swing.JFrame {

    Cnup(){
        initComponents();
        captureButton.setEnabled(true);
        stopButton.setEnabled(true);
        saveButton.setEnabled(true);
        filter_options.setEnabled(false);
    }
    /**
     * @param args the command line arguments
     * 
     */
    //Globals
    
    public static int MAX_PACKET_LIMIT=500;
    List<BasePacket> packets;
    JpcapCaptor captor;
    
    String threadName;
    
    public static int INDEX = 0;
    public static int flag = 0;
    public static int COUNTER = 0;
    boolean CaptureState = false;
    public static int No = 0;
    public static int deviceNo=0;
    
    List<Packet> packetList = new ArrayList<>();
    private void initComponents() {
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jToolBar1 = new javax.swing.JToolBar();
        listButton = new java.awt.Button();
        jLabel1 = new javax.swing.JLabel();
        filter_options = new javax.swing.JComboBox<>();
        captureButton = new java.awt.Button();
        stopButton = new JButton("Stop");
        saveButton = new java.awt.Button();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        jMenu2.setText("File");
        jMenuBar2.add(jMenu2);

        jMenu3.setText("Edit");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("jMenu4");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HACKERMAN Packet Sniffer");
        setName("HACKERMAN Packet Sniffer"); // NOI18N

        jToolBar1.setRollover(true);

        listButton.setActionCommand("List Interfaces");
        listButton.setBackground(new java.awt.Color(0, 0, 102));
        listButton.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        listButton.setForeground(new java.awt.Color(255, 255, 255));
        listButton.setLabel("List Interfaces");
        listButton.setPreferredSize(new java.awt.Dimension(90, 26));
        jToolBar1.add(listButton);

        jLabel1.setText(" Filter");
        jToolBar1.add(jLabel1);

        filter_options.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---", "TCP", "UDP", "ICMP" }));
        filter_options.setPreferredSize(new java.awt.Dimension(320, 24));
        filter_options.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        jToolBar1.add(filter_options);

        captureButton.setBackground(new java.awt.Color(0, 204, 0));
        captureButton.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        captureButton.setLabel("Capture");
        captureButton.setPreferredSize(new java.awt.Dimension(83, 24));
        jToolBar1.add(captureButton);

        stopButton.setBackground(new java.awt.Color(255, 0, 51));
        stopButton.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        stopButton.setLabel("Stop");
        stopButton.setPreferredSize(new java.awt.Dimension(83, 24));
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        jToolBar1.add(stopButton);

        saveButton.setLabel("Save");
        saveButton.setPreferredSize(new java.awt.Dimension(83, 24));
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        jToolBar1.add(saveButton);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Length", "Source", "Destination", "Protocol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setRowHeight(20);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
            }
        });
        jScrollPane4.setViewportView(jTable1);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel2.setText("Packet info:");

        jLabel3.setText("Hex view:");
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
/*public void PrintDeviceList()
    {
        for(int i=0;i<devices.length;i++)
        {
            System.out.println(i+"->"+devices[i].datalink_description+" MAC= "
                    +devices[i].name+ " NAME= "+devices[i].description);
        }    
    }    
*/    
public static void main (String[] args) throws IOException, CaptureDeviceNotFoundException, CaptureDeviceOpenException
{
        Cnup ui=new Cnup();
        ui.setVisible(true);
        NetworkInterface[] devices;
        PacketAnalyzer pa=new PacketAnalyzer();
        
        
                
        //Print Devices List //cmd testing
        devices=JpcapCaptor.getDeviceList();
        for(int i=0;i<devices.length;i++)
        {
            System.out.println(devices[i].description);
            
        }
        ArrayList<BasePacket> analyzed=new ArrayList<>();
        ArrayList<Packet> savedPackets=new ArrayList<>();
        Hashtable hash=new Hashtable();
        listButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JFrame f=new JFrame();
                javax.swing.JTable table=new javax.swing.JTable();
                String interfaces=new String();
                for(int i=0;i<devices.length;i++)
                {
                    interfaces+=i+"->"+devices[i].description+"\n";
                }
                String device_no=JOptionPane.showInputDialog(f,"SELECT the INTERFAC BELOW \n"+interfaces);
                try{
                    deviceNo=Integer.parseInt(device_no);
                    System.out.println(deviceNo);
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(f, "ENTER CORRECT INTERFACE NO");
                }
                
            }
        });
        System.out.println(deviceNo);
        JpcapCaptor captor=JpcapCaptor.openDevice(devices[7], 65536, true, 1000);
        captureButton.addActionListener(new java.awt.event.ActionListener(){
        
        @Override
        public void actionPerformed(ActionEvent e) {
            new Thread(new Runnable(){
                int i=0;
                @Override
                public void run() {
                    while(!stopButton.getModel().isPressed()){
                        SwingUtilities.invokeLater(new Runnable(){
                        Packet p=captor.getPacket();
                        @Override
                        public void run() {
                            if(p!=null){
                                IPv4 temp_ip=new IPv4();
                                savedPackets.add(p);
                                ArrayList<BasePacket> temp=new ArrayList<>();
                                temp.add(pa.AnalyzePacket(p));
        
                                if(temp_ip.isPacketof(p)){
                                    temp_ip.setValues(p);
                                    hash.put(i, temp_ip);
                                }
                                analyzed.add(temp.get(0));
                                Vector row=new Vector();
                                row.add(i);
                                row.add(p.len);
                                row.add(temp.get(0).getSourceAddress());
                                row.add(temp.get(0).getDestinationAddress());
                                row.add(temp.get(0).getProtocol());
                                DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
                                model.addRow(row);
                                i++;
                                temp.clear();
                            }
                        }
                   });
                        try{
                            java.lang.Thread.sleep(200);
                        }
                        catch(Exception e){}
                 }
                }
                
            }).start();
            
        }
       });
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTextArea1.selectAll();
                jTextArea2.selectAll();
                jTextArea1.replaceSelection("");
                jTextArea2.replaceSelection("");
                
                BasePacket packet=analyzed.get((int) jTable1.getValueAt(jTable1.getSelectedRow(), NORMAL));
                Object[] values=packet.getValues();
                ArrayList<String> valueNames=packet.getValuesNames();
                for(int i=0;i<values.length;i++)
                {
                    jTextArea1.append(valueNames.get(i)+":  "+values[i].toString()+"\n");
                }
                int key=(int)jTable1.getValueAt(jTable1.getSelectedRow(), NORMAL);
                Packet p=savedPackets.get(key);
                if(hash.containsKey(key)){
                    IPv4 pkt=new IPv4();
                    pkt=(IPv4) hash.get(key);
                    Object[] values_ip=pkt.getValues();
                    ArrayList<String> valueName_ip=pkt.getValuesNames();
                    jTextArea1.append("\n IPv4 INFO: \n");
                    for(int i=0;i<values.length;i++)
                    {
                        jTextArea1.append(valueNames.get(i)+":  "+values[i].toString()+"\n");
                    }
                    
                }
                byte[] data=analyzed.get(0).getData(p);
                BigInteger bg=new BigInteger(1,data);
                
                String dat=p.data.toString();
                StringBuilder hex=new StringBuilder();
                for(byte b:data)
                {
                    hex.append(String.format("%02x",b));
                }
                jTextArea2.append(bg.toString(16));
                
                
            }
   
   });
        saveButton.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFrame frame=new JFrame();
                    Object result=JOptionPane.showInputDialog(frame,"Enter FileName");
                    JpcapWriter writer=JpcapWriter.openDumpFile(captor, result.toString());
                    
                    for(int i=0;i<savedPackets.size();i++)
                    {
                        Packet p=savedPackets.get(i);
                        writer.writePacket(p);
                        
                    }
                    
                } catch (IOException ex) {
                    Logger.getLogger(Cnup.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
            }
   });
   
    
    
    
           
        
   

   

    
    
        

}

// Variables declaration - do not modify//GEN-BEGIN:variables
    public static java.awt.Button captureButton;
    public static javax.swing.JComboBox<String> filter_options;
    
    public static JButton stopButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTextArea jTextArea1;
    public static javax.swing.JTextArea jTextArea2;
    
    private javax.swing.JToolBar jToolBar1;
    public static java.awt.Button listButton;
    public static java.awt.Button saveButton;
    

}


       


