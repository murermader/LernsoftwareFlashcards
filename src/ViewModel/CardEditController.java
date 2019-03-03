package ViewModel;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CardEditController {

    private Helper helper = new Helper();

    @FXML
    public void handlerBack(ActionEvent event)throws IOException{
        helper.switchScene(event,"CardAdd.fxml");
    }

    @FXML
    public void handlerEdit(ActionEvent event)throws IOException{

    }
}
