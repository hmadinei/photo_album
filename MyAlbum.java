// package APProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.*;
import java.io.*;

public class MyAlbum implements java.io.Serializable{
	public static void main(String args[]) throws EOFException {
	Task task = new Task();
	JFrame enterFrame = new JFrame();
	enterFrame.setBounds(100, 100, 400, 400);
	JPanel enterPanel = new JPanel();
	enterPanel.setLayout(null);
	ImageIcon welcom = new ImageIcon("wallpaper.jpg");
	welcom = new ImageIcon(welcom.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
	JLabel enterLabel = new JLabel(welcom);
	enterLabel.setBounds(0, 0, 400, 400);
	enterPanel.add(enterLabel);
	task.deserializeImage();
	JButton enterButton = new JButton("ALBUM");
	enterButton.setFont(new Font("Serif", Font.ITALIC, 15));
	enterButton.setBounds(123, 155, 153, 90);
	enterButton.setBackground(Color.WHITE);
	enterButton.setFocusPainted(false);
	enterPanel.add(enterButton);
	enterButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			enterFrame.dispose();
			JFrame mainFrame = new JFrame("ALBUM");
			mainFrame.setBounds(100, 100, 450, 300);
			JPanel mainPanel = new JPanel();
			JButton addImage,SlideShow,show,exit;
			mainPanel.setLayout(null);
			addImage = new JButton("Add Image");	
			addImage.setBounds(25, 67, 128, 172);
			mainPanel.add(addImage);
			addImage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					task.fileChooser();
				}
			});
			SlideShow = new JButton("Slide Show");
			SlideShow.setBounds(163, 67, 118, 172);
			mainPanel.add(SlideShow);
			SlideShow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					task.Slideshow();
				}
			});
			show = new JButton("Show");
			show.setBounds(291, 67, 118, 172);
			mainPanel.add(show);
			show.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					task.show(); 
				}
			});
			exit = new JButton("Exit");
			exit.setBounds(25, 0, 70, 20);
			mainPanel.add(exit);
			exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					task.serializeImage();
					mainFrame.dispose();
				}
			});
			
			mainFrame.add(mainPanel);
			mainFrame.setVisible(true);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	enterFrame.add(enterPanel);
	enterFrame.setVisible(true);
	enterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}