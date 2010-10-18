package org.joinvisualizer;

import java.io.Writer;

/**
 * @author Hannes John
 * @since 25.05.2010 17:11:51
 */
public interface DotFile {
	void table(final String name, final String alias);
	void constraint(final String expression, final String table1Alias, final String field1Name, final String operator, final String table2Alias, final String field2Name);
	void constraint(final String expression);
	void paint(final Writer writer);
}
