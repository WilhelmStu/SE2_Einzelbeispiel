package com.se2.einzelbeispiel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpClient {
    private static final String SERVER_IP = "se2-isys.aau.at";
    private static final int SERVER_PORT = 53212;

    public static String sendMessage(String message) {


        String serverMessage = "";

        DataOutputStream sendToServer;
        BufferedReader getFromServer;

        try {

            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            try {
                sendToServer = new DataOutputStream(socket.getOutputStream());
                getFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                sendToServer.writeBytes(message + '\n');
                serverMessage = getFromServer.readLine();

            } catch (Exception e) {
                System.err.println(e.getMessage());
            } finally {
                socket.close();
            }
        } catch (Exception e) {

            System.err.println(e.getMessage());
        }
        return serverMessage;
    }
}
