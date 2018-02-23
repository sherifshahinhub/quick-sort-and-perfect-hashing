package perfectHashing.View;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import perfectHashing.Controller.HashingOn;
import perfectHashing.Controller.HashingOn2;

import javax.swing.JLabel;

public class GUI {
	public static File file;
	public HashingOn2 h;
	public HashingOn hash;
	public JLabel fail;
	public JLabel spacecount;
	public JLabel timeCount;
	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnChooseKeysFile = new JButton("choose keys file");
		btnChooseKeysFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser f = new JFileChooser();
				f.showOpenDialog(null);
				file = f.getSelectedFile();
				System.out.println(file);
			}
		});
		btnChooseKeysFile.setBounds(241, 47, 145, 23);
		frame.getContentPane().add(btnChooseKeysFile);

		JButton btnRun = new JButton("O(N^2)");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double t1=System.currentTimeMillis();
				String arr[] = null;
				try {
					h = new HashingOn2();
					 arr = h.read(file);
					int end = arr.length;
					Set<String> set = new HashSet<String>();

					for (int i = 0; i < end; i++) {
						set.add(arr[i]);
					}
					Iterator it = set.iterator();
					String newArr[] = new String[set.size()];
					int counter = 0;
					while (it.hasNext()) {
						newArr[counter] = (String) it.next();
						counter++;
					}
					h.hash(newArr);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				double t2=System.currentTimeMillis();
				double t =t2-t1;
				fail.setText(h.FailCount+"");
				spacecount.setText(arr.length*arr.length+"");
				timeCount.setText(t+"");
				
			}
		});
		btnRun.setBounds(270, 91, 89, 23);
		frame.getContentPane().add(btnRun);

		JButton btnOn = new JButton("O(N)");
		btnOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double t1=System.currentTimeMillis();
				hash = new HashingOn();
				String arr[] = null;
				try {
					arr = hash.read(file);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int end = arr.length;
				Set<String> set = new HashSet<String>();

				for (int i = 0; i < end; i++) {
					set.add(arr[i]);
				}
				Iterator it = set.iterator();
				String newArr[] = new String[set.size()];
				int counter = 0;
				while (it.hasNext()) {
					newArr[counter] = (String) it.next();
					counter++;
				}
				try {
					hash.hashPhaseOne(newArr);
					hash.hashPhaseTwo(hash.hashTable);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				double t2=System.currentTimeMillis();
				double t =t1-t2;
				fail.setText(hash.fails+"");
				spacecount.setText(hash.counter+"");
				timeCount.setText(t+"");
			}
		});
		btnOn.setBounds(270, 129, 89, 23);
		frame.getContentPane().add(btnOn);
		
		textField = new JTextField();
		textField.setBounds(51, 48, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnFind = new JButton("Find O(N^2)");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean found =h.find(Integer.parseInt(textField.getText()));
					if(found){
						JOptionPane.showMessageDialog(null, "Found");
					}else{
						JOptionPane.showMessageDialog(null, "Not Found");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"Not Found e");
				}
			}
		});
		btnFind.setBounds(51, 91, 112, 23);
		frame.getContentPane().add(btnFind);
		
		JButton btnFindOn = new JButton("Find O(N)");
		btnFindOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean found =hash.find(Integer.parseInt(textField.getText()));
					if(found){
						JOptionPane.showMessageDialog(null, "Found");
					}else{
						JOptionPane.showMessageDialog(null, "Not Found");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"Not found e");
				}
			}
		});
		btnFindOn.setBounds(61, 129, 89, 23);
		frame.getContentPane().add(btnFindOn);
		
		 fail = new JLabel("");
		fail.setBounds(232, 191, 97, 14);
		frame.getContentPane().add(fail);
		
		JLabel lblFailcount = new JLabel("FailCount");
		lblFailcount.setBounds(51, 175, 149, 30);
		frame.getContentPane().add(lblFailcount);
		
		JLabel lblSpace = new JLabel("space");
		lblSpace.setBounds(44, 203, 78, 14);
		frame.getContentPane().add(lblSpace);
		
		JLabel lblTime = new JLabel("time");
		lblTime.setBounds(41, 228, 46, 14);
		frame.getContentPane().add(lblTime);
		
		 spacecount = new JLabel(" ");
		spacecount.setBounds(232, 203, 97, 14);
		frame.getContentPane().add(spacecount);
		
		 timeCount = new JLabel("");
		timeCount.setBounds(242, 216, 97, 26);
		frame.getContentPane().add(timeCount);
	}
}
