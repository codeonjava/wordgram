package com.elasticsearch;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.elasticsearch.index.analysis.AnalysisModule;

import com.util.Parameters;

public class WordGramBinderProcessor extends
		AnalysisModule.AnalysisBinderProcessor {

	@Override
	public void processAnalyzers(AnalyzersBindings analyzersBindings) {
		analyzersBindings.processAnalyzer(Parameters.NAME,
				WordGramAnalyzerProvider.class);
		
		
	}
}


