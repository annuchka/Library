package kp2v2;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


@SuppressWarnings("serial")
public class Authorization extends javax.swing.JFrame implements FocusListener{ 
	private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPasswordField jPasswordField2;
    // ���������� ������������ ��� �������� ���� � ��������
    public static String y="";
    // ���������� ���������� ��� �����������
	int a = 0;
	public Authorization() {
		initComponents();
		}
	    //�����, � ������� ��������� �������� ���� ���������
	    private void initComponents() {
	    	
	        jDesktopPane1 = new javax.swing.JDesktopPane();
	        jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jTextField1 = new javax.swing.JTextField();
	        jPasswordField2 = new javax.swing.JPasswordField();
	        jButton1 = new javax.swing.JButton();
	        //��� ������� �� �������, ���� ���������
	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); 
	        
	        //��� ����
	        jDesktopPane1.setBorder(new javax.swing.border.MatteBorder(null));
	        jDesktopPane1.setAutoscrolls(true);        
            
	        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
	        jLabel1.setText("�����������");
	        
	        //setHorizontalAlignment ������� �� ������������ ������ ������ ��������(� ������ ������ �� ������)
	        jPasswordField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
	        jPasswordField2.setText("jPasswordField2");
	        //�������� ���������, ��� �� ��� ������� ������� �������� ����� � ����
	        jPasswordField2.addFocusListener(new java.awt.event.FocusAdapter() {
	        	@Override
	        	public void focusGained(FocusEvent ae){
	        		if(("jPasswordField2").equals(String.valueOf(jPasswordField2.getPassword()))){
	        			jPasswordField2.setText("");       			
	                 }
	             }
	             @Override
	             public void focusLost(FocusEvent ae){            	 
	                 if(("").equals(String.valueOf(jPasswordField2.getPassword()))){
	                	 jPasswordField2.setText("jPasswordField2");
	                	 }
	             }});	        
	       
	        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
	        jTextField1.setText("�����");
	        //��� ��������� ������� �� ������� ���������� �������
	        jTextField1.setToolTipText("���� �� ��������, �� ������� ��� Id.");
	        //�������� ���������, ��� �� ��� ������� ������� �������� ����� � ����
	        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
	        	 @Override
	             public void focusGained(FocusEvent ae){
	                 if(jTextField1.getText().equals("�����")){
	                	 jTextField1.setText("");
	                 }
	             }

