import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //Declaring data members of text editor
    JFrame frame;// Creating a frame for the text editor

    //Text area
    JTextArea textArea;

    JMenuBar menubar;

    JMenu file, edit;

    //File menu Items
    JMenuItem newFile, openFile, saveFile;

    // Edit menu Items
    JMenuItem cut, copy, paste, selectAll, close;



    TextEditor(){
        // Initialising a frame
        frame = new JFrame();

        //Initialising a menu bar
        menubar = new JMenuBar();

        //Initialising a text area
        textArea = new JTextArea();

        //Initilise menu
        file = new JMenu("File");
        edit = new JMenu("Edit");


        // Initilise filemenu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //Add action listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //Add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initilise edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //Add action listener to edit menu item
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // Add menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Adding menu to the menubar
        menubar.add(file);
        menubar.add(edit);


        // Setting a menu bar to the frame
        frame.setJMenuBar(menubar);

        //Create Content
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));

        // Adding text area to the panel
        panel.add(textArea, BorderLayout.CENTER);

        //Creating a scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Add scroll pane to the panel
        panel.add(scrollPane);

        // Add panel to frame
        frame.add(panel);

        // Setting dimensions of a frame
        frame.setBounds(500, 30, 400, 400);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setTitle("Text Editor");
//        frame.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()== cut)
        {
            // Perform cut operation
            textArea.cut();
        }
        if(e.getSource()== copy)
        {
            //Perform copy operataion
            textArea.copy();
        }
        if(e.getSource() == paste)
        {
            //Perform paste operation
            textArea.paste();
        }
        if(e.getSource() == selectAll)
        {
            //Perform selectall operation
            textArea.selectAll();
        }
        if(e.getSource() == close)
        {
            // Perform close editor operation
            System.exit(0);
        }
        if ((e.getSource() == openFile))
        {
            // Open a file chooser
            JFileChooser filechooser = new JFileChooser("C:");
            int chooseOption = filechooser.showOpenDialog(null);

            //If we have clicked on open button
            if(chooseOption == JFileChooser.APPROVE_OPTION)
            {
                // Getting the selected file
                File file = filechooser.getSelectedFile();

                //Getting the path of the selected file
                String filePath = file.getPath();
                frame.setTitle(file.getName());
                try
                {
                    // Initialise file reader
                    FileReader fileReader = new FileReader(filePath);

                    //Initialise buffer reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";

                    // Reading contents of file line by line
                    while((intermediate= bufferedReader.readLine())!=null)
                    {
                        output += intermediate + "\n";
                    }

                    // Setting the output string to the text area
                    textArea.setText(output);
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }

            }
        }
        if(e.getSource() == saveFile)
        {
            //Initialise a file picker
            JFileChooser fileChooser = new JFileChooser("C:");

            //Get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);

            // Check if we clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION)
            {
                // Create a new file with chosen directory path adn file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try
                {
                    //Initialise file writer
                    FileWriter fileWriter = new FileWriter(file);

                    // Initialise buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    // Write contents of the text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        }
        if(e.getSource()== newFile)
        {
            TextEditor newTextEditor = new TextEditor();
        }

    }
    public static void main(String[] args) {
        TextEditor text = new TextEditor();
    }


}