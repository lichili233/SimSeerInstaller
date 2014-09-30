/*********************************************
 * SimSeerX Installer
 * Can be packed into an executable JAR
 * Downloads Solr, Play and unpack them
 * NOTE: Currently using fixed mirrors for Play and Solr
*********************************************/

import java.awt.Label;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.net.*;
import java.io.*;
import java.util.regex.Pattern;

import java.nio.file.Path; //Requires JAVA 7
import java.nio.file.Paths; //Requires JAVA 7

public class InstallerExec extends JFrame {

    public InstallerExec() {       
        initUI();
    }

    private void initUI() {

        JPanel pane = (JPanel) getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);
        
        pane.setToolTipText("Content Pane");

        JButton btn = new JButton("Start Downloading Components"); //BUTTON for START DOWNLOAD
        btn.setToolTipText("Click to initiate download");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent eventD) {
				System.out.println("--DOWNLOAD INITIALIZED--");

				InputStream is = null;
				
				try {
					//Fixed mirrors for now
					String solrPath = "http://mirror.cogentco.com/pub/apache/lucene/solr/4.10.0/solr-4.10.0.zip";
					String playPath = "http://downloads.typesafe.com/typesafe-activator/1.2.10/typesafe-activator-1.2.10.zip";
					Path currentRelativePath = Paths.get("");
					String saveDir = currentRelativePath.toAbsolutePath().toString();
					System.out.println("Current working path is: " + saveDir);
					
					HttpDownloadUtility.downloadFile(solrPath, saveDir);
					System.out.println("Solr 4.10.0 download complete!");
					HttpDownloadUtility.downloadFile(playPath, saveDir);
					System.out.println("Play Framework 1.2.10 download complete!");

				} catch (IOException ioe) {
					ioe.printStackTrace();
				} finally {
			        try {
			            if (is != null) is.close();
			        } catch (IOException ioe) {
			            // nothing to see here
			        }
				}				
            }
        });
		
        JButton installButton = new JButton("Install Components"); //BUTTON for START INSTALL
        installButton.setToolTipText("Click this ONLY after you finished the downloads!!!");
        installButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent eventI) {				
				System.out.println("--INSTALLATION INITIALIZED--");
				
				InputStream is = null;
				
				try {
					//Retrieve current working directory, to be output folder				
					Path currentRelativePath = Paths.get("");
					String workingDir = currentRelativePath.toAbsolutePath().toString();
					
					//Form installation path (Currently hard coded, does NOT work with Windows)
					String solrInstalledPath = workingDir + "/solr-4.10.0.zip.zip";
					System.out.println("Current Solr zipped file path is: " + solrInstalledPath);
					
					//Form installation path (Currently hard coded, does NOT work with Windows)
					String playInstalledPath = workingDir + "/typesafe-activator-1.2.10.zip";
					System.out.println("Current Play zipped file path is: " + playInstalledPath);
					
					//Test external Unzip utility
					UnzipUtility.unzip(solrInstalledPath, workingDir);
					System.out.println("Solr successfully unpacked");
					UnzipUtility.unzip(playInstalledPath, workingDir);
					//KNOWN ISSUE: Unix executable file may be identified as a general document (Needs verification)
					System.out.println("Play Framework (offline version) successfully unpacked");
					
					//TO DO: ASSIGN PARAMETERS, ENVIRONMENT VARIABLES, CONFIGURATIONS, GIT SIMSEER FILES, DYNAMIC VERSION DOWNLOAD (WIP)
					//		 PACKAGE MANAGEMENT, etc.
					//SOON TO COME: JFrame outputs instead of Console/Terminal outputs
					
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} finally {
			        try {
			            if (is != null) is.close();
			        } catch (IOException ioe) {
			            // nothing to see here
			        }
				}				
            }
        });
		
		JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
		
		JButton creditButton = new JButton("Credits");
		creditButton.setToolTipText("SimSeerX: Kyle Williams, Installer UI: Lichi Li");

        gl.setAutoCreateContainerGaps(false);
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(btn)
				.addComponent(installButton)
	            .addComponent(quitButton)
		        .addComponent(creditButton)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(btn)
				.addComponent(installButton)
		        .addComponent(quitButton)
			    .addComponent(creditButton)
			    //.addGap(120)
        );
        
        pack();

        setTitle("SimSeerX Installation");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	
    public static void main(String[] args) {
		
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                InstallerExec ex = new InstallerExec();
                ex.setVisible(true);
            }
        });
    }
}