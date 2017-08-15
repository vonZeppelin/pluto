package org.lbogdanov.btlock.ui;

import static org.lbogdanov.btlock.App.BUS;

import java.io.IOException;

import org.lbogdanov.btlock.events.PeripheralDiscovered;
import org.lbogdanov.btlock.events.StartDiscovery;

import com.apple.jobjc.Utils;
import com.squareup.otto.Subscribe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

public class ScanDialog extends Dialog<Void> {
    public static class Controller {
        @FXML private ListView<String> devicesList;
        @FXML private Button connectButton;

        @FXML public void connect(ActionEvent event) {
            System.out.println(event);
        }

        @Subscribe public void peripheralDiscovered(PeripheralDiscovered event) {
            String name = Utils.get().strings().javaString(event.peripheral.name());
            devicesList.getItems().add(name);
        }

        public void initialize() {
            connectButton.disableProperty().bind(
                devicesList.getSelectionModel().selectedItemProperty().isNull()
            );
        }
    }

    public ScanDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ScanDialog.fxml"));

            DialogPane dialogPane = getDialogPane();
            dialogPane.setContent(loader.load());
            dialogPane.getButtonTypes().addAll(ButtonType.CLOSE);

            Controller controller = loader.getController();
            BUS.register(controller);
            setOnHidden(e -> {
                BUS.unregister(controller);
            });
            BUS.post(new StartDiscovery());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
