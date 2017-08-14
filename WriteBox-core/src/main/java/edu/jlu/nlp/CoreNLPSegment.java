package edu.jlu.nlp;
import java.util.*;
import java.util.Map;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class CoreNLPSegment {

	public static void main(String[] args) {

		StanfordCoreNLP pipeline = new StanfordCoreNLP("CoreNLP-chinese.properties");
		
		Annotation annotation;
		annotation = new Annotation("美国总统奥巴马在纽约白宫说微软占市场份额的20%。中国主席习近平在北京故宫说移动占英镑份额30%。");
		pipeline.annotate(annotation);
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
				
		Map<Vector<Tokens>,Vector<Tokens>> Sentences=new HashMap<Vector<Tokens>,Vector<Tokens>>();
		
		for(int i=0;i<sentences.size();i++){
			CoreMap sentence = sentences.get(i);
			List<CoreLabel> tokens = sentence.get(CoreAnnotations.TokensAnnotation.class);
			System.out.println("#Sentence"+(i+1));
			Vector<Tokens> relations=new Vector<Tokens>();
			Vector<Tokens> entitys=new Vector<Tokens>();
			for (int j=0;j<tokens.size();j++) {
				String word = tokens.get(j).getString(TextAnnotation.class);
				String pos = tokens.get(j).getString(PartOfSpeechAnnotation.class);
				String ner = tokens.get(j).getString(NamedEntityTagAnnotation.class);
				if(ner.equals("GPE")||ner.equals("PERSON")||ner.equals("ORG")){
					Tokens token=new Tokens(word,ner,j+1);
					entitys.addElement(token);
				}else if(pos.equals("NN")||pos.equals("VV")){
					Tokens token=new Tokens(word,pos,j+1);
					relations.addElement(token);
				}
			}
			Sentences.put(relations, entitys);
		}
		
		//测试输出
		Iterator iter=Sentences.entrySet().iterator(); 
		while(iter.hasNext()){  
			Map.Entry<Vector<Tokens>,Vector<Tokens>> entry=(Map.Entry<Vector<Tokens>,Vector<Tokens>>)iter.next(); 
	        Vector<Tokens> re=entry.getKey();
	        Vector<Tokens> en=entry.getValue();
	        for(int k=0;k<re.size();k++){
	            re.get(k).print();
	        }
	        System.out.println("-------");
	        for(int k=0;k<en.size();k++){
	            	en.get(k).print();
	        }
	        System.out.println("---------------------------------");
	   }  	 
	} 
}

class Tokens{
	Tokens(String word,String tag,int loc){
		this.word=word;
		this.tag=tag;
		this.loc=loc;
	}
	String word;
	String tag;
	int loc;
	void print(){
		System.out.println(word+"\t"+tag+"\t"+loc);
	}
}