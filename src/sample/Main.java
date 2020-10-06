package sample;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.List;
import java.util.stream.Collectors;


public class Main extends Application {

    DataGenerator dataGenerator=new DataGenerator();

    TableView<Items> table;
    ComboBox<String> comboBox;
    Button cbButton;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Furniture store");

        //===============================tworze kolumny i tabele:
        TableColumn<Items, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setStyle("-fx-alignment: CENTER;");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Items, String>("nameItem"));


        TableColumn<Items, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(70);
        priceColumn.setStyle("-fx-alignment: CENTER;");
        priceColumn.setCellValueFactory(new PropertyValueFactory<Items, Double>("price"));

        TableColumn<Items, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(70);
        quantityColumn.setStyle("-fx-alignment: CENTER;");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Items, Integer>("amountOfItem"));

        TableColumn<Items, String> storeColumn = new TableColumn<>("Store");
        storeColumn.setMinWidth(100);
        storeColumn.setStyle("-fx-alignment: CENTER;");
        storeColumn.setCellValueFactory(new PropertyValueFactory<Items, String>("nameStore"));

        table = new TableView<>();
        dataGenerator.addItemsToStore(); //tworza sie produkty
        table.setItems(FXCollections.observableArrayList(dataGenerator.Wszystkieprodukty())); //dodajemy liste wszystkich produktow
        table.getColumns().addAll(nameColumn, priceColumn, quantityColumn, storeColumn);

        //
        VBox vblist = new VBox();
        vblist.getChildren().addAll(table);


        //=================================tworze przyciski z boku
        VBox vbright = new VBox();
        vbright.setSpacing(5);


        comboBox = new ComboBox();
        comboBox.getItems().addAll("Store One", "Store Two", "Store Three", "All stories");
        comboBox.setPromptText("Choose store");
        comboBox.setMinWidth(160);
        comboBox.setOnAction((e) -> {
            System.out.println("=====================");

            switch (comboBox.getValue()) {
                case "Store One": {
                    System.out.println("one");
                    List<Items> filteredStoreList = dataGenerator.Wszystkieprodukty().stream().
                            filter(items -> items.getNameStore().contains("Store One")).collect(Collectors.toList());
                   table.setItems(FXCollections.observableArrayList(filteredStoreList));
                    break;
                }

                case "Store Two":
                    System.out.println("two");
                    List<Items> filteredStoreList2 = dataGenerator.Wszystkieprodukty().stream().
                            filter(items -> items.getNameStore().contains("Store Two")).collect(Collectors.toList());
                    table.setItems(FXCollections.observableArrayList(filteredStoreList2));
                    break;
                case "Store Three":
                    System.out.println("three");
                    List<Items> filteredStoreList3 = dataGenerator.Wszystkieprodukty().stream().
                            filter(items -> items.getNameStore().contains("Store Three")).collect(Collectors.toList());
                    table.setItems(FXCollections.observableArrayList(filteredStoreList3));
                    break;
                case "All stories":
                    System.out.println("all");
                    table.setItems(FXCollections.observableArrayList(dataGenerator.Wszystkieprodukty()));
                    break;
            }
        });

        //=================================tworze wyszukiwanie produktu po nazwie
        Label searchlabel = new Label("\nSearching products\nby name");
        searchlabel.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");
        searchlabel.setMinWidth(160);

        TextField searchfield = new TextField();
        searchfield.setPromptText("write name of product");

        Button searchbutton = new Button("search");
        searchbutton.setOnAction(e->{
            String szukaj = searchfield.getText();
            List<Items> filteredSearchList = dataGenerator.Wszystkieprodukty().stream().
                    filter(i -> i.getNameItem().contains(szukaj)).collect(Collectors.toList());
            table.setItems(FXCollections.observableArrayList(filteredSearchList));
        });

        //======================= tworze kupowanie produktow
        Label buylabel = new Label("\nBuy product");
        buylabel.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");
        buylabel.setMinWidth(160);

        TextField productfield = new TextField();
        productfield.setPromptText("name");
        TextField quantityfield = new TextField();
        quantityfield.setPromptText("quantity");

        Button buybutton = new Button("BUY");
        buybutton.setMinWidth(160);
        Label labelbuy = new Label(" ");
        labelbuy.setStyle("-fx-border-color: blue;");
        labelbuy.setMinSize(160,30);

        buybutton.setOnAction(e-> {
            String prod = productfield.getText();
            int quant = Integer.valueOf(quantityfield.getText());
            int step = 0;
            for(int i = 0;i<dataGenerator.wszystkieprodukty.size();i++) {
                if (dataGenerator.Wszystkieprodukty().get(i).getNameItem().equals(prod)) {

                    if(dataGenerator.Wszystkieprodukty().get(i).getAmountOfItem()>=quant){
                        //jesli wszystko sie zgadza
                        int ilosc = dataGenerator.Wszystkieprodukty().get(i).getAmountOfItem();
                        ilosc -= quant;
                        System.out.println("produkt: "+ dataGenerator.Wszystkieprodukty().get(i).getNameItem()+ " pozostała ilość: "+ilosc);
                        dataGenerator.Wszystkieprodukty().get(i).setAmountIfItem(ilosc);            //ustalam nowa ilosc danego produktu

                        //zmniejszam zapelnienie w magazynie - niestety tooltip sie nie odswieza
                        for(int j = 0;j < dataGenerator.AllStores().size();j++){
                            dataGenerator.AllStores().get(j).setFillInPercentage(dataGenerator.Wszystkieprodukty().get(i), quant);
                        }

                        table.refresh();

                        //usuwam produkt wgl z tableview i z list
                        if(ilosc == 0){
                            System.out.println("usuniety produkt: " +  dataGenerator.Wszystkieprodukty().get(i).getNameItem());
                            dataGenerator.AllStores().remove(dataGenerator.Wszystkieprodukty().get(i));
                            table.getItems().remove(dataGenerator.Wszystkieprodukty().get(i));
                            dataGenerator.wszystkieprodukty.remove(dataGenerator.Wszystkieprodukty().get(i));       //
                            table.refresh();
                        }
                        if(quant == 0){
                            labelbuy.setText("You've choose 0 products");
                        }
                        else{
                            labelbuy.setText("You've got this");
                        }
                        return;
                    }
                    else{
                        //za duza ilosc
                        labelbuy.setText("There is no quantity\nof selected item.\nTry again.");
                        return;
                    }
                }
                step++;
            }
            if(step == dataGenerator.wszystkieprodukty.size()){
                //info, ze nie ma takiego produktu
                labelbuy.setText("The specified product \nis out of stock");
            }
        });


        //
        vbright.getChildren().addAll(comboBox, searchlabel, searchfield, searchbutton, buylabel,
                productfield,quantityfield,buybutton,labelbuy);


        //==============================tworze tooltip

        String mag1 = dataGenerator.AllStores().get(0).summary();       //wyswietlanie info o danym magazynie
        String mag2 = dataGenerator.AllStores().get(1).summary();
        String mag3 = dataGenerator.AllStores().get(2).summary();

        TableColumn<Items, String> nameCol = new TableColumn<Items, String>("Name");
        nameColumn.setCellFactory
                (
                        column ->
                        {
                            return new TableCell<Items, String>()
                            {
                                @Override
                                protected void updateItem(String item, boolean empty)
                                {
                                    super.updateItem(item, empty);
                                    if (item == null) {
                                        setTooltip(null);
                                        setText(null);
                                    }
                                    else {
                                        setText( item );
                                        Tooltip tooltip = new Tooltip();
                                        tooltip.setText(getTableView().getItems().get(getTableRow().getIndex()).getNameStore());
                                        if((getTableView().getItems().get(getTableRow().getIndex()).getNameStore()).equals("Store One")){
                                            tooltip.setText(mag1);
                                            setTooltip(tooltip);
                                        }
                                        else if((getTableView().getItems().get(getTableRow().getIndex()).getNameStore()).equals("Store Two")){
                                            tooltip.setText(mag2);
                                            setTooltip(tooltip);
                                        }
                                        else if((getTableView().getItems().get(getTableRow().getIndex()).getNameStore()).equals("Store Three")) {
                                            tooltip.setText(mag3);
                                            setTooltip(tooltip);
                                        }
                                    }
                                }
                            };
                        });


        //=======================tworze layout glowny
        BorderPane layoutGlowny = new BorderPane();
        layoutGlowny.setLeft(vblist);
        layoutGlowny.setRight(vbright);
        Scene scenemain = new Scene(layoutGlowny);

        primaryStage.setScene(scenemain);
        primaryStage.sizeToScene();
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}

