import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Parcial_Co extends JFrame implements ActionListener {
    private JLabel labelHora;
    private Timer timer;
    private JButton botonActivar, botonDetener;
    private JSlider sliderVelocidad;
    private int velocidad = 1000;

    public Parcial_Co() {
        super("Parcial");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);
        getContentPane().add(panel);

        
        labelHora = new JLabel("Parcial", JLabel.CENTER);
        botonActivar = new JButton("Activar");
        botonDetener = new JButton("Detener");
        sliderVelocidad = new JSlider(JSlider.HORIZONTAL, 0, 3000, 2000);

       
        panel.add(labelHora);
        panel.add(botonActivar);
        panel.add(botonDetener);
        panel.add(sliderVelocidad);

        
        botonActivar.addActionListener(this);
        botonDetener.addActionListener(this);
        sliderVelocidad.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                velocidad = sliderVelocidad.getValue();
                if (timer != null) {
                    timer.setDelay(velocidad);
                }
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonActivar) {
            // Iniciar el Timer si no está corriendo
            if (timer == null) {
                timer = new Timer(velocidad, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        labelHora.setText(obtenerHoraActual());
                    }
                });
                timer.start();
            }
        } else if (e.getSource() == botonDetener) {
            // Detener el Timer si está corriendo
            if (timer != null) {
                timer.stop();
                timer = null;
            }
        }
    }

    private String obtenerHoraActual() {
        int horas = new java.util.Date().getHours();
        int minutos = new java.util.Date().getMinutes();
        int segundos = new java.util.Date().getSeconds();
        return String.format("%002d:%002d:%002d", horas, minutos, segundos);
    }

    public static void main(String[] args) {
        new Parcial_Co();
    }
}
