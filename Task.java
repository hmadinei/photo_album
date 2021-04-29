// package APProject;

import java.util.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Task implements ActionListener,java.io.Serializable {
	
	JFrame frameShow = new JFrame("Your Album");
	JFrame frameSlideShow = new JFrame("Your Album");
	JButton next , pre;
	JPanel panelShow = new JPanel();
	JPanel panelSlideShow = new JPanel();
	JLabel labelShow = new JLabel();
	JLabel labelSlideShow = new JLabel();
	JLabel labelCaption = new JLabel();
	ArrayList<MyImages> images = new ArrayList<MyImages>();
	ArrayList<ImageIcon> imageIcons = new ArrayList<ImageIcon>();
	int i = 0,j = 0; 
	Timer tm;

	public void deserializeImage(){
		try {
			FileInputStream fileInput = new FileInputStream("D:\\hana\\java\\AP_Project\\album.txt");
			ObjectInputStream input = new ObjectInputStream(fileInput);
			images = (ArrayList<MyImages>)input.readObject();
			System.out.println(images.size());
			toImageIcon(images);
			input.close();
			fileInput.close();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	public void toImageIcon(ArrayList<MyImages> images) {
		for(MyImages mi : images) {
			ImageIcon m = new ImageIcon(mi.getPath());
			m = new ImageIcon(m.getImage().getScaledInstance(292, 210 , Image.SCALE_DEFAULT));
			imageIcons.add(m);
		}
	}
	public void fileChooser() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("FileChooser");
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if(fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
			String path = fc.getSelectedFile().getAbsolutePath();
			String album = 	JOptionPane.showInputDialog("Enter the Name of Album");
			String caption = JOptionPane.showInputDialog("Enter Your Caption");
			addImage(path,caption,album);
		}
		else if(fc.showSaveDialog(null)==JFileChooser.CANCEL_OPTION){
			JOptionPane.showInternalMessageDialog(null, "You Dont Choose!");
		}
		
	}
	public void addImage(String path,String caption,String album) {
		images.add(new MyImages(path,caption,album));
		ImageIcon m = new ImageIcon(path);
		m = new ImageIcon(m.getImage().getScaledInstance(292, 210 , Image.SCALE_DEFAULT));
		imageIcons.add(m);
	}
	public void show(){
		panelShow.setLayout(null);
		frameShow.setBounds(100, 100, 450, 300);
		labelShow.setBounds(69, 25, 292, 210);
		
		next = new JButton();
		ImageIcon nextIcon = new ImageIcon("next.png");
		nextIcon = new ImageIcon(nextIcon.getImage().getScaledInstance(49, 49, Image.SCALE_DEFAULT));
		next.setIcon(nextIcon);
		next.setBounds(375, 103, 49, 49);
		panelShow.add(next);
		
		pre = new JButton();
		ImageIcon preIcon = new ImageIcon("pre.png"); 
		preIcon = new ImageIcon(preIcon.getImage().getScaledInstance(49, 49, Image.SCALE_DEFAULT));
		pre.setIcon(preIcon);
		pre.setBounds(10, 99, 49, 49);
		panelShow.add(pre);
		
		next.addActionListener(this);
		pre.addActionListener(this);
		
		labelShow.setIcon(imageIcons.get(0));
		labelCaption.setBounds(69, 241, 292, 20);
		labelCaption.setText(images.get(0).getCaption());
		labelCaption.setHorizontalAlignment(JLabel.CENTER);
		
		panelShow.add(labelCaption);
		panelShow.add(labelShow);
		
		frameShow.add(panelShow);
		frameShow.setVisible(true);
		frameShow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==next) {
			if(i==0) {
				JOptionPane.showMessageDialog(null, "FIRT");
			}
			else {
				i--;
				labelShow.setIcon(imageIcons.get(i));
				panelShow.add(labelShow);
				labelCaption.setText(images.get(i).getCaption());
				labelCaption.setHorizontalAlignment(JLabel.CENTER);
				panelShow.add(labelCaption);
			}
		}
		if(e.getSource()==pre) {
			if(i==images.size()-1)
				JOptionPane.showMessageDialog(null, "LAST");
			else {
				i++;
				labelShow.setIcon(imageIcons.get(i));
				panelShow.add(labelShow);
				labelCaption.setText(images.get(i).getCaption());
				labelCaption.setHorizontalAlignment(JLabel.CENTER);
				panelShow.add(labelCaption);
			}
		}
	}
	public void Slideshow() {
		panelSlideShow.setLayout(null);
		frameSlideShow.setBounds(100, 100, 450, 300);
		labelSlideShow.setBounds(69, 25, 296, 214);
		setImageSize(images.size()-1);
		tm = new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(images.size()!=1) {
					setImageSize(j);
					j++;
				}
				if(j == images.size()-1) {
					tm.stop();
				}
			}
		});
		tm.start();
		frameSlideShow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameSlideShow.setVisible(true);
	}
	public void setImageSize(int j) {
		labelSlideShow.setIcon(imageIcons.get(j));
		panelSlideShow.add(labelSlideShow);
		frameSlideShow.add(panelSlideShow);
	}
	public void serializeImage(){
		FileOutputStream fileOut = null;
		ObjectOutputStream output = null;
		try {
			fileOut = new FileOutputStream("D:\\hana\\java\\AP_Project\\album.txt");
			output = new ObjectOutputStream(fileOut);
			output.writeObject(images);
			output.close();
			fileOut.close();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
