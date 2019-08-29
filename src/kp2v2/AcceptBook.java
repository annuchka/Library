package kp2v2;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@SuppressWarnings("serial")
public class AcceptBook extends javax.swing.JFrame {
		private javax.swing.JButton jButton1;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JTextField jTextField1;
	    private javax.swing.JTextField jTextField2;
	    //������������ ��� ������ � showConfirmDialog
	        Object[] options = {"��","���"};
	    
	    public AcceptBook() {
	        initComponents();
	    }                   
	  //�����, � ������� ��������� �������� ���� ���������
	    private void initComponents() {
	    	jTextField1 = new javax.swing.JTextField();
	    	jTextField2 = new javax.swing.JTextField();
	        jButton1 = new javax.swing.JButton();
	        jLabel1 = new javax.swing.JLabel();
	      
	        
	      //��������� ����. ��������� ��, ��� ���������� ��� ������� �� �������
	        addWindowListener(new WindowAdapter() {
	        	public void windowClosing(WindowEvent e) {
	        		//��������� ������� ����
	        		dispose();
	        		//��������� ���� ������������
	        		new Librarian().setVisible(true);
	        	}});	

	        jTextField1.setText("ID �����");
	      //�������� ���������, ��� �� ��� ������� ������� �������� ����� � ����
	        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
	        	@Override
	             public void focusGained(FocusEvent ae){
	                 if(jTextField1.getText().equals("ID �����")){
	                	 jTextField1.setText("");
	                 }
	             }

	             @Override
	             public void focusLost(FocusEvent ae){
	                 if(jTextField1.getText().isEmpty()){
	                	 jTextField1.setText("ID �����");
	                 }
	             }});
	        jTextField2.setText("ID ��������");
	      //�������� ���������, ��� �� ��� ������� ������� �������� ����� � ����
	        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
	        	@Override
	             public void focusGained(FocusEvent ae){
	                 if(jTextField2.getText().equals("ID ��������")){
	                	 jTextField2.setText("");
	                 }
	             }

	             @Override
	             public void focusLost(FocusEvent ae){
	                 if(jTextField2.getText().isEmpty()){
	                	 jTextField2.setText("ID ��������");
	                 }
	             }});

	        jButton1.setText("�������");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	try {
	    				
		            	if(jTextField1.getText().equals("ID �����")) {
							JOptionPane.showMessageDialog(null, "����������� ID �����.\n ����� ����������.");
							}            	
		            	if(jTextField2.getText().equals("ID ��������")) {
		            		JOptionPane.showMessageDialog(null, "����������� ID ��������.\n ����� ����������.");
		            		} 
		            	if(!(jTextField1.getText().equals("ID �����"))&&!(jTextField2.getText().equals("ID ��������"))) {
		            		final String filepathP = System.getProperty("user.dir")+ File.separator + "Passwords.xml";
	            				final File xmlFileP = new File(filepathP);
	            				DocumentBuilder dbP = DocumentBuilderFactory.newInstance()
	            				.newDocumentBuilder();
	            				Document docP = dbP.parse(xmlFileP);
	            				docP.normalize();
	            				//������� ������ a��������
		 	                	NodeList nodeListP = docP.getElementsByTagName("Account");
		 	                	//���� � �� ������ �� ���� ������ ������ �������, � ���� ����� ID ������������ ���������
	    						//� ���������� �� � ���������
		 	                	String baf ="";
	    						for (int j = 0;j < nodeListP.getLength();j++) {
	    							Element nodeP = (Element)nodeListP.item(j);
	    							Element login = (Element)nodeP.getElementsByTagName("Login").item(0);
	    							String bLogin = login.getTextContent(); 
	    							// ���� ��� �������, �� �������� �����
	        							if (jTextField2.getText().equals(bLogin)){
	        								//����� ������, �������� ����
	        								Accept();
	        								baf="1";
	        							}
	        							
	        						if((j == nodeListP.getLength()-1)&&(baf.equals(""))){
	            							JOptionPane.showMessageDialog(null, "�������� � ����� ID � ���� �� ����������.\n "
	            						+ "��������� ������������ ��������� ������.");
	            							}
	        						}
	    						
	    						Transformer transformerP = TransformerFactory.newInstance().newTransformer();
	    						DOMSource sourceP = new DOMSource(docP);
	    						StreamResult resultP = new StreamResult(new File(filepathP));
	    						transformerP.transform(sourceP, resultP);
		            		}}catch (ParserConfigurationException | SAXException | IOException | TransformerException  ex) {
	    							ex.printStackTrace(System.out);
		            		}
	            	}});
	        

	        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
	        jLabel1.setText("������� ������ ��� ������ ����� :");

	        //���������� �����������
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap(20, Short.MAX_VALUE)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addComponent(jLabel1)
	                        .addGap(18, 18, 18))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGap(103, 103, 103))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(113, 113, 113))))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel1)
	                .addGap(18, 18, 18)
	                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
	                .addGap(18, 18, 18)
	                .addComponent(jButton1)
	                .addGap(19, 19, 19))
	        );

	        pack();
	        setLocationRelativeTo(null);
	    }

	      private void Accept() {
	    	  try {
	    		  if(jTextField1.getText().equals("ID �����")) {
          					JOptionPane.showMessageDialog(null, "����������� ID �����.\n ����� ����������.");
          					}
          				if(jTextField2.getText().equals("ID ��������")) {
          					JOptionPane.showMessageDialog(null, "����������� ID ��������.\n ����� ����������.");
	                }
                  if(!(jTextField1.getText().equals("ID �����"))&&!(jTextField2.getText().equals("ID ��������"))) {
          		final String filepath = System.getProperty("user.dir")+ File.separator + "Library.xml";
          				final File xmlFile = new File(filepath);
          				DocumentBuilder db = DocumentBuilderFactory.newInstance()
          				.newDocumentBuilder();
          				Document doc = db.parse(xmlFile);
          				doc.normalize();    
          				// �������� �� ������� ������
          				
          				
          					NodeList nodeList = doc.getElementsByTagName("Book");
          					String baf = "";
          					for (int i = 0; i < nodeList.getLength(); i++) {
          						Element node = (Element)nodeList.item(i);
          						Element Book = (Element)nodeList.item(i);
          						Element id = (Element)Book.getElementsByTagName("ID").item(0);
          						String bId = id.getTextContent();
          						String txt = "";
          						if(jTextField1.getText().equals(bId)) {
          							
          							Element element = (Element) node;
          							txt=txt+("ID: " + element.getElementsByTagName("ID").item(0).getTextContent())+"\n";
          							txt=txt+("��� ������: " + element.getElementsByTagName("AuthorFN").item(0).getTextContent())+"\n";
          							txt=txt+("������� ������: " + element.getElementsByTagName("AuthorLN").item(0).getTextContent())+"\n";
          							txt=txt+("�������� �����: " + element.getElementsByTagName("Title").item(0).getTextContent())+"\n";
          							txt=txt+("��� �������: " + element.getElementsByTagName("Date").item(0).getTextContent())+"\n";
          							//���� ������ ����� � �������, �� �������� ������ � ������� ����
          							if(!(element.getElementsByTagName("Status").item(0).getTextContent().equals(""))) {
          								baf = element.getElementsByTagName("Status").item(0).getTextContent();
          								JOptionPane.showMessageDialog(null, "������ ����� ��������� � ����������.");
          								//��������� ������ ����
          								dispose();
          								//��������� ���� ������������
          								new Librarian().setVisible(true);          								
          		    						} 
          							else {
          									if(element.getElementsByTagName("On_hands").item(0).getTextContent().equals(jTextField2.getText())) {
          			    						int r = JOptionPane.showOptionDialog(null,"������� ��� �����"+"\n"+" �� �������� � ID: "+jTextField2.getText()+"?"+"\n"+txt,null, JOptionPane.YES_NO_OPTION,
          										JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
          			    						if (r == JOptionPane.YES_OPTION) {
          									baf="1";
          									//������ ������ � ID �������� �������� �� �������
          									Element status = (Element)Book.getElementsByTagName("Status").item(0);
          									status.setTextContent("� �������");
          									Element on_hands = (Element)Book.getElementsByTagName("On_hands").item(0);
          									on_hands.setTextContent("");
          									// ���������� ��������� � XML ����
          									Transformer transformer = TransformerFactory.newInstance().newTransformer();
          									DOMSource source = new DOMSource(doc);
          									StreamResult result = new StreamResult(new File(filepath));
          									transformer.transform(source, result);
          									JOptionPane.showMessageDialog(null, "����� ������� �������.");
          									dispose();
          									new Librarian().setVisible(true);}
          								else if (r == JOptionPane.NO_OPTION) {
          									baf="1";
          									}
          								}
          									else {
          										baf="1";
          										JOptionPane.showMessageDialog(null, "������ ����� � ������� ��������.");}
          			    						}
          			    					
          									}
          								
          						else {if((i == nodeList.getLength()-1) && (baf.equals(""))){
          							JOptionPane.showMessageDialog(null, "����� ����� � ���� �� ����������.\n "
          						+ "��������� ������������ ��������� ������.");
          							}
          						}
          						}
          					}} catch (ParserConfigurationException | SAXException | IOException | TransformerException  ex) {
          						ex.printStackTrace(System.out);
          						}  
	      }  
}

	        

