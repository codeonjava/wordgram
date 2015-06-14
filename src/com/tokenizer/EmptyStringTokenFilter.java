package com.tokenizer;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

public class EmptyStringTokenFilter extends TokenFilter {

	/*
	 * The constructor for our custom token filter just calls the TokenFilter
	 * constructor; that constructor saves the token stream in a variable named
	 * this.input.
	 */
	public EmptyStringTokenFilter(TokenStream tokenStream) {
		super(tokenStream);
	}

	protected CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);
	protected PositionIncrementAttribute positionIncrementAttribute = addAttribute(PositionIncrementAttribute.class);

	@Override
	public boolean incrementToken() throws IOException {

		System.out.println("inserted into TokenFilter => incrementToken");

		String nextToken = null;
		while (nextToken == null) {

			if (!this.input.incrementToken()) {
				return false;
			}

			String currentTokenInStream = this.input
					.getAttribute(CharTermAttribute.class).toString().trim();

			if (currentTokenInStream.length() > 0) {
				nextToken = currentTokenInStream;
			}

		}

		// Save the current token
		this.charTermAttribute.setEmpty().append(nextToken);
		this.positionIncrementAttribute.setPositionIncrement(1);
		return true;
	}
}