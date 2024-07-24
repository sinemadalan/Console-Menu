package ConsoleMenu;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleMenuFrame extends JFrame
{
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel menuPanel;

    public ConsoleMenuFrame()
    {
        setSize(600,450);

        createMainPanel();
        createButtonPanel();
        createMenuPanel();

        add(mainPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.WEST);
        add(menuPanel, BorderLayout.CENTER);
    }

    private void createMainPanel()
    {
        //mainPanel oluşturuldu, componentleri eklendi.
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JLabel headerLabel = new JLabel("CONSOLE MENU");
        JLabel requestLabel = new JLabel( "Please press your process's buttons: ");

        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        Font headerFont = new Font("Tahoma", Font.BOLD, 24);
        Font requestFont = new Font("Tahoma", Font.PLAIN, 18);
        headerLabel.setFont(headerFont);
        requestLabel.setFont(requestFont);

        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(requestLabel, BorderLayout.CENTER);
    }

    private void createButtonPanel()
    {
        //Butonları içeren buttonPanel oluşturuldu. Butonlara listener objesi eklendi.
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(9,1));

        JButton button1 = new JButton("1-");
        JButton button2 = new JButton("2-");
        JButton button3 = new JButton("3-");
        JButton button4 = new JButton("4-");
        JButton button5 = new JButton("5-");
        JButton button6 = new JButton("6-");
        JButton button7 = new JButton("7-");
        JButton button8 = new JButton("8-");
        JButton button9 = new JButton("9-");

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(button7);
        buttonPanel.add(button8);
        buttonPanel.add(button9);

        ActionListener listener1 = new FirstActionListener();
        button1.addActionListener(listener1);

        ActionListener listener2 = new SecondActionListener();
        button2.addActionListener(listener2);

        ActionListener listener3 = new ThirdActionListener();
        button3.addActionListener(listener3);

        ActionListener listener4 = new FourthActionListener();
        button4.addActionListener(listener4);

        ActionListener listener5 = new FifthActionListener();
        button5.addActionListener(listener5);

        ActionListener listener6 = new SixthActionListener();
        button6.addActionListener(listener6);

        ActionListener listener7 = new SeventhActionListener();
        button7.addActionListener(listener7);

        ActionListener listener8 = new EigthActionListener();
        button8.addActionListener(listener8);

        ActionListener listener9 = new NinthActionListener();
        button9.addActionListener(listener9);
    }

    private void createMenuPanel()
    {
        //menuPanel oluşturuldu. Labellar eklendi.
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(9,1));
        menuPanel.add(new JLabel("Finding the kth smallest element"));
        menuPanel.add(new JLabel("Finding the closest pair"));
        menuPanel.add(new JLabel("Finding repeating elements of array"));
        menuPanel.add(new JLabel("Finding the reference of words in text file"));
        menuPanel.add(new JLabel("Finding the smallest value in the array"));
        menuPanel.add(new JLabel("Finding the greatest common divisor"));
        menuPanel.add(new JLabel("Primality test"));
        menuPanel.add(new JLabel("Fast fibonacci calculation"));
        menuPanel.add(new JLabel("Exit"));
    }

    private void removeAllComponents()
    {
        //Panellerdeki elemanlar silindi, panellerdeki yenilemeler yapıldı.
        mainPanel.removeAll();
        buttonPanel.removeAll();
        menuPanel.removeAll();
        mainPanel.revalidate();
        buttonPanel.revalidate();
        menuPanel.revalidate();
        mainPanel.repaint();
        buttonPanel.repaint();
        menuPanel.repaint();
    }

    class FirstActionListener implements ActionListener
    {
        //1. işlem için inner class oluşturuldu ve actionPerformed metodu içerisinde gerekli işlemler yapıldı.
        @Override
        public void actionPerformed(ActionEvent e)
        {
            removeAllComponents();
            createFirstPanel();

            backMenu();
        }

        private void createFirstPanel()
        {
            //1. işlem için gerekli düzenlemeler yapıldı.
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JPanel arrayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel arrayLabel = new JLabel("Please enter your array (add commas between elements):");
            JTextField arrayField = new JTextField(20);
            arrayPanel.add(arrayLabel);
            arrayPanel.add(arrayField);

            JPanel numberPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel numberLabel = new JLabel("Please enter your k number:");
            JTextField numberField = new JTextField(5);
            numberPanel.add(numberLabel);
            numberPanel.add(numberField);

            JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton resultButton = new JButton("Find");
            JLabel resultLabel = new JLabel("Result: ");
            resultPanel.add(resultButton);
            resultPanel.add(resultLabel);

            panel.add(arrayPanel);
            panel.add(numberPanel);
            panel.add(resultPanel);

            //Sonuç butonuna basıldığında yapılacak işlemler düzenlendi.
            resultButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String arrayText = arrayField.getText();
                    String numberText = numberField.getText();

                    try
                    {
                        String[] arrayString = arrayText.split(",");
                        int[] array = new int[arrayString.length];
                        for (int i = 0; i < arrayString.length; i++)
                        {
                            array[i] = Integer.parseInt(arrayString[i].trim());
                        }
                        int k = Integer.parseInt(numberText);

                        // K'nıncı en küçük elemanı bulma
                        int kthSmallest = findKthSmallest(array, k);
                        resultLabel.setText("Result: " + kthSmallest);
                    }
                    catch (NumberFormatException ex)
                    {
                        resultLabel.setText("Invalid login!");
                    }
                    catch (IllegalArgumentException ex)
                    {
                        resultLabel.setText(ex.getMessage());
                    }
                }
            });

            mainPanel.add(panel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    private int findKthSmallest(int[] array, int k)
    {
        if (k <= 0 || k > array.length)
        {
            throw new IllegalArgumentException("K değeri 1 ile array uzunluğu arasında olmalıdır.");
        }
        Arrays.sort(array);
        return array[k - 1];
    }

    class SecondActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            removeAllComponents();
            createSecondPanel();

            backMenu();
        }

        private void createSecondPanel()
        {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JPanel arrayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel arrayLabel = new JLabel("Please enter your array (add commas between elements):");
            JTextField arrayField = new JTextField(20);
            arrayPanel.add(arrayLabel);
            arrayPanel.add(arrayField);

            JPanel numberPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel numberLabel = new JLabel("Enter the number you want to find the closest pair:");
            JTextField numberField = new JTextField(5);
            numberPanel.add(numberLabel);
            numberPanel.add(numberField);

            JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton resultButton = new JButton("Find");
            JLabel resultLabel = new JLabel("Result: ");
            resultPanel.add(resultButton);
            resultPanel.add(resultLabel);

            panel.add(arrayPanel);
            panel.add(numberPanel);
            panel.add(resultPanel);

            //Sonuç butonuna basıldığında yapılacak işlemler düzenlendi.
            resultButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String arrayText = arrayField.getText();
                    String numberText = numberField.getText();

                    try
                    {
                        String[] arrayString = arrayText.split(",");
                        int[] array = new int[arrayString.length];
                        for (int i = 0; i < arrayString.length; i++)
                        {
                            array[i] = Integer.parseInt(arrayString[i].trim());
                        }
                        int number = Integer.parseInt(numberText);

                        int closestPair = findClosestPair(array, number);
                        resultLabel.setText("Result: " + closestPair);
                    }
                    catch (NumberFormatException ex)
                    {
                        resultLabel.setText("Invalid login!");
                    }
                    catch (IllegalArgumentException ex)
                    {
                        resultLabel.setText(ex.getMessage());
                    }
                }
            });

            mainPanel.add(panel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    private int findClosestPair(int[] array, int number)
    {
        if (array.length < 2)
        {
            throw new IllegalArgumentException("Array must contain at least two elements.");
        }

        // Dizi sıralandı.
        Arrays.sort(array);

        // En yakın toplam ve fark başlatıldı.
        int closestSum = array[0] + array[1];
        int minDiff = Math.abs(closestSum - number);

        // İki gösterge kullanılarak dizi tarandı.
        int left = 0;
        int right = array.length - 1;

        while (left < right)
        {
            int currentSum = array[left] + array[right];
            int currentDiff = Math.abs(currentSum - number);

            // Eğer bu toplam daha yakınsa, en yakın toplam güncellendi.
            if (currentDiff < minDiff)
            {
                closestSum = currentSum;
                minDiff = currentDiff;
            }

            // Toplam belirtilen sayıya eşitse döngüden çıkıldı.
            if (currentSum == number)
            {
                break;
            }

            // Toplam belirtilen sayıdan küçükse, sol gösterge arttırıldı.
            if (currentSum < number)
            {
                left++;
            }
            else
            {
                // Diğer durumda sağ gösterge azaltıldı.
                right--;
            }
        }

        return closestSum;
    }

    class ThirdActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            removeAllComponents();
            createThirdPanel();

            backMenu();
        }

        private void createThirdPanel()
        {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JPanel arrayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel arrayLabel = new JLabel("Please enter your array (add commas between elements):");
            JTextField arrayField = new JTextField(20);
            arrayPanel.add(arrayLabel);
            arrayPanel.add(arrayField);

            JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton resultButton = new JButton("Find");
            JLabel resultLabel = new JLabel("Result: ");
            resultPanel.add(resultButton);
            resultPanel.add(resultLabel);

            panel.add(arrayPanel);
            panel.add(resultPanel);

            resultButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String arrayText = arrayField.getText();

                    try
                    {
                        String[] arrayString = arrayText.split(",");
                        int[] array = new int[arrayString.length];
                        for (int i = 0; i < arrayString.length; i++)
                        {
                            array[i] = Integer.parseInt(arrayString[i].trim());
                        }

                        int[] repeatElement = findRepeatElement(array);
                        resultLabel.setText("Result: " + Arrays.toString(repeatElement));
                    }
                    catch (NumberFormatException ex)
                    {
                        resultLabel.setText("Invalid login!");
                    }
                    catch (IllegalArgumentException ex)
                    {
                        resultLabel.setText(ex.getMessage());
                    }
                }
            });

            mainPanel.add(panel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    private int[] findRepeatElement(int[] array)
    {
        // Dönecek dizinin maksimum uzunluğu belirlendi.
        int[] result = new int[array.length];
        int index = 0;

        // Her eleman için diğer elemanlar kontrol edildi.
        for (int i = 0; i < array.length; i++)
        {
            // i. eleman, kendisinden sonraki elemanlarla karşılaştırıldı.
            for (int j = i + 1; j < array.length; j++)
            {
                // Eğer iki eleman eşitse ve daha önce result dizisine eklenmemişse, eklendi.
                if (array[i] == array[j])
                {
                    // result dizisine eklemek için tekrar kontrol edildi.
                    boolean alreadyExists = false;
                    for (int k = 0; k < index; k++)
                    {
                        if (result[k] == array[i])
                        {
                            alreadyExists = true;
                            break;
                        }
                    }
                    if (!alreadyExists)
                    {
                        result[index++] = array[i];
                    }
                }
            }
        }

        // Sonuç dizisi temizlendi. (gereksiz sıfırlar kaldırıldı)
        return Arrays.copyOf(result, index);
    }

    class FourthActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            removeAllComponents();
            createFourthPanel();

            backMenu();
        }

        private void createFourthPanel()
        {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JPanel arrayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel arrayLabel = new JLabel("Please press the button to print:");
            arrayPanel.add(arrayLabel);

            JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton resultButton = new JButton("Find");
            JLabel resultLabel = new JLabel("Result: ");
            resultPanel.add(resultButton);
            resultPanel.add(resultLabel);

            panel.add(arrayPanel);
            panel.add(resultPanel);

            resultButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        File fileName = new File("D:\\Intellij IDEA\\Examples\\src\\ConsoleMenu\\giris_metni.txt");
                        if (!fileName.exists())
                        {
                            fileName.createNewFile();
                        }
                        Scanner read = new Scanner(fileName);

                        int counterApple=0, counterPear=0, counterCherry=0;
                        while(read.hasNext())
                        {
                            String data = read.next();

                            if (data.equals("elma"))
                            {
                                counterApple++;
                            }
                            else if (data.equals("armut"))
                            {
                                counterPear++;
                            }
                            else if (data.equals("kiraz"))
                            {
                                counterCherry++;
                            }
                        }
                        read.close();

                        resultPanel.add(new JLabel("elma=" + counterApple));
                        resultPanel.add(new JLabel("armut=" + counterPear));
                        resultPanel.add(new JLabel("kiraz=" + counterCherry));
                        resultPanel.repaint();
                        resultPanel.revalidate();
                    }
                    catch (IOException exception)
                    {
                        resultLabel.setText("Please enter valid integers.");
                    }
                }
            });

            mainPanel.add(panel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    class FifthActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            removeAllComponents();
            createFifthPanel();

            backMenu();
        }

        private void createFifthPanel()
        {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JPanel arrayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel arrayLabel = new JLabel("Please enter your array (add commas between elements):");
            JTextField arrayField = new JTextField(20);
            arrayPanel.add(arrayLabel);
            arrayPanel.add(arrayField);

            JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton resultButton = new JButton("Find");
            JLabel resultLabel = new JLabel("Result: ");
            resultPanel.add(resultButton);
            resultPanel.add(resultLabel);

            panel.add(arrayPanel);
            panel.add(resultPanel);

            resultButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String arrayText = arrayField.getText();

                    try
                    {
                        String[] arrayString = arrayText.split(",");
                        int[] array = new int[arrayString.length];
                        for (int i = 0; i < arrayString.length; i++)
                        {
                            array[i] = Integer.parseInt(arrayString[i].trim());
                        }

                        //Dizideki en küçük elemanı bulma
                        int smallestElement = findSmallestElement(array);
                        resultLabel.setText("Result: " + smallestElement);
                    }
                    catch (NumberFormatException ex)
                    {
                        resultLabel.setText("Invalid login!");
                    }
                    catch (IllegalArgumentException ex)
                    {
                        resultLabel.setText(ex.getMessage());
                    }
                }
            });

            mainPanel.add(panel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    private int findSmallestElement(int[] array)
    {
        int smallest = array[0];

        for (int i=0; i< array.length; i++)
        {
            if (array[i]<smallest)
            {
                smallest = array[i];
            }
        }
        return smallest;
    }

    class SixthActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            removeAllComponents();
            createSixthPanel();

            backMenu();
        }

        private void createSixthPanel()
        {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JPanel numberPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel firstNumberLabel = new JLabel("Please enter your first number:");
            JTextField firstNumberField = new JTextField(5);
            JLabel secondNumberLabel = new JLabel("Please enter your second number:");
            JTextField secondNumberField = new JTextField(5);

            numberPanel.add(firstNumberLabel);
            numberPanel.add(firstNumberField);
            numberPanel.add(secondNumberLabel);
            numberPanel.add(secondNumberField);

            JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton resultButton = new JButton("Find");
            JLabel resultLabel = new JLabel("Result: ");
            resultPanel.add(resultButton);
            resultPanel.add(resultLabel);

            panel.add(numberPanel);
            panel.add(resultPanel);

            resultButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        int number1 = Integer.parseInt(firstNumberField.getText().trim());
                        int number2 = Integer.parseInt(secondNumberField.getText().trim());

                        int greatestDivisor = findGreatestDivisor(number1, number2);
                        resultLabel.setText("Result: " + greatestDivisor);
                    }
                    catch (NumberFormatException exception)
                    {
                        resultLabel.setText("Please enter valid integers.");
                    }
                }
            });

            mainPanel.add(panel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    private int findGreatestDivisor(int number1, int number2)
    {
        if (number2 == 0)
        {
            return number1;
        }
        return findGreatestDivisor(number2, number1 % number2);
    }

    class SeventhActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            removeAllComponents();
            createSeventhPanel();

            backMenu();
        }

        private void createSeventhPanel()
        {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JPanel numberPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel numberLabel = new JLabel("Please enter the number you want to find whether it is prime or not:");
            JTextField numberField = new JTextField(10);
            numberPanel.add(numberLabel);
            numberPanel.add(numberField);

            JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton resultButton = new JButton("Find");
            JLabel resultLabel = new JLabel("Result: ");
            resultPanel.add(resultButton);
            resultPanel.add(resultLabel);

            panel.add(numberPanel);
            panel.add(resultPanel);

            resultButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        int number = Integer.parseInt(numberField.getText().trim());

                        boolean primeNumber = findPrimeNumber(number, 2);
                        resultLabel.setText("Result: " + primeNumber);
                    }
                    catch (NumberFormatException exception)
                    {
                        resultLabel.setText("Please enter valid integers.");
                    }
                }
            });

            mainPanel.add(panel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    private boolean findPrimeNumber(int number, int divisor)
    {
        if (number <= 2) //Asal sayı olan 2 için kontrol yapıldı.
        {
            return number == 2;
        }

        if (number % divisor == 0) //number'ın divisor ile bölünebilirliği kontrol edildi.
        {
            return false;
        }

        if (divisor * divisor > number) //divisor'ın karesinin number'dan büyük olma durumu kontrol edildi.
        {
            return true;
        }
        return findPrimeNumber(number, divisor + 1); //divisor+1 için recursion metot çağırıldı.
    }

    class EigthActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            removeAllComponents();
            createEigthPanel();

            backMenu();
        }

        private void createEigthPanel()
        {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JPanel numberPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel numberLabel = new JLabel("Please enter the Fibonacci sequence number you want to find:");
            JTextField numberField = new JTextField(10);
            numberPanel.add(numberLabel);
            numberPanel.add(numberField);

            JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton resultButton = new JButton("Find");
            JLabel resultLabel = new JLabel("Result: ");
            resultPanel.add(resultButton);
            resultPanel.add(resultLabel);

            panel.add(numberPanel);
            panel.add(resultPanel);

            resultButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        int number = Integer.parseInt(numberField.getText().trim());

                        int fibonacciNumber = fastFibonacci(number, 1, 1, 0);
                        resultLabel.setText("Result: " + fibonacciNumber);
                    }
                    catch (NumberFormatException exception)
                    {
                        resultLabel.setText("Please enter valid integers.");
                    }
                }
            });

            mainPanel.add(panel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    private int fastFibonacci(int n, int k, int fibk, int fibk1)
    {
        if (k == n)
        {
            return fibk;
        }
        return fastFibonacci(n, k + 1, fibk + fibk1, fibk);
    }

    class NinthActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            System.exit(0);
        }
    }

    private void backMenu()
    {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenuItem backMenu = new JMenuItem("Back Menu");

        menuBar.add(backMenu);

        backMenu.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();

                JFrame frame = new ConsoleMenuFrame();
                frame.setTitle("Console Menu");
                frame.setLocation(450,200);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}