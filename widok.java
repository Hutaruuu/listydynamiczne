import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class widok {

    private JFrame frame;

    private JTextField poleTekstowe;

    private JList<String> lista;
    private DefaultListModel<String> listModel;

    private JButton dodajButton;
    private JButton usunButton;

    private JLabel licznikLabel;

    public widok() {

        frame = new JFrame("listadozrobieniajakas");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        JPanel topPanel = new JPanel(new BorderLayout());

        poleTekstowe = new JTextField();

        dodajButton = new JButton("Dodaj");

        topPanel.add(poleTekstowe, BorderLayout.CENTER);
        topPanel.add(dodajButton, BorderLayout.EAST);

        frame.add(topPanel, BorderLayout.NORTH);


        listModel = new DefaultListModel<>();

        lista = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(lista);

        frame.add(scrollPane, BorderLayout.CENTER);


        JPanel bottomPanel = new JPanel(new BorderLayout());

        usunButton = new JButton("Usuń zaznaczone");

        licznikLabel = new JLabel("Liczba zadań: 0");

        bottomPanel.add(licznikLabel, BorderLayout.WEST);
        bottomPanel.add(usunButton, BorderLayout.EAST);

        frame.add(bottomPanel, BorderLayout.SOUTH);


        dodajButton.addActionListener(e -> {

            String tekst = poleTekstowe.getText().trim();


            if (tekst.isEmpty()) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Zadanie nie może być puste!",
                        "Błąd",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            listModel.addElement(tekst);

            poleTekstowe.setText("");

            aktualizujLicznik();
        });


        usunButton.addActionListener(e -> {

            int index = lista.getSelectedIndex();

            if (index != -1) {
                listModel.remove(index);
                aktualizujLicznik();
            }
        });


        lista.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    int index = lista.locationToIndex(e.getPoint());

                    if (index != -1) {
                        listModel.remove(index);
                        aktualizujLicznik();
                    }
                }
            }
        });

        frame.setVisible(true);
    }


    private void aktualizujLicznik() {
        licznikLabel.setText("Liczba zadań: " + listModel.size());
    }


}