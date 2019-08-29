package kp2v2;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@SuppressWarnings("serial")
public class Reader extends javax.swing.JFrame {
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;

    public Reader() {
        initComponents();
    }
  //�����, � ������� ��������� �������� ���� ���������
	private void initComponents() {
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        
        //��� ������� �� ������� ���� ���������
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //��������� �������� ����
        setTitle("�������� � ID " + Authorization.y );

        jButton4.setText("������� ������������");
        //�������� ���������, ��� �� ��� ������� ������� �������� ����� � ����
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	dispose();
            	new Authorization().setVisible(true);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        //� ������ ���������� ������ ����
        New();
        
        jButton2.setText("������ ������");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
        	   //����� ������� ������
        	   New();
           }});
        jButton1.setText("�����");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
            	Search();
                	}});
        

        jButton3.setText("��� �����");
        jButton3.setToolTipText("��� �� ����� �����, ���������� � ������������.");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            @SuppressWarnings("static-access")
			public void actionPerformed(java.awt.event.ActionEvent evt) {
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
            							Element node = (Element)nodeList.item(i);
            							//������ ���� ����� �������� � ������ ����� � ���������� � ID ��������, ������� �������� ������ � ������ ������
            							Element on_hands = (Element)node.getElementsByTagName("On_hands").item(0);
            							String bon_hands = on_hands.getTextContent();
            							if ((node.ELEMENT_NODE == node.getNodeType())&&(Authorization.y.equals(bon_hands))) {
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
            							}}});
        jTextField1.setText("");
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
        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("�����:");
        

        //���������� �����������
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton4)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(56, 56, 56)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap())
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