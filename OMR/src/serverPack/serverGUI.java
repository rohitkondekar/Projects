package serverPack;
import java.awt.Color;
import java.io.File;
import javax.swing.*;


public class serverGUI extends javax.swing.JFrame
{
    private PCServer pcServer;
    private Thread t;
    private String folder;
    private String filename;
    private String testid;
    private String uuid = "990";
    
    public serverGUI()
    {
        initComponents();
        pcServer = new PCServer(this);
        JFrame.setDefaultLookAndFeelDecorated(true);
        pcServer.printServerProperties();  
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jUTextField.setText(uuid);
        pcServer.changeUUID(uuid);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser = new javax.swing.JFileChooser();
        jServerPanel = new javax.swing.JPanel();
        jInputFilePanel = new javax.swing.JPanel();
        jFolderField = new javax.swing.JTextField();
        jBrowse = new javax.swing.JButton();
        jFolder = new javax.swing.JLabel();
        jTestId = new javax.swing.JLabel();
        jTestIdField = new javax.swing.JTextField();
        jFetchButton = new javax.swing.JButton();
        jstatusPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jStatusArea = new javax.swing.JTextArea();
        jSentPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jSentTextArea = new javax.swing.JTextArea();
        jServerButton = new javax.swing.JToggleButton();
        jServerDetailsPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jServerDetailsText = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jULable = new javax.swing.JLabel();
        jUTextField = new javax.swing.JTextField();
        jUChange = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jFileChooser.setDialogTitle("Select a File");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");
        setFont(new java.awt.Font("SansSerif", 0, 8));

        jServerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Server"));
        jServerPanel.setFont(new java.awt.Font("SansSerif", 0, 8));

        jInputFilePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Input File", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12))); // NOI18N

        jBrowse.setText("Browse");
        jBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBrowseActionPerformed(evt);
            }
        });

        jFolder.setText("Folder");

        jTestId.setText("Test ID");

        jFetchButton.setText("Fetch");
        jFetchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFetchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInputFilePanelLayout = new javax.swing.GroupLayout(jInputFilePanel);
        jInputFilePanel.setLayout(jInputFilePanelLayout);
        jInputFilePanelLayout.setHorizontalGroup(
            jInputFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInputFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInputFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInputFilePanelLayout.createSequentialGroup()
                        .addGroup(jInputFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTestId)
                            .addComponent(jFolder))
                        .addGap(18, 18, 18)
                        .addGroup(jInputFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFolderField, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                            .addComponent(jTestIdField, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jBrowse))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInputFilePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFetchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)))
                .addGap(27, 27, 27))
        );
        jInputFilePanelLayout.setVerticalGroup(
            jInputFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInputFilePanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jInputFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTestId)
                    .addComponent(jTestIdField))
                .addGap(18, 18, 18)
                .addGroup(jInputFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFolder)
                    .addComponent(jFolderField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBrowse))
                .addGap(33, 33, 33)
                .addComponent(jFetchButton)
                .addContainerGap())
        );

        jInputFilePanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBrowse, jFolder});

        jstatusPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Process Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12))); // NOI18N

        jStatusArea.setColumns(20);
        jStatusArea.setRows(5);
        jScrollPane1.setViewportView(jStatusArea);
        jStatusArea.setEditable(false);

        javax.swing.GroupLayout jstatusPanelLayout = new javax.swing.GroupLayout(jstatusPanel);
        jstatusPanel.setLayout(jstatusPanelLayout);
        jstatusPanelLayout.setHorizontalGroup(
            jstatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jstatusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addContainerGap())
        );
        jstatusPanelLayout.setVerticalGroup(
            jstatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jstatusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Files Sent to", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12))); // NOI18N

        jSentTextArea.setColumns(20);
        jSentTextArea.setRows(5);
        jScrollPane2.setViewportView(jSentTextArea);
        jSentTextArea.setEditable(false);

        javax.swing.GroupLayout jSentPanelLayout = new javax.swing.GroupLayout(jSentPanel);
        jSentPanel.setLayout(jSentPanelLayout);
        jSentPanelLayout.setHorizontalGroup(
            jSentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addContainerGap())
        );
        jSentPanelLayout.setVerticalGroup(
            jSentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        jServerButton.setText("Start Server");
        jServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServerButtonActionPerformed(evt);
            }
        });

        jServerDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Server Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12))); // NOI18N

        jServerDetailsText.setColumns(20);
        jServerDetailsText.setRows(5);
        jScrollPane3.setViewportView(jServerDetailsText);
        jServerDetailsText.setEditable(false);

        javax.swing.GroupLayout jServerDetailsPanelLayout = new javax.swing.GroupLayout(jServerDetailsPanel);
        jServerDetailsPanel.setLayout(jServerDetailsPanelLayout);
        jServerDetailsPanelLayout.setHorizontalGroup(
            jServerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jServerDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addContainerGap())
        );
        jServerDetailsPanelLayout.setVerticalGroup(
            jServerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jServerDetailsPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("UUID Code"));

        jULable.setFont(new java.awt.Font("SansSerif", 0, 15));
        jULable.setText("UUID Code");

        jUChange.setText("Change");
        jUChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUChangeActionPerformed(evt);
            }
        });

        jLabel1.setText("3 Digit");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jULable)
                        .addGap(18, 18, 18)
                        .addComponent(jUTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jUChange, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 320, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jUTextField))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jULable)
                        .addComponent(jUChange)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jServerPanelLayout = new javax.swing.GroupLayout(jServerPanel);
        jServerPanel.setLayout(jServerPanelLayout);
        jServerPanelLayout.setHorizontalGroup(
            jServerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jServerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jServerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jstatusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jInputFilePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jServerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jServerPanelLayout.createSequentialGroup()
                        .addComponent(jServerDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jServerPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                        .addComponent(jServerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)))
                .addGroup(jServerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jServerPanelLayout.setVerticalGroup(
            jServerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jServerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jServerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jServerPanelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jServerPanelLayout.createSequentialGroup()
                        .addGroup(jServerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jInputFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jServerDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jServerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jstatusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jServerButton))))
                .addContainerGap())
        );

        jInputFilePanel.getAccessibleContext().setAccessibleName("Details");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jServerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jServerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBrowseActionPerformed
        
        int returnVal = jFileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = jFileChooser.getSelectedFile();
        jFolderField.setText(file.getPath()+"/");
        }

    }//GEN-LAST:event_jBrowseActionPerformed

    private void jServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jServerButtonActionPerformed
        
        folder = jFolderField.getText();
        testid = jTestIdField.getText();
        folder = folder+ testid + "/";
        filename = folder+testid+".txt";
        
        if(jServerButton.isSelected())
        {
            if(folder == null || testid == null || folder.equals("") || testid.equals(""))
            {
                writeStatusDetails("Error: Enter File Name and TestId\n");
                jServerButton.setSelected(false);
            }
            else if(!isInteger(testid))
            {
                writeStatusDetails("Error: Enter Valid Test Id!!\n");
                jServerButton.setSelected(false);
            }
            else
            {                
                if(!pcServer.checkFile(filename))
                {
                    writeStatusDetails("Error: Invalid File !!\n");
                    jServerButton.setSelected(false);
                }
                else
                {
                    jSentTextArea.setText(null);
                    jServerButton.setText("Stop Server");
                    t = new Thread()
                    {
                        public void run()
                        {
                            pcServer.startConnection(folder,testid);
                            jServerButton.setText("Start Server");
                            jServerButton.setSelected(false);                           
                        }
                    };
                    t.start();                    
                }
            }
        }
        else
        {
            writeStatusDetails("Server Stopped!\n");
            pcServer.stopServer();
            jServerButton.setText("Start Server");

        }
    }//GEN-LAST:event_jServerButtonActionPerformed

    private void jFetchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFetchButtonActionPerformed
        
        folder = jFolderField.getText();  
        testid = jTestIdField.getText();
        folder = folder + testid + "/";
        filename = folder+testid+".txt";
        
        if(folder.equals("") || folder == null || testid.equals("") || testid == null)
        {
            writeStatusDetails("Error: Enter File Name and TestId\n");
            return;
        }
        
        Details testDetails = new Details(folder,filename,testid,this);
        testDetails.writeDetails();        
    }//GEN-LAST:event_jFetchButtonActionPerformed

    private void jUChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUChangeActionPerformed

        String tempUUID = jUTextField.getText();
        if(tempUUID.length() != 3)
        {
            jUTextField.setText(tempUUID.substring(0,3));
            tempUUID = jUTextField.getText();
        }
        
        if(tempUUID == null || tempUUID.equals("") || !isInteger(tempUUID))
        {
            tempUUID = "990";
            jUTextField.setText(tempUUID);
        }        
        pcServer.changeUUID(tempUUID);
    }//GEN-LAST:event_jUChangeActionPerformed

    
    public void writeServerDetails(String s)
    {
        jServerDetailsText.append(s);
    }
    
    public void writeSentDetails(String s)
    {
        jSentTextArea.append(s);
    }
    public void writeStatusDetails(String s)
    {
        jStatusArea.append(s);
    }
    
    public boolean isInteger( String input )
    {
        try
        {
            Integer.parseInt(input);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    
    
    
    public static void main(String args[])
    {        
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new serverGUI().setVisible(true);                    
            }
        });
     }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBrowse;
    private javax.swing.JButton jFetchButton;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JLabel jFolder;
    private javax.swing.JTextField jFolderField;
    private javax.swing.JPanel jInputFilePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jSentPanel;
    private javax.swing.JTextArea jSentTextArea;
    private javax.swing.JToggleButton jServerButton;
    private javax.swing.JPanel jServerDetailsPanel;
    private javax.swing.JTextArea jServerDetailsText;
    private javax.swing.JPanel jServerPanel;
    private javax.swing.JTextArea jStatusArea;
    private javax.swing.JLabel jTestId;
    private javax.swing.JTextField jTestIdField;
    private javax.swing.JButton jUChange;
    private javax.swing.JLabel jULable;
    private javax.swing.JTextField jUTextField;
    private javax.swing.JPanel jstatusPanel;
    // End of variables declaration//GEN-END:variables
}
