import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTable;

public class Cashier {

	private JFrame frame;
	private JTextField textFieldPhone;
	private JTextField textFieldProductCode;
	private JTextField textFieldMoney;
	double price=0;
	String Product="";
	String ImageFile =" ";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cashier window = new Cashier();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cashier() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle("Cashiers System");
		frame.setBounds(100, 100, 866, 828);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPhone = new JLabel("Phone Number");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setBounds(194, 17, 157, 44);
		frame.getContentPane().add(lblPhone);
		
		JLabel lblCustomerInfo = new JLabel("Customer Info");
		lblCustomerInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCustomerInfo.setBounds(15, 16, 157, 44);
		frame.getContentPane().add(lblCustomerInfo);
		
		JLabel lblOutputPhone = new JLabel("");
		lblOutputPhone.setForeground(Color.DARK_GRAY);
		lblOutputPhone.setBackground(Color.BLACK);
		lblOutputPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutputPhone.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOutputPhone.setBounds(360, 68, 469, 110);
		frame.getContentPane().add(lblOutputPhone);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setBackground(SystemColor.window);
		textFieldPhone.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldPhone.setBounds(351, 17, 271, 39);
		frame.getContentPane().add(textFieldPhone);
		textFieldPhone.setColumns(10);
		
		JLabel lblPhone0 = new JLabel("Customer Info :");
		lblPhone0.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone0.setForeground(Color.GRAY);
		lblPhone0.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPhone0.setBounds(204, 68, 147, 46);
		frame.getContentPane().add(lblPhone0);
		
		JLabel lblImage = new JLabel("Image");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBounds(25, 59, 164, 158);
		frame.getContentPane().add(lblImage);
		
