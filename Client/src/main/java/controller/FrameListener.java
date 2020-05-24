package controller;

import network.NetworkManager;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

public class FrameListener implements WindowListener{

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
        NetworkManager.getInstance().stopServerConnection();
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

}
