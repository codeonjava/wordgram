package com.elasticsearch;

import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.plugins.AbstractPlugin;

import com.util.Parameters;

public class WordGramPlugin extends AbstractPlugin {

	@Override
	public String name() {
		return Parameters.NAME;
	}

	@Override
	public String description() {
		return Parameters.NAME;
	}

	public void onModule(AnalysisModule analysisModule) {
		analysisModule.addProcessor(new WordGramBinderProcessor());
	}
}