package sample.Table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import sample.ConnectTable;
import sample.Modele.ModelKatedra;
import sample.Modele.ModelPlan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableKatedra {
    public TableKatedra() {
    }
    ObservableList<ModelKatedra> oblistKatedra = FXCollections.observableArrayList();
    public void setKatedra(){
        try {
            Connection conn = ConnectTable.connectdb();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `katedra`");
            while (rs.next()){
                oblistKatedra.add(new ModelKatedra(rs.getString("id_katedry"),
                        rs.getString("id_wydzial"),
                        rs.getString("nazwa_katedry")));

            }
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void setKatedraNazwy(){
        oblistKatedra.clear();
        try {
            Connection conn = ConnectTable.connectdb();
            ResultSet rs = conn.createStatement().executeQuery("SELECT katedra.id_katedry,wydzial.nazwa_wydzialu,katedra.nazwa_katedry FROM `katedra` INNER JOIN wydzial ON katedra.id_wydzial=wydzial.id_wydzial");
            while (rs.next()){
                oblistKatedra.add(new ModelKatedra(rs.getString("katedra.id_katedry"),
                        rs.getString("wydzial.nazwa_wydzialu"),
                        rs.getString("katedra.nazwa_katedry")));

            }
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }
   /* public void setDodaj( String idprzedS, String idtypS, String nazwaS, String ectsS ,String godzinyS ){

        oblist.clear();
        try {
            Connection conn = ConnectTable.connectdb();
            conn.createStatement().executeUpdate("INSERT INTO `przedmioty` (`Id_przedmiot`, `id_typ_zajec`, `nazwa_przedmiotu`, `ects`, `godziny`) VALUES ('"+idprzedS+"','"+idtypS+"', '"+nazwaS+"','"+ectsS+"','"+godzinyS+"')");
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `przedmioty`");
            while (rs.next()){
                oblist.add(new ModelPrzedmioty(rs.getString("Id_przedmiot"),
                        rs.getString("id_typ_zajec"),
                        rs.getString("nazwa_przedmiotu"),
                        rs.getString("ects"),
                        rs.getString("godziny")));

            }
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            infoBox("zle wypelniona baza danych",null, "Błąd");
        }

    }

    public void setUsun(String idprzedS){
        try {
            Connection conn = ConnectTable.connectdb();
            conn.createStatement().executeUpdate("DELETE FROM `przedmioty` WHERE `przedmioty`.`Id_przedmiot` = "+idprzedS);
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `przedmioty`");
            while (rs.next()){
                oblist.add(new ModelPrzedmioty(rs.getString("Id_przedmiot"),
                        rs.getString("id_typ_zajec"),
                        rs.getString("nazwa_przedmiotu"),
                        rs.getString("ects"),
                        rs.getString("godziny")));

            }
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            infoBox("Błąd",null, "zle wypelniona baza danych");
        }}*/

    public ObservableList<ModelKatedra> getOblist(){
        return oblistKatedra;
    }
    public void setOblistClear(){
        oblistKatedra.clear();
    }
    public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();}
}


