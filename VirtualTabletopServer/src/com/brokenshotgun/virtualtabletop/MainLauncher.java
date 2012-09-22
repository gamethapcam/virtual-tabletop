package com.brokenshotgun.virtualtabletop;

import javax.swing.SwingUtilities;

import com.brokenshotgun.virtualtabletop.gui.ServerWindow;

public class MainLauncher {
	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                new ServerWindow().setVisible(true);
            }
        });
    }
}
