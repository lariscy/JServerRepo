package com.githup.lariscy.jserverrepo.client;

import com.githup.lariscy.jserverrepo.client.util.GuiceFXMLLoader;
import com.githup.lariscy.jserverrepo.client.util.GuiceFXMLLoader.FXMLView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.inject.Inject;

/**
 * @author Steven
 */
public class AppGUI {
    
    private Stage stage;
    
    @Inject
    private GuiceFXMLLoader loader;

    public void start(Stage stage){
        this.stage = stage;
        initLayout();
        stage.show();
    }

    private void initLayout() {
        BorderPane root = loader.load(FXMLView.CONTAINER);
        
        root.setTop(loader.load(FXMLView.TOP_MENU));
        root.setLeft(loader.load(FXMLView.SIDE_MENU));
        root.setCenter(loader.load(FXMLView.SERVER_TREE));
        root.setBottom(loader.load(FXMLView.STATUS_BAR));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

}