	             @Override
	             public void focusLost(FocusEvent ae){
	                 if(jTextField1.getText().isEmpty()){
	                	 jTextField1.setText("�����");
	                 }
	             }});
	        //addKeyListener ��������� ����������
	        jTextField1.addKeyListener(new KeyAdapter(){ 
	            @Override
	            //��� ������� ������ Enter ���� ����� ���������� ����
	            public void keyPressed(KeyEvent e){
	                if (e.getKeyCode() == KeyEvent.VK_ENTER){
	                	//����� �����������
	                	doing();
	                }
	            }
	        });
	        
	      //addKeyListener ��������� ����������
	        jPasswordField2.addKeyListener(new KeyAdapter(){
	            @Override
	          //��� ������� ������ Enter ���� ����� ���������� ����
	            public void keyPressed(KeyEvent e){
	                if (e.getKeyCode() == KeyEvent.VK_ENTER){
	                	//����� �����������
	                	doing();
	                }
	            }
	        });

	        jButton1.setText("�����");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	//����� �����������
	            	doing();
	            }
	        });
	        jLabel2.setText("������ ������?");
	        //��������� �������
	        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
	        	 @Override
	             public void mouseEntered(MouseEvent ae){
	        		 jLabel2.setForeground(new java.awt.Color(255, 255, 255));
	             }

	             @Override
	             public void mouseExited(MouseEvent ae){
	            	 jLabel2.setForeground(new java.awt.Color(0, 0, 0));
	             }
	             //��������� ������� �������� �� �������
	             public void mouseClicked(MouseEvent ae) {
	            	 String result ="";
	            	 UIManager.put("OptionPane.cancelButtonText", "������");
	            	 UIManager.put("OptionPane.okButtonText"    , "������������");
	            	 result = JOptionPane.showInputDialog(null,"������� ��� ID: ", "������������� ������",
	            			 JOptionPane.PLAIN_MESSAGE);
	            	 try {
	            		 final String filepathP = System.getProperty("user.dir")+ File.separator + "Passwords.xml";
	            		 final File xmlFileP = new File(filepathP);
	            		 DocumentBuilder dbP = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	            		 Document docP = dbP.parse(xmlFileP);
	            		 docP.normalize();
	            		 //������� ������ a��������
	            		 NodeList nodeListP = docP.getElementsByTagName("Account");
	            		 String buf = "";
	            		 for (int j = 0;j < nodeListP.getLength();j++) {
	            			 Element nodeP = (Element)nodeListP.item(j);
	            			 Element login = (Element)nodeP.getElementsByTagName("Login").item(0);
	            			 String bLogin = login.getTextContent(); 
	            			 Element pass = (Element)nodeP.getElementsByTagName("Pass").item(0);
	            			 String bPass = pass.getTextContent();
	            			 try {
	            				 if (result.equals(bLogin)){
	            					 buf="1";
	            					 UIManager.put("OptionPane.okButtonText"    , "OK");
	            					 JOptionPane.showMessageDialog(null,"��� ������: \n" + bPass);
	            					 }
	            				 }catch(NullPointerException  ex) {
	            					 buf="1";
	            					 ex.printStackTrace(System.out);
	            					 }
	            			 if((j == nodeListP.getLength()-1)&&(buf.equals(""))){
	            				 UIManager.put("OptionPane.okButtonText"    , "OK");
	            				 JOptionPane.showMessageDialog(null, "������������ � ����� ID � ���� �� ����������.\n "
	            				 + "��������� ������������ ��������� ������.");
	            				 }
	            			 }
	            		 Transformer transformerP = TransformerFactory.newInstance().newTransformer();
	            		 DOMSource sourceP = new DOMSource(docP);
	            		 StreamResult resultP = new StreamResult(new File(filepathP));
	            		 transformerP.transform(sourceP, resultP); 
	            		 }catch (ParserConfigurationException | SAXException | IOException | TransformerException  ex) {
	            			 ex.printStackTrace(System.out);
	            			 }
	            	 }} );
	            	 	      
	        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
	        jDesktopPane1.setLayer(jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);
	        jDesktopPane1.setLayer(jPasswordField2, javax.swing.JLayeredPane.DEFAULT_LAYER);
	        jDesktopPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
	        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
	      
	        //���������� �����������
	        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
	        jDesktopPane1.setLayout(jDesktopPane1Layout);
	        jDesktopPane1Layout.setHorizontalGroup(
	            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jDesktopPane1Layout.createSequentialGroup()
	                .addGap(62, 62, 62)
	                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
	                    .addComponent(jButton1)
	                    .addComponent(jLabel2)
	                    .addComponent(jPasswordField2)
	                    .addComponent(jTextField1)
	                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addContainerGap(58, Short.MAX_VALUE))
	        );
	        jDesktopPane1Layout.setVerticalGroup(
	            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jDesktopPane1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel1)
	                .addGap(18, 18, 18)
	                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jLabel2)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jButton1)
	                .addContainerGap(23, Short.MAX_VALUE))
	        );

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jDesktopPane1)
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jDesktopPane1)
	        );

	        pack();
	        setLocationRelativeTo(null);
	    }
	    private void doing(){
	    	try {
	    		// ������ ��������� ������ ��������� XML ����� � ������� �������� ������� ������
	    		final File xmlFile = new File(System.getProperty("user.dir")+ File.separator + "Passwords.xml");
	    		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    		DocumentBuilder db = dbf.newDocumentBuilder();
	    		Document doc = db.parse(xmlFile);
	    		// ��������� ������������ �� �����������, �� �������������
	    		doc.getDocumentElement().normalize();
	    		// �������� ��� ���� � ������ "Account"
	    		NodeList AccountList = doc.getElementsByTagName("Account");
	    		for (int i = 0; i < AccountList.getLength(); i++) {
	    			// ��������� ����������
	    			Node node = AccountList.item(i);
	    			if (Node.ELEMENT_NODE == node.getNodeType()) {
	    				Element element = (Element) node;
	    				if((element.getElementsByTagName("Login").item(0).getTextContent().equals(jTextField1.getText()))&&
	    						(element.getElementsByTagName("Pass").item(0).getTextContent().equals(String.valueOf(jPasswordField2.getPassword())))&&
	    						"Adm".equals(element.getElementsByTagName("Type").item(0).getTextContent())){
	    					a=1;
	    					}
	    				else {if((element.getElementsByTagName("Login").item(0).getTextContent().equals(jTextField1.getText()))&&
	    						(element.getElementsByTagName("Pass").item(0).getTextContent().equals(String.valueOf(jPasswordField2.getPassword())))&&
	    						"Bib".equals(element.getElementsByTagName("Type").item(0).getTextContent())){
	    					a=2;
	    					}
	    				else {if((element.getElementsByTagName("Login").item(0).getTextContent().equals(jTextField1.getText()))&&
	    						(element.getElementsByTagName("Pass").item(0).getTextContent().equals(String.valueOf(jPasswordField2.getPassword())))&&
	    						"Chit".equals(element.getElementsByTagName("Type").item(0).getTextContent())){
	    					a=3;
	    					y = jTextField1.getText();
	    					}
	    				else{if((a==0)&&(i== AccountList.getLength()-1)){
	    					UIManager.put("OptionPane.okButtonText"    , "OK");
	    					JOptionPane.showMessageDialog(null, "��������� ������������ ����� ������.\n "
	    							+ "���� �� ������� � ����� ���������� - \n ���������� � ������������ ��� �����������");}
	    				}}}}
	    			else{}
	    			} 
	    		} catch (ParserConfigurationException | SAXException | IOException ex) {
	    			ex.printStackTrace(System.out);
	    			}
	    	//� ������� switch �������� � ����� ������� ������ �� ������
	    switch(a) {
	    	case 1: 
	    		new Administrator().setVisible(true);
	    		dispose();
	    	break;
	    	case 2: 
	    		new Librarian().setVisible(true);
	    		dispose();
       		break;
       		case 3:
       			new Reader().setVisible(true);
       			dispose();
       		break;
       	default:
       	    break;
       	    }
	    }
	    public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub	
	    	}
	    // ������� ����
	    public static void main(String args[]) {
	    	try {
	    		for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	    			if ("Nimbus".equals(info.getName())) {
	    				javax.swing.UIManager.setLookAndFeel(info.getClassName());
	    				break;
	    				}
	    			}
	    		} catch (ClassNotFoundException ex) {
	    			java.util.logging.Logger.getLogger(Authorization.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    		} catch (InstantiationException ex) {
	    			java.util.logging.Logger.getLogger(Authorization.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    		} catch (IllegalAccessException ex) {
	    			java.util.logging.Logger.getLogger(Authorization.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    			java.util.logging.Logger.getLogger(Authorization.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    			}
	    	java.awt.EventQueue.invokeLater(new Runnable() {
	    		public void run() {
	    			new Authorization().setVisible(true);
	    			}
	    		});
	    	}
	    @Override
	    public void focusGained(FocusEvent arg0) {
	    	// TODO Auto-generated method stub
	    	}
	    @Override
	    public void focusLost(FocusEvent arg0) {
	    	// TODO Auto-generated method stub
	    	}
	    }
	
    
