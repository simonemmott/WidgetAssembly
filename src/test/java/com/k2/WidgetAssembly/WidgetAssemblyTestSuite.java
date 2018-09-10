package com.k2.WidgetAssembly;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	ContainedWidgetTests.class,
	WidgetAssemblyTests.class,
	WidgetFactoryTests.class,
	WidgetTests.class
})
public class WidgetAssemblyTestSuite {

}
