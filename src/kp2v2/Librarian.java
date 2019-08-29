package kp2v2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


@SuppressWarnings("serial")
public class Librarian extends javax.swing.JFrame {
	private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jLabel1;

    
    public Librarian() {
        initComponents();
    }

  //�����, � ������� ��������� �������� ���� ���������
    private void initComponents() {
    	jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        //��� ������� �� ������� ��������� �������� ������
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("������������");

        jButton1.setText("������ �����");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	//������� ��� ���������
            	dispose();
            	//��������� ���� ������ �����
            	new GiveBook().setVisible(true);
            }
        });

        jButton2.setText("������� �����");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	//������� ���� ���������
            	dispose();
            	//��������� ���� �������� �����
            	new AcceptBook().setVisible(true);
            }
        });

        jButton3.setText("������� ������������");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	//������� ���� ���������
            	dispose();
            	//��������� ���� �����������
            	new Authorization().setVisible(true);
            }
        });
        
        jButton4.setText("����������� ������ ��������");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            @SuppressWarnings("static-access")
			public void actionPerformed(java.awt.event.ActionEvent evt) {
            	//������� ���� ���������
            	dispose();
            	try {
            		final String filepath = System.getProperty("user.dir")+ File.separator + "Passwords.xml";
            				final File xmlFile = new File(filepath);
            				DocumentBuilder db = DocumentBuilderFactory.newInstance()
            				.newDocumentBuilder();
            				Document doc = db.parse(xmlFile);
            				doc.normalize();
            				//������� ������ ��������
	 	                	NodeList nodeList = doc.getElementsByTagName("Account");
	 	                	//��������st ��� �������� ����������� ID
	 	                	final Random randomID = new Random();
	 	                	//�������
    						int i = 0;
    						//�������� ��� ID
    						int ID = 10000;
    						//� ���� ���������� �������� ��������� ������ , ��� 4- ��� �� ������ � ������, 48- 122 - ������� �� ��������� ASCII
    						String password = new Random().ints(4, 48, 122).collect(StringBuilder::new,
    						        StringBuilder::appendCodePoint, StringBuilder::append).toString();
    						//��������� ��������� ������� �� 1 �� 10000 ��������� 1, �� �������� ������������ �� 0
    						String a = String.valueOf(randomID.nextInt(ID) + 1);
    						//���� � �� ������ �� ���� ������ ������ �������, � ���� ����� ID ������������ ���������
    						//� ���������� �� �� ��������������� ID ��� ������
    						while (i < nodeList.getLength()) {
    							Element node = (Element)nodeList.item(i);
    							Element login = (Element)node.getElementsByTagName("Login").item(0);
    							String bLogin = login.getTextContent();      			            
        						if (node.ELEMENT_NODE == node.getNodeType()) {
        							// ���� ��� �������, �� ������������ ����� ID ��������� ������� �� ������
        							if (a.equals(bLogin)){
        								a="";
        								i=0;
        								a = String.valueOf(randomID.nextInt(ID) + 1);
        								}
        							//��� �� �������, ��������� �� ��������� ��������� � ��� �� ����� ������
        							else {i++;}
        							}}
    						//�������� �������� �������
    						Node root = doc.getDocumentElement();
            				Element account = doc.createElement("Account");
            	            //������������� ��������� ������ � ����� �������   
                            Element type = doc.createElement("Type");
                            type.setTextContent("Chit");
                            Element login = doc.createElement("Login");
                            login.setTextContent(a);
                            Element pass = doc.createElement("Pass");
                            pass.setTextContent(password);
                            
                            // ��������� ���������� �������� ����� � ������� <Book>
                            account.appendChild(type);
                            account.appendChild(login);
                            account.appendChild(pass);
                            // ��������� ����� � �������� �������
                            root.appendChild(account);
                            
						// ���������� ��������� � XML ����
						Transformer transformer = TransformerFactory.newInstance().newTransformer();
						DOMSource source = new DOMSource(doc);
						StreamResult result = new StreamResult(new File(filepath));
						transformer.transform(source, result);
						JOptionPane.showMessageDialog(null, "ID ������ ��������: "+a +"\n"+"������ ������ ��������: "+ password +"\n"+
								"��������� ��� ���������� ��������.");
						new Librarian().setVisible(true);
						
						} catch (ParserConfigurationException | SAXException | IOException | TransformerException  ex) {
							ex.printStackTrace(System.out);
							}
            	
            }
        });
        jButton5.setText("�����");
        
        jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
            	Search();
                	}});
        
        jButton6.setText("������ ������");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	//����� ������, ������� �������� ����� 
            	New();
            }});
        
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField1.addKeyListener(new KeyAdapter(){ 
            @Override
            //��� ������� ������ Enter ���� ����� ���������� ����
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                	//����� ��������� ������ ����
                	Search();
                }
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        New();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("�����:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton4)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton5)
                            .addGap(13, 13, 13)
                            .addComponent(jButton6))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(11, 11, 11)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("static-access")
	private void Search() {
    	String txt="";
    	
    	if(jTextField1.getText().equals("")) {
            	JOptionPane.showMessageDialog(null, "����������� ������ ��� ������.");
          }
    	else{                	
              try { 
            	  final String filepath = System.getProperty("user.dir")+ File.separator + "Library.xml";
            	  final File xmlFile = new File(filepath);
            	  DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            	  Document doc = db.parse(xmlFile);
            	  doc.normalize();
            	  // �������� ��� ���� � ������ "Book"
            	  NodeList nodeList = doc.getElementsByTagName("Book");
            	  for (int i = 0; i < nodeList.getLength(); i++) {
            		// ���� ���������� ���������� ������� � ID, ���� ��� ����, ���������� ��� �����
          			//� ����������  txt
          			Element node = (Element)nodeList.item(i);
          			Element id = (Element)node.getElementsByTagName("ID").item(0);
          			String bId = id.getTextContent();
          			if ((node.ELEMENT_NODE == node.getNodeType())&&(jTextField1.getText().equals(bId))) {
          				Element element = (Element) node;
          				txt=txt+("ID: " + element.getElementsByTagName("ID").item(0).getTextContent())+"\n";
          				txt=txt+("��� ������: " + element.getElementsByTagName("AuthorFN").item(0).getTextContent())+"\n";
          				txt=txt+("������� ������: " + element.getElementsByTagName("AuthorLN").item(0).getTextContent())+"\n";
          				txt=txt+("�������� �����: " + element.getElementsByTagName("Title").item(0).getTextContent())+"\n";
          				txt=txt+("��� �������: " + element.getElementsByTagName("Date").item(0).getTextContent())+"\n";
          				//���������� �������� �� ������� ������ �������, ���� ��� �����, �� � ������ ���  �� ����������(��� �� ���� ����������� ������)
          				if(!(element.getElementsByTagName("Status").item(0).getTextContent().equals(""))) {
          					txt=txt+("������: " + element.getElementsByTagName("Status").item(0).getTextContent())+"\n";}
          				if(!(element.getElementsByTagName("On_hands").item(0).getTextContent().equals(""))) {
          					txt=txt+("�� ����� � �������� � ID: " + element.getElementsByTagName("On_hands").item(0).getTextContent())+"\n";}
          				txt=txt+"\n";
          				}}
            	  for (int i = 0; i < nodeList.getLength(); i++) {
          			// ���� ���������� ���������� ������� � �������� �����, ���� ��� ����, ���������� ��� �����
          			//� ����������  txt
            		  Element node = (Element)nodeList.item(i);
            		  Element title = (Element)node.getElementsByTagName("Title").item(0);
            		  String bTitle = title.getTextContent();
            		  if ((node.ELEMENT_NODE == node.getNodeType())&&(jTextField1.getText().equals(bTitle))) {
            			  Element element = (Element) node;
            			  txt=txt+("ID: " + element.getElementsByTagName("ID").item(0).getTextContent())+"\n";
            			  txt=txt+("��� ������: " + element.getElementsByTagName("AuthorFN").item(0).getTextContent())+"\n";
            			  txt=txt+("������� ������: " + element.getElementsByTagName("AuthorLN").item(0).getTextContent())+"\n";
            			  txt=txt+("�������� �����: " + element.getElementsByTagName("Title").item(0).getTextContent())+"\n";
            			  txt=txt+("��� �������: " + element.getElementsByTagName("Date").item(0).getTextContent())+"\n";
            			  if(!(element.getElementsByTagName("Status").item(0).getTextContent().equals(""))) {
            				  txt=txt+("������: " + element.getElementsByTagName("Status").item(0).getTextContent())+"\n";}
            			  if(!(element.getElementsByTagName("On_hands").item(0).getTextContent().equals(""))) {
            				  txt=txt+("�� ����� � �������� � ID: " + element.getElementsByTagName("On_hands").item(0).getTextContent())+"\n";}
            			  txt=txt+"\n";
          			 }}
            	  for (int i = 0; i < nodeList.getLength(); i++) {
          			// ���� ���������� ���������� ������� � �������� ������, ���� ��� ����, ���������� ��� �����
          			//� ����������  txt
            		  Element node = (Element)nodeList.item(i);
            		  Element authorln = (Element)node.getElementsByTagName("AuthorLN").item(0);
            		  String bAuthorln = authorln.getTextContent();
            		  if ((node.ELEMENT_NODE == node.getNodeType())&&(jTextField1.getText().equals(bAuthorln))) {
            			  Element element = (Element) node;
            			  txt=txt+("ID: " + element.getElementsByTagName("ID").item(0).getTextContent())+"\n";
            			  txt=txt+("��� ������: " + element.getElementsByTagName("AuthorFN").item(0).getTextContent())+"\n";
            			  txt=txt+("������� ������: " + element.getElementsByTagName("AuthorLN").item(0).getTextContent())+"\n";
            			  txt=txt+("�������� �����: " + element.getElementsByTagName("Title").item(0).getTextContent())+"\n";
            			  txt=txt+("��� �������: " + element.getElementsByTagName("Date").item(0).getTextContent())+"\n";
            			  if(!(element.getElementsByTagName("Status").item(0).getTextContent().equals(""))) {
            				  txt=txt+("������: " + element.getElementsByTagName("Status").item(0).getTextContent())+"\n";}
            			  if(!(element.getElementsByTagName("On_hands").item(0).getTextContent().equals(""))) {
            				  txt=txt+("�� ����� � �������� � ID: " + element.getElementsByTagName("On_hands").item(0).getTextContent())+"\n";}
            			  txt=txt+"\n";
            			  }}
            	  for (int i = 0; i < nodeList.getLength(); i++) {
            		  // ���� ���������� ���������� ������� � ������ ������, ���� ��� ����, ���������� ��� �����
            		  //� ����������  txt
            		  Element node = (Element)nodeList.item(i);
            		  Element authorfn = (Element)node.getElementsByTagName("AuthorFN").item(0);
            		  String bAuthorfn = authorfn.getTextContent();
            		  if ((node.ELEMENT_NODE == node.getNodeType())&&(jTextField1.getText().equals(bAuthorfn))) {
            			  Element element = (Element) node;
            			  txt=txt+("ID: " + element.getElementsByTagName("ID").item(0).getTextContent())+"\n";
            			  txt=txt+("��� ������: " + element.getElementsByTagName("AuthorFN").item(0).getTextContent())+"\n";
            			  txt=txt+("������� ������: " + element.getElementsByTagName("AuthorLN").item(0).getTextContent())+"\n";
            			  txt=txt+("�������� �����: " + element.getElementsByTagName("Title").item(0).getTextContent())+"\n";
            			  txt=txt+("��� �������: " + element.getElementsByTagName("Date").item(0).getTextContent())+"\n";
            			  if(!(element.getElementsByTagName("Status").item(0).getTextContent().equals(""))) {
            				  txt=txt+("������: " + element.getElementsByTagName("Status").item(0).getTextContent())+"\n";}
            			  if(!(element.getElementsByTagName("On_hands").item(0).getTextContent().equals(""))) {
            				  txt=txt+("�� ����� � �������� � ID: " + element.getElementsByTagName("On_hands").item(0).getTextContent())+"\n";}
            			  txt=txt+"\n";
            			  }}
            	  for (int i = 0; i < nodeList.getLength(); i++) {
            		  // ���� ���������� ���������� ������� � ����� ������� �����, ���� ��� ����, ���������� ��� �����
            		  //� ����������  txt
            		  Element node = (Element)nodeList.item(i);
            		  Element date = (Element)node.getElementsByTagName("Date").item(0);
            		  String bDate = date.getTextContent();
            		  if ((node.ELEMENT_NODE == node.getNodeType())&&(jTextField1.getText().equals(bDate))) {
            			  Element element = (Element) node;
            			  txt=txt+("ID: " + element.getElementsByTagName("ID").item(0).getTextContent())+"\n";
            			  txt=txt+("��� ������: " + element.getElementsByTagName("AuthorFN").item(0).getTextContent())+"\n";
            			  txt=txt+("������� ������: " + element.getElementsByTagName("AuthorLN").item(0).getTextContent())+"\n";
            			  txt=txt+("�������� �����: " + element.getElementsByTagName("Title").item(0).getTextContent())+"\n";
            			  txt=txt+("��� �������: " + element.getElementsByTagName("Date").item(0).getTextContent())+"\n";
            			  if(!(element.getElementsByTagName("Status").item(0).getTextContent().equals(""))) {
            				  txt=txt+("������: " + element.getElementsByTagName("Status").item(0).getTextContent())+"\n";}
            			  if(!(element.getElementsByTagName("On_hands").item(0).getTextContent().equals(""))) {
            				  txt=txt+("�� ����� � �������� � ID: " + element.getElementsByTagName("On_hands").item(0).getTextContent())+"\n";}
            			  txt=txt+"\n";
            			  }}
            	  //����� � ������ �����, ��� ����������� � txt, ���� ��� ������, �� � ������ �������� ��������� ���� 
            	  jTextArea1.setText(txt);
            	  if(txt=="") {
            		  jTextArea1.setText("�� ������ ������� ������ �� �������.");
            		  }
            	  }catch (ParserConfigurationException | SAXException | IOException  ex) {
            		  ex.printStackTrace(System.out);
            		  }
              }
    }

      public  void New(){
        try {
    	    
    		final String filepath = System.getProperty("user.dir")+ File.separator + "Library.xml";
    				final File xmlFile = new File(filepath);
    				DocumentBuilder db = DocumentBuilderFactory.newInstance()
    				.newDocumentBuilder();
    				Document doc = db.parse(xmlFile);
    				doc.normalize();
    				String txt="";

    						// �������� ��� ���� � ������ "Book"
    						NodeList nodeList = doc.getElementsByTagName("Book");

    						for (int i = 0; i < nodeList.getLength(); i++) {
    						// ������� ���������� �� ������� �� ��������� ���������
    						 Node node = nodeList.item(i);
    						if (Node.ELEMENT_NODE == node.getNodeType()) {
    						Element element = (Element) node;
    						txt=txt+("ID: " + element.getElementsByTagName("ID").item(0).getTextContent())+"\n";
    						txt=txt+("��� ������: " + element.getElementsByTagName("AuthorFN").item(0).getTextContent())+"\n";
    						txt=txt+("������� ������: " + element.getElementsByTagName("AuthorLN").item(0).getTextContent())+"\n";
    						txt=txt+("�������� �����: " + element.getElementsByTagName("Title").item(0).getTextContent())+"\n";
    						txt=txt+("��� �������: " + element.getElementsByTagName("Date").item(0).getTextContent())+"\n";
    						if(!(element.getElementsByTagName("Status").item(0).getTextContent().equals(""))) {
        						txt=txt+("������: " + element.getElementsByTagName("Status").item(0).getTextContent())+"\n";}
        						if(!(element.getElementsByTagName("On_hands").item(0).getTextContent().equals(""))) {
        						txt=txt+("�� ����� � �������� � ID: " + element.getElementsByTagName("On_hands").item(0).getTextContent())+"\n";}
        						txt=txt+"\n";
    			 }}
    						jTextArea1.setText(txt);

    	                  } catch (ParserConfigurationException | SAXException | IOException  ex) {
    	                      ex.printStackTrace(System.out);
    	}
      }}