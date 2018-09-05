package com.k2.JavaAssembly;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	ContainedWidgetTests.class,
	WidgetAssemblyTests.class,
	WidgetTests.class
})
public class WidgetAssemblyTestSuite {

}
