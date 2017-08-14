package edu.jlu.nlp;

import java.io.*;
import java.util.HashMap;
import java.util.Vector;
//import edu.stanford.nlp.hcoref.data.CorefChain;
import edu.stanford.nlp.io.*;
import edu.stanford.nlp.pipeline.*;

/** This class demonstrates building and using a Stanford CoreNLP pipeline. */
  public class StanfordCoreNlpDemo {
	
	StanfordCoreNlpDemo(String fileName)throws IOException{
		handleFile(fileName);
	}
	
	void handleFile(String fileName)throws IOException{
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(fileName), "GB2312")); 
			String line; 
			StringBuilder contents=new StringBuilder(); 
			while ((line = reader.readLine()) != null) { 
				if(line.startsWith("Sentence")){
					HashMap<String,Integer> words=new HashMap<String,Integer>() ;
					while (!(line = reader.readLine()).startsWith("Sentence")){
						if(line.startsWith("root")||(line.startsWith("nn"))){
							int idx=line.indexOf(" ");
							int lIdx=line.lastIndexOf("-");
							int dx=line.indexOf(")");
							int nIdx=line.indexOf("(");
							int ndx=line.indexOf("-");
							Integer i=Integer.parseInt(line.substring(lIdx+1, dx));
							if(line.startsWith("root")){
								words.put(line.substring(idx+1,lIdx), i);
							}else{
								String t=line.substring(idx+1,lIdx)+line.substring(nIdx+1, ndx);
								words.put(t, i);
							}
						}
					}
					wordSet.addElement(words);
				}
				contents.append(line);
			} 
			reader.close();
	}
	
	Vector <HashMap<String,Integer>> wordSet=new Vector<HashMap<String,Integer>>(); 
	
	public static String readInput(String fileName,String charset)throws IOException{ 
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(
					new FileInputStream(fileName), charset)); 
			String line; 
			StringBuilder contents=new StringBuilder(); 
			while ((line = reader.readLine()) != null) { 
				//line = line.trim();
				if( line.trim().length() == 0 ) continue;
				contents.append(line);
			} 
			reader.close();
			return contents.toString();	
	}
	

  /** Usage: java -cp "*" StanfordCoreNlpDemo [inputFile [outputTextFile [outputXmlFile]]] */
	public static void main(String[] args) throws IOException {
    // set up optional output files
	  PrintWriter out;
	    if (args.length > 1) {
	      //OutputStreamWriter outstream = new OutputStreamWriter(new FileOutputStream(args[1]), "GB2312");
	      out = new PrintWriter(args[1]);
	    } else {
	      out = new PrintWriter(System.out);
	    }
	   
	    StanfordCoreNLP pipeline = new StanfordCoreNLP("CoreNLP-chinese.properties");

	    // Initialize an Annotation with some text to be annotated. The text is the argument to the constructor.
	    Annotation annotation;
	    if (args.length > 0) {
	      annotation = new Annotation(readInput(args[0],"GB2312"));
	      //System.out.println(readInput(args[0],"GB2312"));
	    } else {
	 
	    annotation = new Annotation("美国总统奥巴马在纽约白宫说微软占市场份额的20%");
	    }
	    // run all the selected Annotators on this text
	    pipeline.annotate(annotation);
	    // this prints out the results of sentence analysis to file(s) in good formats
	    pipeline.prettyPrint(annotation, out);

  }
} 

