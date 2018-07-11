package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class main extends JFrame {

	private JPanel contentPane;
	private JButton btnsave;
	private JButton btnLoad;
	private JButton btnclear;
    private JTextArea txtpane;
    private String txt;
    private String name,loadname;
    private JTextField txtfilename;
    private JTextField txtloadname;
    private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main() throws Exception{
		setTitle("Notepad java");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnsave = new JButton("Save as>");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
            txt=txtpane.getText();	
			try (FileOutputStream fos = new FileOutputStream(txtfilename.getText());DataOutputStream dos = new DataOutputStream(fos);){
			dos.writeUTF(txt);
			}catch(Exception e){
			System.out.println(e);
			}
			}
		});
		btnsave.setBounds(10, 227, 86, 23);
		contentPane.add(btnsave);
		
		btnLoad = new JButton("Load >");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try (FileInputStream fis = new FileInputStream(txtloadname.getText());DataInputStream dis = new DataInputStream(fis);){
            txt = dis.readUTF();
			txtpane.setText(txt);
			}catch(Exception e) {
			System.out.println(e);	
			}		
			}
		});
		btnLoad.setBounds(202, 227, 80, 23);
		contentPane.add(btnLoad);
		
		btnclear = new JButton("Clear");
		btnclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			txtpane.setText("");	
			}
		});
		btnclear.setBounds(382, 227, 72, 23);
		contentPane.add(btnclear);
		
		txtpane = new JTextArea();
		txtpane.setBounds(10, 11, 444, 205);
		contentPane.add(txtpane);
		
		txtfilename = new JTextField();
		txtfilename.setHorizontalAlignment(SwingConstants.CENTER);
		txtfilename.setBounds(106, 228, 86, 20);
		contentPane.add(txtfilename);
		txtfilename.setColumns(10);
		
		txtloadname = new JTextField();
		txtloadname.setHorizontalAlignment(SwingConstants.CENTER);
		txtloadname.setBounds(286, 228, 86, 20);
		contentPane.add(txtloadname);
		txtloadname.setColumns(10);
		
		scrollPane = new JScrollPane(txtpane,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 444, 205);
		contentPane.add(scrollPane);
	}
}
