package org.joinvisualizer;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hannes John
 * @since 25.05.2010 17:13:55
 */
public class DotFileImpl implements DotFile {
	private final List<Table> tables = new ArrayList<Table>();
	private final List<Field> fields = new ArrayList<Field>();
	private final List<Constraint> constraints = new ArrayList<Constraint>();
	@Override
	public void table(String name, String alias) {
		tables.add(new Table(name, alias));
	}
	@Override
	public void constraint(String expression, String table1Alias, String field1Name, String operator, String table2Alias, String field2Name) {
		final Field field1 = field(table(table1Alias), field1Name);
		final Field field2 = field(table(table2Alias), field2Name);
		constraints.add(new Constraint(expression, field1, field2, operator));
	}
	@Override
	public void constraint(final String expression) {
		constraints.add(new Constraint(expression));
	}
	@Override
	public void paint(final Writer writer) {
		try {
			writer.write("graph select {\n" +
					"\trankdir=LR;\n" +
					"\tnode [shape=record, fontname=Arial, fontsize=10];\n" +
					"\tedge [fontname=Arial, fontsize=10];\n");
			for(final Table table : tables) {
				table.paintHeader(writer);
				for(final Field field : fields) {
					if(field.isPartOf(table)) {
						field.paintDeclaration(writer);
					}
				}
				table.paintFooter(writer);
			}
			for(final Constraint constraint : constraints) {
				constraint.paint(writer);
			}
			writer.write("}");
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
	private Table table(final String alias) {
		for (final Table table : tables) {
			if (table.is(alias)) {
				return table;
			}
		}
		final Table table = new Table(alias);
		tables.add(table);
		return table;
	}
	private Field field(final Table table, final String name) {
		for (final Field field : fields) {
			if (field.is(table, name)) {
				return field;
			}
		}
		final Field field = new Field(table, name);
		fields.add(field);
		return field;
	}
}
