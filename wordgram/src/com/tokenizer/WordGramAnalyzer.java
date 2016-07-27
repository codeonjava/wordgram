package com.tokenizer;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;

public class WordGramAnalyzer extends Analyzer {

	/*
	 * This is the only function that we need to override for our analyzer. It
	 * takes in a java.io.Reader object and saves the tokenizer and list of
	 * token filters that operate on it.
	 */
	@Override
	protected TokenStreamComponents createComponents(String field, Reader reader) {

		Tokenizer tokenizer = new WordTokenizer(reader);
		TokenStream filter = new EmptyStringTokenFilter(tokenizer);
		filter = new LowerCaseFilter(filter);
		return new TokenStreamComponents(tokenizer, filter);
	}
}