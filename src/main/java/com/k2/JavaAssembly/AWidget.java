package com.k2.JavaAssembly;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public abstract class AWidget<T> {
	
	public AWidget(Class<T> widgetInterface) {
		this.widgetInterface = widgetInterface;
	}

	public abstract Writer output(WidgetAssembly<?> widgetAssembly, T obj, Writer out) throws IOException;
	
	private Map<String, List<ContainedWidget>> containers;
	
	private Class<T> widgetInterface;
	public Class<T> getWidgetInterface() {
		return widgetInterface;
	}

	public AWidget<T> bind(String containerAlias, AWidget<?> widget, String fieldAlias) {
		if (containers == null) containers = new HashMap<String, List<ContainedWidget>>();
		List<ContainedWidget> containedWidgets = containers.get(containerAlias);
		if (containedWidgets == null) {
			containedWidgets = new ArrayList<ContainedWidget>();
			containers.put(containerAlias, containedWidgets);
		}
		containedWidgets.add(ContainedWidget.create(widgetInterface, containerAlias, widget, fieldAlias));
		return this;
	}
	
	public List<ContainedWidget> getContainedWidgets(String containerAlias) {
		if (containers == null) containers = new HashMap<String, List<ContainedWidget>>();
		List<ContainedWidget> containedWidgets = containers.get(containerAlias);
		return (containedWidgets == null)? new ArrayList<ContainedWidget>(0): containedWidgets;
	}
	
	@SuppressWarnings("unchecked")
	protected void writeContainer(String containerAlias, WidgetAssembly<?> wa, T data, Writer out) throws IOException {
		for (ContainedWidget cw : getContainedWidgets(containerAlias)) {
			if (cw.bindsCollection()) {
				for (Object obj : cw.getBoundCollection(data))
					cw.getWidget().output(wa, obj, out);
			} else {
				cw.getWidget().output(wa, cw.getBoundData(data), out);
			}
		}
		
	}

	protected void indentContainer(String containerAlias, WidgetAssembly<?> wa, T data, Writer out) throws IOException {
		wa.indent();
		try  {
			writeContainer(containerAlias, wa, data, out);
		} finally {
			wa.outdent();
		}
		
	}

}
