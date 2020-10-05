package user.panels;

import packets.PlayerLogin;
import user.Client;
import user.network.ClientSocket;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoginPanel extends JPanel {
    private static LoginPanel instance;

    public static LoginPanel getInstance() {
        if (instance == null) {
            instance = new LoginPanel();
        }
        return instance;
    }

    private LoginPanel() {
        super(new GridBagLayout());
        redraw();
    }

    public void redraw() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;

        JLabel serverIpLabel = new JLabel("Adresse du serveur : ");
        add(serverIpLabel, constraints);
        constraints.gridx++;

        JTextField serverIpText = new JTextField("127.0.0.1:4269");
        add(serverIpText);
        constraints.gridx++;

        JButton connect = new JButton("Se connecter");
        add(connect,constraints);

        connect.addActionListener(actionEvent -> {
            try {
                ClientSocket clientSocket = ClientSocket.getInstance();
                String [] text = serverIpText.getText().split(":");
                clientSocket.connect(text[0],Integer.parseInt(text[1]));
                clientSocket.send(new PlayerLogin(Client.getInstance().getPlayer()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }
}
