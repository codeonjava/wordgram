package com.elasticsearch;

import java.io.IOException;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;
import org.elasticsearch.index.settings.IndexSettings;

import com.tokenizer.WordGramAnalyzer;

public class WordGramAnalyzerProvider extends AbstractIndexAnalyzerProvider {

	private final WordGramAnalyzer phraseAnalyzer;

	@Inject
	public WordGramAnalyzerProvider(Index index,
			@IndexSettings Settings indexSettings, Environment env,
			@Assisted String name, @Assisted Settings settings)
			throws IOException {
		super(index, indexSettings, name, settings); 
		
		phraseAnalyzer = new WordGramAnalyzer();
	}

	@Override
	public WordGramAnalyzer get() {
		return this.phraseAnalyzer;
	}

}
