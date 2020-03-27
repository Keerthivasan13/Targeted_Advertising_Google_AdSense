import java.util.ArrayList;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instance;
import weka.core.Instances;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Keerthi
 */
class Pair
{
    public String category;
    public int count;
    public Pair(String a,int b)
    {
        category=a;
        count=b;
    }
}
public class Classifier {
	public static String classifyInstance(NaiveBayes model,Instances data,ArrayList<String> keywords,String primaryCategory) throws Exception
	{
        ArrayList<Pair> list= new ArrayList<Pair>();
        boolean found=false;
        Instance nd = new Instance(3);
        nd.setValue(data.attribute(0),primaryCategory);
        for(String word : keywords)
        {
            try{
                nd.setValue(data.attribute(2), word);
            }
            catch(Exception e)
            {
                continue;
            }
            data.add(nd);
            String n=data.classAttribute().value((int)model.classifyInstance(data.lastInstance()));
            for(Pair pair : list)
            {
                if(pair.category.equals(n))
                {
                    pair.count+=1;
                    found=true;
                    break;
                }
            }
            if(found==false)
            {
                list.add(new Pair(n,1));
            }
            found=false;
        }
        int max=0; String sc="";
        for(Pair pair : list)
        {
            if(pair.count>max)
            {
                max=pair.count;
                sc=pair.category;
            }
        }
        return sc;
    }
}