		JButton btnPhone = new JButton("FIND");
		btnPhone.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldPhone.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Please input customer phone number", "warning",JOptionPane.ERROR_MESSAGE);
				}else{	
					  try {
						  String cust_no="";
							String cust_name="";
							
							File fXmlFile = new File("CustomerInfo.xml");
							DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
							DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
							Document doc = dBuilder.parse(fXmlFile);
							NodeList nList = doc.getElementsByTagName("customer");
							doc.getDocumentElement().normalize();
	                                                int count=0;
							for (int temp = 0; temp < nList.getLength(); temp++) {

								Node nNode = nList.item(temp);

								System.out.println("\nCurrent Element :" + nNode.getNodeName());
	                                                              
								if (nNode.getNodeType() == Node.ELEMENT_NODE) {
									Element eElement = (Element) nNode;
									if(textFieldPhone.getText().equals(eElement.getAttribute("id"))){
	                                                                        count++;
										lblOutputPhone.setText(eElement.getAttribute("id")+" : "+ eElement.getElementsByTagName("firstname").item(0).getTextContent()+" "
												+ ""+ eElement.getElementsByTagName("lastname").item(0).getTextContent());
										ImageFile=eElement.getElementsByTagName("img").item(0).getTextContent();
										  ImageIcon imageIcon = new ImageIcon(new ImageIcon(ImageFile).getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), lblImage.CENTER));
									        lblImage.setIcon(imageIcon);
									}																
								}
							}	
	                                                if(count==0){
	                                                    JOptionPane.showMessageDialog(null, "Not found this Customer");
	                                                }
							
						  } catch (Exception ex) {
							ex.printStackTrace();
						    }				
				}
			}
		});
		btnPhone.setBounds(637, 18, 127, 36);
		frame.getContentPane().add(btnPhone);
		
		JLabel lblProductCalc = new JLabel("Product Calc");
		lblProductCalc.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductCalc.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblProductCalc.setBounds(15, 215, 147, 44);
		frame.getContentPane().add(lblProductCalc);
		
		JLabel lblProductCode = new JLabel("Product Code");
		lblProductCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductCode.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblProductCode.setBounds(194, 215, 157, 44);
		frame.getContentPane().add(lblProductCode);
		
		textFieldProductCode = new JTextField();
		textFieldProductCode.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldProductCode.setColumns(10);
		textFieldProductCode.setBackground(Color.WHITE);
		textFieldProductCode.setBounds(351, 219, 271, 39);
		frame.getContentPane().add(textFieldProductCode);
		
		JLabel lblProductsInfo0 = new JLabel("Products Info :");
		lblProductsInfo0.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductsInfo0.setForeground(Color.GRAY);
		lblProductsInfo0.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblProductsInfo0.setBounds(204, 275, 147, 46);
		frame.getContentPane().add(lblProductsInfo0);
		
		JLabel lblOutputProductInfo = new JLabel("");
		lblOutputProductInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutputProductInfo.setForeground(Color.DARK_GRAY);
		lblOutputProductInfo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOutputProductInfo.setBackground(Color.BLACK);
		lblOutputProductInfo.setBounds(351, 275, 469, 110);
		frame.getContentPane().add(lblOutputProductInfo);
		
		JLabel lblSumProduct = new JLabel("");
		lblSumProduct.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSumProduct.setBounds(351, 401, 133, 40);
		frame.getContentPane().add(lblSumProduct);
		
		JButton btnProductCode = new JButton("SUBMIT");
		btnProductCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldProductCode.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Please input product code can't empty","Warning",JOptionPane.ERROR_MESSAGE);
				}else{												
					try {
							File fXmlFile = new File("ProductData.xml");
							DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
							DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
							Document doc = dBuilder.parse(fXmlFile);
							NodeList nList = doc.getElementsByTagName("product");
							doc.getDocumentElement().normalize();
	                                                int count=0;
							for (int temp = 0; temp < nList.getLength(); temp++) {

								Node nNode = nList.item(temp);

								System.out.println("\nCurrent Element :" + nNode.getNodeName());

								if (nNode.getNodeType() == Node.ELEMENT_NODE) {
									Element eElement = (Element) nNode;
									if(textFieldProductCode.getText().equals(eElement.getAttribute("id"))){								
	                                                                        count++;
										price+=Double.parseDouble(eElement.getElementsByTagName("price").item(0).getTextContent());
										System.out.println(price);
										Product+=eElement.getAttribute("id")+" "+eElement.getElementsByTagName("name").item(0).getTextContent()+" = "+eElement.getElementsByTagName("price").item(0).getTextContent()+"<br>";
										
									}
									
								}
							}
	                                              if(count==0){
	                                                  JOptionPane.showMessageDialog(null, "Not found this product","warning",JOptionPane.ERROR_MESSAGE);
	                                              }else{
	                                            	  lblOutputProductInfo.setText("<html>"+Product+"</html>");
	                                            	  lblSumProduct.setText("<html>"+price+"</html>");
	                                              }
							
						  } catch (Exception ex) {
							ex.printStackTrace();
						    }				
				}	
				
			}
		});
		btnProductCode.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnProductCode.setBounds(637, 220, 127, 36);
		frame.getContentPane().add(btnProductCode);
		
		JLabel lblCustomerMoney = new JLabel("Customer Money");
		lblCustomerMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerMoney.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCustomerMoney.setBounds(194, 467, 157, 44);
		frame.getContentPane().add(lblCustomerMoney);
		
		textFieldMoney = new JTextField();
		textFieldMoney.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldMoney.setColumns(10);
		textFieldMoney.setBackground(Color.WHITE);
		textFieldMoney.setBounds(351, 471, 271, 39);
		frame.getContentPane().add(textFieldMoney);
		
		JLabel lblChange = new JLabel("");
		lblChange.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblChange.setBounds(351, 538, 157, 40);
		frame.getContentPane().add(lblChange);
		
		JButton btnMoney = new JButton("CALCULATE");
		btnMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldMoney.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please input the money", "warning",JOptionPane.ERROR_MESSAGE);
                }   else if(Double.parseDouble(textFieldMoney.getText())<price){
                    JOptionPane.showMessageDialog(null, "The money is not avaliable to buy","warning",JOptionPane.WARNING_MESSAGE);
                }else{
                	lblChange.setText((Double.parseDouble(textFieldMoney.getText()))-price+" ");
                }
			}
		});
		btnMoney.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnMoney.setBounds(637, 472, 147, 36);
		frame.getContentPane().add(btnMoney);
		
		JButton btnEnd = new JButton("END");
		btnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				price=0;               
				textFieldPhone.setText("");
                lblOutputPhone.setText("");
                ImageFile="";
                lblImage.setIcon(null);
                textFieldProductCode.setText("");
                lblOutputProductInfo.setText("");
                lblSumProduct.setText("");
                textFieldMoney.setText("");
                lblChange.setText("");
                Product="";
			}
		});
		btnEnd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEnd.setBounds(340, 674, 127, 36);
		frame.getContentPane().add(btnEnd);
		
		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTotal.setBounds(280, 401, 56, 40);
		frame.getContentPane().add(lblTotal);
		
		JLabel label = new JLabel("CHANGE :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(262, 538, 89, 40);
		frame.getContentPane().add(label);
	}
}
