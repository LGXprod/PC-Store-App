package model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Build {

	private ObservableList<Part> parts;
        private double total;

	public ObservableList<Part> getParts() {
		return this.parts;
	}

	public Build() {

		parts = FXCollections.observableArrayList();

	}

	public void addPart(Part part) {
		parts.add(part);
	}

	public boolean isValid() {
		return hasPartOfType("cpu") && hasPartOfType("motherboard") && hasPartOfType("memory") && hasPartOfType("case")
				&& hasPartOfType("storage");
	}

	public double totalPrice() {

		double sum = 0.0;

		for (Part p : parts)
			sum += p.getPrice();

		return sum;
	}

        public final double getTotal(){ return totalPrice(); }

	public boolean hasPartOfType(String type) {
		for (Part p : parts) {
			if (p.hasType(type))
				return true;
		}

		return false;
	}
        
        public List<String> typesMissing(){
            String[] checkType = new String[]{"cpu", "motherboard", "memory", "case", "storage"};
            List<String> missingParts = new ArrayList<String>();
            for (String type : checkType){
                if(!(hasPartOfType(type))){
                    missingParts.add(type);
                }
            }
            
            return missingParts;
        }

	public void remove(Part p) {
		this.parts.remove(p);
	}

	public void remove(List<Part> selectedItems) {

		this.parts.removeAll(selectedItems);

	}

	public void addParts(List<Part> selectedItems) {

		this.parts.addAll(selectedItems);

	}

}
