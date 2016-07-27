package com.tokenizer;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class WordTokenizer extends Tokenizer {

	protected CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);

	private List<String> tokenizedWords = new ArrayList<String>();

	@Override
	public boolean incrementToken() throws IOException {

		this.charTermAttribute.setEmpty();
		if (tokenizedWords.size() == 0) {
			return false;
		}
		if (this.position < tokenizedWords.size()) {
			this.charTermAttribute.append(tokenizedWords.get(this.position));
			this.position = this.position + 1;
			return true;
		} else {
			tokenizedWords.clear();
			this.position = 0;
			return false;
		}
	}

	public WordTokenizer(Reader reader) {
		super(reader);
	}

	public String stringToTokenize;

	protected int position = 0;

	@Override
	/**
	 * 
	 * This is in case of elastic search.. For lucence endixing pls look into lucence example with
	tokenstream help defination.rar in drive inside lucence example folder
	 * 
	 * This is a important function and each time new query execute or new text inserted into 
	 * index using this analyzer this reset function will execute [tested]
	 * 
	 * constructor of this class will be called per shard size means if there are 2 shard 
	 * then for each shard new object of this class will be created as the constructor will
	 *  be called as per object creation.
	 *  
	 *  Another important thing is :
	 *  
	 *  the input  used in this function is of Tokenizer class not of this class and will hold the text query 
	 *  or index value for each search or indexed data . So this must be in reset not in constructor.
	 *  
	 *  What is the text for index or for query is hold by this input Reader
	 * 
	 * 
	 */
	public void reset() throws IOException {

		super.reset();
		int numChars;
		char[] buffer = new char[1024];
		StringBuilder stringBuilder = new StringBuilder();
		try {
			while ((numChars = input.read(buffer, 0, buffer.length)) != -1) {
				stringBuilder.append(buffer, 0, numChars);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		this.stringToTokenize = stringBuilder.toString();
		normalizedPhrase();

	}

	private void normalizedPhrase() {

		StringTokenizer tokenizer = new StringTokenizer(stringToTokenize);

		StringBuilder sb = new StringBuilder();

		while (tokenizer.hasMoreElements()) {

			if (tokenizedWords.size() <= 3) {
				String token = (String) tokenizer.nextElement();
				if (true) {
					char[] c=token.toLowerCase().toCharArray();
					StringBuilder sbb=new StringBuilder();
					for(int i=0;i<c.length;i++){
						sbb.append(c[0]);	
					}
					sb.append(sbb.toString());
				 
				}

				tokenizedWords.add(sb.toString().toLowerCase());

			} else {
				tokenizedWords.add(stringToTokenize);
				break;
			}

		}

	}

	

}