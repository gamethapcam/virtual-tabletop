package com.brokenshotgun.virtualtabletop.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class ServerWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = -8160968626419226628L;
	
	private BoxLayout serverLayout;
	
	private JButton startButton;
	private JButton stopButton; 
	
	public ServerWindow() {
		super("Virtual Tabletop");
		WindowListener exitListener = new WindowListener() {
			@Override public void windowOpened(WindowEvent e) { }

			@Override
			public void windowClosing(WindowEvent e) {
				shutdownTomcat();
			}

			@Override public void windowClosed(WindowEvent e) { }
			@Override public void windowIconified(WindowEvent e) { }
			@Override public void windowDeiconified(WindowEvent e) { }
			@Override public void windowActivated(WindowEvent e) { }
			@Override public void windowDeactivated(WindowEvent e) { }
        };
        addWindowListener(exitListener);
        setMinimumSize(new Dimension(275, 75));
        setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = getContentPane();
		serverLayout = new BoxLayout(pane, BoxLayout.Y_AXIS);
		pane.setLayout(serverLayout);
		
		startButton = new JButton("Start server");
		stopButton = new JButton("Stop server");
		
		startButton.addActionListener(this);
		stopButton.addActionListener(this);
		
		startButton.setActionCommand("start");
		stopButton.setActionCommand("stop");
		
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		pane.add(startButton);
		pane.add(stopButton);
		
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("start")) {
			startServer();
		} else if (cmd.equals("stop")) {
			stopServer();
		}
	}
	
	private Tomcat tomcat;
	
	private void startServer() {
		startButton.setEnabled(false);
		try {
			String webappDirLocation = "webapp/";
	        tomcat = new Tomcat();

	        //The port that we should run on can be set into an environment variable
	        //Look for that variable and default to 8080 if it isn't there.
	        String webPort = System.getenv("PORT");
	        if(webPort == null || webPort.isEmpty()) {
	            webPort = "8080";
	        }

	        tomcat.setPort(Integer.valueOf(webPort));

	        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
	        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

	        tomcat.start();
	        //tomcat.getServer().await();
		} catch(Exception ex) {
			ex.printStackTrace();
			startButton.setEnabled(true);
		}
	}
	
	private void stopServer() {
		if(tomcat != null) {
			shutdownTomcat();
			startButton.setEnabled(true);
		} else {
			JOptionPane.showMessageDialog(this, "Server not running.");
		}
	}
	
	private void shutdownTomcat() {
		if(tomcat != null) {
			try {
				tomcat.stop();
				tomcat.destroy();
				tomcat = null;
			} catch (LifecycleException e) {
				e.printStackTrace();
			}
		}
	}
}
