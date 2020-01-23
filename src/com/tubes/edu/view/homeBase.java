package com.tubes.edu.view;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public abstract class homeBase extends AnchorPane {

    protected final ScrollPane homePanel;
    protected final VBox barisKontainer;
    protected final HBox kolomContainer;
    protected final VBox cotainer;
    protected final ImageView gambar;
    protected final Label judul;
    protected final Button tombol;
    protected final VBox vBox;
    protected final ImageView imageView;
    protected final Label label;
    protected final Button button;
    protected final VBox vBox0;
    protected final ImageView imageView0;
    protected final Label label0;
    protected final Button button0;
    protected final VBox vBox1;
    protected final HBox hBox;
    protected final Label label1;
    protected final HBox hBox0;
    protected final Button button1;
    protected final FontAwesomeIcon fontAwesomeIcon;
    protected final HBox hBox1;
    protected final Button button2;
    protected final FontAwesomeIcon fontAwesomeIcon0;
    protected final HBox hBox2;
    protected final Button btnKeluar;
    protected final FontAwesomeIcon fontAwesomeIcon1;
    protected final HBox hBox3;
    protected final HBox hBox4;
    protected final HBox hBox5;
    protected final Button button3;
    protected final Label lblNamaUser;
    protected final HBox hBox6;
    protected final Button btnCari;
    protected final FontAwesomeIcon fontAwesomeIcon2;
    protected final TextField txtCari;
    protected final HBox panelNotFound;
    protected final VBox vBox2;
    protected final VBox vBox3;
    protected final Label label2;
    protected final Label lblAnimeNotFound;
    protected final Label label3;
    protected final HBox panelFound;
    protected final VBox vBox4;
    protected final VBox vBox5;
    protected final ImageView imageAnimeFound;
    protected final Label lblAnimeFoundJudul;
    protected final Button button4;

    public homeBase() {
        
        homePanel = new ScrollPane();
        barisKontainer = new VBox();
        
        vBox = new VBox();
        imageView = new ImageView();
        label = new Label();
        button = new Button();
        vBox0 = new VBox();
        imageView0 = new ImageView();
        label0 = new Label();
        button0 = new Button();
        vBox1 = new VBox();
        hBox = new HBox();
        label1 = new Label();
        hBox0 = new HBox();
        button1 = new Button();
        fontAwesomeIcon = new FontAwesomeIcon();
        hBox1 = new HBox();
        button2 = new Button();
        fontAwesomeIcon0 = new FontAwesomeIcon();
        hBox2 = new HBox();
        btnKeluar = new Button();
        fontAwesomeIcon1 = new FontAwesomeIcon();
        hBox3 = new HBox();
        hBox4 = new HBox();
        hBox5 = new HBox();
        button3 = new Button();
        lblNamaUser = new Label();
        hBox6 = new HBox();
        btnCari = new Button();
        fontAwesomeIcon2 = new FontAwesomeIcon();
        txtCari = new TextField();
        panelNotFound = new HBox();
        vBox2 = new VBox();
        vBox3 = new VBox();
        label2 = new Label();
        lblAnimeNotFound = new Label();
        label3 = new Label();
        panelFound = new HBox();
        vBox4 = new VBox();
        vBox5 = new VBox();
        imageAnimeFound = new ImageView();
        lblAnimeFoundJudul = new Label();
        button4 = new Button();

        setId("AnchorPane");
        setPrefHeight(644.0);
        setPrefWidth(1066.0);
        setStyle("-fx-background-color: transparent;");
        getStyleClass().add("mainFxmlClass");

        homePanel.setHbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.ALWAYS);
        homePanel.setLayoutX(134.0);
        homePanel.setLayoutY(67.0);
        homePanel.setPrefHeight(577.0);
        homePanel.setPrefWidth(932.0);
        homePanel.setVbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.ALWAYS);
        
        //inisial
        kolomContainer = new HBox();
        cotainer = new VBox();
        gambar = new ImageView();
        judul = new Label();
        tombol = new Button();
        

        //membuat kontainer
        barisKontainer.setPrefHeight(1120.0);
        barisKontainer.setPrefWidth(919.0);
        
        //membuat baris container
        kolomContainer.setPrefHeight(340.0);
        kolomContainer.setPrefWidth(861.0);
        
        //membuat kolom container
        cotainer.setAlignment(javafx.geometry.Pos.CENTER);
        cotainer.setPrefHeight(245.0);
        cotainer.setPrefWidth(242.0);
        
        //untuk tumbnail
        gambar.setFitHeight(258.0);
        gambar.setFitWidth(164.0);
        gambar.setPickOnBounds(true);
        gambar.setPreserveRatio(true);
        gambar.setImage(new Image(getClass().getResource("../asset/tumbnail/Kaguya-sama%20wa%20Kokurasetai%20Tensai-tachi%20no%20Renai%20Zunousen.jpg").toExternalForm()));
        VBox.setMargin(gambar, new Insets(10.0, 0.0, 20.0, 0.0));

        //untuk judul
        judul.setAlignment(javafx.geometry.Pos.CENTER);
        judul.setPrefHeight(29.0);
        judul.setPrefWidth(240.0);
        judul.setText("Kaguya-sama : Love Is War");
        judul.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        judul.setWrapText(true);
        VBox.setMargin(judul, new Insets(0.0, 0.0, 10.0, 0.0));
        judul.setFont(new Font("Segoe UI", 16.0));
        
        //untuk tombol
        tombol.setMnemonicParsing(false);
        tombol.setOnAction(this::masukKeLinkView2);
        tombol.setPrefHeight(38.0);
        tombol.setPrefWidth(97.0);
        tombol.setStyle("-fx-text-fill: #000000; -fx-text-fill: white; -fx-background-color: #37c5cd;");
        tombol.setText("Tonton");
        VBox.setMargin(tombol, new Insets(0.0));
        tombol.setFont(new Font("Segoe UI", 20.0));
        //memasukan gambar, judul, tombol ke dalam container
        cotainer.getChildren().add(gambar);
        cotainer.getChildren().add(judul);
        cotainer.getChildren().add(tombol);
        //memasukan container ke dalam kolom kontainer
        kolomContainer.getChildren().add(cotainer);
        //memasukan kolom container kedalam baris kontainer
        barisKontainer.getChildren().add(kolomContainer);
        //atur margin-bottom 
        VBox.setMargin(kolomContainer, new Insets(0.0, 0.0, 50.0, 0.0));

        
        
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setPrefHeight(293.0);
        vBox.setPrefWidth(216.0);

        imageView.setFitHeight(369.0);
        imageView.setFitWidth(164.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("../asset/tumbnail/Kimi%20No%20Na%20Wa.jpg").toExternalForm()));
        VBox.setMargin(imageView, new Insets(10.0, 0.0, 20.0, 0.0));

        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setPrefHeight(29.0);
        label.setPrefWidth(240.0);
        label.setText("Kimi No Na Wa");
        label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label.setWrapText(true);
        label.setFont(new Font("Segoe UI", 16.0));

        button.setMnemonicParsing(false);
        button.setOnAction(this::masukKeLinkView1);
        button.setPrefHeight(38.0);
        button.setPrefWidth(109.0);
        button.setStyle("-fx-text-fill: #000000; -fx-text-fill: white; -fx-background-color: #37c5cd;");
        button.setText("Tonton");
        button.setWrapText(true);
        VBox.setMargin(button, new Insets(12.0, 0.0, 0.0, 0.0));
        button.setFont(new Font("Segoe UI", 20.0));

        vBox0.setAlignment(javafx.geometry.Pos.CENTER);
        vBox0.setLayoutX(10.0);
        vBox0.setLayoutY(10.0);
        vBox0.setPrefHeight(245.0);
        vBox0.setPrefWidth(242.0);

        imageView0.setFitHeight(258.0);
        imageView0.setFitWidth(164.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("../asset/tumbnail/Charlotte.jpg").toExternalForm()));
        VBox.setMargin(imageView0, new Insets(10.0, 0.0, 20.0, 0.0));

        label0.setAlignment(javafx.geometry.Pos.CENTER);
        label0.setPrefHeight(29.0);
        label0.setPrefWidth(235.0);
        label0.setText("Charlotte");
        label0.setWrapText(true);
        VBox.setMargin(label0, new Insets(0.0, 0.0, 10.0, 0.0));
        label0.setFont(new Font("Segoe UI", 16.0));

        button0.setMnemonicParsing(false);
        button0.setOnAction(this::masukKeLinkView3);
        button0.setPrefHeight(38.0);
        button0.setPrefWidth(97.0);
        button0.setStyle("-fx-text-fill: #000000; -fx-text-fill: white; -fx-background-color: #37c5cd;");
        button0.setText("Tonton");
        button0.setFont(new Font("Segoe UI", 20.0));
        VBox.setMargin(kolomContainer, new Insets(0.0, 0.0, 50.0, 0.0));
        homePanel.setContent(barisKontainer);
        homePanel.setPadding(new Insets(30.0, 0.0, 0.0, 0.0));

        vBox1.setPrefHeight(644.0);
        vBox1.setPrefWidth(135.0);
        vBox1.setStyle("-fx-background-color: #37c5cd;");

        hBox.setAlignment(javafx.geometry.Pos.CENTER);
        hBox.setPrefHeight(100.0);
        hBox.setPrefWidth(200.0);

        label1.setRotate(10.0);
        label1.setText("Ans");
        label1.setTextFill(javafx.scene.paint.Color.WHITE);
        label1.setFont(new Font("Segoe UI", 39.0));

        hBox0.setAlignment(javafx.geometry.Pos.CENTER);
        hBox0.setPrefHeight(71.0);
        hBox0.setPrefWidth(150.0);
        hBox0.setStyle("-fx-background-color: #3ba1b8;");

        button1.setMnemonicParsing(false);
        button1.setOnAction(this::kembaliKeHomePanel);
        button1.setStyle("-fx-background-color: transparent;");
        button1.setFont(new Font("Segoe UI", 20.0));

        button1.setGraphic(fontAwesomeIcon);

        hBox1.setAlignment(javafx.geometry.Pos.CENTER);
        hBox1.setLayoutX(10.0);
        hBox1.setLayoutY(110.0);
        hBox1.setPrefHeight(71.0);
        hBox1.setPrefWidth(150.0);

        button2.setMnemonicParsing(false);
        button2.setStyle("-fx-background-color: transparent;");
        button2.setFont(new Font("Segoe UI", 20.0));

        button2.setGraphic(fontAwesomeIcon0);

        hBox2.setAlignment(javafx.geometry.Pos.CENTER);
        hBox2.setLayoutX(10.0);
        hBox2.setLayoutY(110.0);
        hBox2.setPrefHeight(71.0);
        hBox2.setPrefWidth(150.0);

        btnKeluar.setMnemonicParsing(false);
        btnKeluar.setOnAction(this::btnKeluar);
        btnKeluar.setStyle("-fx-background-color: transparent;");

        fontAwesomeIcon1.setFocusTraversable(true);
        btnKeluar.setGraphic(fontAwesomeIcon1);

        hBox3.setAlignment(javafx.geometry.Pos.CENTER);
        hBox3.setLayoutX(10.0);
        hBox3.setLayoutY(181.0);
        hBox3.setPrefHeight(71.0);
        hBox3.setPrefWidth(150.0);

        hBox4.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        hBox4.setLayoutX(141.0);
        hBox4.setLayoutY(4.0);
        hBox4.setPrefHeight(56.0);
        hBox4.setPrefWidth(925.0);

        hBox5.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        hBox5.setPrefHeight(56.0);
        hBox5.setPrefWidth(650.0);

        button3.setAlignment(javafx.geometry.Pos.BOTTOM_CENTER);
        button3.setContentDisplay(javafx.scene.control.ContentDisplay.BOTTOM);
        button3.setMnemonicParsing(false);
        button3.setOnAction(this::btnGantiAkun);
        button3.setPrefHeight(25.0);
        button3.setPrefWidth(103.0);
        button3.setStyle("-fx-background-color: transparent;");
        button3.setText("Ganti akun?");
        button3.setTextFill(javafx.scene.paint.Color.valueOf("#b90505"));
        HBox.setMargin(button3, new Insets(10.0, 0.0, 0.0, 0.0));

        lblNamaUser.setPrefHeight(24.0);
        lblNamaUser.setPrefWidth(518.0);
        lblNamaUser.setText("Nama User");
        HBox.setMargin(lblNamaUser, new Insets(0.0, 0.0, -1.0, 0.0));
        lblNamaUser.setFont(new Font("Segoe UI", 20.0));

        hBox6.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        hBox6.setPrefHeight(56.0);
        hBox6.setPrefWidth(346.0);

        btnCari.setMnemonicParsing(false);
        btnCari.setOnAction(this::showTxtCari);
        btnCari.setStyle("-fx-background-color: transparent;");

        btnCari.setGraphic(fontAwesomeIcon2);

        txtCari.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        txtCari.setPrefHeight(38.0);
        txtCari.setPrefWidth(0.0);
        txtCari.setPromptText("Judul anime");
        txtCari.setStyle("-fx-background-color: transparent; -fx-border-color: #37c5cd; -fx-border-width: 0;");
        txtCari.setFont(new Font("Segoe UI", 20.0));
        hBox4.setPadding(new Insets(0.0, 15.0, 0.0, 0.0));

        panelNotFound.setAlignment(javafx.geometry.Pos.CENTER);
        panelNotFound.setLayoutX(174.0);
        panelNotFound.setLayoutY(125.0);
        panelNotFound.setPrefHeight(285.0);
        panelNotFound.setPrefWidth(866.0);
        panelNotFound.setVisible(false);

        vBox2.setPrefHeight(443.0);
        vBox2.setPrefWidth(865.0);

        vBox3.setAlignment(javafx.geometry.Pos.CENTER);
        vBox3.setPrefHeight(285.0);
        vBox3.setPrefWidth(868.0);

        label2.setStyle("-fx-text-fill: #37c5cd;");
        label2.setText("Anime");
        label2.setFont(new Font("Segoe UI", 40.0));

        lblAnimeNotFound.setStyle("-fx-text-fill: #37c5cd;");
        lblAnimeNotFound.setText("'X'");
        lblAnimeNotFound.setFont(new Font("Segoe UI", 40.0));

        label3.setStyle("-fx-text-fill: #37c5cd;");
        label3.setText("Tidak Ditemukan.");
        label3.setFont(new Font("Segoe UI", 40.0));

        panelFound.setAlignment(javafx.geometry.Pos.CENTER);
        panelFound.setLayoutX(184.0);
        panelFound.setLayoutY(135.0);
        panelFound.setPrefHeight(285.0);
        panelFound.setPrefWidth(866.0);
        panelFound.setVisible(false);

        vBox4.setPrefHeight(299.0);
        vBox4.setPrefWidth(850.0);

        vBox5.setAlignment(javafx.geometry.Pos.CENTER);
        vBox5.setPrefHeight(299.0);
        vBox5.setPrefWidth(537.0);

        imageAnimeFound.setFitHeight(258.0);
        imageAnimeFound.setFitWidth(164.0);
        imageAnimeFound.setPickOnBounds(true);
        imageAnimeFound.setPreserveRatio(true);

        lblAnimeFoundJudul.setAlignment(javafx.geometry.Pos.CENTER);
        lblAnimeFoundJudul.setPrefHeight(32.0);
        lblAnimeFoundJudul.setPrefWidth(276.0);
        lblAnimeFoundJudul.setText("Kaguya-sama love is war");
        lblAnimeFoundJudul.setTextAlignment(javafx.scene.text.TextAlignment.JUSTIFY);
        lblAnimeFoundJudul.setWrapText(true);
        VBox.setMargin(lblAnimeFoundJudul, new Insets(10.0, 0.0, 20.0, 0.0));
        lblAnimeFoundJudul.setFont(new Font("Segoe UI", 25.0));

        button4.setMnemonicParsing(false);
        button4.setOnAction(this::masukKeLinkViewCari);
        button4.setPrefHeight(38.0);
        button4.setPrefWidth(97.0);
        button4.setStyle("-fx-text-fill: #000000; -fx-text-fill: white; -fx-background-color: #37c5cd;");
        button4.setText("Tonton");
        VBox.setMargin(button4, new Insets(0.0));
        button4.setFont(new Font("Segoe UI", 20.0));
        
        
        
        vBox.getChildren().add(imageView);
        vBox.getChildren().add(label);
        vBox.getChildren().add(button);
        kolomContainer.getChildren().add(vBox);
        vBox0.getChildren().add(imageView0);
        vBox0.getChildren().add(label0);
        vBox0.getChildren().add(button0);
        kolomContainer.getChildren().add(vBox0);
        
        getChildren().add(homePanel);
        hBox.getChildren().add(label1);
        vBox1.getChildren().add(hBox);
        hBox0.getChildren().add(button1);
        vBox1.getChildren().add(hBox0);
        hBox1.getChildren().add(button2);
        vBox1.getChildren().add(hBox1);
        hBox2.getChildren().add(btnKeluar);
        vBox1.getChildren().add(hBox2);
        vBox1.getChildren().add(hBox3);
        getChildren().add(vBox1);
        hBox5.getChildren().add(button3);
        hBox5.getChildren().add(lblNamaUser);
        hBox4.getChildren().add(hBox5);
        hBox6.getChildren().add(btnCari);
        hBox6.getChildren().add(txtCari);
        hBox4.getChildren().add(hBox6);
        getChildren().add(hBox4);
        vBox3.getChildren().add(label2);
        vBox3.getChildren().add(lblAnimeNotFound);
        vBox3.getChildren().add(label3);
        vBox2.getChildren().add(vBox3);
        panelNotFound.getChildren().add(vBox2);
        getChildren().add(panelNotFound);
        vBox5.getChildren().add(imageAnimeFound);
        vBox5.getChildren().add(lblAnimeFoundJudul);
        vBox5.getChildren().add(button4);
        vBox4.getChildren().add(vBox5);
        panelFound.getChildren().add(vBox4);
        getChildren().add(panelFound);

    }

    protected abstract void masukKeLinkView2(javafx.event.ActionEvent actionEvent);

    protected abstract void masukKeLinkView1(javafx.event.ActionEvent actionEvent);

    protected abstract void masukKeLinkView3(javafx.event.ActionEvent actionEvent);

    protected abstract void kembaliKeHomePanel(javafx.event.ActionEvent actionEvent);

    protected abstract void btnKeluar(javafx.event.ActionEvent actionEvent);

    protected abstract void btnGantiAkun(javafx.event.ActionEvent actionEvent);

    protected abstract void showTxtCari(javafx.event.ActionEvent actionEvent);

    protected abstract void masukKeLinkViewCari(javafx.event.ActionEvent actionEvent);

}
