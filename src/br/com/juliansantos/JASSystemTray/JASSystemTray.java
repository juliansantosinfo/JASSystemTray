package br.com.juliansantos.JASSystemTray;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.AWTException;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPopupMenu;

/**
 *
 * @author Julian Santos
 */
public final class JASSystemTray implements Runnable {

    private PopupMenu popupMenu;
    private JPopupMenu jPopupMenu;
    private TrayIcon trayIcon;
    private Image iconTray;
    private String toolTip;

    public JASSystemTray(PopupMenu popupMenu, Image iconTray) {
        this.popupMenu = popupMenu;
        this.iconTray = iconTray;
        initSystemTray(popupMenu);
    }

    public JASSystemTray(JPopupMenu jPopupMenu, Image iconTray) {
        this.jPopupMenu = jPopupMenu;
        this.iconTray = iconTray;
        initSystemTray(jPopupMenu);
    }

    public void initSystemTray(PopupMenu popupMenu) {

        trayIcon = new TrayIcon(iconTray, toolTip, popupMenu);
        trayIcon.setImageAutoSize(true);
        trayIcon.addMouseListener(new MouseAdapter() {
        });
    }

    public void initSystemTray(JPopupMenu jPopupMenu) {

        trayIcon = new TrayIcon(iconTray, toolTip);
        trayIcon.setImageAutoSize(true);
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    jPopupMenu.setLocation(e.getX(), e.getY());
                    jPopupMenu.setInvoker(jPopupMenu);
                    jPopupMenu.setVisible(true);
                }
            }
        });
    }

    @Override
    public void run() {
        try {
            SystemTray.getSystemTray().add(trayIcon);
        } catch (AWTException ex) {
            Logger.getLogger(JASSystemTray.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
