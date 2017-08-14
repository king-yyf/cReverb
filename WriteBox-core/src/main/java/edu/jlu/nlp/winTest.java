package edu.jlu.nlp;

import java.io.*;
import java.util.List;
import java.util.*;
import edu.stanford.nlp.hcoref.data.CorefChain;
import edu.stanford.nlp.hcoref.CorefCoreAnnotations;
import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.*;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class winTest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					winTest frame = new winTest();
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
	public winTest() {
		setTitle("\u81EA\u7136\u8BED\u8A00\u6837\u672C\u6807\u6CE8\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(69, 28, -45, 34);
		contentPane.add(panel);
		
		JTextArea textArea = new JTextArea();
		textArea.setText("\u7F8E\u56FD\u603B\u7EDF\u5965\u5DF4\u9A6C\u5728\u7EBD\u7EA6\u767D\u5BAB\u8BF4\u5FAE\u8F6F\u5360\u5E02\u573A\u4EFD\u989D\r\n\u768420%");
		textArea.setBounds(34, 36, 271, 43);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("\u5206\u8BCD\u7ED3\u679C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(34, 146, 80, 34);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("\u8BED\u6CD5\u89E3\u6790\u6811");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		button.setBounds(156, 146, 93, 34);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u8BED\u53E5\u5206\u5757");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(287, 146, 93, 34);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u5206\u8BCD\u5173\u7CFB\u62BD\u53D6");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_2.setBounds(34, 212, 118, 34);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("\u5173\u7CFB\u4E09\u5143\u7EC4");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_3.setBounds(262, 212, 118, 34);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("\u786E\u5B9A");
		button_4.setBounds(315, 36, 65, 43);
		contentPane.add(button_4);
	}
	
	public String languageHander(String content) throws FileNotFoundException{
		String result=new String();
		PrintWriter out=new PrintWriter("C://nlp//out.txt");
		StanfordCoreNLP pipeline = new StanfordCoreNLP("CoreNLP-chinese.properties");
		Annotation annotation;
	    annotation = new Annotation(content);
	    pipeline.annotate(annotation);
	    pipeline.prettyPrint(annotation, out);
	   // List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		return result;
	}
}
