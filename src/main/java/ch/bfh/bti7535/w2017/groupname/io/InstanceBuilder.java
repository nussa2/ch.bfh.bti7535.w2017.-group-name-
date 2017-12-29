package ch.bfh.bti7535.w2017.groupname.io;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * Gibt Instances nach dem gewünschten Schema zurück
 */
public class InstanceBuilder {

    private ArrayList<Attribute> attributes = new ArrayList<Attribute>();
    private String relationName;
    private Instances dataSet;
    private boolean setClassIndex = false;
    private List<List<Object>> rawData = new ArrayList<>();

    public InstanceBuilder() {
    }

    /**
     * Setzen des Relationsnamens
     *
     * @param relationName neuer Name
     * @return
     */
    public InstanceBuilder setRelationName(String relationName) {
        this.relationName = relationName;
        return this;
    }

    /**
     * Initiieren des ClassIndex
     */
    public void initClassIndex(){
        this.setClassIndex = true;
    }

    /**
     * Effektives Bereitstellen der Daten
     *
     * @return
     */
    public Instances build() {
        System.out.println("started building instances");
        dataSet = new Instances(relationName, attributes,0);
        if (setClassIndex) {
            dataSet.setClassIndex(dataSet.numAttributes() - 1);
        }
        addData();
        return dataSet;

    }

    /**
     * Hinzufügen eines Zahlen-Attributes
     * @param name
     * @return
     */
    public InstanceBuilder addNumericAttribute(String name){
        attributes.add(new Attribute(name));
        return this;
    }

    /**
     * Hinzufügen eines Nominal-Attributes
     * @param name
     * @param values
     * @return
     * @throws IllegalAccessException
     */
    public InstanceBuilder addNominalAttribute(String name, String ... values) throws IllegalAccessException {
        if (values.length < 1 ){
            throw new IllegalAccessException("too few values");
        }
        attributes.add(new Attribute(name, Arrays.asList(values)));
        return this;
    }

    /**
     * Hinzufügen der Daten
     * @param values
     * @return
     * @throws IllegalAccessException
     */
    public InstanceBuilder addData(List<Object> values) throws IllegalAccessException {

        if (values.size() != attributes.size()){
            throw new IllegalAccessException("numbers of attributes not matching with given values");
        }
        rawData.add(values);
        return this;
    }

    /**
     * Hinzufügen der Daten für interne Verwendung
     */
    private void addData() {
        for (List<Object> values:rawData) {

            double[] vals = new double [attributes.size()];


            int i = 0;
            for (Object value: values) {
                if (value instanceof Double || value instanceof Integer){
                    vals[i] = (double) value;
                } else if(checkIfNomAttrContainsValue(dataSet.attribute(i),value)){
                    vals[i] = dataSet.attribute(i).indexOfValue((String) value);
                } else {
                    dataSet.attribute(i).addStringValue(value.toString());
                }
                i++;
            }

            dataSet.add(new DenseInstance(1.0, vals));
        }
    }

    /**
     * Überprüft, ob das Nominale Attribut auch ein Value enthält
     * @param attribute
     * @param value
     * @return
     */
    private boolean checkIfNomAttrContainsValue(Attribute attribute, Object value){
        Enumeration<Object> values = attribute.enumerateValues();
        while (values.hasMoreElements()){
            if (values.nextElement().equals(value)){
                return true;
            }
        }
        return false;
    }
}
