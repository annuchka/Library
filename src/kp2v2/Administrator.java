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
public class Administrator extends javax.swing.JFrame {
	private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel jLabel1;
    
    public Administrator() {
        initComponents();
    }

    //Mетод, в котором прописаны действия всех элементов
    private void initComponents() {
    	
    	jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Администратор");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        //Метод, который обновляет список в окошке(прописан в конце этого класса)
        New();

        jButton1.setText("Поиск");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
            	Search();
                	}});
        
        
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField1.addKeyListener(new KeyAdapter(){ 
            @Override
            //При нажатии кнопки Enter тоже будет произведен вход
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                	//Метод поиска книг
                	Search();
                }
            }
        });
        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel1.setText("Найти:");

        jButton2.setText("Добавить книгу");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	dispose();
            	new AddBook().setVisible(true);
            	
            }
        });

        jButton3.setText("Удалить книгу");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	dispose();
            	new DeleteBook().setVisible(true);
            }
        });

        jButton4.setText("Сменить пользователя");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	dispose();
            	new Authorization().setVisible(true); 
            }
        });

      //Размещение компонентов
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(34, 34, 34)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap()));

        pack();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("static-access")
    //Метод поиска книг
	private void Search() {
    	String txt="";
    	
    	if(jTextField1.getText().equals("")) {
            	JOptionPane.showMessageDialog(null, "Отсутствуют данные для поиска.");
          }
    	else{                	
              try { 
            	  final String filepath = System.getProperty("user.dir")+ File.separator + "Library.xml";
            	  final File xmlFile = new File(filepath);
            	  DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            	  Document doc = db.parse(xmlFile);
            	  doc.normalize();
            	  // Получаем все узлы с именем "Book"
            	  NodeList nodeList = doc.getElementsByTagName("Book");
            	  for (int i = 0; i < nodeList.getLength(); i++) {
          			Element node = (Element)nodeList.item(i);
          			Element id = (Element)node.getElementsByTagName("ID").item(0);
          			String bId = id.getTextContent();
          			if ((node.ELEMENT_NODE == node.getNodeType())&&(jTextField1.getText().equals(bId))) {
          				Element element = (Element) node;
          				txt=txt+("ID: " + element.getElementsByTagName("ID").item(0).getTextContent())+"\n";
          				txt=txt+("Имя автора: " + element.getElementsByTagName("AuthorFN").item(0).getTextContent())+"\n";
          				txt=txt+("Фамилия автора: " + element.getElementsByTagName("AuthorLN").item(0).getTextContent())+"\n";
          				txt=txt+("Название книги: " + element.getElementsByTagName("Title").item(0).getTextContent())+"\n";
          				txt=txt+("Год издания: " + element.getElementsByTagName("Date").item(0).getTextContent())+"\n";
          				if(!(element.getElementsByTagName("Status").item(0).getTextContent().equals(""))) {
          					txt=txt+("Статус: " + element.getElementsByTagName("Status").item(0).getTextContent())+"\n";}
          				if(!(element.getElementsByTagName("On_hands").item(0).getTextContent().equals(""))) {
          					txt=txt+("На руках у читателя с ID: " + element.getElementsByTagName("On_hands").item(0).getTextContent())+"\n";}
          				txt=txt+"\n";
          				}}
            	  for (int i = 0; i < nodeList.getLength(); i++) {
            		  Element node = (Element)nodeList.item(i);
            		  Element title = (Element)node.getElementsByTagName("Title").item(0);
            		  String bTitle = title.getTextContent();
            		  if ((node.ELEMENT_NODE == node.getNodeType())&&(jTextField1.getText().equals(bTitle))) {
            			  Element element = (Element) node;
            			  txt=txt+("ID: " + element.getElementsByTagName("ID").item(0).getTextContent())+"\n";
            			  txt=txt+("Имя автора: " + element.getElementsByTagName("AuthorFN").item(0).getTextContent())+"\n";
            			  txt=txt+("Фамилия автора: " + element.getElementsByTagName("AuthorLN").item(0).getTextContent())+"\n";
            			  txt=txt+("Название книги: " + element.getElementsByTagName("Title").item(0).getTextContent())+"\n";
            			  txt=txt+("Год издания: " + element.getElementsByTagName("Date").item(0).getTextContent())+"\n";
            			  if(!(element.getElementsByTagName("Status").item(0).getTextContent().equals(""))) {
            				  txt=txt+("Статус: " + element.getElementsByTagName("Status").item(0).getTextContent())+"\n";}
            			  if(!(element.getElementsByTagName("On_hands").item(0).getTextContent().equals(""))) {
            				  txt=txt+("На руках у читателя с ID: " + element.getElementsByTagName("On_hands").item(0).getTextContent())+"\n";}
            			  txt=txt+"\n";
          			 }}
            	  for (int i = 0; i < nodeList.getLength(); i++) {
            		  Element node = (Element)nodeList.item(i);
            		  Element authorln = (Element)node.getElementsByTagName("AuthorLN").item(0);
            		  String bAuthorln = authorln.getTextContent();
            		  if ((node.ELEMENT_NODE == node.getNodeType())&&(jTextField1.getText().equals(bAuthorln))) {
            			  Element element = (Element) node;
            			  txt=txt+("ID: " + element.getElementsByTagName("ID").item(0).getTextContent())+"\n";
            			  txt=txt+("Имя автора: " + element.getElementsByTagName("AuthorFN").item(0).getTextContent())+"\n";
            			  txt=txt+("Фамилия автора: " + element.getElementsByTagName("AuthorLN").item(0).getTextContent())+"\n";
            			  txt=txt+("Название книги: " + element.getElementsByTagName("Title").item(0).getTextContent())+"\n";
            			  txt=txt+("Год издания: " + element.getElementsByTagName("Date").item(0).getTextContent())+"\n";
            			  if(!(element.getElementsByTagName("Status").item(0).getTextContent().equals(""))) {
            				  txt=txt+("Статус: " + element.getElementsByTagName("Status").item(0).getTextContent())+"\n";}
            			  if(!(element.getElementsByTagName("On_hands").item(0).getTextContent().equals(""))) {
            				  txt=txt+("На руках у читателя с ID: " + element.getElementsByTagName("On_hands").item(0).getTextContent())+"\n";}
            			  txt=txt+"\n";
            			  }}
            	  for (int i = 0; i < nodeList.getLength(); i++) {
            		  Element node = (Element)nodeList.item(i);
            		  Element authorfn = (Element)node.getElementsByTagName("AuthorFN").item(0);
            		  String bAuthorfn = authorfn.getTextContent();
            		  if ((node.ELEMENT_NODE == node.getNodeType())&&(jTextField1.getText().equals(bAuthorfn))) {
            			  Element element = (Element) node;
            			  txt=txt+("ID: " + element.getElementsByTagName("ID").item(0).getTextContent())+"\n";
            			  txt=txt+("Имя автора: " + element.getElementsByTagName("AuthorFN").item(0).getTextContent())+"\n";
            			  txt=txt+("Фамилия автора: " + element.getElementsByTagName("AuthorLN").item(0).getTextContent())+"\n";
            			  txt=txt+("Название книги: " + element.getElementsByTagName("Title").item(0).getTextContent())+"\n";
            			  txt=txt+("Год издания: " + element.getElementsByTagName("Date").item(0).getTextContent())+"\n";
            			  if(!(element.getElementsByTagName("Status").item(0).getTextContent().equals(""))) {
            				  txt=txt+("Статус: " + element.getElementsByTagName("Status").item(0).getTextContent())+"\n";}
            			  if(!(element.getElementsByTagName("On_hands").item(0).getTextContent().equals(""))) {
            				  txt=txt+("На руках у читателя с ID: " + element.getElementsByTagName("On_hands").item(0).getTextContent())+"\n";}
            			  txt=txt+"\n";
            			  }}
            	  for (int i = 0; i < nodeList.getLength(); i++) {
            		  Element node = (Element)nodeList.item(i);
            		  Element date = (Element)node.getElementsByTagName("Date").item(0);
            		  String bDate = date.getTextContent();
            		  if ((node.ELEMENT_NODE == node.getNodeType())&&(jTextField1.getText().equals(bDate))) {
            			  Element element = (Element) node;
            			  txt=txt+("ID: " + element.getElementsByTagName("ID").item(0).getTextContent())+"\n";
            			  txt=txt+("Имя автора: " + element.getElementsByTagName("AuthorFN").item(0).getTextContent())+"\n";
            			  txt=txt+("Фамилия автора: " + element.getElementsByTagName("AuthorLN").item(0).getTextContent())+"\n";
            			  txt=txt+("Название книги: " + element.getElementsByTagName("Title").item(0).getTextContent())+"\n";
            			  txt=txt+("Год издания: " + element.getElementsByTagName("Date").item(0).getTextContent())+"\n";
            			  if(!(element.getElementsByTagName("Status").item(0).getTextContent().equals(""))) {
            				  txt=txt+("Статус: " + element.getElementsByTagName("Status").item(0).getTextContent())+"\n";}
            			  if(!(element.getElementsByTagName("On_hands").item(0).getTextContent().equals(""))) {
            				  txt=txt+("На руках у читателя с ID: " + element.getElementsByTagName("On_hands").item(0).getTextContent())+"\n";}
            			  txt=txt+"\n";
            			  }}
            	  jTextArea1.setText(txt);
            	  if(txt=="") {
            		  jTextArea1.setText("По вашему запросу ничего не найдено.");
            		  }
            	  }catch (ParserConfigurationException | SAXException | IOException  ex) {
            		  ex.printStackTrace(System.out);
            		  }
              }
    }

    //Метод обновления списка книг после его изменения
      public  void New(){
        try {
        	final String filepath = System.getProperty("user.dir")+ File.separator + "Library.xml";
        	final File xmlFile = new File(filepath);
        	DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        	Document doc = db.parse(xmlFile);
        	doc.normalize();
        	String txt="";  
        	// Получаем все узлы с именем "Book"
        	NodeList nodeList = doc.getElementsByTagName("Book");
        	for (int i = 0; i < nodeList.getLength(); i++) {
        		// Выводим информацию по каждому элементу
        		Node node = nodeList.item(i);
        		if (Node.ELEMENT_NODE == node.getNodeType()) {
        			Element element = (Element) node;
        			txt=txt+("ID: " + element.getElementsByTagName("ID").item(0).getTextContent())+"\n";
        			txt=txt+("Имя автора: " + element.getElementsByTagName("AuthorFN").item(0).getTextContent())+"\n";
        			txt=txt+("Фамилия автора: " + element.getElementsByTagName("AuthorLN").item(0).getTextContent())+"\n";
        			txt=txt+("Название книги: " + element.getElementsByTagName("Title").item(0).getTextContent())+"\n";
        			txt=txt+("Год издания: " + element.getElementsByTagName("Date").item(0).getTextContent())+"\n";
        			if(!(element.getElementsByTagName("Status").item(0).getTextContent().equals(""))) {
        				txt=txt+("Статус: " + element.getElementsByTagName("Status").item(0).getTextContent())+"\n";}
        			if(!(element.getElementsByTagName("On_hands").item(0).getTextContent().equals(""))) {
        				txt=txt+("На руках у читателя с ID: " + element.getElementsByTagName("On_hands").item(0).getTextContent())+"\n";}
        			txt=txt+"\n";
        			}}
        	jTextArea1.setText(txt); 
        	} catch (ParserConfigurationException | SAXException | IOException  ex) {
        		ex.printStackTrace(System.out);
        		}
        }
      }        





