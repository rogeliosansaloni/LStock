package controller;

import network.NetworkManager;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class FrameListener implements WindowListener{

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        try{
            NetworkManager.getInstance().stopServerConnection();
        }catch (IOException ex){
            System.out.println("An error ocurred when the client was disconnected");
        }
    }

    public void windowClosed(WindowEvent e) {

    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent ie) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

}
