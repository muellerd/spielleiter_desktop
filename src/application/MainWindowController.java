package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.util.Callback;
import model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{
    public Button buttonNewTavern;
    public ListView listViewLog;
    public ListView listViewHelden;
    public TextField textFieldHeldenName;
    public Button buttonNewName;
    public CheckBox checkBoxW;
    public CheckBox checkBoxM;
    public ListView listViewGoods;
    public TextField textFieldSearch;

    private ArrayList<String> logList;
    private ArrayList<Held> heldenList;
    private HashMap<String, Held> heldenNameToHelds;
    private ArrayList<Good> goodsList;
    private HashMap<String, Good> goodsNameToGoods;

    private ObservableList<String> listViewLogItems;
    private ObservableList<Held> listViewHeldenItems;
    private ObservableList<Good> listViewGoodsItems;

    private ListCell currentCell;

    public void createNewTavern(ActionEvent actionEvent) {
        Tavern t = Main.sessionData.getRandomTavern();
        this.listViewLogItems.add(t.getName());
        this.listViewLog.scrollTo(this.listViewLogItems.size()-1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.logList = new ArrayList<>();
        this.listViewLogItems = FXCollections.observableArrayList(logList);
        this.listViewLog.setItems(listViewLogItems);

        this.heldenList = new ArrayList<>();
        this.heldenNameToHelds = new HashMap<>();
        this.listViewHeldenItems = FXCollections.observableArrayList(heldenList);
        this.listViewHelden.setItems(listViewHeldenItems);
        this.listViewHelden.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView param) {
                ListCell<Held> cell = new ListCell<Held>(){
                    @Override
                    protected void updateItem(Held item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null){
                            setText(item.getName());
                        }
                    }
                };
                cell.setOnDragEntered(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                        if(cell.getText() != null && !cell.getText().equals("")){
                            cell.getStyleClass().add("cellHighlight");
                            currentCell = cell;
                        }
                    }
                });
                cell.setOnDragExited(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                        cell.getStyleClass().remove("cellHighlight");
                        currentCell = null;
                    }
                });
                return cell;
            }
        });

        this.goodsList = Main.sessionData.getGoodsList();
        this.goodsNameToGoods = Main.sessionData.getGoodsDict();
        this.listViewGoodsItems = FXCollections.observableArrayList(goodsList);
        FilteredList<Good> filteredGoods = new FilteredList<Good>(listViewGoodsItems, s -> true);

        textFieldSearch.textProperty().addListener(obs -> {
            String filter = textFieldSearch.getText();
            if(filter == null || filter.length() == 0){
                filteredGoods.setPredicate(s -> true);
            }
            else{
                filteredGoods.setPredicate(s -> s.getName().toLowerCase().contains(filter.toLowerCase()) || s.getCategory().toLowerCase().contains(filter.toLowerCase()));
            }
        });

        this.listViewGoods.setItems(filteredGoods);
        this.listViewGoods.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView param) {
                ListCell<Good> cell = new ListCell<Good>(){
                    @Override
                    protected void updateItem(Good item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null){
                            setText(item.getName());
                        }
                    }
                };
                return cell;
            }
        });

        this.listViewHelden.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if(event.getGestureSource().equals(listViewGoods)){
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            }
        });

        this.listViewHelden.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if(db.hasString()){
                    Good g = goodsNameToGoods.get(db.getString());
                    Held h = heldenNameToHelds.get(currentCell.getText());
                    System.out.println("Drop " + g.getName() + " on " + h.getName());
                    h.addGood(g, 1);
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        listViewHelden.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2){
                    Object obj = listViewHelden.getSelectionModel().getSelectedItem();
                    if(obj != null){
                        Held h = (Held) obj;
                        System.out.println(h.getNameAndCost());
                    }


                }
            }
        });
    }

    public void addHeld(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            Held h = new Held(textFieldHeldenName.getText());
            heldenNameToHelds.put(h.getName(), h);
            listViewHeldenItems.add(h);
            textFieldHeldenName.setText("");
        }
    }

    public void createNewName(ActionEvent actionEvent) {
        if(!checkBoxM.isSelected() && !checkBoxW.isSelected()){
            this.listViewLogItems.add("Kein Geschlecht gewählt");
            this.listViewLog.scrollTo(this.listViewLogItems.size()-1);
        }
        String name = "";
        if(!checkBoxW.isSelected() && checkBoxM.isSelected()){
            name = Main.sessionData.getRandomMaleName();
        }
        if(!checkBoxM.isSelected() && checkBoxW.isSelected()){
            name = Main.sessionData.getRandomFemaleName();
        }
        if(checkBoxW.isSelected() && checkBoxM.isSelected()){
            name = Main.sessionData.getRandomName();
        }
        if(!name.equals("")){
            this.listViewLogItems.add(name);
            this.listViewLog.scrollTo(this.listViewLogItems.size()-1);
        }
    }

    public void dragGood(MouseEvent mouseEvent) {
        System.out.println("Drag detected");
        Dragboard dragBoard = listViewGoods.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(listViewGoods.getSelectionModel().getSelectedItem().toString());
        dragBoard.setContent(content);
    }

    public void dropGood(DragEvent dragEvent) {

    }
}
