import javax.swing.*;
import java.awt.*;
import java.util.*;

class TicketCounter {
    private int availableSeats;
    public TicketCounter(int totalSeats) {
        this.availableSeats = totalSeats;
    }

    public synchronized boolean bookTicket(String user, int numberOfSeats, JTextArea outputArea) {
        StringBuilder sb = new StringBuilder();
        sb.append(user).append(" is requesting ").append(numberOfSeats).append(" seat(s)...\n");

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            sb.append(e.getMessage()).append("\n");
            final String outText = sb.toString();
            SwingUtilities.invokeLater(() -> outputArea.append(outText));
            return false;
        }

        if (availableSeats == 0) {
            sb.append("❌ Sorry ").append(user).append(", all seats are booked!\n\n");
            final String outText = sb.toString();
            SwingUtilities.invokeLater(() -> outputArea.append(outText));
            return false;
        } else if (availableSeats >= numberOfSeats) {
            availableSeats -= numberOfSeats;
            sb.append("✅ ").append(user).append(" successfully booked ").append(numberOfSeats).append(" seat(s).\n");
        } else {
            sb.append("⚠️ Only ").append(availableSeats).append(" seat(s) left. ").append(user).append(" could not book all.\n");
            sb.append("✅ ").append(user).append(" booked ").append(availableSeats).append(" seat(s).\n");
            availableSeats = 0;
        }

        sb.append("Seats remaining: ").append(availableSeats).append("\n");
        sb.append("----------------------------------------------------\n");
        final String outText = sb.toString();
        SwingUtilities.invokeLater(() -> outputArea.append(outText));
        return true;
    }
}

class BookingRequest extends Thread {
    private String userName;
    private int seatsToBook;
    private TicketCounter counter;
    private JTextArea outputArea;

    public BookingRequest(TicketCounter counter, String userName, int seatsToBook, JTextArea outputArea) {
        this.counter = counter;
        this.userName = userName;
        this.seatsToBook = seatsToBook;
        this.outputArea = outputArea;
    }

    public void run() {
        counter.bookTicket(userName, seatsToBook, outputArea);
    }
}

public class TicketBookingGUI extends JFrame {
    private JTextField totalSeatsField, nameField, seatsField;
    private JTextArea outputArea;
    private java.util.List<BookingRequest> requests;
    private TicketCounter counter;
    private boolean seatsSet = false;

    public TicketBookingGUI() {
        setTitle("🎟️ Concurrent Ticket Booking System");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        requests = new ArrayList<>();
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Total Seats:"));
        totalSeatsField = new JTextField(5);
        topPanel.add(totalSeatsField);

        JButton setSeatsButton = new JButton("Set Seats");
        topPanel.add(setSeatsButton);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));

        JPanel leftPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Add Booking Request"));
        nameField = new JTextField();
        seatsField = new JTextField();

        JButton addButton = new JButton("Add Request");
        leftPanel.add(new JLabel("User Name:"));
        leftPanel.add(nameField);
        leftPanel.add(new JLabel("Seats to Book:"));
        leftPanel.add(seatsField);
        leftPanel.add(addButton);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Booking Output"));

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton startButton = new JButton("Start Booking");
        bottomPanel.add(startButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setSeatsButton.addActionListener(e -> {
            if (!totalSeatsField.getText().isEmpty()) {
                int totalSeats = Integer.parseInt(totalSeatsField.getText());
                counter = new TicketCounter(totalSeats);
                seatsSet = true;
                outputArea.append("🎫 Total Seats Set To: " + totalSeats + "\n");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter total seats first!");
            }
        });

        addButton.addActionListener(e -> {
            if (!seatsSet) {
                JOptionPane.showMessageDialog(this, "Please set total seats first!");
                return;
            }

            String name = nameField.getText().trim();
            String seatsText = seatsField.getText().trim();

            if (name.isEmpty() || seatsText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter both name and seat count!");
                return;
            }

            int seats = Integer.parseInt(seatsText);
            BookingRequest req = new BookingRequest(counter, name, seats, outputArea);
            requests.add(req);
            outputArea.append("🧍 Added Request: " + name + " (" + seats + " seats)\n");

            nameField.setText("");
            seatsField.setText("");
        });

        startButton.addActionListener(e -> {
            if (requests.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No booking requests added!");
                return;
            }

            outputArea.append("\n🚀 Processing all booking requests concurrently...\n\n");

            for (BookingRequest req : requests) {
                req.start();
            }

            new Thread(() -> {
                for (BookingRequest req : requests) {
                    try {
                        req.join();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                SwingUtilities.invokeLater(() -> outputArea.append("\n✅ All booking requests processed successfully!\n"));
            }).start();
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TicketBookingGUI().setVisible(true);
        });
    }
}