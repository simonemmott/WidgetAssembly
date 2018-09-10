package com.k2.WidgetAssembly;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AWidget<T> {
	
	public AWidget(Class<T> widgetInterface) {
		this.widgetInterface = widgetInterface;
	}

	public abstract Writer output(WidgetAssemblyNode<?,T> node, T obj, Writer out) throws IOException;
	
	private Class<T> widgetInterface;
	public Class<T> getWidgetInterface() {
		return widgetInterface;
	}

}
