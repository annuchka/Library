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
	public class DeleteBook extends javax.swing.JFrame {
			private javax.swing.JButton jButton1;
		    private javax.swing.JLabel jLabel1;
		    private javax.swing.JTextField jTextField1;
		    //Используется для клавиш в showConfirmDialog
		    Object[] options = {"Да","Нет"};
		    
		    public DeleteBook() {
		        initComponents();
		    }
        
		  //Метод, в котором прописаны действия всех элементов
		    private void initComponents() {
		    	
		        jTextField1 = new javax.swing.JTextField();
		        jLabel1 = new javax.swing.JLabel();
		        jButton1 = new javax.swing.JButton();

		        //Слушатель окна. Описывает то, что произойдет при нажатии на крестик
		        addWindowListener(new WindowAdapter() {
		        	public void windowClosing(WindowEvent e) {
		        		 dispose();
		        		 new Administrator().setVisible(true);
		        	}});	        
		        jTextField1.setText("ID");
		        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
		        	@Override
		             public void focusGained(FocusEvent ae){
		                 if(jTextField1.getText().equals("ID")){
		                	 jTextField1.setText("");
		                 }
		             }

		             @Override
		             public void focusLost(FocusEvent ae){
		                 if(jTextField1.getText().isEmpty()){
		                	 jTextField1.setText("ID");
		                 }
		             }});
		        
		        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); 
		        jLabel1.setText("Введите ID удаляемой книги:");

		        jButton1.setText("Удалить");
		        jButton1.addActionListener(new java.awt.event.ActionListener() {
		            public void actionPerformed(java.awt.event.ActionEvent evt) {
		            	try {
		            		final String filepath = System.getProperty("user.dir")+ File.separator + "Library.xml";
		            		final File xmlFile = new File(filepath);
		            		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		            		Document doc = db.parse(xmlFile);
		            		doc.normalize();	         
		                    String txt = "";
		                    //Проверка на наличие данных
		                    if(jTextField1.getText().equals("ID")) {
		                    	JOptionPane.showMessageDialog(null, "Отсутствует ID.\n Удаление невозможно.");
		 	                }
		                    if(!(jTextField1.getText().equals("ID"))) {
		                    	//Создаем список книг
		                    	NodeList nodeList = doc.getElementsByTagName("Book");
		                    	for (int i = 0; i < nodeList.getLength(); i++) {
		                    		Element node = (Element)nodeList.item(i);	
		                    		Element Book = (Element)nodeList.item(i);
		                    		Element id = (Element)Book.getElementsByTagName("ID").item(0);
		                    		String bId = id.getTextContent();
		                    		if(jTextField1.getText().equals(bId)) {
		                    			Element element = (Element) node;
		                    			txt=txt+("ID: " + element.getElementsByTagName("ID").item(0).getTextContent())+"\n";
		                    			txt=txt+("Имя автора: " + element.getElementsByTagName("AuthorFN").item(0).getTextContent())+"\n";
		                    			txt=txt+("Фамилия автора: " + element.getElementsByTagName("AuthorLN").item(0).getTextContent())+"\n";
		                    			txt=txt+("Название книги: " + element.getElementsByTagName("Title").item(0).getTextContent())+"\n";
		                    			txt=txt+("Год издания: " + element.getElementsByTagName("Date").item(0).getTextContent())+"\n";
		                    			if(!(element.getElementsByTagName("Status").item(0).getTextContent().equals(""))) {
		                    				txt=txt+("Статус: " + element.getElementsByTagName("Status").item(0).getTextContent())+"\n";}
		                    			if(!(element.getElementsByTagName("On_hands").item(0).getTextContent().equals(""))) {
		                    				txt=txt+("На руках у читателя №: " + element.getElementsByTagName("On_hands").item(0).getTextContent())+"\n";}
		                    			//Переменная r для отслеживания выбора в окне(да или нет)
		                    			int r = JOptionPane.showOptionDialog(null,"Вы уверены, что хотите"+"\n"+"безвозвратно удалить"+"\n"+"эту книгу?"+"\n"+txt,null,
		                    					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
		                    			if (r == JOptionPane.YES_OPTION) {
		                    				Book.getParentNode().removeChild(Book);
		                    				// Записываем изменения в XML файл
		                    				Transformer transformer = TransformerFactory.newInstance().newTransformer();
		                    				DOMSource source = new DOMSource(doc);
		                    				StreamResult result = new StreamResult(new File(filepath));
		                    				transformer.transform(source, result);
		                    				JOptionPane.showMessageDialog(null, "Книга успешно удалена.");
		                    				dispose();
		                    				new Administrator().setVisible(true);}
		                    			else if (r == JOptionPane.NO_OPTION) {}	
		                    			}
		                    		else {if(i == nodeList.getLength()-1){
		                    			JOptionPane.showMessageDialog(null, "Такой книги в базе не существует.\n "
		                    		+ "Проверьте правильность введенных данных.");
		                    			}}
		                    		}
		                    	}} catch (ParserConfigurationException | SAXException | IOException | TransformerException  ex) {
		                    		ex.printStackTrace(System.out);
		                    		}
		            	}});
		 	                
		          
			    //Размещение компонентов
		        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                .addContainerGap(29, Short.MAX_VALUE)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                        .addComponent(jLabel1)
		                        .addGap(24, 24, 24))
		                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
		                        .addGap(67, 67, 67))))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addComponent(jLabel1)
		                .addGap(25, 25, 25)
		                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGap(18, 18, 18)
		                .addComponent(jButton1)
		                .addContainerGap(24, Short.MAX_VALUE))
		        );

		        pack();
		        setLocationRelativeTo(null);
		    }}