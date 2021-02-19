module org.alb.eightbitpiano {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.media;
	requires javafx.base;
	requires java.desktop;
	requires java.sql;

    opens org.alb.eightbitpiano to javafx.fxml;
    exports org.alb.eightbitpiano;
}