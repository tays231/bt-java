package text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class clockApp {
    private JFrame mainFrame;
    private JFrame clockFrame;
    private JLabel clockLabel;
    private JTextField timezoneField;
    private Timer timer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new clockApp().initialize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void initialize() {
        mainFrame = new JFrame("World Clock App");
        mainFrame.setSize(400, 200);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        clockLabel = new JLabel(getCurrentTime());
        clockLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        mainFrame.add(clockLabel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        timezoneField = new JTextField(10);
        JButton setTimeZoneButton = new JButton("Set Timezone");
        setTimeZoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setClockTimeZone();
            }
        });

        inputPanel.add(new JLabel("Timezone:"));
        inputPanel.add(timezoneField);
        inputPanel.add(setTimeZoneButton);

        mainFrame.add(inputPanel, BorderLayout.SOUTH);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClock();
            }
        });
        timer.start();

        mainFrame.setVisible(true);
    }

    private void setClockTimeZone() {
        String timeZoneStr = timezoneField.getText().trim();
        if (!timeZoneStr.isEmpty()) {
            TimeZone timeZone = TimeZone.getTimeZone(timeZoneStr);
            Calendar cal = Calendar.getInstance(timeZone);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String currentTime = sdf.format(cal.getTime());

            showClockFrame(currentTime);
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Please enter a valid timezone.");
        }
    }

    private void showClockFrame(String currentTime) {
        if (clockFrame != null) {
            clockFrame.dispose(); // Đóng frame cũ nếu đã tồn tại
        }

        clockFrame = new JFrame("World Clock");
        clockFrame.setSize(300, 150);
        clockFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        clockFrame.setLayout(new BorderLayout());

        JLabel clockLabel = new JLabel(currentTime);
        clockLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        clockFrame.add(clockLabel, BorderLayout.CENTER);

        clockFrame.setVisible(true);
    }

    private void updateClock() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        clockLabel.setText(sdf.format(cal.getTime()));
    }

    private String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }
}
