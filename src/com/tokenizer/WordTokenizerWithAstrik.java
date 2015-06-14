package com.tokenizer;

import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class WordTokenizerWithAstrik extends Tokenizer {

	protected CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);

	@Override
	public boolean incrementToken() throws IOException {

		this.charTermAttribute.setEmpty();

		int nextIndex = this.stringToTokenize.indexOf('*', this.position);

		if (nextIndex != -1) {
			String nextToken = this.stringToTokenize.substring(this.position,
					nextIndex);
			this.charTermAttribute.append(nextToken);
			System.out.println(this.charTermAttribute);
			this.position = nextIndex + 1;
			return true;
		}

		else if (this.position < this.stringToTokenize.length()) {
			String nextToken = this.stringToTokenize.substring(this.position);
			this.charTermAttribute.append(nextToken);
			System.out.println(this.charTermAttribute);
			this.position = this.stringToTokenize.length();
			return true;
		}

		else {
			return false;
		}
	}
	
	
	

	public WordTokenizerWithAstrik(Reader reader) {
		super(reader);
		int numChars;
		char[] buffer = new char[1024];
		StringBuilder stringBuilder = new StringBuilder();
		try {
			while ((numChars = reader.read(buffer, 0, buffer.length)) != -1) {
				stringBuilder.append(buffer, 0, numChars);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		this.stringToTokenize = stringBuilder.toString();
		System.out.println("================================================");
		System.out.println(this.stringToTokenize);
		System.out.println("================================================");
	}

	public String stringToTokenize;

	protected int position = 0;
}