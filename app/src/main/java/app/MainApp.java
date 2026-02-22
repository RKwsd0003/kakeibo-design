package app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        // ===== Top: month switch (dummy for now) =====
        Button prevMonth = new Button("◀");
        Label monthLabel = new Label("2026-02"); // ダミー。後で現在月にする
        Button nextMonth = new Button("▶");

        HBox topBar = new HBox(10, prevMonth, monthLabel, nextMonth);
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(10));
        topBar.setStyle("-fx-background-color: rgba(255,255,255,0.06);");

        // ===== Left pane: totals + list + add button =====
        // Totals bar (fixed/variable/total)
        Label fixedTotal = new Label("固定費: ¥0");
        Label variableTotal = new Label("変動費: ¥0");
        Label grandTotal = new Label("合計: ¥0");

        HBox totalsBar = new HBox(16, fixedTotal, variableTotal, grandTotal);
        totalsBar.setAlignment(Pos.CENTER_LEFT);
        totalsBar.setPadding(new Insets(10));
        totalsBar.setStyle("-fx-background-color: rgba(255,255,255,0.06); -fx-background-radius: 8;");

        // List (for now: placeholder table)
        TableView<Void> table = new TableView<>();
        table.setPlaceholder(new Label("明細がまだありません（右下＋で追加）"));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        TableColumn<Void, String> c1 = new TableColumn<>("購入日");
        TableColumn<Void, String> c2 = new TableColumn<>("カテゴリ");
        TableColumn<Void, String> c3 = new TableColumn<>("品名");
        TableColumn<Void, String> c4 = new TableColumn<>("値段");
        table.getColumns().addAll(c1, c2, c3, c4);

        // Add button bottom-right
        Button addBtn = new Button("＋");
        addBtn.setPrefSize(44, 44);
        addBtn.setStyle(
                "-fx-font-size: 18px; " +
                "-fx-background-radius: 22; " +
                "-fx-min-width: 44px; -fx-min-height: 44px;"
        );

        HBox bottomBar = new HBox(addBtn);
        bottomBar.setAlignment(Pos.CENTER_RIGHT);
        bottomBar.setPadding(new Insets(10));

        BorderPane leftPane = new BorderPane();
        leftPane.setTop(totalsBar);
        leftPane.setCenter(table);
        leftPane.setBottom(bottomBar);
        BorderPane.setMargin(totalsBar, new Insets(10, 10, 10, 10));
        BorderPane.setMargin(table, new Insets(0, 10, 0, 10));
        BorderPane.setMargin(bottomBar, new Insets(10));

        // ===== Right pane: pie chart placeholder =====
        VBox rightPane = new VBox(10);
        rightPane.setPadding(new Insets(10));
        rightPane.setPrefWidth(320);
        rightPane.setStyle("-fx-background-color: rgba(255,255,255,0.04);");
        rightPane.getChildren().addAll(
                new Label("円グラフ（予定地）"),
                new Label("カテゴリ別支出などを表示予定")
        );

        // ===== Center split =====
        SplitPane split = new SplitPane(leftPane, rightPane);
        split.setDividerPositions(0.65);

        // ===== Root =====
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(split);

        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Kakeibo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}