package com.k2.JavaAssembly.sample.widgetB;

public class WidgetBData implements WidgetBInterface {

	public WidgetBData(String alias, Long id) {
		this.alias = alias;
		this.id = id;
	}
	
	private String alias;
	public String getAlias() {
		return alias;
	}

	private Long id;
	public Long getId() {
		return id;
	}

}
