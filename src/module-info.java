module jtext {
    requires java.logging;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.halfegg.jtext to javafx.fxml;
    opens com.halfegg.jtext.io to javafx.fxml;
    opens com.halfegg.jtext.util to javafx.fxml;

    exports com.halfegg.jtext;
    exports com.halfegg.jtext.io;
    exports com.halfegg.jtext.util;
}