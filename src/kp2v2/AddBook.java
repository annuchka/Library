package kp2v2;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
public class AddBook extends javax.swing.JFrame {
		private javax.swing.JButton jButton1;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JTextField jTextField1;
	    private javax.swing.JTextField jTextField2;
	    private javax.swing.JTextField jTextField3;
	    private javax.swing.JTextField jTextField4;
	    
	    public AddBook() {
	        initComponents();
	    }         
	  //�����, � ������� ��������� �������� ���� ���������
	    private void initComponents() {
	        jTextField1 = new javax.swing.JTextField();
	        jTextField2 = new javax.swing.JTextField();
	        jTextField3 = new javax.swing.JTextField();
	        jTextField4 = new javax.swing.JTextField();
	        jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jButton1 = new javax.swing.JButton();
	        

	        //C�������� ����. ��������� ��, ��� ���������� ��� ������� �� �������
	        addWindowListener(new WindowAdapter() {
	        	public void windowClosing(WindowEvent e) {
	        		dispose();
	        		new Administrator().setVisible(true);
	        		}});
	        
	        jTextField1.setText("��� ������");
	        //�������� ���������, ��� �� ��� ������� ������� �������� ����� � ����
	        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
	        	@Override
	             public void focusGained(FocusEvent ae){
	                 if(jTextField1.getText().equals("��� ������")){
	                	 jTextField1.setText("");
	                 }
	             }

	             @Override
	             public void focusLost(FocusEvent ae){
	                 if(jTextField1.getText().isEmpty()){
	                	 jTextField1.setText("��� ������");
	                 }
	             }});  
	        jTextField2.setText("������� ������");
	        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
	        	@Override
	             public void focusGained(FocusEvent ae){
	                 if(jTextField2.getText().equals("������� ������")){
	                	 jTextField2.setText("");
	                 }
	             }

	             @Override
	             public void focusLost(FocusEvent ae){
	                 if(jTextField2.getText().isEmpty()){
	                	 jTextField2.setText("������� ������");
	                 }
	             }});

	        jTextField3.setText("�������� �����");
	        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
	        	@Override
	             public void focusGained(FocusEvent ae){
	                 if(jTextField3.getText().equals("�������� �����")){
	                	 jTextField3.setText("");
	                 }
	             }

	             @Override
	             public void focusLost(FocusEvent ae){
	                 if(jTextField3.getText().isEmpty()){
	                	 jTextField3.setText("�������� �����");
	                 }
	             }});

	        jTextField4.setText("��� �������");
	        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
	        	@Override
	             public void focusGained(FocusEvent ae){
	                 if(jTextField4.getText().equals("��� �������")){
	                	 jTextField4.setText("");
	                 }
	             }

	             @Override
	             public void focusLost(FocusEvent ae){
	                 if(jTextField4.getText().isEmpty()){
	                	 jTextField4.setText("��� �������");
	                 }
	             }});

	        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
	        jLabel1.setText("������� ������ ����� �����:");
	        jLabel2.setText("ID ��������� �������������!");

	        jButton1.setText("��������");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            @SuppressWarnings("static-access")
				public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	try {                    
	            		final String filepath = System.getProperty("user.dir")+ File.separator + "Library.xml";
	            		final File xmlFile = new File(filepath);
	            		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	            		Document doc = db.parse(xmlFile);
	            		doc.normalize();
	            		// ���������, ����� �� ��� ���������
	            		if(jTextField1.getText().equals("��� ������")) {
	            			JOptionPane.showMessageDialog(null, "����������� ��� ������.\n ������ ����������.");	 	              
	 	                }
	 	                if(jTextField2.getText().equals("������� ������")) {
	 	                	JOptionPane.showMessageDialog(null, "����������� ������� ������.\n ������ ����������.");
	 	                }
	 	                if(jTextField3.getText().equals("�������� �����")) {
	 	                	JOptionPane.showMessageDialog(null, "����������� �������� �����.\n ������ ����������.");
	 	                }
	 	                if(jTextField4.getText().equals("��� �������")) {
	 	                	JOptionPane.showMessageDialog(null, "����������� ��� �������.\n ������ ����������.");
	 	                }
	 	                if(!(jTextField1.getText().equals("��� ������"))&&!(jTextField2.getText().equals("������� ������"))&&
	 	                		!(jTextField3.getText().equals("�������� �����"))&&!(jTextField4.getText().equals("��� �������"))) {
	 	                	//������� ������ ����
	 	                	NodeList nodeList = doc.getElementsByTagName("Book");
	 	                	//���������� ��� �������� ����������� ID
	 	                	final Random random = new Random();
	 	                	//�������
    						int i = 0;
    						//���������� ����, ������� ����� �������� ����������
    						int kol = 1000;
    						//��������� ��������� ������� �� 1 �� 1000(��������� 1, �� �������� ������������ �� 0
    						String a = String.valueOf(random.nextInt(kol) + 1);
    						while (i < nodeList.getLength()) {
    							Element node = (Element)nodeList.item(i);
    							Element id = (Element)node.getElementsByTagName("ID").item(0);
    							String bId = id.getTextContent();      			            
        						if (node.ELEMENT_NODE == node.getNodeType()) {
        							if (a.equals(bId)){
        								a="";
        								i=0;
        								a = String.valueOf(random.nextInt(kol) + 1);
        								}
        							else {i++;}
        							}}
    						//�������� �������� �������
    						Node root = doc.getDocumentElement();
    						// ������� ����� ����� �� ���������
    						// ���� ����� <Book>
    						Element book = doc.createElement("Book");
    						// <ID>
    						Element id = doc.createElement("ID");
    						id.setTextContent(a);
    						// <Title>
    						Element title = doc.createElement("Title");
    						// ������������� �������� ������ ������ ����
    						title.setTextContent(jTextField3.getText());
    						// <AuthorFN>
    						Element authorfn = doc.createElement("AuthorFN");
    						authorfn.setTextContent(jTextField1.getText());
    						// <AuthorSN>
    						Element authorln = doc.createElement("AuthorLN");
    						authorln.setTextContent(jTextField2.getText());
    						// <Date>
    						Element date = doc.createElement("Date");
    						date.setTextContent(jTextField4.getText());
    						// <Status>
    						Element status = doc.createElement("Status");
    						status.setTextContent("� �������");
    						// <On_hands>
    						Element on_hands = doc.createElement("On_hands");
    						on_hands.setTextContent("");  
    						// ��������� ���������� �������� ����� � ������� <Book>
    						book.appendChild(id);
    						book.appendChild(authorfn);
    						book.appendChild(authorln);
    						book.appendChild(title);
    						book.appendChild(date);
    						book.appendChild(status);
    						book.appendChild(on_hands);
    						// ��������� ����� � �������� �������
    						root.appendChild(book);
    						// ���������� ��������� � XML ����
    						Transformer transformer = TransformerFactory.newInstance().newTransformer();
    						DOMSource source = new DOMSource(doc);
    						StreamResult result = new StreamResult(new File(filepath));
    						transformer.transform(source, result);
    						JOptionPane.showMessageDialog(null, "����� ������� ���������.");
    						dispose();
    						new Administrator().setVisible(true);
    						}} catch (ParserConfigurationException | SAXException | IOException | TransformerException  ex) {
    							ex.printStackTrace(System.out);
    							}
	            	}});
	 	                
	          
		    //���������� �����������
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(111, 111, 111))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(69, 69, 69))))
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(41, 41, 41)
	                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(94, 94, 94)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                .addGap(0, 42, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	        );

	        pack();
	        setLocationRelativeTo(null);
	    }}

