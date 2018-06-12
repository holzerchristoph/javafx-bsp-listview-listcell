package sample;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;
import java.util.Date;

// How should I render the person data in a listview?
class PersonCell extends ListCell<Main.Person>{
    static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY");
    static Background maleBG = new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(2), new Insets(2)));
    static Background femaleBG = new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(2), new Insets(2)));
    @Override
    protected void updateItem(Main.Person item, boolean empty) {
        super.updateItem(item, empty);
        if (empty){
            setText(""); // or setText("no content....")  or setText(null)
        }
        else{
            StringBuilder sb = new StringBuilder();
            String date="("+sdf.format(item.birthday)+")";
            sb.append(item.firstname).append(", ").append(item.surname).append(" "+date);
            setText(sb.toString());
            if (item.gender=='m')
                setBackground(maleBG);
            if (item.gender=='f')
                setBackground(femaleBG);
        }
    }
}


public class Main extends Application {

    // Person Inner Class
    class Person{
        String firstname;
        String surname;
        Date birthday;
        char gender;

        public Person(String firstname, String surname, Date birthday, char gender) {
            this.firstname = firstname;
            this.surname = surname;
            this.birthday = birthday;
            this.gender=gender;
        }

        /* first possibility to visualize firstname and surname into a listview */
        /*
        @Override
        public String toString() {
            return  firstname + ", " + surname;
        }
        */
    }

    ObservableList<Person> arr = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();

        ListView listView;
        root.setCenter(listView = new ListView());
        // populate the arraylist
        arr.add(new Person("mario", "rossi", new Date(), 'm'));
        arr.add(new Person("susi", "sorglos", new Date(),'f'));
        arr.add(new Person("max", "mustermann", new Date(),'m'));

        /* second possibility to set the layout of a cell*/
        listView.setCellFactory(item->new PersonCell());

        listView.setItems(arr);
        primaryStage.setTitle("listview example");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
