/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svm;

/**
 *
 * @author fandi
 */
import java.io.IOException;
import java.util.TreeMap;
import libsvm.*;

public class LibSVMApp2 {

    public int predict(TreeMap<Integer, Double> input, String modelFile) {
        int predictedClass = 0;
        try {
            svm_model model = svm.svm_load_model(modelFile);

            svm_node[] x = new svm_node[input.size()];
            int i = 0;
            for (Integer attributeIndex : input.keySet()) {
                x[i] = new svm_node();
                x[i].index = attributeIndex;
                x[i].value = input.get(attributeIndex);
                i++;
            }
            predictedClass = (int) svm.svm_predict(model, x);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return predictedClass;
    }

    public String translateOccupation(int predictedClass) {
        String hasil = null;
        if (predictedClass == 0) {
            hasil = "unoccupied";
        } else if (predictedClass == 1) {
            hasil = "occupied";
        } else {
            hasil = "unknown";
        }
        return hasil;
    }
}
