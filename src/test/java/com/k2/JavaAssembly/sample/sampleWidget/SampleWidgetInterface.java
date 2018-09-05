package com.k2.JavaAssembly.sample.sampleWidget;

import java.util.List;

import com.k2.JavaAssembly.sample.widgetA.WidgetAInterface;
import com.k2.JavaAssembly.sample.widgetB.WidgetBInterface;

public interface SampleWidgetInterface {

	public String getName();
	public String getDescription();
	public List<WidgetAInterface> getWidgetAs();
	public void setWidgetAs(List<WidgetAInterface> widgetAs);
	public List<WidgetBInterface> getWidgetBs();
	public void setWidgetBs(List<WidgetBInterface> widgetBs);
	public WidgetAInterface getWidgetA();
	public void setWidgetA(WidgetAInterface widgetA);
	public WidgetBInterface getWidgetB();
	public void setWidgetB(WidgetBInterface widgetB);

}
