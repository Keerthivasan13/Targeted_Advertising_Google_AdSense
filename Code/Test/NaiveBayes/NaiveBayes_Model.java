import java.util.ArrayList;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Keerthi
 */
public class Engine {
    private static Instances PageKeywordRecords;
    private static NaiveBayes PageKeywordModel;
    private static Instances AdKeywordRecords;
    private static NaiveBayes AdKeywordModel;
	
    private static void CreateWebPageModel()
    {
        InstanceQuery query;
        try{
			query = new InstanceQuery();
			query.setUsername("root");
			query.setPassword("");
			query.setQuery("select Primary_Category,Secondary_category,Keyword from pagekeyword");
			PageKeywordRecords= query.retrieveInstances();
			PageKeywordRecords.setClassIndex(PageKeywordRecords.numAttributes()-2);
			PageKeywordModel= new NaiveBayes();
			PageKeywordModel.buildClassifier(PageKeywordRecords);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
	
    private static void CreateAdModel()
    {
        InstanceQuery query;
        try{
			query = new InstanceQuery();
			query.setUsername("root");
			query.setPassword("");
			query.setQuery("select c.Name as PC,b.Name as SC ,Keyword from adkeyword a,adcategory b,adcategory c where a.Category=b.Id and b.Parent_Id=c.Id and c.Parent_Id=0");
			AdKeywordRecords= query.retrieveInstances();
			AdKeywordRecords.setClassIndex(AdKeywordRecords.numAttributes()-2);
			AdKeywordModel= new NaiveBayes();
			AdKeywordModel.buildClassifier(AdKeywordRecords);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
	
    private static void EvaluateModel(NaiveBayes model,Instances data)
    { 
        try{
			Evaluation eval = new Evaluation(data);
			eval.evaluateModel(model, data);
			System.out.println(eval.toSummaryString());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
	
    public static void main(String[] args) {
        try{
			ArrayList<String> list= new ArrayList<String>();
			// Test
			list.add("forum");
			list.add("kilo");
			list.add("asd");
			list.add("rummy");
			String pc="Online Communities";
			CreateWebPageModel();
			EvaluateModel(PageKeywordModel,PageKeywordRecords);
			CreateAdModel();
			EvaluateModel(AdKeywordModel,AdKeywordRecords);
			Classifier classifier = new Classifier();       
		    // System.out.println(classifier.classifyInstance(PageKeywordModel,PageKeywordRecords,list,pc));
			System.out.println(classifier.classifyInstance(AdKeywordModel,AdKeywordRecords,list,pc));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
}
}
