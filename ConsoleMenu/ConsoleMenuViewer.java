package ConsoleMenu;

import javax.swing.*;

public class ConsoleMenuViewer
{
    public static void main(String[] args)
    {
        JFrame frame = new ConsoleMenuFrame();

        frame.setTitle("ConsoleMenu");
        frame.setLocation(450,200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
