
package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    private ComboBox<Country> cmbStati;
    
    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	txtResult.clear();
    	int anno = 0;
    	
    	try {
    		anno = Integer.parseInt(txtAnno.getText());
    	} catch (NumberFormatException e) {
    		txtResult.setText("Inserire un anno numerico");
    		return;
    	}
    	
    	if(anno<0 || anno<1816 || anno>2006) {
    		txtResult.setText("Inserire un anno valido");
    		return;
    	}
    	
    	model.creaGrafo(anno);
    	
    	for(Country c : model.getCountries().values()) {
    		txtResult.appendText(c+" confina con "+model.getNumeroStatiConfinanti(c)+" paesi"
    				+ " e la sua componente connessa è: "+model.getComponenteConnessa(c)+"\n");
    	}
    	
    }
    
    @FXML
    void handleRaggiungibili(ActionEvent event) {
    	txtResult.clear();
    	int anno = 0;
    	Country c = cmbStati.getValue();
    	
    	try {
    		anno = Integer.parseInt(txtAnno.getText());
    	} catch (NumberFormatException e) {
    		txtResult.setText("Inserire un anno numerico");
    		return;
    	}
    	
    	if(anno<0 || anno<1816 || anno>2006) {
    		txtResult.setText("Inserire un anno valido");
    		return;
    	}
    	
    	if(c==null) {
    		txtResult.setText("Selezionare uno stato dalla box accanto");
    		return;
    	}
    	
    	for(Country cc : model.getRaggiungibili(c, anno)) {
    		txtResult.appendText(cc+"\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	for(Country c : model.getCountries().values()) {
    		cmbStati.getItems().add(c);
    	}
    }
}
