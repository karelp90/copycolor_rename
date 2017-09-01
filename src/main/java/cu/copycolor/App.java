package cu.copycolor;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

/**
 * Created by admin on 23/08/2017.
 */
public class App {
    private JButton buttonOk;
    private JPanel panelMain;
    private JComboBox comboBox1;
    private JTextField textFiledDir;

    public App() {
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renameFolders(textFiledDir.getText());
                JOptionPane.showMessageDialog(null, "Folder rename succesfully");
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void renameFolders(String dir) {

        File[] directories = new File(dir).listFiles(File::isDirectory);
        System.out.println(Arrays.toString(directories) + " - Cantidad: " + directories.length);

        for (int i = 0; i < directories.length; i++) {
            File[] filesInDir = new File(directories[i].toString()).listFiles(File::isFile);
            int[] numeros = new int[filesInDir.length];

            for (int j = 0; j < filesInDir.length; j++) {
                System.out.println(filesInDir[j].toString());
                String[] temp = filesInDir[j].toString().split(" - ");
                System.out.println(Arrays.toString(temp));

                String[] temp2 = temp[0].split("\\\\");
                System.out.println(temp2[temp2.length - 1]);
                if (StringUtils.isNumeric(temp2[temp2.length - 1]))
                    numeros[j] = Integer.parseInt(temp2[temp2.length - 1]);
                else {

                    System.out.println("Caso especial: " + Arrays.toString(temp));

                    String lastPos = temp[temp.length - 1];
                    String[] temp3 = lastPos.split("\\.");
                    System.out.println(lastPos);
                    System.out.println("Lassssstt: " + Arrays.toString(temp3));
                    numeros[j] = Integer.parseInt(temp3[0]);
                }


            }
            System.out.println(Arrays.toString(numeros));

            int nextNumber = mayorNum(numeros) + 1;
            File oldFolderName = new File(directories[i].toString());
            File newFolderName = new File(directories[i].toString() + " - [" + String.format("%03d", nextNumber) + " - xxx]");
            boolean isFileRenamed = oldFolderName.renameTo(newFolderName);

            if (isFileRenamed)
                System.out.println("File has been renamedww");
            else
                System.out.println("Error renaming the file");
        }


    }

    public int mayorNum(int[] num) {
        int largetst = num[0];

        for (int i = 1; i < num.length; i++)
            if (num[i] > largetst)
                largetst = num[i];

        System.out.println("Largest Number is : " + largetst);
        return largetst;
    }
}
