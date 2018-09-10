package com.k2.WidgetFactory;

import java.util.HashMap;
import java.util.Map;

import com.k2.Util.classes.ClassUtil;
import com.k2.WidgetAssembly.AWidget;
import com.k2.WidgetFactory.annotation.Widget;

public class WidgetFactory {
	
	public static WidgetFactory create() {
		return new WidgetFactory();
	}
	
	private WidgetFactory() {}
	@SuppressWarnings("unchecked")
	private WidgetFactory(String ... packageNames) {
		for (String packageName : packageNames) {
			register((Class<? extends AWidget<?>>[]) ClassUtil.getClasses(packageName, Widget.class));
		}
	}

	private Map<Class<?>,Map<String, AWidget<?>>> widgets = new HashMap<Class<?>,Map<String, AWidget<?>>>();
	
	public Map<String, AWidget<?>> getInterfaceWidgets(Class<?> widgetInterface) {
		Map<String, AWidget<?>> interfaceWidgets = widgets.get(widgetInterface);
		if (interfaceWidgets == null) {
			interfaceWidgets = new HashMap<String, AWidget<?>>();
			widgets.put(widgetInterface, interfaceWidgets);
		}
		return interfaceWidgets;
	}
	public void register(Class<? extends AWidget<?>> widgetClass, String wigetAlias) {
		
		try {
			AWidget<?> widget = widgetClass.newInstance();
			getInterfaceWidgets(widget.getWidgetInterface()).put(wigetAlias, widget);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new WidgetFactoryError("Unable to create a widget instance from the class {}. No zero arg consgtructor?", e, widgetClass.getName());
		}
		
	}

	@SuppressWarnings("unchecked")
	public <T> AWidget<T> get(Class<T> widgetInterface, String widgetAlias) {
		AWidget<T> widget = (AWidget<T>) getInterfaceWidgets(widgetInterface).get(widgetAlias);
		if (widget == null)
			throw new WidgetFactoryError("No widgets registered with alias {} for interface {}.", widgetAlias, widgetInterface.getName());
		return widget;
	}

	@SuppressWarnings("unchecked")
	public void register(Class<? extends AWidget<?>> ... widgetClasses) {
		for (Class<? extends AWidget<?>> widgetClass : widgetClasses) 
			if (widgetClass.isAnnotationPresent(Widget.class)) {
				Widget ann = widgetClass.getAnnotation(Widget.class);
				register(widgetClass, ann.value());
			} else 
				throw new WidgetFactoryError("The widget {} is not annotated with '@Widget(\"alias\")'", widgetClass.getName());
	}

	public static WidgetFactory create(String ... packageNames) {
		return new WidgetFactory(packageNames);
	}

}
