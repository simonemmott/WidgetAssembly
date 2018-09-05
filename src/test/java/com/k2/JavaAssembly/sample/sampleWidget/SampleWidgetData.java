package com.k2.JavaAssembly.sample.sampleWidget;

import java.util.ArrayList;
import java.util.List;

import com.k2.JavaAssembly.sample.widgetA.WidgetAInterface;
import com.k2.JavaAssembly.sample.widgetB.WidgetBInterface;

public class SampleWidgetData implements SampleWidgetInterface {

	public SampleWidgetData(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	private String name;
	public String getName() {
		return name;
	}

	private String description;
	public String getDescription() {
		return description;
	}
	
	private List<WidgetAInterface> widgetAs;
	public List<WidgetAInterface> getWidgetAs() {
		return (widgetAs == null)?new ArrayList<WidgetAInterface>(0):widgetAs;
	}
	public void setWidgetAs(List<WidgetAInterface> widgetAs) {
		this.widgetAs = widgetAs;
	}

	private List<WidgetBInterface> widgetBs;
	public List<WidgetBInterface> getWidgetBs() {
		return (widgetBs == null)?new ArrayList<WidgetBInterface>(0):widgetBs;
	}
	public void setWidgetBs(List<WidgetBInterface> widgetBs) {
		this.widgetBs = widgetBs;
	}
	
	private WidgetAInterface widgetA;
	public WidgetAInterface getWidgetA() {
		return widgetA;
	}
	public void setWidgetA(WidgetAInterface widgetA) {
		this.widgetA = widgetA;
	}
	
	public WidgetBInterface widgetB;
	public WidgetBInterface getWidgetB() {
		return widgetB;
	}
	public void setWidgetB(WidgetBInterface widgetB) {
		this.widgetB = widgetB;
	}

}
