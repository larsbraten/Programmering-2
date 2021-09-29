package com.company;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class UserInterface extends Application {
    //Content

    MemberArchive memberArchive = new MemberArchive();
    Alert memberDetails = new Alert(Alert.AlertType.INFORMATION);
    TextInputDialog pointsToAdd = new TextInputDialog();

    //Buttons
    Button confirmAction = new Button("Confirm");
    Button cancel = new Button("Cancel");

    //Tables
    TableColumn memberNo = new TableColumn("Member ID");
    TableColumn memberName = new TableColumn("Name");
    TableColumn memberStatus = new TableColumn("Status");
    TableColumn points = new TableColumn("Points");
    TableColumn enrolledDate = new TableColumn("Date of enrollment");
    TableView<BonusMember> membersTable = new TableView<>();

    //Menus
    MenuItem newMember = new MenuItem("Add member");
    MenuItem deleteMember = new MenuItem("Delete member");
    MenuItem verifyMember = new MenuItem("Verify member");
    MenuItem memberDetailsMenuItem = new MenuItem("Details");
    MenuItem addPoints = new MenuItem("Add points");
    ContextMenu tableMenu = new ContextMenu(newMember, deleteMember, verifyMember,
            memberDetailsMenuItem, addPoints);

    //Labels
    Label firstNameLabel = new Label("First Name: ");
    Label surnameLabel = new Label("Surname: ");
    Label emailLabel = new Label("Email: ");
    Label passwordLabel = new Label("Password: ");

    TextField firstNameTextField = new TextField();
    TextField surnameTextField = new TextField();
    TextField emailTextField = new TextField();
    TextField passwordTextField = new TextField();

    //Logger
    public static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        if (primaryStage.getScene() == null) {
            logger.info("Initiating component attributes.");
            memberNo.setCellValueFactory(new PropertyValueFactory<>("MemberNo"));
            memberNo.setMinWidth(166);
            memberName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            memberName.setMinWidth(166);
            memberStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            memberStatus.setMinWidth(166);
            points.setCellValueFactory(new PropertyValueFactory<>("points"));
            points.setMinWidth(166);
            enrolledDate.setCellValueFactory(new PropertyValueFactory<>("EnrolledDate"));
            enrolledDate.setMinWidth(166);
            membersTable.getColumns().addAll(memberNo, memberName, memberStatus, points, enrolledDate);
            membersTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            membersTable.setContextMenu(tableMenu);
            membersTable.setTableMenuButtonVisible(true);
            memberArchive.newMember(new Personals("Freddie", "Mercury",
                    "hihihoho@gmail.com", "testtt"), LocalDate.now());
            primaryStage.setScene(GenerateMainScene());
            primaryStage.setTitle("MemberRegister <Right click for options>");
            primaryStage.setResizable(false);
            memberDetails.setResizable(false);
            memberDetails.initModality(Modality.APPLICATION_MODAL);
            logger.info("Program initiated. Running...");
        }
        newMember.setOnAction(b -> {
            primaryStage.setScene(GenerateAddMemberScene());
            primaryStage.setTitle("Add member");
            logger.info("Loading new user scene");
        });
        cancel.setOnAction(b -> {
            primaryStage.setScene(GenerateMainScene());
            primaryStage.setTitle("MemberRegister <Right click for options>");
            logger.info("New user attempt cancelled. Loading main scene...");
        });
        deleteMember.setOnAction(b -> {
            logger.info("Deleting user...");
            logger.info("Deleted users:\n" +
                    memberArchive.removeMember(membersTable.getSelectionModel().getSelectedItems()));
            primaryStage.setScene(GenerateMainScene());
        });
        verifyMember.setOnAction(b -> {
            logger.info("Validating user rank eligibility...");
            memberArchive.checkMembers(LocalDate.now());
            primaryStage.setScene(GenerateMainScene());
        });
        confirmAction.setOnAction(b -> {
            logger.info("Validating user data...");
            if (!firstNameTextField.getText().isBlank() &&
                    !surnameTextField.getText().isBlank() &&
                    !emailTextField.getText().isBlank() &&
                    !passwordTextField.getText().isBlank()) {
                logger.info("Constructing new member...");
                memberArchive.newMember(new Personals(surnameTextField.getText(),
                        firstNameTextField.getText(),
                        emailTextField.getText(),
                        passwordTextField.getText()), LocalDate.now());
                logger.info("New member constructed. Returning to main scene.");
                primaryStage.setScene(GenerateMainScene());
                primaryStage.setTitle("MemberRegister <Right click for options>");
            } else {
                logger.warn("User data invalid.");
            }
        });
        memberDetailsMenuItem.setOnAction(b -> {
            logger.info("Retrieving user details...");
            try {
                BonusMember selectedMember = membersTable.getSelectionModel().getSelectedItem();
                memberDetails.setContentText(selectedMember.toString());
                memberDetails.setTitle(selectedMember.getClass().getSimpleName());
                memberDetails.setHeaderText(selectedMember.getName());
                memberDetails.show();
                logger.info("User details found.");
            } catch (NullPointerException e) {
                logger.error("User specific action attempted, but no user selected.");
            }

        });
        addPoints.setOnAction(b -> {
            try {
                BonusMember bonusMember = membersTable.getSelectionModel().getSelectedItem();
                pointsToAdd.setHeaderText("Points to add");
                pointsToAdd.setTitle(bonusMember.getName());
                pointsToAdd.showAndWait();
                int userData = Integer.parseInt(pointsToAdd.getResult());
                memberArchive.registerPoints(bonusMember.getMemberNo(), userData);
            } catch (NumberFormatException e) {
                logger.error("Invalid user input. Data must be parsable numerical.");
            } catch (NullPointerException e) {
                logger.error("User specific action attempted, but no user selected.");
            } finally {
                membersTable.refresh();
            }
        });

        primaryStage.show();
    }

    public Scene GenerateAddMemberScene() {
        firstNameLabel.setLayoutX(15);
        firstNameLabel.setLayoutY(15);
        surnameLabel.setLayoutX(15);
        surnameLabel.setLayoutY(65);
        emailLabel.setLayoutX(15);
        emailLabel.setLayoutY(115);
        passwordLabel.setLayoutX(15);
        passwordLabel.setLayoutY(165);
        Group labelGroup = new Group(firstNameLabel, surnameLabel, emailLabel, passwordLabel);

        firstNameTextField.setPrefSize(150, 25);
        firstNameTextField.setLayoutX(100);
        firstNameTextField.setLayoutY(15);
        firstNameTextField.promptTextProperty().set("Firstname");
        surnameTextField.setPrefSize(150, 25);
        surnameTextField.setLayoutX(100);
        surnameTextField.setLayoutY(65);
        surnameTextField.promptTextProperty().set("Surname");
        emailTextField.setPrefSize(150, 25);
        emailTextField.setLayoutX(100);
        emailTextField.setLayoutY(115);
        emailTextField.promptTextProperty().set("E-mail");
        passwordTextField.setPrefSize(150, 25);
        passwordTextField.setLayoutX(100);
        passwordTextField.setLayoutY(165);
        passwordTextField.promptTextProperty().set("Password");
        Group textFieldGroup = new Group(firstNameTextField, surnameTextField,
                emailTextField, passwordTextField);

        confirmAction.setLayoutY(225);
        confirmAction.setLayoutX(25);
        confirmAction.setPrefSize(75, 25);
        cancel.setLayoutY(225);
        cancel.setLayoutX(150);
        cancel.setPrefSize(75, 25);
        Group buttonGroup = new Group(confirmAction, cancel);
        buttonGroup.setLayoutX(25);

        Group scenery = new Group(labelGroup, textFieldGroup, buttonGroup);
        return new Scene(scenery, 300, 275);
    }

    public Scene GenerateMainScene() {
        //Table config
        membersTable.setItems(memberArchive.getObservableList());
        Group memberOverview = new Group(membersTable);
        return new Scene(memberOverview);
    }
}
