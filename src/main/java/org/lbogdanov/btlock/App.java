package org.lbogdanov.btlock;


import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import org.lbogdanov.btlock.objc.CBActor;
import org.lbogdanov.btlock.ui.ScanDialog;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * 
 */
public class App extends Application {
    public static final Bus BUS = new Bus(ThreadEnforcer.ANY, App.class.getName());

    private CBActor actor;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        actor = new CBActor();
        Platform.setImplicitExit(false);

        SwingUtilities.invokeLater(this::initTrayIcon);
    }

    @Override
    public void stop() {
        actor = null;
    }

    private void initTrayIcon() {
        try {
            SystemTray tray = SystemTray.getSystemTray();
            PopupMenu popupMenu = new PopupMenu();
            TrayIcon trayIcon = new TrayIcon(ImageIO.read(getClass().getResource("/lock.png")), "BT Lock", popupMenu);

            popupMenu.add(new CheckboxMenuItem("Enable", true)).addActionListener(e -> {
                
            });
            popupMenu.add(new MenuItem("Settings...")).addActionListener(e -> {
                Platform.runLater(() -> {
                    new ScanDialog().show();
                });
            });
            popupMenu.addSeparator();
            popupMenu.add(new MenuItem("Exit")).addActionListener(e -> {
                tray.remove(trayIcon);
                Platform.exit();
            });

            tray.add(trayIcon);
        } catch (AWTException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
