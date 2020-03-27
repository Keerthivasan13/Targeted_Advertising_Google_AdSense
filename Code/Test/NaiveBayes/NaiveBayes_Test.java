import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.FastVector.FastVectorEnumeration;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.DatabaseConnection;
import weka.core.converters.DatabaseConverter;
import weka.core.converters.DatabaseLoader;
import weka.core.converters.DatabaseSaver;
import weka.core.converters.FileSourcedConverter;
import weka.experiment.InstanceQuery;

public class AdClassifier {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InstanceQuery query;
		try {
			query = new InstanceQuery();
			query.setUsername("root");
			query.setPassword("");
			query.setQuery("select a.keywords,b.Name from advertisement a, adcategory b where a.Category=b.Id");
			Instances data = query.retrieveInstances();
			data.setClassIndex(data.numAttributes() - 1);
			//   data.
			Attribute primaryCat=data.attribute(data.numAttributes() - 3);
			FastVector fvNominalVal1 = new FastVector(primaryCat.numValues());
			
			for(int i=0;i<primaryCat.numValues();i++)
			{
				fvNominalVal1.addElement(primaryCat.value(i));
			}

			Attribute secondaryCat=data.attribute(data.numAttributes() - 2);
			FastVector fvNominalVal2 = new FastVector(secondaryCat.numValues());
			
			for(int i=0;i<secondaryCat.numValues();i++)
			{
				fvNominalVal2.addElement(secondaryCat.value(i));
			}

			primaryCat = new Attribute("PrimaryCategory", fvNominalVal1);
			secondaryCat = new Attribute("SecondaryPrimaryCategory", fvNominalVal2);
			FastVector key=new FastVector(1);
			key.addElement("energy");
			Attribute keyword= new Attribute("Keyword",key);


			FastVector fvWekaAttributes = new FastVector(3);
			fvWekaAttributes.addElement(primaryCat);
			fvWekaAttributes.addElement(secondaryCat);
			fvWekaAttributes.addElement(keyword);

			Instances newData = new Instances("Rel", fvWekaAttributes, 1);
			newData.setClassIndex(newData.numAttributes()-2);

			Instance iExample = new Instance(3);
			iExample.setValue((Attribute)fvWekaAttributes.elementAt(0), "Science & Technology");
			iExample.setValue((Attribute)fvWekaAttributes.elementAt(2),"energy");    
			newData.add(iExample);
			
			//Secondary Category is Set as the Target Attribute
			//Model is created here
			// Evaluation train=classify(new BayesNet(),data);
			NaiveBayes model= new NaiveBayes();
			model.buildClassifier(data);                        
			Evaluation evaluation = new Evaluation(data);
			evaluation.evaluateModel(model, data);
			
			//   evaluation.evaluateModelOnce(model, iExample);
			// System.out.println(evaluation.evaluateModelOnce(model, iExample));
			//eval_train.crossValidateModel(model, data, 10, data.getRandomNumberGenerator(1));
			
			System.out.println(evaluation.toSummaryString());                  
			double clsLabel = model.classifyInstance(newData.instance(0));
			System.out.println("Class predicted: " + newData.classAttribute().value((int) clsLabel));
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
